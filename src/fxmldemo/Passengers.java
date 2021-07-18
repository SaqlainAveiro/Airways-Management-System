package fxmldemo;

/**
 *
 * @author Saqlain
 */
public class Passengers {
    
    String FlightID, UserID, SeatNumbers;
    int NoOfSeats;

    public Passengers(String FlightID, String UserID, String SeatNumbers, int NoOfSeats) {
        this.FlightID = FlightID;
        this.UserID = UserID;
        this.SeatNumbers = SeatNumbers;
        this.NoOfSeats = NoOfSeats;
    }

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String FlightID) {
        this.FlightID = FlightID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getSeatNumbers() {
        return SeatNumbers;
    }

    public void setSeatNumbers(String SeatNumbers) {
        this.SeatNumbers = SeatNumbers;
    }

    public int getNoOfSeats() {
        return NoOfSeats;
    }

    public void setNoOfSeats(int NoOfSeats) {
        this.NoOfSeats = NoOfSeats;
    }

    

}