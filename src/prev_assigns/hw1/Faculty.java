package prev_assigns.hw1;
/**
 * Class description of a Faculty member.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class Faculty extends Employee {
    /* Member Variables */
    private String officeHours;
    private String rank;

    /* Constructors */
    public Faculty(String name, MyDate dateHired, String rank) { // For basic with defaults
        this(new Employee(name, dateHired), "office hours TBD", rank);
    }

    public Faculty(Employee employee, String officeHours, String rank) { // For full argument assignment
        super(employee);

        this.officeHours = officeHours;
        this.rank = rank;
    }

    /* Overridden Methods */
    @Override
    public String toString() {
        return "Faculty: " + this.getName();
    }

    /* Getters/Setters */
    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
