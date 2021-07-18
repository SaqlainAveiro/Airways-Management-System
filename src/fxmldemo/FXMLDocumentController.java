package fxmldemo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author Aristo_PC
 */
public class FXMLDocumentController implements Initializable {
    
    
    FxmlDemo fd = new FxmlDemo();
    
    
    public ResultSet resultSet;
    public String lastId;
    
    @FXML
    private JFXTextField signUpEmail;

    @FXML
    private JFXPasswordField signUpConfirmPassword;

    @FXML
    private JFXComboBox<String> signUpYear;

    @FXML
    private JFXButton signUpBtn;
    
    @FXML
    private JFXButton signUpBackBtn;

    @FXML
    private JFXTextField signUpLname;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXComboBox<String> signUpCountry;

    @FXML
    private JFXTextField signUpFname;

    @FXML
    private JFXComboBox<String> signUpDate;

    @FXML
    private JFXComboBox<String> signUpGender;

    @FXML
    private JFXTextField signUpContactNo;

    @FXML
    private JFXComboBox<String> signUpMonth;
    
    
    public void showError(String s1,String s2){
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setHeaderText(s1);
        errorAlert.setContentText(s2);
        errorAlert.showAndWait();
    }
    public boolean checkValidateName(String fName) {
        for(int i=0;i<fName.length();i++)
        {
            if(fName.charAt(i)<65 || fName.charAt(i)>122){
                return true;
            }
                
        }
        return false;
    }
    public static final Pattern VALIDEMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
            Matcher matcher = VALIDEMAIL.matcher(emailStr);
            return matcher.find();
    }
    
    @FXML
    void signUpBackBtnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage window =(Stage) signUpBackBtn.getScene().getWindow();
       
            window.setScene(new Scene(root,700,500));
        } catch (Exception e) {
        }
    }
 
    
    @FXML
    void signUpAction(ActionEvent event) {
            int flag=0;
        String fName="",lName="",password="",email="",contactNo="",date="",month="",year="",dob="",country="",gender="";
        
       label: {
        
         fName=signUpFname.getText();
           
           
        if(fName.equals("")){
            
             showError("First Name field is empty!!",
                     "The size of First Name must be between 1 and 25 characters");
             flag=1;
             break label;
        }else if(checkValidateName(fName)){
            System.out.println("hello world");
            showError("Invalid First Name!!",
                     "First Name contains only characters");
            flag=1;
            break label;
        }
        
         lName=signUpLname.getText();
         if(lName.equals("")){
             showError("Last Name field is empty!!",
                     "The size of Last Name must be between 1 and 25 characters");
             flag=1;
             break label;
        }
         if(checkValidateName(lName)){
            showError("Invalid Last Name!!",
                     "Last Name contains only characters");
            flag=1;
            break label;
        }
         if(!signUpEmail.getText().equals(""))
        {     
         if(validate(signUpEmail.getText())){
             email = signUpEmail.getText();
         }else{
             showError("Invalid Email!!", "Email should be: example@example.example");
             flag=1;
             break label;
         }
         
         
        }else{
            showError("Email field is empty", "Email should be: example@example.example");
            flag=1;
            break label;
        }
        
        if(signUpContactNo.getText().length()==11)
        {
            if(checkValidatePhone(signUpContactNo.getText())){
                contactNo=signUpContactNo.getText();
            }else{
                showError("Invalid Contact!!","Contact no contains only numbers");
                flag=1;
                break label;
            }
         
         
        }else{
          showError("Invalid Contact!!","Contact No must be 11 characters long!!");
          flag=1;
          break label;
        }
         
        
        if(signUpPassword.getText().length()>=6)
        {
            if(signUpConfirmPassword.getText().equals(signUpPassword.getText()))
            {
                password = signUpPassword.getText();
            }else{
               showError("Password didn't match !!","Password must be 6 characters long");
               flag=1;
               break label;
            }
         
        }else{
            showError("Invalid Password","Password must be 6 characters long");
            flag=1;
            break label;
        }
        
        
        
        if((signUpDate.getValue()==null || signUpMonth.getValue()==null) || signUpYear.getValue()==null)
        {
            showError("Invalid Date of Birth", "");
            flag=1;
            break label;
        }else{
            date = signUpDate.getValue();
            month = signUpMonth.getValue();
            if(month.length()==1)
            {
                month = "0"+month;
            }
            year = signUpYear.getValue();
            if(date.length()==1)
            {
                date = "0"+date;
            }
            dob = date+"-"+month+"-"+year;
        }
        
        if(signUpCountry.getValue() == null)
        {
            showError("Select a Country", "");
            flag=1;
            break label;
        }else{
            country=signUpCountry.getValue();
            
        }
        if(signUpGender.getValue()==null)
        {
            showError("Select a Gender", "");
            flag=1;
            break label;
        }else{
            gender=signUpGender.getValue();
            
        }
       }
        
        
        
     try {
            if(flag==0){
            resultSet = fd.statement.executeQuery("select * from UserLog");
            resultSet.afterLast();
            resultSet.previous();
            lastId = resultSet.getString("UserID");
            int Lid = Integer.parseInt(lastId)+1;
            lastId = String.valueOf(Lid);
            System.out.println(lastId);
            System.out.println(fName+" "+lName+" "+email+" "+contactNo+" "+
                    " "+password+" "+dob+" "+country+" "+gender);
            fd.statement.executeUpdate("INSERT INTO UserLog VALUES ('"+lastId+"','"+email+"','"
                    +fName+"','"+lName+"','"+password+"','"+dob+"','"+gender+"','"+contactNo+"','"
                    +country+"');");
            fd.statement.executeUpdate("INSERT INTO Membership VALUES ('"+lastId+"','General',"+0+");");
            Alert successMsg = new Alert(AlertType.INFORMATION);
            successMsg.setHeaderText("Created Account Successfully!!");
            successMsg.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage window =(Stage) signUpBtn.getScene().getWindow();
       
            window.setScene(new Scene(root));
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }
     
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda",
            "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", 
            "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", 
            "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada",
            "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands",
            "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)",
            "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", 
            "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", 
            "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", 
            "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", 
            "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati",
            "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", 
            "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of",
            "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", 
            "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru",
            "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", 
            "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", 
            "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint "
                + "Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
                "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", 
                "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland",
                "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga",
                "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};

        
        String[] dateArray = new String[31];
        String[] yearArray = new String[62];
        String[] monthArray = new String[12];
        
        for(int i=0;i<=30;i++)
        {
            dateArray[i]= String.valueOf(i+1);
        }
        for(int i=0;i<=11;i++)
        {
            monthArray[i]= String.valueOf(i+1);
        }
        for(int i=0;i<=61;i++){
            yearArray[i]=String.valueOf(2021-i);
        }
        ObservableList<String> date = FXCollections.observableArrayList(dateArray);
        signUpDate.setItems(date);
         ObservableList<String> month = FXCollections.observableArrayList(monthArray);
         signUpMonth.setItems(month);
         ObservableList<String> year = FXCollections.observableArrayList(yearArray);
         signUpYear.setItems(year);
         ObservableList<String> gender = FXCollections.observableArrayList("Male","Female","Others");
         signUpGender.setItems(gender);
         ObservableList<String> country = FXCollections.observableArrayList(countries);
         signUpCountry.setItems(country);
       
         fd.connectDB();
         
         
    }    

    private boolean checkValidatePhone(String text) {
        for(int i=0;i<text.length();i++)
        {
            if(text.charAt(i)<48 || text.charAt(i)>57)
                return false;
        }
        return true;
    }

}
