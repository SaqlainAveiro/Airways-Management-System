package fxmldemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Saqlain
 */
public class CheckFlightPassengersItemController{
    
    
    @FXML
    private HBox FlightOfficialEmpItem;

    @FXML
    private ImageView passengerimg;

    @FXML
    private Label userid,username,usergender,usercontact;
    
    @FXML
    private AnchorPane passengerviewAnchorPane;
    
    private Users passengers;
            
    @FXML
    public void setItems(Users flightpassengers){   
        
        this.passengers = flightpassengers;
        
        //System.out.println("ID : "+flightpassengers.getFirstName());
        
        Image imgProfile = new Image(getClass().getResourceAsStream("4.png"));
        passengerimg.setImage(imgProfile);
        userid.setText(flightpassengers.getUserID());
        username.setText(flightpassengers.getFirstName() + " " + flightpassengers.getLastName());
        usergender.setText(flightpassengers.getGender());
        usercontact.setText(flightpassengers.getContactNo());
    }
 
}
