package fxmldemo;

/**
 *
 * @author Saqlain
 */
public class Flights {
      
    String FlightID ,From1 ,To1 ,DepartureDate ,ArrivalDate,ClassType, AC_ID, AC_Name, Pilot1, Pilot2, CabinCrew1, CabinCrew2, CabinCrew3;
    int Fare,Capacity;

    public Flights(String FlightID, String From1, String To1, String DepartureDate, String ArrivalDate, String ClassType, String Pilot1, String Pilot2, String CabinCrew1, String CabinCrew2, String CabinCrew3, int Fare, String AC_ID, String AC_Name, int Capacity) {
        this.FlightID = FlightID;
        this.From1 = From1;
        this.To1 = To1;
        this.DepartureDate = DepartureDate;
        this.ArrivalDate = ArrivalDate;
        this.ClassType = ClassType;
        this.AC_ID = AC_ID;
        this.AC_Name = AC_Name;
        this.Pilot1 = Pilot1;
        this.Pilot2 = Pilot2;
        this.CabinCrew1 = CabinCrew1;
        this.CabinCrew2 = CabinCrew2;
        this.CabinCrew3 = CabinCrew3;
        this.Fare = Fare;
        this.Capacity = Capacity;
    }

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String FlightID) {
        this.FlightID = FlightID;
    }

    public String getFrom1() {
        return From1;
    }

    public void setFrom1(String From1) {
        this.From1 = From1;
    }

    public String getTo1() {
        return To1;
    }

    public void setTo1(String To1) {
        this.To1 = To1;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String ArrivalDate) {
        this.ArrivalDate = ArrivalDate;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String ClassType) {
        this.ClassType = ClassType;
    }

    public String getAC_ID() {
        return AC_ID;
    }

    public void setAC_ID(String AC_ID) {
        this.AC_ID = AC_ID;
    }

    public String getAC_Name() {
        return AC_Name;
    }

    public void setAC_Name(String AC_Name) {
        this.AC_Name = AC_Name;
    }

    public String getPilot1() {
        return Pilot1;
    }

    public void setPilot1(String Pilot1) {
        this.Pilot1 = Pilot1;
    }

    public String getPilot2() {
        return Pilot2;
    }

    public void setPilot2(String Pilot2) {
        this.Pilot2 = Pilot2;
    }

    public String getCabinCrew1() {
        return CabinCrew1;
    }

    public void setCabinCrew1(String CabinCrew1) {
        this.CabinCrew1 = CabinCrew1;
    }

    public String getCabinCrew2() {
        return CabinCrew2;
    }

    public void setCabinCrew2(String CabinCrew2) {
        this.CabinCrew2 = CabinCrew2;
    }

    public String getCabinCrew3() {
        return CabinCrew3;
    }

    public void setCabinCrew3(String CabinCrew3) {
        this.CabinCrew3 = CabinCrew3;
    }

    public int getFare() {
        return Fare;
    }

    public void setFare(int Fare) {
        this.Fare = Fare;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    
}
