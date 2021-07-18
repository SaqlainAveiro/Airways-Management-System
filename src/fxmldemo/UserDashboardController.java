package fxmldemo;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ehsan Huq
 */
public class UserDashboardController extends LoginPageController implements Initializable {
    
    @FXML
    private GridPane bookingInfoGrid;
    
    @FXML
    private DatePicker bookTicketRetDate,bookTicketDepDate;
     
    @FXML
    private Label confirmBookingFlightIdLabel,userDashboardUsername,FlightHistoryLabel,userDashboardMembership,
    
        userDashboardPoints,selectedSeats,selectedSeats1,AricraftLabel,confirmBookingDepartureFrom,

        confirmBookingDepartureFrom1,confirmBookingDepartureTo,confirmBookingDepartureTo1,

        confirmBookingName, noOfSeatsShow, confirmBookingDateLabel, confirmBookingDateLabel1,

        confirmBookingTotalLabel, confirmBookingDepartureTimeLabel, confirmBookingDepartureTimeLabel1,

        confirmBookingFareCalculationLabel, CustomerSupportLabel , cancelBookingFlightIdLabel,cancelBookingDateLabel,

        cancelBookingDepartureTimeLabel,cancelBookingName ,cancelBookingDepartureFrom,cancelBookingDepartureTo,cancelSelectedSeats,

        confirmBookingDepartureTime1;

    @FXML
    private JFXComboBox<String> bookTicketClassType,bookTicketTo,bookTicketFrom,bookTicketNoOfSeats;

    
    @FXML
    private JFXButton bookTicketShowBtn,A1Id,A2Id,A3Id,A4Id,A5Id,B1Id,B2Id,B3Id,B4Id,B5Id,C1Id,C2Id,C3Id,C4Id,C5Id,
    
            D1Id,D2Id,D3Id,D4Id,D5Id,E1Id,E2Id,E3Id,E4Id,E5Id,F1Id,F2Id,F3Id,F4Id,F5Id,G1Id,G2Id,G3Id,G4Id,G5Id,
    
            H1Id,H2Id,H3Id,H4Id,H5Id,bookingBtn;
    
    @FXML
    private Button BookTicketButton,JourneyButton,LogoutButton,CustomerButton,MyFlightBtn,bookingBackBtn,UserSendButton;



    @FXML
    private AnchorPane CusotmerSupportAnchor,MyFlightsAnchorpane,flightInfoAnchorpane,CancelAnchorpane,ConfirmCancelAnchor,
            
            ConfirmBookingAnchor, BookTicketAnchor, JourneyAnchor;

    
    @FXML
    private JFXTextField inputFlightId,historySearch;
    
    @FXML
    private TextArea UserAdminConvo;
    
    
    
    @FXML
    private TableView<InfoFlight> showFlightTable;
    @FXML
    private TableView<FlightHistory> flightHistoryTable;
    @FXML
    private TableView<FlightHistory> BookedTable;
    
    
    
    @FXML
    private TableColumn<InfoFlight,String>flightTableFlightId;
    @FXML
    private TableColumn<InfoFlight,String>flightTableSource;
    @FXML
    private TableColumn<InfoFlight,String>flightTableDestination;
    @FXML
    private TableColumn<InfoFlight,String>flightTableDDAT;
    @FXML
    private TableColumn<InfoFlight,String>flightTableRDAT;
    @FXML
    private TableColumn<InfoFlight,String>flightTableClsType;
    @FXML
    private TableColumn<InfoFlight,Integer>flightTableFare;
    
    
    
    @FXML
    private TableColumn<FlightHistory,String>historyFlightID;
    @FXML
    private TableColumn<FlightHistory,String>historySource;
    @FXML
    private TableColumn<FlightHistory,String>historyDestination;
    @FXML
    private TableColumn<FlightHistory,String>historyDateTime;
    @FXML
    private TableColumn<FlightHistory,String>historyNoOfSeats;
    @FXML
    private TableColumn<FlightHistory,String>historyBookedSeats;
    
    
    
    @FXML
    private TableColumn<FlightHistory,String>bookedFlightID;
    @FXML
    private TableColumn<FlightHistory,String>bookedSource;
    @FXML
    private TableColumn<FlightHistory,String>bookedDestination;
    @FXML
    private TableColumn<FlightHistory,String>bookedDate;
    @FXML
    private TableColumn<FlightHistory,String>bookedSeatsbookingTable;
    @FXML
    private TableColumn<FlightHistory,String>bookedNoOfSeats;
    
    
    @FXML
    private TextField fedbackInputFlightId,inputFeedback,UserMsg;
    
    
    @FXML
    private TableView<feedback> tableid;
    @FXML
    private TableColumn<feedback,String>feedbackid;
    @FXML
    private TableColumn<feedback,String>FeedbackUserIdColumn;
    @FXML
    private TableColumn<feedback,String>FeedbackFlightIdColumn;
    
    
    
    public void showError(String s1){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(s1);
        errorAlert.showAndWait();
    }
    public void showMessage(String s1){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setHeaderText(s1);
        errorAlert.showAndWait();
    }
    
    
    ObservableList<InfoFlight> flightList = FXCollections.observableArrayList();
    ObservableList<FlightHistory> historyList = FXCollections.observableArrayList();
    ObservableList<FlightHistory> bookedTableList = FXCollections.observableArrayList();
    ObservableList<FlightHistory> filterdeList = FXCollections.observableArrayList();
    ObservableList<feedback> feedbacklist = FXCollections.observableArrayList();
    
    
    String bookingFlightId,allconvo="",getallconvo="";
    int flightFare,availableSeats;
        
    
    @FXML
    void selectUserIDButtonAction (){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
                                        
        try{
            
            //System.out.println("ID = "+Data.userID);

            ResultSet CustomerMessegeRS = fd.statement.executeQuery("SELECT * FROM CustomerSupport Where UserID = '"+Data.userID+"'");
            
            while(CustomerMessegeRS.next()){
                getallconvo = CustomerMessegeRS.getString("UserAdminConvo");     
            }
            System.out.println("WOW "+getallconvo);
  

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
            
            //System.out.println("Reply : "+allconvo);
            
            allconvo="";
            
        }catch(Exception e){
            
        } 
    }
 
    
    @FXML
    void UserSendButtonAction(ActionEvent event){
       
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        String UserText = UserMsg.getText(),PrevReply="";
        
        try{
            
            int status=0;
            
            ResultSet AdminPrevMessegeRS = fd.statement.executeQuery("Select * From CustomerSupport Where UserID = '"+Data.userID+"'");      
            
            while(AdminPrevMessegeRS.next()){
                PrevReply = AdminPrevMessegeRS.getString("UserAdminConvo");
            }
            
            
            System.out.println("1 Result = "+PrevReply);
            
            char ch=39;
            
            UserText = UserText.replace(ch,'"');
                      
            String query="";
            String update = "UPDATE CustomerSupport Set UserAdminConvo = '"+PrevReply+'~'+UserText+"' Where UserID = '"+Data.userID+"';";
            String insert = "INSERT INTO CustomerSupport VALUES ( '"+Data.userID+"' , '"+PrevReply+'~'+UserText+"' );";
            
            if(!PrevReply.equals("")){
                query = update; 
            }else{
                query = insert;
            }
            
            System.out.println("2 Result = "+query);
            
            status = fd.statement.executeUpdate(query);
            
            PrevReply += '~' + UserText; 
            
            allconvo= UserAdminConvo.getText();
            
            UserText = UserText.replace('"',ch);
            
            allconvo+= "\nUser : " + UserText;
            
            /*System.out.println("ST = "+allconvo);
            System.out.println("Data = "+Data.userID);
            System.out.println("ST = "+PrevReply);*/
            
            UserAdminConvo.setText(allconvo); 
            
            allconvo=""; 
            
            UserMsg.clear();
                        
        }catch(Exception e){
            
        }
     
     
     }
    
    
    @FXML
    void bookTicketShowBtnAction(ActionEvent event){
        
        String from,depDate,retDate,to,noOfPassenger,classType;
        Date dat;
        
        block:{
            
            from = bookTicketFrom.getValue();
            if(from == null){
                showError("Select a Source");
                break block;
            }
            
            
            to = bookTicketTo.getValue();
            if(to == null){
                showError("Select a Destination");
                break block;
            }
            
            
            if(from.equals(to)){
                showError("Please choose different Source or Destination");
                 break block;
            }
            
            
            depDate = String.valueOf(bookTicketDepDate.getValue());
            if(depDate.equals(null)){
                showError("Select a Departure date!");
                break block;
            }
            System.out.println(depDate);

            
            
            classType = bookTicketClassType.getValue();
            if(classType == null){
                showError("Select a Class");
                break block;
            }
            
            
            boolean listFound =false;
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            
            try{
                
                ResultSet rs = fd.statement.executeQuery("select * from FlightInfo where From1= '"+from+"' AND To1 = '"+to+"' AND ClassType = '"+classType+"';");

                flightList.clear();

                while(rs.next()){

                    if(rs.getString("DepartureDate").startsWith(depDate)) {
                        
                        listFound=true;

                        flightList.add(new InfoFlight(rs.getString("FlightID"), rs.getString("From1"), rs.getString("To1"),
                        rs.getString("DepartureDate"), rs.getInt("AvailableSeats"), rs.getString("ClassType"),
                        rs.getInt("Fare")));
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            flightTableFlightId.setCellValueFactory(new PropertyValueFactory<>("id"));
            flightTableSource.setCellValueFactory(new PropertyValueFactory<>("src"));
            flightTableDestination.setCellValueFactory(new PropertyValueFactory<>("dst"));
            flightTableDDAT.setCellValueFactory(new PropertyValueFactory<>("ddt"));
            flightTableRDAT.setCellValueFactory(new PropertyValueFactory<>("rdt"));
            flightTableClsType.setCellValueFactory(new PropertyValueFactory<>("clstype"));
            flightTableFare.setCellValueFactory(new PropertyValueFactory<>("fare"));

            if(listFound == false){
                showError("No Flights Available At The Moment");
                break block;
            }
            showFlightTable.setItems(flightList);

            flightInfoAnchorpane.toFront();
            showFlightTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                
                bookingFlightId =showFlightTable.getSelectionModel().getSelectedItem().getId();
                System.out.println(showFlightTable.getSelectionModel().getSelectedItem().getId());
                
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                FxmlDemo fd1 = new FxmlDemo();
                fd1.connectDB();
            
                try{
                    ResultSet rs = fd.statement.executeQuery("select * from FlightInfo inner join AircraftInfo on FlightInfo.FlightID = AircraftInfo.FlightID " +
                                                     "and FlightInfo.FlightID ='"+bookingFlightId+"';");


                    confirmBookingName.setText(Data.name);
                    
                    if (rs.next()){

                        availableSeats = rs.getInt("AvailableSeats");
                        flightFare = rs.getInt("Fare");
                        confirmBookingFlightIdLabel.setText(bookingFlightId);
                        AricraftLabel.setText(rs.getString("AC_ID"));
                        confirmBookingDateLabel.setText(rs.getString("DepartureDate").substring(0,10));
                        confirmBookingDateLabel1.setText(confirmBookingDateLabel.getText());
                        confirmBookingDepartureTimeLabel.setText(rs.getString("DepartureDate").substring(11,19));
                        confirmBookingDepartureTime1.setText(confirmBookingDepartureTimeLabel.getText());
                        confirmBookingDepartureFrom.setText(rs.getString("From1"));
                        confirmBookingDepartureFrom1.setText(confirmBookingDepartureFrom.getText());
                        confirmBookingDepartureTo.setText(rs.getString("To1"));
                        confirmBookingDepartureTo1.setText(confirmBookingDepartureTo.getText());
                        noOfSeatsShow.setText("0");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }


            initButton();

                ConfirmBookingAnchor.toFront();

                }
            });
        }
        
    }
    
    
    @FXML
    void useractionbutton(ActionEvent event) throws IOException {
        
        if(event.getSource()==BookTicketButton){
            Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
            Stage window =(Stage) BookTicketButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
        
        else if(event.getSource()==JourneyButton){
            
            feedbacklist.clear();
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            
            try{
                
                ResultSet rs = fd.statement.executeQuery("select * from Feedback ;");

                while(rs.next()){
                    feedbacklist.add(new feedback(rs.getString("userid"), rs.getString("flightid"), rs.getString("feedback")));
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
            
            FeedbackUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
            FeedbackFlightIdColumn.setCellValueFactory(new PropertyValueFactory<>("flightid"));
            feedbackid.setCellValueFactory(new PropertyValueFactory<>("feedback"));
            tableid.setItems(feedbacklist);

            JourneyAnchor.toFront();
        }
        
        else if(event.getSource()==CustomerButton){
            CustomerSupportLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255),CornerRadii.EMPTY,Insets.EMPTY)));
            CusotmerSupportAnchor.toFront();
        }
        
        else if(event.getSource()==MyFlightBtn){
            
            bookedTableList.clear();
            historyList.clear();
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            
            try{
                ResultSet rs = fd.statement.executeQuery("select *,CONVERT(varchar,DepartureDate) as Dates from FlightInfo inner join"
                        + " PassengerFlight on FlightInfo.FlightID =PassengerFlight.FlightID  AND PassengerFlight.UserID = "+Data.userID+" ;");
                                
                while(rs.next()){
                    
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date sDate = new Date();
                    
                    String d = String.valueOf(dateFormat.format(sDate));
                    
                    int sdate = Integer.parseInt(d.substring(0, 4)+d.substring(5, 7)+d.substring(8, 10));
                    int date =Integer.parseInt(rs.getString("DepartureDate").substring(0, 4)+rs.getString("DepartureDate").substring(5, 7)+rs.getString("DepartureDate").substring(8, 10));
                    
                    if(sdate>date){
                        historyList.add(new FlightHistory(rs.getString("FlightID"), rs.getString("From1"), rs.getString("To1"),
                        rs.getString("Dates"),rs.getString("SeatNumbers"), rs.getString("NoOfSeats"))); 
                    }else{
                        bookedTableList.add(new FlightHistory(rs.getString("FlightID"), rs.getString("From1"), rs.getString("To1"),
                        rs.getString("DepartureDate").substring(0, 19),rs.getString("SeatNumbers"), rs.getString("NoOfSeats")));
                    }
                }
                
                
            }catch(SQLException e){
                e.printStackTrace();
            }

            bookedFlightID.         setCellValueFactory(new PropertyValueFactory<>("id"));
            bookedSource.           setCellValueFactory(new PropertyValueFactory<>("src"));
            bookedDestination.      setCellValueFactory(new PropertyValueFactory<>("dst"));
            bookedDate.             setCellValueFactory(new PropertyValueFactory<>("date"));
            bookedSeatsbookingTable.setCellValueFactory(new PropertyValueFactory<>("selected"));
            bookedNoOfSeats.        setCellValueFactory(new PropertyValueFactory<>("nos"));
            BookedTable.setItems(bookedTableList);

            historyFlightID.        setCellValueFactory(new PropertyValueFactory<>("id"));
            historySource.          setCellValueFactory(new PropertyValueFactory<>("src"));
            historyDestination.     setCellValueFactory(new PropertyValueFactory<>("dst"));
            historyDateTime.        setCellValueFactory(new PropertyValueFactory<>("date"));
            historyBookedSeats.     setCellValueFactory(new PropertyValueFactory<>("selected"));
            historyNoOfSeats.       setCellValueFactory(new PropertyValueFactory<>("nos"));
            flightHistoryTable.setItems(historyList);

            MyFlightsAnchorpane.toFront();
            
            
            BookedTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                         
                        cancelBookingName.setText(Data.name);
                        cancelBookingFlightIdLabel.setText(BookedTable.getSelectionModel().getSelectedItem().getId());
                        cancelBookingDepartureFrom.setText(BookedTable.getSelectionModel().getSelectedItem().getSrc());
                        cancelBookingDepartureTo.setText(BookedTable.getSelectionModel().getSelectedItem().getDst());
                        cancelBookingDateLabel.setText(BookedTable.getSelectionModel().getSelectedItem().getDate().substring(0, 10));
                        cancelBookingDepartureTimeLabel.setText(BookedTable.getSelectionModel().getSelectedItem().getDate().substring(11,19));
                        cancelSelectedSeats.setText(BookedTable.getSelectionModel().getSelectedItem().selected);
                        
                        CancelAnchorpane.toFront();
                        
                    }
                });
        } 
        
        else if(event.getSource()==LogoutButton){
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage window =(Stage) LogoutButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }
    
    
    @FXML
    void confirmBookingBtnAction(ActionEvent event) throws IOException{
        try{
                String nos = noOfSeatsShow.getText();
                String selected = selectedSeats.getText();
                
                FxmlDemo fd = new FxmlDemo();
                fd.connectDB();
                
                fd.statement.executeUpdate("INSERT INTO PassengerFlight VALUES  ('"+bookingFlightId+"','"+Data.userID+"',"+nos+",'"+selected+"');");
                
                nos = String.valueOf(availableSeats - Integer.parseInt(nos));
                
                fd.statement.executeUpdate("update FlightInfo set AvailableSeats = "+nos+" , BookedSeats = '"+bookedSeats+selected+"' where FlightID = '"+bookingFlightId+"' ;");
                
                ResultSet rs  =fd.statement.executeQuery("Select * from Membership where UserID = '"+Data.userID+"'");
                
                if(rs.next()){
                    
                    int points = rs.getInt("Point");
                    String status = rs.getString("MemberStatus");
                    
                    points += Integer.parseInt(confirmBookingTotalLabel.getText().replace("= BDT ", ""))/100;
                    
                    if(status.equals("General") && points>1000){
                        points -= 1000;
                        status="Bronze";
                        showMessage("Congratulations!!! You have achieved our Bronze membership");
                    }else if(status.equals("Bronze") && points>3000){
                        points -= 3000;
                        status="Silver";
                        showMessage("Congratulations!!! You have achieved our Silver membership");
                    }else if(status.equals("Silver") && points>8000){
                        points -= 8000;
                        status="Gold";
                        showMessage("Congratulations!!! You have achieved our Gold membership");
                    }
                    else if(status.equals("Gold") && points>15000){
                        points -= 15000;
                        status="Diamond";
                        showMessage("Congratulations!!! You have achieved our Diamond membership");
                    }
                    
                    fd.statement.executeUpdate("update Membership set Point = "+points+" , MemberStatus = '"+status+"' where UserID= '"+Data.userID+"';");
                    Data.membership=status;
                    Data.points=points;
                }
                
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setHeaderText("Booking Successful!");
                al.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
                Stage window =(Stage) BookTicketButton.getScene().getWindow();
                window.setScene(new Scene(root,1230,766));
                
                
        }catch(SQLException e){
            e.printStackTrace();
            showError("You are not allowed to book ticket!!");
        }
    }
    
    
    @FXML
    void submitbuttononaction (ActionEvent event) throws IOException,SQLException{
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        feedbacklist.clear();
        
        fd.statement.executeUpdate("insert into Feedback values ('"+Data.userID+"','"+fedbackInputFlightId.getText()+"','"+inputFeedback.getText()+"')");
        feedbacklist.add(new feedback(Data.userID,fedbackInputFlightId.getText() , inputFeedback.getText()));
        tableid.setItems(feedbacklist);
        fedbackInputFlightId.setText("");
        inputFeedback.setText("");
    }
    
    
    @FXML
    void confirmBookingBackBtnAction(ActionEvent event) throws IOException{
        flightInfoAnchorpane.toFront();
    }
     
    
    @FXML
    void bookBackBtnAction(ActionEvent event) throws IOException{
        BookTicketAnchor.toFront();
    }
    
    
    
    @FXML
    private Label confirmBookingDiscountLabel;
    
    @FXML
    private JFXButton flightCancelBtn,flightCancelBackBtn,confirmFlightCancelBack,confirmFlightCancel;
    
    @FXML
    private AnchorPane tabAnchor;
    
    @FXML
    public void allCancelAction(ActionEvent event){
        
        if(event.getSource() == flightCancelBtn){
            ConfirmCancelAnchor.toFront();
        }else if(event.getSource() == flightCancelBackBtn){
            tabAnchor.toFront();
        }else if(event.getSource() == confirmFlightCancelBack){
            CancelAnchorpane.toFront();
        }else if(event.getSource() == confirmFlightCancel){
            FlightHistory selectedItem = BookedTable.getSelectionModel().getSelectedItem();
            try{
                
            
            
            FxmlDemo fd = new FxmlDemo();
            fd.connectDB();
            int nos =Integer.parseInt(selectedItem.getNos()) ;
            
            fd.statement.executeUpdate("delete from PassengerFlight where FlightID = '"+selectedItem.getId()+"' and SeatNumbers = '"+selectedItem.getSelected()+"' ;");
            
            ResultSet rs = fd.statement.executeQuery("select * from FlightInfo where FlightID = '"+selectedItem.getId()+"' ;");
            
            int available=0;
            String selectedSeat="";
            int fare=0;
            if(rs.next()){
                available = rs.getInt("AvailableSeats")+Integer.parseInt(selectedItem.getNos());
                selectedSeat = rs.getString("BookedSeats").replace(selectedItem.getSelected(), "");
                fare= rs.getInt("Fare");
            }
            fd.statement.executeUpdate("update FlightInfo set AvailableSeats = "+available+", BookedSeats = '"+selectedSeat+"' where FlightID = '"+selectedItem.getId()+"' ;");
            int points = (nos*fare)/100;
            String status;
            
            if(Data.membership.equals("General")){
                points = Data.points - points;
            }else if(Data.membership.equals("Bronze")){
                if(Data.points<points){
                    points=(1000+Data.points)-points;
                    Data.membership="General";
                }else{
                    points = Data.points - points;
                }
            }else if(Data.membership.equals("Silver")){
                if(Data.points<points){
                    points=(3000+Data.points)-points;
                    Data.membership="General";
                }else{
                    points = Data.points - points;
                }
            }else if(Data.membership.equals("Gold")){
                if(Data.points<points){
                    points=(8000+Data.points)-points;
                    Data.membership="Silver";
                }else{
                    points = Data.points - points;
                }
            }
            else if(Data.membership.equals("Diamond")){
                if(Data.points<points){
                    points=(15000+Data.points)-points;
                    Data.membership="Diamond";
                }else{
                    points = Data.points - points;
                }
            }
            Data.points=points;
            fd.statement.executeUpdate("update Membership set MemberStatus = '"+Data.membership+"', Point = "+Data.points+" ;");
            BookedTable.getItems().remove(selectedItem);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your journey has been cancelled successfully!!");
            alert.showAndWait();
            
            Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
            Stage window =(Stage) BookTicketButton.getScene().getWindow();
            window.setScene(new Scene(root,1230,766));
            
            }catch(Exception e){
                showError("Currently you have no booked Flights");
                e.printStackTrace();
            }
            
        }
        
    }

    @FXML
    public void seatButtonAction(ActionEvent event){
        
        JFXButton btn = (JFXButton) event.getSource();
             
        if("-fx-background-color: Orange".equals(btn.getStyle())){

        String str = selectedSeats.getText();

        selectedSeats.setText(str.replace(btn.getText(), ""));
        selectedSeats1.setText(selectedSeats.getText());
        String nos = String.valueOf(Integer.parseInt(noOfSeatsShow.getText())-1);
        noOfSeatsShow.setText(nos);

        confirmBookingFareCalculationLabel.setText(nos+" x "+flightFare);
        String tot =String.valueOf(Integer.parseInt(nos) * flightFare);
        confirmBookingTotalLabel.setText("= BDT "+tot);

        btn.setStyle("-fx-background-color: lightGreen");

        }else if("-fx-background-color: #b1a7a6".equals(btn.getStyle())){

        }else{

            String nos = String.valueOf(Integer.parseInt(noOfSeatsShow.getText())+1);
            noOfSeatsShow.setText(nos);
            selectedSeats.setText(selectedSeats.getText()+btn.getText());
            selectedSeats1.setText(selectedSeats.getText());

            confirmBookingFareCalculationLabel.setText(nos+" x "+flightFare);

            int discount=0;

            if(Data.membership.equals("Bronze")){
                discount=2;   
            }else if(Data.membership.equals("Silver")){
                discount=4;
            }else if(Data.membership.equals("Gold")){
                discount=6;
            }else if(Data.membership.equals("Diamond")){
                discount=8;
            }

            int fare = (Integer.parseInt(nos) * flightFare*discount)/100;
            confirmBookingDiscountLabel.setText("BDT "+fare+"  ("+Data.membership+")");

            String tot =String.valueOf((Integer.parseInt(nos) * flightFare)-fare);
            confirmBookingTotalLabel.setText("= BDT "+tot);

            btn.setStyle("-fx-background-color: Orange");
        }
    }
    
    @FXML
    private void filterHistoryAction(KeyEvent key) throws Exception{
       
        String s = historySearch.getText();
        s = s.toLowerCase();
        System.out.println(s);
       
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = new Date();
        String d= String.valueOf(dateFormat.format(sDate));
        
       
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ResultSet rs = fd.statement.executeQuery("select FlightInfo.FlightID as FlightID ,From1 ,To1, SeatNumbers, NoOfSeats, " +
                                                "CONVERT(varchar,DepartureDate) as Dates from FlightInfo inner join PassengerFlight " +
                                                "on FlightInfo.FlightID = PassengerFlight.FlightID and PassengerFlight.UserID = '"+Data.userID+"' and " +
                                                "FlightInfo.DepartureDate < '"+d+"' and (LOWER(FlightInfo.FlightID) like '%"+s+"%' or " +
                                                "LOWER(From1) like '%"+s+"%' or LOWER(To1) like '%"+s+"%' or LOWER(SeatNumbers) like '%"+s+"%' or " +
                                                "LOWER(NoOfSeats) like '%"+s+"%' or LOWER( CONVERT(varchar,DepartureDate)) like '%"+s+"%' );");
        filterdeList.clear();
       
        while(rs.next()){
           
            filterdeList.add(new FlightHistory(rs.getString("FlightID"), rs.getString("From1"), rs.getString("To1"),
            
                   rs.getString("Dates"),rs.getString("SeatNumbers"), rs.getString("NoOfSeats"))); 
        }
       
        flightHistoryTable.setItems(filterdeList);
    }
    
    @FXML
    private void finalBookingBackAction(ActionEvent e){
        ConfirmBookingAnchor.toFront();
    }
    
    @FXML
    private AnchorPane finalBookingAnchor;
    
    @FXML
    private void confirmBookingContinueAction(ActionEvent e){
       finalBookingAnchor.toFront();
    }
    
    String bookedSeats;
    
    private void initButton(){
        
        selectedSeats.setText("");
        noOfSeatsShow.setText("0");
        confirmBookingFareCalculationLabel.setText("");
        confirmBookingTotalLabel.setText("");
        String str = "";
        
        try {
            
            FxmlDemo fd1 = new FxmlDemo();
            fd1.connectDB();
            
            ResultSet rs = fd1.statement.executeQuery("select * from FlightInfo where FlightID = '"+bookingFlightId+"' ;");
            
            if(rs.next()){
                str = rs.getString("BookedSeats");
                bookedSeats = rs.getString("BookedSeats");
                if(rs.getString("BookedSeats") == null){
                    str="";
                    bookedSeats = "";
                }
                System.out.println(str);
            }
        } catch (Exception e) {
            
        }
        
                        
        
        if(str.contains(A1Id.getText())){
            A1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            A1Id.setStyle("-fx-background-color: lightGreen");   
        }
         
        if(str.contains(A2Id.getText())){
            A2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            A2Id.setStyle("-fx-background-color: lightGreen");   
        }
        
        if(str.contains(A3Id.getText())){
            A3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            A3Id.setStyle("-fx-background-color: lightGreen");   
        }
        
        if(str.contains(A4Id.getText())){
            A4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            A4Id.setStyle("-fx-background-color: lightGreen");   
        }
        
        if(str.contains(A5Id.getText())){
            A5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            A5Id.setStyle("-fx-background-color: lightGreen");   
        }
        
        if(str.contains(B1Id.getText())){
            B1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            B1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(B2Id.getText())){
            B2Id.setStyle("-fx-background-color: #b1a7a6");
        }
        else{
            B2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(B3Id.getText())){
            B3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            B3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(B4Id.getText())){
            B4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            B4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(B5Id.getText())){
            B5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            B5Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(C1Id.getText())){
            C1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
             C1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(C2Id.getText())){
            C2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
             C2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(C3Id.getText())){
            C3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
             C3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(C4Id.getText())){
            C4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
             C4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(C5Id.getText())){
            C5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
             C5Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(D1Id.getText())){
            D1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
             D1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(D2Id.getText())){
            D2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            D2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(D3Id.getText())){
            D3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            D3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(D4Id.getText())){
            D4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            D4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(D5Id.getText())){
            D5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            D5Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(E1Id.getText())){
            E1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            E1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(E2Id.getText())){
            E2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            E2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(E3Id.getText())){
            E3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            E3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(E4Id.getText())){
            E4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            E4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(E5Id.getText())){
            E5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            E5Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(F1Id.getText())){
            F1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            F1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(F2Id.getText())){
            F2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            F2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(F3Id.getText())){
            F3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            F3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(F4Id.getText())){
            F4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            F4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(F5Id.getText())){
            F5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            F5Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(G1Id.getText())){
            G1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            G1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(G2Id.getText())){
            G2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            G2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(G3Id.getText())){
            G3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            G3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(G4Id.getText())){
            G4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            G4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(G5Id.getText())){
            G5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            G5Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(H1Id.getText())){
            H1Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            H1Id.setStyle("-fx-background-color: lightGreen");
        }
         
        if(str.contains(H2Id.getText())){
            H2Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            H2Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(H3Id.getText())){
            H3Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            H3Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(H4Id.getText())){
            H4Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            H4Id.setStyle("-fx-background-color: lightGreen");
        }
        
        if(str.contains(H5Id.getText())){
            H5Id.setStyle("-fx-background-color: #b1a7a6");
        }else{
            H5Id.setStyle("-fx-background-color: lightGreen");
        }
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        selectUserIDButtonAction ();
        
        String[] states = new String[]{
            "Abu Dhabi","Atlanta","Bangkok","Boston","Chattogram","Chennai","Chicago","Coxs Bazar",
            "Denver","Delhi","Dhaka","Dubai","Goa","Honolulu","Jashore","Kolkata","Kerala","Las Vegas",
            "London","Mumbai","New York","Punjab","Philadelphia","Portland",
            "Rajshahi","Sharjah","Sylhet","Tokyo","Washington, D.C."};
        
        String noofseats[] = new String[30];
        
        for(int i=0;i<30;i++){
            noofseats[i]= String.valueOf(i+1);
        }
        
        ObservableList<String> classType = FXCollections.observableArrayList("Economic","Business");
        bookTicketClassType.setItems(classType);
        
        ObservableList<String> state = FXCollections.observableArrayList(states);
        bookTicketFrom.setItems(state);
        bookTicketTo.setItems(state);

        userDashboardUsername.setText(Data.name);
        userDashboardMembership.setText("Membership: "+Data.membership);
        userDashboardPoints.setText(String.valueOf("Points: "+Data.points));
    }
}

