/**
 * Class description for a Person.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class Person {
    /* Member Variables */
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;

    /* Constructors */
    public Person(String name) { // For basic with defaults
        this(name, "address unknown", "phone number unknown", "email address unknown");
    }

    public Person(String name, String address, String phoneNumber,
                  String emailAddress) { // For full argument assignment
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public Person(Person other) { // For copying member values from another Person object
        this(other.name, other.address, other.phoneNumber, other.emailAddress);
    }

    /* Overridden Methods */
    @Override
    public String toString() {
        return "Person: " + this.name;
    }

    /* Getters/Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
