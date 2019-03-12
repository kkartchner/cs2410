package prev_assigns.hw1;
/**
 * Class description for a Staff member.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class Staff extends Employee {
    /* Member Variables */
    private String title;

    /* Constructors */
    public Staff(String name, MyDate dateHired, String title) { // For basic with defaults
        this(new Employee(name, dateHired), title);
    }

    public Staff(Employee employee, String title) { // For full argument assignment
        super(employee);

        this.title = title;
    }

    /* Overridden Methods */
    @Override
    public String toString() {
        return "Staff: " + this.getName();
    }

    /* Getters/Setters */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
