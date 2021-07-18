package fxmldemo;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Saqlain
 */
public class FlightOfficialsItemsController{
    
    @FXML
    private AnchorPane empviewanchorpane;
    
    @FXML
    private HBox FlightOfficialEmpItem;
    
    @FXML
    private ImageView flightempImg;

    @FXML
    private Label flightempID,flightempName,flightempAge;

    @FXML
    private JFXButton SelectEmpButton;
    
    private MyListener myListener;

    String FlightOfficialsEmpID="";
    
      
    
    @FXML
    void SelectEmpButtonAction(ActionEvent event) {
        
        System.out.println("ID = "+flightempID.getText());
        
        //SelectedFlightOfficials.pilot1  = flightempID.getText();
    }
    
    private Employees emp;  
    
    @FXML
    void Onclickemp(MouseEvent event) {
        myListener.onClickListener(emp);
    }
    
    @FXML
    public void setItems(Employees emps, MyListener myListener){   
        
        this.emp = emps;
        this.myListener = myListener;
        
        //System.out.println("ID : "+emps.getEmployeeID());
        
        Image imgProfile = new Image(getClass().getResourceAsStream("pilot.png"));
        flightempImg.setImage(imgProfile);
        flightempID.setText(emps.getEmployeeID());
        flightempName.setText(emps.getName());
        flightempAge.setText(Integer.toString(emps.getAge()));
    }
    
}
