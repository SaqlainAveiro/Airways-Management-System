package fxmldemo;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Saqlain
 */
public class DataClass {
    
    
    public static ObservableList<Flights> getDataofFlights(){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
       
        ObservableList <Flights> FlightList = FXCollections.observableArrayList();
        
        try{
            
            ResultSet FlightRS = fd.statement.executeQuery("Select * From FlightInfo Inner Join AircraftInfo On FlightInfo.FlightID = AircraftInfo.FlightID");
            
            while(FlightRS.next()){
                
                FlightList.add(new Flights (            FlightRS.getString("FlightID"), 
                FlightRS.getString("From1") ,           FlightRS.getString("To1"),     
                FlightRS.getString("DepartureDate") ,   FlightRS.getString("ArrivalDate"),
                FlightRS.getString("ClassType"),        FlightRS.getString("Pilot1"),
                FlightRS.getString("Pilot2") ,          FlightRS.getString("CabinCrew1"),
                FlightRS.getString("CabinCrew2"),       FlightRS.getString("CabinCrew3"),
                Integer.parseInt(FlightRS.getString("Fare")),
                FlightRS.getString("AC_ID"),            FlightRS.getString("AC_Name"),          
                Integer.parseInt(FlightRS.getString("Capacity"))));
            }
            
        }catch(Exception e){
            
        }        
        return FlightList;
    }
    
    public static ObservableList<Employees> getDataofEmployees(){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
       
        ObservableList <Employees> EmployeeList = FXCollections.observableArrayList();
        
        try{
                        
            ResultSet EmployeeRS = fd.statement.executeQuery("SELECT * FROM Employee");
            
            while(EmployeeRS.next()){
                
                EmployeeList.add(new Employees (            EmployeeRS.getString("EmployeeID"), 
                EmployeeRS.getString("Email") ,             EmployeeRS.getString("Name"),     
                EmployeeRS.getString("Contact") ,           EmployeeRS.getString("Designation") ,
                Integer.parseInt(EmployeeRS.getString("Age"))));
            }
            
        }catch(Exception e){
            
        }        
        return EmployeeList;
    }
    
    public static ObservableList<Passengers> getDataofPassengers(){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList <Passengers> PassengerList = FXCollections.observableArrayList();
        
        try{
            
            ResultSet PassengerRS = fd.statement.executeQuery("SELECT * FROM PassengerFlight");
            
            while(PassengerRS.next()){
                
                PassengerList.add(new Passengers (          
                PassengerRS.getString("FlightID"), 
                PassengerRS.getString("UserID") , 
                PassengerRS.getString("SeatNumbers"),
                Integer.parseInt(PassengerRS.getString("NoOfSeats"))));
            }
            
        }catch(Exception e){
            
        }        
        return PassengerList;
    }
    
    public static ObservableList<String> getDataofCustomerMesseges(){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList <String> CustomerMessegeList = FXCollections.observableArrayList();
        
        try{
                        
            ResultSet CustomerMessegesRS = fd.statement.executeQuery("SELECT * FROM CustomerSupport");
            
            while(CustomerMessegesRS.next()){
                
                CustomerMessegeList.add(new String (CustomerMessegesRS.getString("UserID")));
            }
            
        }catch(Exception e){
            
        }        
        return CustomerMessegeList;
    }
    
    public static ObservableList<Employees> getDataOfPilots(){
         
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList <Employees> Pilotlist = FXCollections.observableArrayList();
        
        try{
                        
            ResultSet PilotlistRS = fd.statement.executeQuery("SELECT * FROM Employee Where Designation = 'Pilot'");
            
            while(PilotlistRS.next()){
                
                Pilotlist.add(new Employees (                PilotlistRS.getString("EmployeeID"), 
                PilotlistRS.getString("Email") ,             PilotlistRS.getString("Name"),     
                PilotlistRS.getString("Contact") ,           PilotlistRS.getString("Designation") ,
                Integer.parseInt(PilotlistRS.getString("Age"))));
            }
            
        }catch(Exception e){
            
        }        
        return Pilotlist;
     }
    
    public static ObservableList<Employees> getDataOfCabinCrews(){
         
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList <Employees> CClist = FXCollections.observableArrayList();
        
        try{
                        
            ResultSet CClistRS = fd.statement.executeQuery("SELECT * FROM Employee Where Designation = 'Cabin Crew'");
            
            while(CClistRS.next()){
                
                CClist.add(new Employees (                CClistRS.getString("EmployeeID"), 
                CClistRS.getString("Email") ,             CClistRS.getString("Name"),     
                CClistRS.getString("Contact") ,           CClistRS.getString("Designation") ,
                Integer.parseInt(CClistRS.getString("Age"))));
            }
            
        }catch(Exception e){
            
        }        
        return CClist;
     }
    
    public static ObservableList<Flights>getDataofSearchedFlights(String ValueToSearch){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList<Flights>SearchedFlightList = FXCollections.observableArrayList();
        
        try{

            ResultSet SearchedFlightListRS = fd.statement.executeQuery(
                    
                "Select * From FlightInfo Inner Join AircraftInfo On FlightInfo.FlightID = AircraftInfo.FlightID \n" +
                "Where CONCAT(FlightInfo.FlightID, From1, To1, DepartureDate, ArrivalDate, ClassType, Fare, AC_ID, AC_Name, Capacity) " +
                "LIKE '%"+ValueToSearch+"%'"
            );
            
                        
            while(SearchedFlightListRS.next()){
                
                SearchedFlightList.add(new Flights (                SearchedFlightListRS.getString("FlightID"), 
                SearchedFlightListRS.getString("From1") ,           SearchedFlightListRS.getString("To1"),     
                SearchedFlightListRS.getString("DepartureDate") ,   SearchedFlightListRS.getString("ArrivalDate"),
                SearchedFlightListRS.getString("ClassType"),        SearchedFlightListRS.getString("Pilot1"),
                SearchedFlightListRS.getString("Pilot2") ,          SearchedFlightListRS.getString("CabinCrew1"),
                SearchedFlightListRS.getString("CabinCrew2"),       SearchedFlightListRS.getString("CabinCrew3"),
                Integer.parseInt(SearchedFlightListRS.getString("Fare")),          
                SearchedFlightListRS.getString("AC_ID"),            SearchedFlightListRS.getString("AC_Name"),        
                Integer.parseInt(SearchedFlightListRS.getString("Capacity"))));
            }
            
        }catch(Exception e){
            
        }
        return SearchedFlightList;
    }
    
    public static ObservableList<Employees>getDataofSearchedEmployees(String ValueToSearch){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList<Employees>SearchedEmployeeList = FXCollections.observableArrayList();
        
        try{

            ResultSet SearchedEmployeeListRS = fd.statement.executeQuery(
                    
                "Select * From Employee Where CONCAT(EmployeeID, Email, Name, Age, Contact, Designation) LIKE '%"+ValueToSearch+"%'"
            );
            
                        
            while(SearchedEmployeeListRS.next()){
                
                SearchedEmployeeList.add(new Employees (            SearchedEmployeeListRS.getString("EmployeeID"), 
                SearchedEmployeeListRS.getString("Email") ,         SearchedEmployeeListRS.getString("Name"),     
                SearchedEmployeeListRS.getString("Contact") ,       SearchedEmployeeListRS.getString("Designation"),                
                Integer.parseInt(SearchedEmployeeListRS.getString("Age"))));
            }
            
        }catch(Exception e){
            
        }
        return SearchedEmployeeList;
    }
    
    public static ObservableList<String> getDataofFlightIDs(){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        ObservableList <String> FligtIDList = FXCollections.observableArrayList();
        
        try{
                        
            ResultSet FligtIDListRS = fd.statement.executeQuery("SELECT * FROM FlightInfo");
            
            while(FligtIDListRS.next()){
                
                FligtIDList.add(new String (FligtIDListRS.getString("FlightID")));
            }
            
        }catch(Exception e){
            
        }        
        return FligtIDList;
    }
    
    public static ObservableList<Users>getDataofFlightPassengers(String ValueToSearch){
        
        FxmlDemo fd = new FxmlDemo();
        fd.connectDB();
        
        
        ObservableList <Users> FlightPassengersList = FXCollections.observableArrayList();
        
        try{

            ResultSet FlightPassengersListRS = fd.statement.executeQuery(
                    
                "Select * From FlightInfo "
                + "Inner Join PassengerFlight On FlightInfo.FlightID = PassengerFlight.FlightID "
                + "Inner Join UserLog On PassengerFlight.UserID = UserLog.UserID "
                + "Where FlightInfo.FlightID = '"+ValueToSearch+"'"
            );
            
                        
            while(FlightPassengersListRS.next()){
                
                FlightPassengersList.add(new Users (                FlightPassengersListRS.getString("UserID"), 
                FlightPassengersListRS.getString("Email") ,         FlightPassengersListRS.getString("FirstName"),     
                FlightPassengersListRS.getString("LastName") ,      FlightPassengersListRS.getString("Passkey"),                
                FlightPassengersListRS.getString("DOB"),            FlightPassengersListRS.getString("Gender") ,
                FlightPassengersListRS.getString("ContactNo"),      FlightPassengersListRS.getString("CountryName")
                
                ));
            }
            
        }catch(Exception e){
            
        }
        return FlightPassengersList;

    }
    
}