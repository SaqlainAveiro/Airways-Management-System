package fxmldemo;


import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aristo_PC
 */
public class LoginPageController implements Initializable {
    
    public String userName,passWord;
    
    @FXML
    private Hyperlink createAccountBtn;
    
    @FXML
    private Button loginbutton;
    
    @FXML
    public TextField userid;
    
    @FXML
    public PasswordField password;
   
     
    public void showError(String s1){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(s1);
        errorAlert.showAndWait();
    }
    
    @FXML
    void loginbuttonAction(ActionEvent event) throws Exception{
        
        block :{
            
            String ui= userid.getText().trim();
            String pass = password.getText().trim();
            int flag=0;
            
            if(ui.equals("admin") && pass.equals("admin")){
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setHeaderText("Login Successful!");
                al.showAndWait();
                flag=2;
            }
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            
            ResultSet rs = fd.statement.executeQuery("select * from UserLog inner join Membership on Userlog.UserID = Membership.UserID ;");
            
            while(rs.next() && flag!=2){
                                                
                if(rs.getString("UserID").equals(ui) && rs.getString("Passkey").equals(pass)){
                    Data.userID=rs.getString("UserID");
                    Data.pass=pass;
                    Data.name=rs.getString("FirstName")+" "+rs.getString("LastName");
                    Data.membership=rs.getString("MemberStatus");
                    Data.points = rs.getInt("Point");
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setHeaderText("Login Successful!");
                    al.showAndWait();
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                
                Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));

                Stage window =(Stage) loginbutton.getScene().getWindow();

                
                window.setScene(new Scene(root,1200,700));  
            }else if(flag==2){
                
                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));

                Stage window =(Stage) loginbutton.getScene().getWindow();

                window.setScene(new Scene(root,1200,700));  
                
            }else{
                showError("Invalid UserID or Password!!");
                break block;
            }  
        } 
    }
    
    @FXML
    void exitbuttonaction(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    void createAccountAction() throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       
       Stage window =(Stage) createAccountBtn.getScene().getWindow();
       
       window.setScene(new Scene(root,1050,600));
 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
