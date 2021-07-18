package fxmldemo;

/**
 *
 * @author Saqlain
 */
public class Users {
    
    String UserID, Email, FirstName, LastName, Passkey, DOB, Gender, ContactNo, CountryName;

    public Users(String UserID, String Email, String FirstName, String LastName, String Passkey, String DOB, String Gender, String ContactNo, String CountryName) {
        this.UserID = UserID;
        this.Email = Email;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Passkey = Passkey;
        this.DOB = DOB;
        this.Gender = Gender;
        this.ContactNo = ContactNo;
        this.CountryName = CountryName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPasskey() {
        return Passkey;
    }

    public void setPasskey(String Passkey) {
        this.Passkey = Passkey;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    

}
