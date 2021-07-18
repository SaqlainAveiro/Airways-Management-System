package fxmldemo;

import static fxmldemo.ConnectMS_SQL_Controller.checkValidateName;
import static fxmldemo.ConnectMS_SQL_Controller.checkValidatePhone;
import static fxmldemo.ConnectMS_SQL_Controller.validateMail;
import static fxmldemo.ConnectMS_SQL_Controller.validateid;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saqlain
 */
public class ModifyEmployeeDetailsController implements Initializable {
    
    
    @FXML
    private Label showempid;
    
    @FXML
    private TextField enterEmpID;

    @FXML
    private Button searchEmpButton;

    @FXML
    private TextField upEmpName,upEmpAge,upEmpDesignation,upEmpMail,upEmpContact;

    @FXML
    private Button updateEmpdetailsButton,deleteEmpdetailsButton;
    
    String EmployeeID="", EmployeeMail="", EmployeeName="", EmployeeContact="", EmployeeDesignation="";
    
    int EmployeeAge;
    
    
    
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    
    public void showError(String s1,String s2){
        errorAlert.setHeaderText(s1);
        errorAlert.setContentText(s2);
        errorAlert.showAndWait();
    }
    
    
    public void setTextField(String EmpID, String EmpMail, String EmpName, String EmpContact, String EmpDesignation, int EmpAge){
        
        EmployeeID=EmpID;
        
        showempid.setText(EmployeeID);
        upEmpMail.setText(EmpMail);
        upEmpName.setText(EmpName);
        upEmpContact.setText(EmpContact);
        upEmpDesignation.setText(EmpDesignation);
        upEmpAge.setText(Integer.toString(EmpAge));
    }
    
    
    
    @FXML
    void updatetEmpDetailsButtonAction(ActionEvent event) {

        int flag=0;
               
          
        labelofupdatingempid:{

            EmployeeMail=upEmpMail.getText();
            if(EmployeeMail.equals("")){
                showError("Mail field is empty!","The size of Mail must be between 1 and 12 characters.");
                flag=1;
                break labelofupdatingempid;
            }
            else{
                if(!validateMail(EmployeeMail)){
                    showError("Invalid Email!", "Email should be: 'example@example.example'.");
                    flag=1;
                    break labelofupdatingempid;
                }
            }
            
            
            

            EmployeeName=upEmpName.getText();
            if(EmployeeName.equals("")){
                showError("Name field is empty!","The size of Name must be between 1 and 12 characters.");
                flag=1;
                break labelofupdatingempid;
            }else{ 
                if(checkValidateName(EmployeeName)){
                    showError("Invalid Name!", "Name contains only characters.");
                    flag=1;
                    break labelofupdatingempid;
                }
            }

            
            
            EmployeeContact=upEmpContact.getText();
            if(EmployeeContact.length()==11){
                if(checkValidatePhone(EmployeeContact)){
                    showError("Invalid Contact No.!","Contact No. must contain only numbers.");
                    flag=1;
                    break labelofupdatingempid;
                }
            }else{
                showError("Invalid Contact No.!","Contact No. must be 11 characters long.");
                flag=1;
                break labelofupdatingempid;
            }
            
            
            

            EmployeeDesignation=upEmpDesignation.getText();
            if(EmployeeDesignation.equals("")){
                showError("Designation field is empty!","Designation must be of 5 to 15 characters");
                flag=1;
                break labelofupdatingempid;
            }
            else{
                if(!(EmployeeDesignation=="Pilot" || EmployeeDesignation=="Cabin Crew")){
                    showError("Invalid Designation","Designation must be either 'Pilot' or 'Cabin Crew'");
                    flag=1;
                    break labelofupdatingempid;
                }
            }
            
            
            
            if(!upEmpAge.getText().equals(""))
            {
                EmployeeAge = Integer.parseInt(upEmpAge.getText());
                
                if(EmployeeAge<25 || EmployeeAge>50){
                    showError("Invalid Age inserted!","Age must be between 25 to 50");
                    flag=1;
                    break labelofupdatingempid;
                }
            }
        }
        
            
        try {
                if(flag==0){
                    
                    FxmlDemo fd = new FxmlDemo();
                    fd.connectDB();

                
                    fd.statement.executeUpdate("UPDATE Employee Set Email = '"+EmployeeMail+"' , Name = '"+EmployeeName+"' , " + "Age = "+EmployeeAge+","
                    + " Contact = '"+EmployeeContact+"' , Designation = '"+EmployeeDesignation+"' WHERE EmployeeID = '"+EmployeeID+"'");


 
                    Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                    successMsg.setHeaderText("Updated Employee Details Successfully!");
                    successMsg.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));

                Stage window =(Stage) updateEmpdetailsButton.getScene().getWindow();

                window.close();
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }
    }
    
    @FXML
    void deleteEmpdetailsButtonAction(ActionEvent event){
        
        int flag=0;
       
        labelofdeletingflightid:{
            
            if(!enterEmpID.getText().equals(""))
            {     
                if(validateid(enterEmpID.getText(),1)){
                 EmployeeID = enterEmpID.getText();
                }
                else{
                    showError("Invalid Employee ID!", "Employee ID must be like E-'Number'.");
                    flag=1;
                    break labelofdeletingflightid;
                }
            }else{
                showError("Employee ID field is empty!", "Employee ID must be like E-'Number'.");
                flag=1;
                break labelofdeletingflightid;
            }
        }
        
        try{
            if(flag==0)
            {     
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                fd.statement.executeUpdate("DELETE FROM Employee WHERE EmployeeID='"+EmployeeID+"'");
                
                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Deleted Employee Details Successfully!");
                successMsg.showAndWait();


                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            
                Stage window =(Stage) deleteEmpdetailsButton.getScene().getWindow();
       
                window.close();       
            }
            
        }catch (Exception e) {
             System.out.println(e);
        }
    }

    
    
    
    
    
    
    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getEmployeeMail() {
        return EmployeeMail;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getEmployeeContact() {
        return EmployeeContact;
    }

    public String getEmployeeDesignation() {
        return EmployeeDesignation;
    }

    public int getEmployeeAge() {
        return EmployeeAge;
    }
    

    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
