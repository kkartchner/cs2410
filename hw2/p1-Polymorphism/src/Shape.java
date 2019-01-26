/**
 * Class definition for Shape. Assigned as abstract so getArea() is forced to be implemented in derived classes.
 *
 * @author Ky Kartchner
 */
abstract class Shape {
    /* Member variables */
    private double side; // Store the length of each side

    /* Abstract Methods */
    public abstract double getArea(); // Children must implement this function

    /* Other Methods */
    public String areaType() { // Overridden in derived classes that are returning surface area.
        return "area";
    }

    /* Getters/Setters */
    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}
