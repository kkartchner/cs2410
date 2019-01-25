/**
 * Class description of MyDate. Includes members for storing the year, month, and day of a specified date.
 *
 * @author Ky Kartchner
 * @version 1.0
 */
public class MyDate {
    /* Member variables */
    private int year;
    private int month;
    private int day;

    /* Constructors */
    public MyDate(int year, int month, int day) { // For specifying date to store
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public MyDate(MyDate other) { // For copying member values from another MyDate object
        this(other.getYear(), other.getMonth(), other.getDay());
    }

    /* Overridden Methods */
    @Override
    public String toString() { // Print the date in format mm/dd/yyyy (month is zero-based so add 1 for displaying)
        return String.format("%02d/%2d/%4d", month + 1, day, year);
    }

    /* Getters/Setters */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
