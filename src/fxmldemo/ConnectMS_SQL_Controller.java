package fxmldemo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Saqlain
 */
public class ConnectMS_SQL_Controller implements Initializable {

    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private JFXDatePicker DepartureDate,ArrivalDate;
    
    @FXML
    private JFXTimePicker DepartureTime,ArrivalTime;
    
    @FXML
    private JFXComboBox<String> From, To, ClassType, ACNAME, checkflightpassengersComboBox;
        
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private JFXTextField firstpilot,secondpilot,firstcc,secondcc,thirdcc;
    
    @FXML
    private TextField FlightID, Fare, EmpID, EmpName, EmpAge, EmpDesignation, EmpMail, EmpContact, enterEmpID,
            
    upEmpName, upEmpAge, upEmpDesignation, upEmpMail, upEmpContact, UserID, adminReply, FlightTableSearchboxField, ACID, ACCAP,
            
    EmployeeTableSearchboxField;
    
    @FXML
    private JFXTextArea UserAdminConvo;

    @FXML
    private JFXListView<String> UserIDList;

    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    public  TableView<Flights> Current_Flight_List_Table;
    
    @FXML
    private TableColumn<Flights, String> col_FlightID, col_From, col_To, col_Departure, col_Arrival, col_ClassType, col_ACNAME, col_ACID;
    
    @FXML
    private TableColumn<Flights, Integer> col_Fare, col_ACCAP;
    
    
    @FXML
    private TableView<Employees> Current_Employee_List_Table;
    
    @FXML
    private TableColumn<Employees, String> col_EmpID, col_EmpName, col_EmpMail, col_EmpContact, col_EmpDesignation;
    
    @FXML
    private TableColumn<Employees, Integer> col_EmpAge;
    
    
    @FXML
    private TableView<Passengers> Current_Passenger_List_Table;
    
    @FXML
    private TableColumn<Passengers, String> col_PassFlightID, col_PassUserID,col_PassSeatTaken;
    
    @FXML
    private TableColumn<Passengers, Integer> col_PassSeatNum;

    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private TabPane managePassenger, setFlight, CustomerSupport, employeemanage;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private AnchorPane Current_Flight_List_Anchor, Add_Flight_Details_Anchor, Update_Flight_Details_Anchor,
            
                    Current_Employee_List_Anchor, Add_New_Employee_Details_Anchor, Update_Current_Employee_Details_Anchor,    

                    Current_Passenger_List_Anchor, Update_Passenger_List_Anchor, Customer_Support_Forum_Anchor, User_Feedback_List_Anchor, Analysis_User_Feedback_List_Anchor;    

    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private Button ConfirmSetFlightButton, searchFlightButton, deleteflightdetailsButton, updateflightdetailsButton, ConfirmsetEmpButton,
                   
                   searchEmpButton, updateEmpdetailsButton , deleteEmpdetailsButton , selectUserIDButton, adminReplyButton;
    
    @FXML
    private JFXButton checkflightpassengersButton,AdminLogOutButton,setFlightbutton,employeebutton,customerbutton, passengerbutton;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    @FXML
    private Label loggedinlabel;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    
    public void showError(String s1,String s2){
        errorAlert.setHeaderText(s1);
        errorAlert.setContentText(s2);
        errorAlert.showAndWait();
    }
 
    public static final Pattern VALIDEMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean validateMail(String emailStr) {
        Matcher matcher = VALIDEMAIL.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatedate(String str) {
        
        int year=0,month=0,day=0,hour=0,minute=0,second=0,flag=0;
        
        //System.out.println("String : "+str);
        
        char[] ch = str.toCharArray();
        
        for(char c: ch){
            if(c>='0' && c<='9' || c=='-' || c==':' || c==' '){
                flag=0;
            }else{
                flag=1; //System.out.println("Wrong character maybe");
                break;
            }
        }
        
        if(ch[4]=='-' && ch[7]=='-' && ch[10]==' ' && ch[13]==':' && ch[16]==':' && flag==0){
            year    = (ch[0]-48)*1000   + ( ch[1]-48)*100 + (ch[2]-48)*10 + (ch[3]-48);
            month   = (ch[5]-48)*10     +  (ch[6]-48);
            day     = (ch[8]-48)*10     +  (ch[9]-48);
            hour    = (ch[11]-48)*10    +  (ch[12]-48);
            minute  = (ch[14]-48)*10    +  (ch[15]-48);
            second  = (ch[17]-48)*10    +  (ch[18]-48);
            
            //System.out.println("Year : "+year+", Month : "+month+", Day : "+day+", Hour : "+hour+", Min : "+minute+", Sec : "+second);
            
            if(year>=2021 && month>=1 && month<=12 && day>=1 && day<=31 && hour>=0 && hour<=23 && minute>=0 && minute<=59 && second>=0 && second<=59){
                return true;
            }
        }
        return false;
    }
    
    public static boolean validateid(String str, int x) {
        
        char[] ch = str.toCharArray();
        
        int flag=-1;
        
        if(ch[0]=='F'  && x==0 || ch[0]=='E' && x==1){
            flag=1;     System.out.println("E/F First valid\n");
        }else if(ch.length>=3 && ch[0]=='A' && ch[1]=='C' && x==2){
            flag=2;     System.out.println("AC First valid\n");
        }
        
        if(flag!=-1){
                        
            for(int i=flag ; i<ch.length ; i++){
                if(ch[i]>='0' && ch[i]<='9'){
                    flag=0;
                }else{
                    flag=-1;  System.out.println("Second Invalid\n");  break;
                }
            }
            if(flag!=-1){
                return true;
            }         
        }
        return false;
    }
    
    public static boolean checkValidatePhone(String text) {
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)<48 || text.charAt(i)>57)
                return true;
        }
        return false;
    }
    
    public static boolean checkValidateName(String fName) {
        for(int i=0;i<fName.length();i++){
            if(fName.charAt(i)<65 || fName.charAt(i)>122){
                return true;
            }
        }
        return false;
    }
    
    
    
    
    @FXML
    public void adminactionbutton(ActionEvent event){
                
        if(event.getSource()==setFlightbutton){
            setFlight.toFront();
        }
        else if(event.getSource()==employeebutton){
            employeemanage.toFront();   
        } 
        else if(event.getSource()==passengerbutton){
            managePassenger.toFront();  
        }
        else if(event.getSource()==customerbutton){
            CustomerSupport.toFront();  
        }else if(event.getSource()==AdminLogOutButton){
            
            try {
                Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                Stage window =(Stage) AdminLogOutButton.getScene().getWindow();
                window.setScene(new Scene(root));
            } catch (IOException ex) {
                Logger.getLogger(ConnectMS_SQL_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
    String flightid="",from="",to="",departuredate="",arrivaldate="",classtype="",departuretime="",arrivaltime="",
            totaldeparturedate="",totalarrivaldate="",acid="",acname="",pilot1="",pilot2="",cc1="",cc2="",cc3="";
    int fare;
    
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
        
        firstpilot.setText(pilot1);
        secondpilot.setText(pilot2);
        firstcc.setText(cc1);
        secondcc.setText(cc2);
        thirdcc.setText(cc3);
    }
    
    @FXML
    void ConfirmSetFlightButtonAction(ActionEvent event) {
        
        int fare=0,capacity=0,flag=0;
 
        labelofsettingflightid:{
            
            if(!firstpilot.getText().equals(""))
            {     
                if(validateid(firstpilot.getText(),1)){
                    pilot1 = firstpilot.getText();
                }
                else{
                    showError("Invalid 1st Pilot Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("1st Pilot's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofsettingflightid;
            }
            
            if(!secondpilot.getText().equals(""))
            {     
                if(validateid(secondpilot.getText(),1)){
                    pilot2 = secondpilot.getText();
                }
                else{
                    showError("Invalid 2nd Pilot Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("2nd Pilot's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofsettingflightid;
            }
            
            if(!pilot1.equals("") && pilot1==pilot2){
                showError("1st Pilot and 2nd Pilot cannot be same!","");
                flag=1;
                break labelofsettingflightid;
            }
    
            
            if(firstcc.getText().equals("") || secondcc.getText().equals("") || thirdcc.getText().equals("")){
                showError("Cabin Crew Employee ID field(s) is empty!", "");
                flag=1;
                break labelofsettingflightid;
            }
      
            
            if(!firstcc.getText().equals(""))
            {     
                if(validateid(firstcc.getText(),1)){
                    cc1 = firstcc.getText();
                }
                else{
                    showError("Invalid 1st Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("1st Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofsettingflightid;
            }
            
            if(!secondcc.getText().equals(""))
            {     
                if(validateid(secondcc.getText(),1)){
                    cc2 = secondcc.getText();
                }
                else{
                    showError("Invalid 2nd Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("2nd Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofsettingflightid;
            }
            
            
            if(!thirdcc.getText().equals(""))
            {     
                if(validateid(thirdcc.getText(),1)){
                    cc3 = thirdcc.getText();
                }
                else{
                    showError("Invalid 3rd Cabin Crew Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("3rd Cabin Crew's Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofsettingflightid;
            }
            
            if(!cc1.equals("") && cc1==cc2 || !cc1.equals("") && cc1==cc3 || !cc2.equals("") && cc2==cc3){
                showError("Cannot choose same Cabin Crews!","");
                flag=1;
                break labelofsettingflightid;
            } 
            
            ///////////////////////////////////////////////////////////////
            
            if(FlightID.getText().equals("")){
                showError("Flight ID field is empty!","Flight ID must be like F-'Number'");
                flag=1;
                break labelofsettingflightid;
            }else{
                if(validateid(FlightID.getText(),0)){
                    flightid = FlightID.getText();
                }
                else{
                    showError("Invalid Flight ID!", "Flight ID must be like F-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }
            
            ///////////////////////////////////////////////////////////////
            
            if(!Fare.getText().equals("")){
                fare = Integer.parseInt(Fare.getText());
                
                if(fare<1000 || fare>1000000){
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("Fare field is empty!","");
                flag=1;
                break labelofsettingflightid;
            }
            
            ///////////////////////////////////////////////////////////////
            
            from = From.getValue();
            if(from == null){
                flag=1;
                showError("Select a Source","");
                break labelofsettingflightid;
            }
            
            ///////////////////////////////////////////////////////////////

            to = To.getValue();
            if(to == null){
                flag=1;
                showError("Select a Destination","");
                break labelofsettingflightid;
            }
            
            ///////////////////////////////////////////////////////////////
            
            if(!from.equals("") && from==to){
                showError("Source location and Destination location cannot be same!","");
                flag=1;
                break labelofsettingflightid;
            }
            
            ///////////////////////////////////////////////////////////////
            
            if(!String.valueOf(DepartureDate.getValue()).equals("")){     
                departuredate = String.valueOf(DepartureDate.getValue());

                if(!String.valueOf(DepartureTime.getValue()).equals("")){
                    departuretime = String.valueOf(DepartureTime.getValue());
                }else{
                    showError("Departure Time is empty!","");
                    flag=1;
                    break labelofsettingflightid;
                }
                
            }else{
                showError("Departure Date field is empty!", "Date should be: YYYY-MM-DD");
                flag=1;
                break labelofsettingflightid;
            }

            ///////////////////////////////////////////////////////////////

            if(!String.valueOf(ArrivalDate.getValue()).equals("")){     
                arrivaldate = String.valueOf(ArrivalDate.getValue());
                
                if(!String.valueOf(ArrivalTime.getValue()).equals("")){
                    arrivaltime=String.valueOf(ArrivalTime.getValue());
                }else{
                    showError("Arrival Time is empty!","");
                    flag=1;
                    break labelofsettingflightid;
                }
                
            }else{
                showError("Arrival Date field is empty!", "Date should be: YYYY-MM-DD");
                flag=1;
                break labelofsettingflightid;
            }  
            

            
            ///////////////////////////////////////////////////////////////    
            

            classtype = ClassType.getValue();
            if(classtype == null){
                showError("Class Type field is empty!","");
                flag=1;
                break labelofsettingflightid;
            }   
            
            
            ///////////////////////////////////////////////////////////////
            
            if(ACID.getText().equals("")){
                showError("Aircraft ID field is empty!","Aircraft ID must be like AC-'Number'");
                flag=1;
                break labelofsettingflightid;
            }else{
                if(validateid(ACID.getText(),2)){
                 acid = ACID.getText();
                }
                else{
                    showError("Invalid Aircraft ID!", "Aircraft ID must be like AC-'Number'");
                    flag=1;
                    break labelofsettingflightid;
                }
            }
            
            ///////////////////////////////////////////////////////////////
            
            acname = ACNAME.getValue();
            if(acname == null){
                showError("Select an Aircraft Model!","");
                flag=1;
                break labelofsettingflightid;
            } 
           
            
            ///////////////////////////////////////////////////////////////
            
            if(!ACCAP.getText().equals("")){
                
                capacity = Integer.parseInt(ACCAP.getText());
                
                if(capacity<10 || capacity>40){
                    flag=1;
                    break labelofsettingflightid;
                }
            }else{
                showError("Capcity field is empty!","");
                flag=1;
                break labelofsettingflightid;
            }
            
            
            
            ///////////////////////////////////////////////////////////////
            
            totaldeparturedate = departuredate + " " + departuretime + ":00";
            
            totalarrivaldate = arrivaldate + " " + arrivaltime + ":00";
            
            //System.out.println("Total Departure Date : "+totaldeparturedate);
            //System.out.println("Total Arrival Date : "+totalarrivaldate);
            
            if(!validatedate(totaldeparturedate)){
                showError("Invalid Departure Date!", "Date should be: YYYY-MM-DD HH:MM:SS");
                flag=1;
                break labelofsettingflightid;
            }
            
            if(!validatedate(totalarrivaldate)){
                showError("Invalid Arrival Date!", "Date should be: YYYY-MM-DD HH:MM:SS");
                flag=1;
                break labelofsettingflightid;
            }
        }
        
        //System.out.println("Flag : "+flag);
        
        try {
            if(flag==0){
                
                //System.out.println("Done!");
                
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                fd.statement.executeUpdate("INSERT INTO FlightInfo "
                        
                        + "Values ( '"+flightid+"' , '"+from+"' , '"+to+"' , '"+totaldeparturedate+"' , "
                        + "'"+totalarrivaldate+"' , '"+classtype+"' , '"+pilot1+"' , '"+pilot2+"' , '"+cc1+"' , '"+cc2+"' , "
                        + "'"+cc3+"' , "+fare+" , 40 , '')");

                
                fd.statement.executeUpdate("INSERT INTO AircraftInfo Values( '"+acid+"' , '"+acname+"' , '"+flightid+"' , "+capacity+" )");
                

                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Inserted Flight Details Successfully!");
                successMsg.showAndWait();
 

                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));

                Stage window =(Stage) ConfirmSetFlightButton.getScene().getWindow();

                window.setScene(new Scene(root,1200,700));
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }
     
    }
    
    @FXML
    void checkflightpassengersButtonAction(ActionEvent event) {
                    
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("CheckFlightPassengers.fxml"));

        try{
            loader.load();
        }catch(IOException ex){
            Logger.getLogger(ConnectMS_SQL_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        loader.getResources();
        stage.showAndWait();
    }
    
    
    
    
    String enterempidstr="",searchempid="",searchmail="",searchname="",searchcontact="",searchdesignation="",searchage="";
    String empid="",empname="",empmail="",empcontact="",empdesignation="";
    int empage;
    
    @FXML
    void searchEmpButtonAction(ActionEvent event) {
        
        int flag=0;
                
        labelofsearchingempid:{
            
            if(!enterEmpID.getText().equals(""))
            {     
                if(validateid(enterEmpID.getText(),1)){
                    enterempidstr = enterEmpID.getText();
                }
                else{
                    showError("Invalid Employee ID!", "Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsearchingempid;
                }
            }else{
                showError("Employee ID field is empty!", "Employee ID must be like E-'Number'");
                flag=1;
                break labelofsearchingempid;
            }
        }    
        
        try{
            if(flag==0)
            {      
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                ResultSet SearchEmployeeResult = fd.statement.executeQuery("SELECT * FROM Employee WHERE EmployeeID='"+enterempidstr+"'");              
                
                while(SearchEmployeeResult.next()){
                    
                    searchmail          = SearchEmployeeResult.getString("Email");
                    searchname          = SearchEmployeeResult.getString("Name");    
                    searchcontact       = SearchEmployeeResult.getString("Contact");
                    searchdesignation   = SearchEmployeeResult.getString("Designation");
                    searchage           = SearchEmployeeResult.getString("Age");
                }
                
                upEmpMail.setText(searchmail);
                upEmpName.setText(searchname);
                upEmpContact.setText(searchcontact);
                upEmpDesignation.setText(searchdesignation);
                upEmpAge.setText(searchage);
                
            }
            
        }catch (Exception e) {
             System.out.println(e);
        }
    }
    
    @FXML
    void updatetEmpDetailsButtonAction(ActionEvent event) {

        int flag=0,age=0;
        
        String name="",mail="",contactno="",designation="",emid=enterempidstr;
        
          
        labelofupdatingempid:{

            mail=upEmpMail.getText();
            if(mail.equals("")){
                showError("Mail field is empty!","The size of Mail must be between 1 and 12 characters.");
                flag=1;
                break labelofupdatingempid;
            }
            else{
                if(!validateMail(mail)){
                    showError("Invalid Email!", "Email should be: 'example@example.example'.");
                    flag=1;
                    break labelofupdatingempid;
                }
            }
            
            
            

            name=upEmpName.getText();
            if(name.equals("")){
                showError("Name field is empty!","The size of Name must be between 1 and 12 characters.");
                flag=1;
                break labelofupdatingempid;
            }else{ 
                if(checkValidateName(name)){
                    showError("Invalid Name!", "Name contains only characters.");
                    flag=1;
                    break labelofupdatingempid;
                }
            }

            
            
            contactno=upEmpContact.getText();
            if(contactno.length()==11){
                if(!checkValidatePhone(contactno)){
                    showError("Invalid Contact No.!","Contact No. must contain only numbers.");
                    flag=1;
                    break labelofupdatingempid;
                }
            }else{
                showError("Invalid Contact No.!","Contact No. must be 11 characters long.");
                flag=1;
                break labelofupdatingempid;
            }
            
            
            

            designation=upEmpDesignation.getText();
            if(designation.equals("")){
                showError("Designation field is empty!","Designation must be of 5 to 15 characters");
                flag=1;
                break labelofupdatingempid;
            }
            else{
                if(!(designation=="Pilot" || designation=="Cabin Crew" || designation=="Manager" || designation=="Security")){
                    showError("Invalid Designation","Designation must be either 'Pilot' or 'Cabin Crew' or 'Manager' or 'Security'");
                    flag=1;
                    break labelofupdatingempid;
                }
            }
            
            
            
            if(!upEmpAge.getText().equals(""))
            {
                age = Integer.parseInt(upEmpAge.getText());
                
                if(age<25 || age>50){
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
                
                fd.statement.executeUpdate("UPDATE Employee Set Email = '"+mail+"' , Name = '"+name+"' , " + "Age = "+age+","
                + " Contact = '"+contactno+"' , Designation = '"+designation+"' WHERE EmployeeID = '"+emid+"'");


                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Updated Employee Details Successfully!");
                successMsg.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));

                Stage window =(Stage) ConfirmsetEmpButton.getScene().getWindow();

                window.setScene(new Scene(root,1200,700));

                ListofEmployees = DataClass.getDataofEmployees();

                Current_Employee_List_Table.setItems(ListofEmployees);
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }
    }
    
    @FXML
    void deleteEmpdetailsButtonAction(ActionEvent event){
        
        String enterempidstr="";
        int flag=0;
       
        labelofdeletingflightid:{
            
            if(!enterEmpID.getText().equals(""))
            {     
                if(validateid(enterEmpID.getText(),1)){
                 enterempidstr = enterEmpID.getText();
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
                
                fd.statement.executeUpdate("DELETE FROM Employee WHERE EmployeeID='"+enterempidstr+"'");
                

                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Deleted Employee Details Successfully!");
                successMsg.showAndWait();


                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            
                Stage window =(Stage) ConfirmsetEmpButton.getScene().getWindow();
       
                window.setScene(new Scene(root,950,500));
            
                ListofEmployees = DataClass.getDataofEmployees();
        
                Current_Employee_List_Table.setItems(ListofEmployees);         
            }
            
        }catch (Exception e) {
             System.out.println(e);
        }
    }
    
    @FXML
    void ConfirmsetEmpButtonAction(ActionEvent event) {

        int flag=0,age=0;
        
        String enterempidstr="",empid="",mail="",name="",contactno="",designation="";
    
        
        labelofsettingempid:{
        
            
            empid=EmpID.getText();
            if(empid.equals("")){
                showError("Employee ID field is empty!","Employee ID must be like E-'Number'");
                flag=1;
                break labelofsettingempid;
            }else{
                if(!validateid(empid,1)){
                    showError("Invalid Employee ID!","Employee ID must be like E-'Number'");
                    flag=1;
                    break labelofsettingempid;
                }
            }
            

            mail=EmpMail.getText();
            if(mail.equals("")){
                showError("Mail field is empty!","The size of Mail must be between 1 and 12 characters.");
                flag=1;
                break labelofsettingempid;
            }
            else{
                if(!validateMail(mail)){
                    showError("Invalid Email!", "Email should be: 'example@example.example'.");
                    flag=1;
                    break labelofsettingempid;
                }
            }
            
            

            name=EmpName.getText();
            if(name.equals("")){
                showError("Name field is empty!","The size of Name must be between 1 and 12 characters.");
                flag=1;
                break labelofsettingempid;
            }else{ 
                if(checkValidateName(name)){
                    showError("Invalid Name!", "Name contains only characters.");
                    flag=1;
                    break labelofsettingempid;
                }
            }

            
            
            contactno=EmpContact.getText();
            if(contactno.length()==11){
                if(!checkValidatePhone(contactno)){
                    showError("Invalid Contact No.!","Contact No. must contain only numbers.");
                    flag=1;
                    break labelofsettingempid;
                }
            }else{
                showError("Invalid Contact No.!","Contact No. must be 11 characters long.");
                flag=1;
                break labelofsettingempid;
            }  
            
            

            designation=EmpDesignation.getText();
            if(designation.equals("")){
                showError("Designation field is empty!","Designation must be of 5 to 15 characters");
                flag=1;
                break labelofsettingempid;
            }
            else{
                if(!(designation=="Pilot" || designation=="Cabin Crew")){
                    showError("Invalid Designation","Designation must be either 'Pilot' or 'Cabin Crew'");
                    flag=1;
                    break labelofsettingempid;
                }
            }
            
            
      
            if(!EmpAge.getText().equals(""))
            {
                age = Integer.parseInt(EmpAge.getText());
                
                if(age<25 || age>50){
                    showError("Invalid Age inserted!","Age must be between 25 to 50");
                    flag=1;
                    break labelofsettingempid;
                }
            }
        }
        
        
        
        try {
            if(flag==0){
                
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                            
                fd.statement.executeQuery("INSERT INTO Employee Values('"+empid+"' , '"+mail+"' , '"+name+"' , '"+contactno+"' , '"+designation+"', "+age+")");


                Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
                successMsg.setHeaderText("Inserted Employee Details Successfully!");
                successMsg.showAndWait();
            

                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            
                Stage window =(Stage) ConfirmsetEmpButton.getScene().getWindow();
       
                window.setScene(new Scene(root,1200,640));
            
                ListofEmployees = DataClass.getDataofEmployees();
        
                Current_Employee_List_Table.setItems(ListofEmployees);
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }
    }
    
    
    
    
    String allconvo="",SelectUserID="",UserText="", AdminText="",getallconvo="";
    
    @FXML
    void selectUserIDButtonAction (){
        
        SelectUserID = UserIDList.getSelectionModel().getSelectedItem();
                        
        UserID.setText(SelectUserID); 
        
        try{
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            
            ResultSet CustomerMessegeRS = fd.statement.executeQuery("SELECT * FROM CustomerSupport Where UserID = '"+SelectUserID+"'");
            
            while(CustomerMessegeRS.next()){
                getallconvo = CustomerMessegeRS.getString("UserAdminConvo");     
            }
                        

            for(int i=0; i<getallconvo.length() ; i++){
                
                if(getallconvo.charAt(i)=='~'){
                    
                    allconvo+="\nUser : ";
                    
                }else if(getallconvo.charAt(i)=='<'){
                    
                    allconvo+="\nAdmin : ";
                }
                else{
                    
                    if(getallconvo.charAt(i)=='"'){                  
                        char ch=39;   
                        allconvo+=ch;      
                    }else{
                        allconvo+=getallconvo.charAt(i);
                    }
                }
            }

            UserAdminConvo.setText(allconvo); 
            
            allconvo="";
            
        }catch(Exception e){
            
        } 
    }
    
    @FXML
    void adminReplyButtonAction (ActionEvent event){

        String AdminReply = adminReply.getText(),AdminPrevReply="";
        
        try{
            
            int status=0;
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            
            ResultSet AdminPrevMessegeRS = fd.statement.executeQuery("Select * From CustomerSupport Where UserID = '"+SelectUserID+"'");       
             
            while(AdminPrevMessegeRS.next()){
                AdminPrevReply = AdminPrevMessegeRS.getString("UserAdminConvo");
            }
            
            char ch=39;
            
            AdminReply = AdminReply.replace(ch,'"');
       
            AdminPrevReply += '<' + AdminReply;
            
            
            fd.statement.executeUpdate("Update CustomerSupport Set UserAdminConvo = '"+AdminPrevReply+"' Where UserID = '"+SelectUserID+"'");       
              
             
            allconvo= UserAdminConvo.getText();
            
            AdminReply = AdminReply.replace('"',ch);
            
            allconvo+= "\nAdmin : " + AdminReply;
            
            UserAdminConvo.setText(allconvo); 
            
            allconvo=""; 
            
            adminReply.clear();
            
            System.out.println("Reply : "+AdminReply);
            
        }catch(Exception e){
            
        } 
    }
    
    
    @FXML
    void showFlightTable(){
        /////////////////////////////////////////////////Flight Info Table/////////////////////////////////////////////
        
        col_FlightID.   setCellValueFactory(new PropertyValueFactory<Flights,String>("FlightID"));      
        col_From.       setCellValueFactory(new PropertyValueFactory<Flights,String>("From1"));      
        col_To.         setCellValueFactory(new PropertyValueFactory<Flights,String>("To1"));    
        col_Departure.  setCellValueFactory(new PropertyValueFactory<Flights,String>("DepartureDate"));  
        col_Arrival.    setCellValueFactory(new PropertyValueFactory<Flights,String>("ArrivalDate"));  
        col_ClassType.  setCellValueFactory(new PropertyValueFactory<Flights,String>("ClassType"));
        col_Fare.       setCellValueFactory(new PropertyValueFactory<Flights,Integer>("Fare")); 
        col_ACID.       setCellValueFactory(new PropertyValueFactory<Flights,String>("AC_ID"));
        col_ACNAME.     setCellValueFactory(new PropertyValueFactory<Flights,String>("AC_Name"));
        col_ACCAP.      setCellValueFactory(new PropertyValueFactory<Flights,Integer>("Capacity"));

       
   
        Current_Flight_List_Table.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                Flights flights = Current_Flight_List_Table.getSelectionModel().getSelectedItem();
                
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ModifyFlightDetails.fxml"));
                
                try{
                    loader.load();
                }catch(IOException ex){
                    Logger.getLogger(ConnectMS_SQL_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                ModifyFlightDetailsController modifyflightdetails = loader.getController();
                
                modifyflightdetails.setUpdate(true);
                
                modifyflightdetails.setTextField(
                
                    flights.getFlightID(),      flights.getFrom1(),         flights.getTo1(), 
                    flights.getDepartureDate(), flights.getArrivalDate(),   flights.getClassType(),     
                    flights.getPilot1(),        flights.getPilot2(),        flights.getCabinCrew1(),
                    flights.getCabinCrew2(),    flights.getCabinCrew3(),    flights.getFare(), 
                    flights.getAC_ID(),         flights.getAC_Name(),       flights.getCapacity()
                
                );
                
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                loader.getResources();
                stage.showAndWait(); 
                
                try {
                    if(!modifyflightdetails.getClasstype().equals("") &&
                       !modifyflightdetails.getFrom().equals("") &&
                       !modifyflightdetails.getTo().equals("") &&
                       !modifyflightdetails.getTotaldeparturedate().equals("") &&
                       !modifyflightdetails.getTotalarrivaldate().equals("") &&
                       !Integer.toString(modifyflightdetails.getFare()).equals("") &&
                       !modifyflightdetails.getFlightID().equals("") &&
                       !modifyflightdetails.getPilot1().equals("") &&
                       !modifyflightdetails.getPilot2().equals("") &&
                       !modifyflightdetails.getCc1().equals("") &&
                       !modifyflightdetails.getCc2().equals("") &&
                       !modifyflightdetails.getCc3().equals("")){

                        System.out.println("Updating!");
                        
                        from = modifyflightdetails.getFrom();
                        to = modifyflightdetails.getTo();
                        totaldeparturedate = modifyflightdetails.getTotaldeparturedate();
                        totalarrivaldate = modifyflightdetails.getTotalarrivaldate();
                        classtype = modifyflightdetails.getClasstype();
                        fare = modifyflightdetails.getFare();
                        flightid = modifyflightdetails.getFlightID();
                        pilot1 = modifyflightdetails.getPilot1();
                        pilot2 = modifyflightdetails.getPilot2();
                        cc1 = modifyflightdetails.getCc1();
                        cc2 = modifyflightdetails.getCc2();
                        cc3 = modifyflightdetails.getCc3();

                        
                        fd.statement.executeUpdate("UPDATE FlightInfo Set From1 = '"+from+"' , To1 = '"+to+"' , "
                                + "DepartureDate = '"+totaldeparturedate+"' , ArrivalDate = '"+totalarrivaldate+"' ,"
                                + "ClassType = '"+classtype+"' , Pilot1 = '"+pilot1+"' , Pilot2 = '"+pilot2+"' , CabinCrew1 = '"+cc1+"' , CabinCrew2 = '"+cc2+"' , CabinCrew3 = '"+cc3+"'"
                                + ", Fare = "+fare+" WHERE FlightID = '"+FlightID+"'");


                    }

                } catch (Exception e) {
                     System.out.println(e);
                }

                
                ListofFlights = DataClass.getDataofFlights();

                Current_Flight_List_Table.setItems(ListofFlights);

            }
            
        });
      
        
        ListofFlights = DataClass.getDataofFlights();

        Current_Flight_List_Table.setItems(ListofFlights);
        
  
        FlightTableSearchboxField.textProperty().addListener((observablevalue, oldValue, newValue) -> {
            
            String lowerCaseFilter = newValue.toLowerCase();
            
            ListofSearchedFlights = DataClass.getDataofSearchedFlights(lowerCaseFilter);
            
            SortedList<Flights> sortedData = new SortedList<>(ListofSearchedFlights);

            sortedData.comparatorProperty().bind(Current_Flight_List_Table.comparatorProperty());
            
            Current_Flight_List_Table.setItems(sortedData);
        });
        
        
        /*FilteredList<Flights> filteredDataofFlights = new FilteredList<>(ListofFlights, b -> true);
        
        FlightTableSearchboxField.textProperty().addListener((observablevalue, oldValue, newValue) -> {
            
            filteredDataofFlights.setPredicate(flights -> {

                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(flights.getFlightID().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else if(flights.getFrom1().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else if(flights.getTo1().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else if(flights.getDepartureDate().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else if(flights.getArrivalDate().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else if(flights.getClassType().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else if(String.valueOf(flights.getFare()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                }else{ 
                    return false; 
                }
            });
        });*/
        
        String[] coutries = new String[]{
         "Abu Dhabi","Atlanta","Bangkok","Boston","Chattogram","Chennai","Chicago","Coxs Bazar",
         "Denver","Delhi","Dhaka","Dubai","Goa","Honolulu","Jashore","Kolkata","Kerala","Las Vegas",
         "London","Mumbai","New York","Punjab","Philadelphia","Portland",
         "Rajshahi","Sylhet","Tokyo","Washington, D.C."
        };  
        
        String[] airplanes = new String[]{"Boeing 737-Max","Boeing 747-8","Airbus A318","Dash 8-400"};
        
                
        ObservableList<String> ClassType1 = FXCollections.observableArrayList("Economic","Business");
        ClassType.setItems(ClassType1);
        
        ObservableList<String> Coutries = FXCollections.observableArrayList(coutries);
        From.setItems(Coutries);
        To.setItems(Coutries);
        
        ObservableList<String> AircraftName = FXCollections.observableArrayList(airplanes);
        ACNAME.setItems(AircraftName);

        /////////////////////////////////////////////////Flight Info Table/////////////////////////////////////////////
    }
    
    @FXML
    void showEmployeeTable(){
        /////////////////////////////////////////////////Employee Table/////////////////////////////////////////////
        
        col_EmpID.          setCellValueFactory(new PropertyValueFactory<Employees,String>("EmployeeID"));      
        col_EmpMail.        setCellValueFactory(new PropertyValueFactory<Employees,String>("Email"));      
        col_EmpName.        setCellValueFactory(new PropertyValueFactory<Employees,String>("Name"));    
        col_EmpContact.     setCellValueFactory(new PropertyValueFactory<Employees,String>("Contact"));  
        col_EmpDesignation. setCellValueFactory(new PropertyValueFactory<Employees,String>("Designation"));
        col_EmpAge.         setCellValueFactory(new PropertyValueFactory<Employees,Integer>("Age")); 
       
        
        
        Current_Employee_List_Table.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                
                Employees emps = Current_Employee_List_Table.getSelectionModel().getSelectedItem();
                
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ModifyEmployeeDetails.fxml"));
                
                try{
                    loader.load();
                }catch(IOException ex){
                    Logger.getLogger(ConnectMS_SQL_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                ModifyEmployeeDetailsController modifyempdetails = loader.getController();
                
                
                modifyempdetails.setTextField(
                
                    emps.getEmployeeID(),      emps.getEmail(),     emps.getName(), emps.getContact(),
                    emps.getDesignation(),     emps.getAge()
                );
                
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                loader.getResources();
                stage.showAndWait(); 
                
                try {
                    if(!modifyempdetails.getEmployeeID().equals("") &&
                       !modifyempdetails.getEmployeeDesignation().equals("") &&
                       !modifyempdetails.getEmployeeMail().equals("") &&
                       !modifyempdetails.getEmployeeContact().equals("") &&
                       !modifyempdetails.getEmployeeName().equals("") &&
                       !Integer.toString(modifyempdetails.getEmployeeAge()).equals("")){

                        System.out.println("Updating!");
                        
                        
                        empid = modifyempdetails.getEmployeeID();
                        empdesignation=modifyempdetails.getEmployeeDesignation();
                        empmail=modifyempdetails.getEmployeeMail();
                        empcontact=modifyempdetails.getEmployeeContact();
                        empname=modifyempdetails.getEmployeeName();
                        empage=modifyempdetails.getEmployeeAge();
                        
                        
                        FxmlDemo fd = new FxmlDemo();
                        fd.connectDB();

                        fd.statement.executeUpdate("UPDATE Employee Set Email = '"+empmail+"' , Name = '"+empname+"' , " + "Age = "+empage+", "+
                         "Contact = '"+empcontact+"' , Designation = '"+empdesignation+"' WHERE EmployeeID = '"+empid+"'");

                    }

                } catch (Exception e) {
                     System.out.println(e);
                }

                
                ListofEmployees = DataClass.getDataofEmployees();
        
                Current_Employee_List_Table.setItems(ListofEmployees);

            }
            
        });
      
        
        ListofEmployees = DataClass.getDataofEmployees();
        
        Current_Employee_List_Table.setItems(ListofEmployees);
        
  
        EmployeeTableSearchboxField.textProperty().addListener((observablevalue, oldValue, newValue) -> {
            
            String lowerCaseFilter = newValue.toLowerCase();
            
            ListofSearchedEmployees = DataClass.getDataofSearchedEmployees(lowerCaseFilter);
            
            SortedList<Employees> sortedData = new SortedList<>(ListofSearchedEmployees);

            sortedData.comparatorProperty().bind(Current_Employee_List_Table.comparatorProperty());
            
            Current_Employee_List_Table.setItems(sortedData);
        });
        
    
        /////////////////////////////////////////////////Employee Table/////////////////////////////////////////////
        
    }

    
    ObservableList<Flights> ListofFlights,ListofSearchedFlights,ListofPassengersinFlightID;
    
    ObservableList<Employees> ListofEmployees,ListofSearchedEmployees;
    
    ObservableList<Passengers> ListofPassengers;
    
    ObservableList<String> ListofUserConvo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        managePassenger.    setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));                 
        setFlight.          setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));
        CustomerSupport.    setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));
        employeemanage.     setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));   
        
        loggedinlabel.setText("ADMIN");
        
        
        showFlightTable();
        
        showEmployeeTable();
        
        /////////////////////////////////////////////////Passenger Table/////////////////////////////////////////////
        
        col_PassFlightID.   setCellValueFactory(new PropertyValueFactory<Passengers,String>("FlightID"));     
        col_PassUserID.     setCellValueFactory(new PropertyValueFactory<Passengers,String>("UserID"));     
        col_PassSeatNum.    setCellValueFactory(new PropertyValueFactory<Passengers,Integer>("NoOfSeats"));
        col_PassSeatTaken.  setCellValueFactory(new PropertyValueFactory<Passengers,String>("SeatNumbers"));
        
        ListofPassengers = DataClass.getDataofPassengers();
        
        Current_Passenger_List_Table.setItems(ListofPassengers);
        
        /////////////////////////////////////////////////Passenger Table/////////////////////////////////////////////
        
        
        /////////////////////////////////////////////////Customer Convo/////////////////////////////////////////////
        
        ListofUserConvo = DataClass.getDataofCustomerMesseges();
        UserIDList.setItems(ListofUserConvo);
        
        UserIDList.setOnMousePressed(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                selectUserIDButtonAction ();
            }
            
        });
        
        /////////////////////////////////////////////////Customer Convo/////////////////////////////////////////////
    }
    
}