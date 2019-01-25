/**
 * Tests aspects of the various inherited classes including getters and setters, and the toString() overridden method
 * for each class.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class TestPerson {
    public static void main(String[] args) {

        // Test student class methods and members:
        Student student1 = new Student("Phil", "sophomore");
        student1.setAddress("123 N. Student Blvd.");
        System.out.println(student1.toString());
        System.out.printf("%s is a %s\n", student1.getName(), student1.getClassStatus());
        System.out.printf("%s's address is: %s\n\n", student1.getName(), student1.getAddress());

        // Test employee class methods and members:
        Employee employee1 = new Employee("Bert", new MyDate(2018, 3, 12));
        System.out.println(employee1.toString());
        System.out.printf("%s's hire date: %s\n", employee1.getName(), employee1.getDateHired().toString());
        System.out.printf("%s's salary is: $%,d\n\n", employee1.getName(), employee1.getSalary());

        // Test faculty class methods and members:
        Faculty faculty1 = new Faculty("Bernita", new MyDate(2011, 10, 22), "Professor");
        faculty1.setEmailAddress(faculty1.getName().toLowerCase() + "@email.com");
        System.out.println(faculty1.toString());
        System.out.printf("%s's email is: %s\n", faculty1.getName(), faculty1.getEmailAddress());
        System.out.printf("%s's rank is: %s\n\n", faculty1.getName(), faculty1.getRank());

        // Test staff class methods and members:
        Staff staff1 = new Staff("Jill", new MyDate(2016, 6, 14), "Lab instructor");
        staff1.setPhoneNumber("823-125-5748");
        System.out.println(staff1.toString());
        System.out.printf("%s's phone number is: %s\n", staff1.getName(), staff1.getPhoneNumber());
        System.out.printf("%s's title is: %s\n\n", staff1.getName(), staff1.getTitle());
    }
}
