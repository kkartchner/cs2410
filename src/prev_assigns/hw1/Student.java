package prev_assigns.hw1;
/**
 * Class description for a Student.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class Student extends Person {
    /* Member Variables */
    private String classStatus;

    /* Constructors */
    public Student(String name, String classStatus) { // For basic with defaults
        this(new Person(name), classStatus);
    }

    public Student(Person person, String classStatus) { // For full argument assignment
        super(person);

        this.classStatus = classStatus;
    }

    /* Overridden Methods */
    @Override
    public String toString() {
        return "Student: " + this.getName();
    }

    /* Getters/Setters */
    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }
}
