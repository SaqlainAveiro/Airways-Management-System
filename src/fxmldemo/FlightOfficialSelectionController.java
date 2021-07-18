package fxmldemo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static fxmldemo.ConnectMS_SQL_Controller.validateid;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saqlain
 */
public class FlightOfficialSelectionController implements Initializable {
    
    @FXML
    private JFXButton SeePilotsButton,SeeCabinCrewsButton,ConfirmFlightOfficialsButton1,ConfirmFlightOfficialsButton,
            FirstCCButton, SecondCCButton, ThirdCCButton , firstpilotsetbutton, secondpilotbutton;
    
    @FXML
    private AnchorPane CabinCrewAnchorPane,PilotAnchorPane , empviewanchorpane;
    
    @FXML
    private ScrollPane PilotListViewScrollPane,CabinCrewListViewScrollPane;

    @FXML
    private VBox PilotListView,CabinCrewListview;

    @FXML
    private JFXTextField firstpilottext,secondpilottext,FirstCCText,SecondCCText,ThirdCCText;
    
    @FXML
    private MyListener myListener;
    
    
        ////////////////////////////////////////////////////////////////////////////////////
    
    
    @FXML
    void FirstCCButtonAction(ActionEvent event) {
        
    }

    @FXML
    void SecondCCButtonAction(ActionEvent event) {

    }

    @FXML
    void ThirdCCButtonAction(ActionEvent event) {

    }

    @FXML
    void firstpilotsetbuttonaction(ActionEvent event) {
      
    }

    @FXML
    void secondpilotbuttonaction(ActionEvent event) {
        
    }
    
    
    private void setChoosenPilotID(Employees emp){

    }
    
    @FXML
    void Clickedvbox(MouseEvent event) {
        
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    
    public void showError(String s1,String s2){
        errorAlert.setHeaderText(s1);
        errorAlert.setContentText(s2);
        errorAlert.showAndWait();
    }
    
    
    @FXML
    void FlightOfficialsButtonAction(ActionEvent event) {
        
        if(event.getSource()==SeePilotsButton){
            PilotAnchorPane.toFront();
        }
        else if(event.getSource()==SeeCabinCrewsButton){
            CabinCrewAnchorPane.toFront();   
        } 
    }
    
    
    String pilot1="",pilot2="",cc1="",cc2="",cc3="";

    @FXML
    void ConfirmFlightOfficialsButtonAction(ActionEvent event) {
       
        int flag=0;
        labelofenteringempid:{
            
            if(!firstpilottext.getText().equals(""))
            {     
                if(validateid(firstpilottext.getText(),1)){
                    pilot1 = firstpilottext.getText();
                }
                else{
                    showError("Invalid 1st Pilot Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofenteringempid;
                }
            }else{
                showError("1st Pilot's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofenteringempid;
            }
            
            if(!secondpilottext.getText().equals(""))
            {     
                if(validateid(secondpilottext.getText(),1)){
                    pilot2 = secondpilottext.getText();
                }
                else{
                    showError("Invalid 2nd Pilot Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofenteringempid;
                }
            }else{
                showError("2nd Pilot's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofenteringempid;
            }
            
            if(!pilot1.equals("") && pilot1==pilot2){
                showError("1st Pilot and 2nd Pilot cannot be same!","");
                flag=1;
                break labelofenteringempid;
            }
    
            
            if(FirstCCText.getText().equals("") || SecondCCText.getText().equals("") || ThirdCCText.getText().equals("")){
                showError("Cabin Crew Employee ID field(s) is empty!", "");
                flag=1;
                break labelofenteringempid;
            }
      
            
            if(!FirstCCText.getText().equals(""))
            {     
                if(validateid(FirstCCText.getText(),1)){
                    cc1 = FirstCCText.getText();
                }
                else{
                    showError("Invalid 1st Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofenteringempid;
                }
            }else{
                showError("1st Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofenteringempid;
            }
            
            if(!SecondCCText.getText().equals(""))
            {     
                if(validateid(SecondCCText.getText(),1)){
                    cc2 = SecondCCText.getText();
                }
                else{
                    showError("Invalid 2nd Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofenteringempid;
                }
            }else{
                showError("2nd Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofenteringempid;
            }
            
            
            if(!ThirdCCText.getText().equals(""))
            {     
                if(validateid(ThirdCCText.getText(),1)){
                    cc3 = ThirdCCText.getText();
                }
                else{
                    showError("Invalid 3rd Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofenteringempid;
                }
            }else{
                showError("3rd Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofenteringempid;
            }
            
            if(!cc1.equals("") && cc1==cc2 || !cc1.equals("") && cc1==cc3 || !cc2.equals("") && cc2==cc3){
                showError("Cannot choose same Cabin Crews!","");
                flag=1;
                break labelofenteringempid;
            }   
        }
        
        if(flag==0){
            Stage window =(Stage) ConfirmFlightOfficialsButton.getScene().getWindow();

            window.close();
        }
    }
    
    
    
    ObservableList<Employees> ListofPilots,ListofCabinCrews;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CabinCrewAnchorPane.   setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));                 
        PilotAnchorPane.       setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));
        
        
        ListofPilots = DataClass.getDataOfPilots();
   
        if (ListofPilots.size() > 0) {
            setChoosenPilotID(ListofPilots.get(0));
            
            myListener = new MyListener() {
                @Override
                public void onClickListener(Employees emp) {
                    System.out.println("Clicked!");
                    setChoosenPilotID(emp);
                }
            };
        }
        
        
        try {
        
            for(int i=0 ; i<ListofPilots.size() ; i++){
                                        
            FXMLLoader loader = new FXMLLoader();
                
            loader.setLocation(getClass().getResource("FlightOfficialsItems.fxml"));
            
            
            empviewanchorpane = loader.load();

            FlightOfficialsItemsController empitems = loader.getController();

            empitems.setItems(ListofPilots.get(i),myListener);

            PilotListView.getChildren().add(empviewanchorpane);
            
            VBox.setMargin(empviewanchorpane, new Insets(ListofPilots.size()));
                
            }
        } catch (IOException ex) {
            Logger.getLogger(FlightOfficialSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        ListofCabinCrews = DataClass.getDataOfCabinCrews();
        
        if (ListofCabinCrews.size() > 0) {
            setChoosenPilotID(ListofCabinCrews.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Employees emp) {
                    setChoosenPilotID(emp);
                }
            };
        }
        
        for(int i=0 ; i<ListofCabinCrews.size() ; i++){
                        
            FXMLLoader loader = new FXMLLoader();
                
            loader.setLocation(getClass().getResource("FlightOfficialsItems.fxml"));
            
            try {
                //HBox hbox = loader.load();
                
                empviewanchorpane = loader.load();
                
                FlightOfficialsItemsController empitems = loader.getController();
                
                empitems.setItems(ListofCabinCrews.get(i),myListener);
                
                CabinCrewListview.getChildren().add(empviewanchorpane);
                
                VBox.setMargin(empviewanchorpane, new Insets(ListofCabinCrews.size()));
                
            } catch (IOException ex) {
                Logger.getLogger(FlightOfficialSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    
    
    public String getPilot1() {
        return pilot1;
    }

    public String getPilot2() {
        return pilot2;
    }

    public String getCc1() {
        return cc1;
    }

    public String getCc2() {
        return cc2;
    }

    public String getCc3() {
        return cc3;
    }

}
