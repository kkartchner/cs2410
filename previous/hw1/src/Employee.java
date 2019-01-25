/**
 * Class description for an Employee.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class Employee extends Person {
    /* Member Variables */
    private String office;
    private int salary;
    private MyDate dateHired;

    /* Constructors */
    public Employee(String name, MyDate dateHired) { // For basic with defaults
        this(new Person(name), "office TBD", 10_000, dateHired);
    }

    public Employee(Person person, String office, int salary, MyDate dateHired) { // For full argument assignment
        super(person);

        this.office = office;
        this.salary = salary;
        this.dateHired = new MyDate(dateHired);
    }

    public Employee(Employee other) { // For copying members from another Employee object
        this(new Person(other), other.getOffice(), other.getSalary(), new MyDate(other.getDateHired()));
    }

    /* Overridden Methods */
    @Override
    public String toString() {
        return "Employee: " + this.getName();
    }

    /* Getters/Setters */
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public MyDate getDateHired() {
        return dateHired;
    }
}
