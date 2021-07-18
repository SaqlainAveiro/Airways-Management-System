package fxmldemo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Saqlain
 */
public class CheckFlightPassengersController implements Initializable {
    
    @FXML
    private AnchorPane passengerviewAnchorPane;
    
    @FXML
    private ScrollPane passengersScrollPane;

    @FXML
    private VBox passengersVBox;

    @FXML
    private JFXComboBox<String> checkflightpassengersComboBox;

    @FXML
    private JFXButton checkflightpassengersButton;
    
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    
    public void showError(String s1,String s2){
        errorAlert.setHeaderText(s1);
        errorAlert.setContentText(s2);
        errorAlert.showAndWait();
    }
    
        
    String FID="";
    
    ObservableList <Users> ListofPassengers;


    @FXML
    void checkflightpassengersButtonAction(ActionEvent event) {
        
        int flag=0;
 
        labelofpassengersinflightid:{
            
            FID = checkflightpassengersComboBox.getValue();            
            
            if(FID == null){
                
                showError("Select a Flight ID!","");  
                
                flag=-1;
                
                break labelofpassengersinflightid;
            }
        }
        
        if(flag!=-1){
            
            System.out.println("Now : "+FID);
        
            ListofPassengers = DataClass.getDataofFlightPassengers(FID);

            try {
                
                passengersVBox.getChildren().clear();
                
                for(int i=0 ; i<ListofPassengers.size() ; i++){

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(getClass().getResource("CheckFlightPassengersItem.fxml"));

                passengerviewAnchorPane = loader.load();

                CheckFlightPassengersItemController passengeritems = loader.getController();

                passengeritems.setItems(ListofPassengers.get(i));

                passengersVBox.getChildren().add(passengerviewAnchorPane);

                VBox.setMargin(passengerviewAnchorPane, new Insets(ListofPassengers.size()));
                                
                }
                                
                ListofPassengers.clear();
                
            } catch (IOException ex) {
                Logger.getLogger(FlightOfficialSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> FlightIDS = DataClass.getDataofFlightIDs();
        
        checkflightpassengersComboBox.setItems(FlightIDS);
        
    }   
 
}
