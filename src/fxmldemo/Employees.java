package fxmldemo;

/**
 *
 * @author Saqlain
 */
public class Employees {
    
    String EmployeeID ,Email ,Name ,Contact ,Designation; 
    int Age;

    public Employees(String EmployeeID, String Email, String Name, String Contact, String Designation, int Age) {
        this.EmployeeID = EmployeeID;
        this.Email = Email;
        this.Name = Name;
        this.Contact = Contact;
        this.Designation = Designation;
        this.Age = Age;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
}
