package fxmldemo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import static fxmldemo.ConnectMS_SQL_Controller.validatedate;
import static fxmldemo.ConnectMS_SQL_Controller.validateid;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author Saqlain
 */
public class ModifyFlightDetailsController implements Initializable{


    @FXML
    private TextField upfare;
    
    @FXML
    private JFXDatePicker updepdate,uparrivaldate;
    
    @FXML
    private JFXTimePicker updeptime,uparrivaltime;  
    
    @FXML
    private DateTimeFormatter dateTimeFormatter;
    
    @FXML
    private LocalDate depdate,ardate;
    
    @FXML
    private LocalTime deptime,artime;
    
    @FXML
    private JFXComboBox<String> upfrom, upto, upclasstype;
    
    @FXML
    private Label upacid, upaccap, upacname, showFID;
    
    @FXML
    private AnchorPane Update_Flight_Details_Anchor;
    
    @FXML
    private JFXButton deleteflightdetailsButton, updateflightdetailsButton;
    
    private boolean update;
    
    String FlightID="",departuredate="",arrivaldate="",departuretime="",arrivaltime="",totaldeparturedate="",totalarrivaldate="",classtype="",from="",to="",
            
           pilot1="",pilot2="",cc1="",cc2="",cc3="";

    int fare=0;
    
    @FXML
    private JFXButton ChooseFlightOfficialsButton,checkflightpassengersButton;

    @FXML
    private JFXTextField firstpilot1,secondpilot1,firstcc1,secondcc1,thirdcc1;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        String[] coutries = new String[]{
         "Abu Dhabi","Atlanta","Bangkok","Boston","Chattogram","Chennai","Chicago","Cox's Bazar",
         "Denver","Delhi","Dhaka","Dubai","Goa","Honolulu","Jashore","Kolkata","Kerala","Las Vegas",
         "London","Mumbai","New York","Punjab","Philadelphia","Portland",
         "Rajshahi","Sylhet","Tokyo","Washington, D.C."
        };
        
        String[] airplanes = new String[]{"Boeing 737-Max","Boeing 747-8","Airbus A318","Dash 8-400"};
        
        
        ObservableList<String> ClassType1 = FXCollections.observableArrayList("Economic","Business");
        upclasstype.setItems(ClassType1);
        
        ObservableList<String> Coutries = FXCollections.observableArrayList(coutries);
        upfrom.setItems(Coutries);
        upto.setItems(Coutries);
    }
    
    
    public void setTextField(String flightid, String from, String to, String departure, String arrival, String classtype, 
            String pilot1, String pilot2, String cc1, String cc2, String cc3, int fare, String acid, String acname, int accap){
        
        FlightID = flightid;
        
        showFID.setText("Flight ID : "+FlightID);
        
        int change=0;
        
        for(int i=0 ; i<departure.length() ; i++){
            if(departure.charAt(i)==' '){
                change=1;
            }else{
                if(change==0){
                    departuredate+=departure.charAt(i);
                }else{
                    if(departure.charAt(i)==':'){
                        change++;
                    }
                    if(change==3)   break;
                    
                    departuretime+=departure.charAt(i);
                    
                } 
            }
        }
        
        change=0;
        
        for(int i=0 ; i<arrival.length() ; i++){
            if(arrival.charAt(i)==' '){
                change=1;
            }else{
                if(change==0){
                    arrivaldate+=arrival.charAt(i);
                }else{
                    if(arrival.charAt(i)==':'){
                        change++;
                    }
                    if(change==3)   break;
                    
                    arrivaltime+=arrival.charAt(i);
                } 
            }
        }
        
        totaldeparturedate = departuredate + " " + departuretime;
            
        totalarrivaldate = arrivaldate + " " + arrivaltime;
        
        
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        depdate = LocalDate.parse(departuredate, dateTimeFormatter);
        ardate = LocalDate.parse(arrivaldate, dateTimeFormatter);
        
        
        dateTimeFormatter = DateTimeFormatter.ofPattern("H:mm");
        deptime = LocalTime.parse(departuretime,dateTimeFormatter);
        artime = LocalTime.parse(arrivaltime,dateTimeFormatter);
        
        
        upfrom.         setValue(from);
        upto.           setValue(to);
        updepdate.      setValue(depdate);
        uparrivaldate.  setValue(ardate);
        updeptime.      setValue(deptime);
        uparrivaltime.  setValue(artime);
        upclasstype.    setValue(classtype);
        upacname.       setText(acname);
        upacid.         setText(acid);
        upfare.         setText(Integer.toString(fare));
        upaccap.        setText(Integer.toString(accap));
        firstpilot1.     setText(pilot1);
        secondpilot1.    setText(pilot2);
        firstcc1.        setText(cc1);
        secondcc1.       setText(cc2);
        thirdcc1.        setText(cc3);
   
    }
    

    
    void setUpdate(boolean b){
        this.update = b;
    }
    
    
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      
    public void showError(String s1,String s2){
        errorAlert.setHeaderText(s1);
        errorAlert.setContentText(s2);
        errorAlert.showAndWait();
    }
    
    
    
    @FXML
    void ChooseFlightOfficialsButtonAction(ActionEvent event){
                        
        FXMLLoader loader = new FXMLLoader();
                
        loader.setLocation(getClass().getResource("FlightOfficialSelection.fxml"));

        try{
            loader.load();
        }catch(IOException ex){
            Logger.getLogger(ConnectMS_SQL_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        FlightOfficialSelectionController setflightofficials = loader.getController();

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        loader.getResources();
        stage.showAndWait(); 
        
        pilot1=setflightofficials.getPilot1();
        pilot2=setflightofficials.getPilot2();
        cc1=setflightofficials.getCc1();
        cc2=setflightofficials.getCc2();
        cc3=setflightofficials.getCc3();
        
        firstpilot1.setText(pilot1);
        secondpilot1.setText(pilot2);
        firstcc1.setText(cc1);
        secondcc1.setText(cc2);
        thirdcc1.setText(cc3);
    }
     
    @FXML
    void updateflightdetailsButtonAction(ActionEvent event) {
                
        int flag=0;
        
        labelofupdatingflightid:{
            
            System.out.println("Found");
            
            
            if(firstpilot1.getText()!=null){     
                if(validateid(firstpilot1.getText(),1)){
                    pilot1 = firstpilot1.getText();
                }
                else{
                    System.out.println("Not found");
                    showError("Invalid 1st Pilot Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofupdatingflightid;
                }
            }else{
                showError("1st Pilot's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofupdatingflightid;
            }
            
            if(secondpilot1.getText()!=null)
            {     
                if(validateid(secondpilot1.getText(),1)){
                    pilot2 = secondpilot1.getText();
                }
                else{
                    showError("Invalid 2nd Pilot Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofupdatingflightid;
                }
            }else{
                showError("2nd Pilot's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofupdatingflightid;
            }
            
            if(!pilot1.equals("") && pilot1==pilot2){
                showError("1st Pilot and 2nd Pilot cannot be same!","");
                flag=1;
                break labelofupdatingflightid;
            }
    
            
            if(firstcc1.getText()==null || secondcc1.getText()==null || thirdcc1.getText()==null){
                showError("Cabin Crew Employee ID field(s) is empty!", "");
                flag=1;
                break labelofupdatingflightid;
            }
      
            
            if(firstcc1.getText()!=null)
            {     
                if(validateid(firstcc1.getText(),1)){
                    cc1 = firstcc1.getText();
                }
                else{
                    showError("Invalid 1st Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofupdatingflightid;
                }
            }else{
                showError("1st Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofupdatingflightid;
            }
            
            if(secondcc1.getText()!=null)
            {     
                if(validateid(secondcc1.getText(),1)){
                    cc2 = secondcc1.getText();
                }
                else{
                    showError("Invalid 2nd Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofupdatingflightid;
                }
            }else{
                showError("2nd Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofupdatingflightid;
            }
            
            
            if(thirdcc1.getText()!=null)
            {     
                if(validateid(thirdcc1.getText(),1)){
                    cc3 = thirdcc1.getText();
                }
                else{
                    showError("Invalid 3rd Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofupdatingflightid;
                }
            }else{
                showError("3rd Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofupdatingflightid;
            }
            
            if(!cc1.equals("") && cc1==cc2 || !cc1.equals("") && cc1==cc3 || !cc2.equals("") && cc2==cc3){
                showError("Cannot choose same Cabin Crews!","");
                flag=1;
                break labelofupdatingflightid;
            }
            

             ///////////////////////////////////////////////////////////////
            
            from = upfrom.getValue();
            if(from == null){
                flag=1;
                showError("Select a Source","");
                break labelofupdatingflightid;
            }
            
            ///////////////////////////////////////////////////////////////

            to = upto.getValue();
            if(to == null){
                flag=1;
                showError("Select a Destination","");
                break labelofupdatingflightid;
            }
            
            ///////////////////////////////////////////////////////////////
            
            if(!from.equals("") && from==to){
                showError("Source location and Destination location cannot be same!","");
                flag=1;
                break labelofupdatingflightid;
            }

            ///////////////////////////////////////////////////////////////
            
            if(!String.valueOf(updepdate.getValue()).equals("")){     
                departuredate = String.valueOf(updepdate.getValue());

                if(!String.valueOf(updeptime.getValue()).equals("")){
                    departuretime = String.valueOf(updeptime.getValue());
                }else{
                    showError("Departure Time is empty!","");
                    flag=1;
                    break labelofupdatingflightid;
                }
                
            }else{
                showError("Departure Date field is empty!", "Date should be: YYYY-MM-DD");
                flag=1;
                break labelofupdatingflightid;
            }

            ///////////////////////////////////////////////////////////////

            if(!String.valueOf(uparrivaldate.getValue()).equals("")){     
                arrivaldate = String.valueOf(uparrivaldate.getValue());
                
                if(!String.valueOf(uparrivaltime.getValue()).equals("")){
                    arrivaltime=String.valueOf(uparrivaltime.getValue());
                }else{
                    showError("Arrival Time is empty!","");
                    flag=1;
                    break labelofupdatingflightid;
                }
                
            }else{
                showError("Arrival Date field is empty!", "Date should be: YYYY-MM-DD");
                flag=1;
                break labelofupdatingflightid;
            }  
            
            ///////////////////////////////////////////////////////////////
            
            
            totaldeparturedate = departuredate + " " + departuretime + ":00";
            
            totalarrivaldate = arrivaldate + " " + arrivaltime + ":00";
            
            //System.out.println("Total Departure Date : "+totaldeparturedate);
            //System.out.println("Total Arrival Date : "+totalarrivaldate);
            
            if(!validatedate(totaldeparturedate)){
                showError("Invalid Departure Date!", "Date should be: YYYY-MM-DD HH:MM:SS");
                flag=1;
                break labelofupdatingflightid;
            }
            
            if(!validatedate(totalarrivaldate)){
                showError("Invalid Arrival Date!", "Date should be: YYYY-MM-DD HH:MM:SS");
                flag=1;
                break labelofupdatingflightid;
            }
            

            
            ///////////////////////////////////////////////////////////////    
            

            classtype = upclasstype.getValue();
            if(classtype == null){
                showError("Class Type field is empty!","");
                flag=1;
                break labelofupdatingflightid;
            }   
            
            
            ///////////////////////////////////////////////////////////////  
            
            if(!upfare.getText().equals(""))
            {
                fare = Integer.parseInt(upfare.getText());
                
                if(fare<1000 || fare>1000000){
                    showError("Invalid Fare!", "Fare price must be between 1000 to 1000000");
                    flag=1;
                    break labelofupdatingflightid;
                }
            }            
        }
        //System.out.println("Updated : "+flid+" "+from+" "+to+" "+departuredate+" "+" "+returndate+" "+classtype+" "+flighttype+" "+fare);
            
        try {
            if(flag==0){
               
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                           
                fd.statement.executeUpdate("UPDATE FlightInfo Set From1 = '"+from+"' , To1 = '"+to+"' , "
                                + "DepartureDate = '"+totaldeparturedate+"' , ArrivalDate = '"+totalarrivaldate+"' ,"
                                + "ClassType = '"+classtype+"' , Pilot1 = '"+pilot1+"' , Pilot2 = '"+pilot2+"' , CabinCrew1 = '"+cc1+"' , CabinCrew2 = '"+cc2+"' , CabinCrew3 = '"+cc3+"'"
                                + ", Fare = "+fare+" WHERE FlightID = '"+FlightID+"'");


                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Updated Flight Details Successfully!");
                successMsg.showAndWait();

                
                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                
                Stage window =(Stage) updateflightdetailsButton.getScene().getWindow();
  
                window.close();     
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }

    }

    @FXML
    void deleteflightdetailsButtonAction(ActionEvent event) {
        
        String enterflightidstr="";
        int flag=0;
       
        labelofdeletingflightid:{
            
            if(!FlightID.equals(""))
            {     
                if(validateid(FlightID,0)){
                 enterflightidstr = FlightID;
                }
                else{
                    showError("Invalid Flight ID!", "Flight ID must be like F-'Number'");
                    flag=1;
                    break labelofdeletingflightid;
                }
            }else{
                showError("Flight ID field is empty!", "Flight ID must be like F-'Number'");
                flag=1;
                break labelofdeletingflightid;
            }
        }
        
        try{
            if(flag==0){  
                
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                fd.statement.executeUpdate("DELETE FROM FlightInfo WHERE FlightID='"+FlightID+"'");
                fd.statement.executeUpdate("DELETE FROM AircraftInfo WHERE FlightID='"+FlightID+"'");
                

                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Deleted Flight Details Successfully!");
                successMsg.showAndWait();

               
                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                
                Stage window =(Stage) deleteflightdetailsButton.getScene().getWindow();
                
                window.close(); 
            }
            
        }catch (Exception e) {
             System.out.println(e);
        }
    } 
    
    @FXML
    void checkflightpassengersButtonAction(ActionEvent event){

    }
    
    
    
    
    public String getFlightID() {
        return FlightID;
    }

    public String getTotaldeparturedate() {
        return totaldeparturedate;
    }

    public String getTotalarrivaldate() {
        return totalarrivaldate;
    }

    public String getClasstype() {
        return classtype;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getFare() {
        return fare;
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
