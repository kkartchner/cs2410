package prev_assigns.hw2.p1_Polymorphism;

/**
 * Class definition for a Triangle.
 *
 * @author Ky Kartchner
 */
public class Triangle extends Shape {
    /* Constructors */
    Triangle() {
        this((int) (Math.random() * 19 + 2)); // Random side length between 2 and 20 inclusive
    }

    Triangle(double sideLength) {
        this.setSide(sideLength);
    }

    /* Overridden Methods */
    @Override
    public double getArea() {
        // Area of equilateral triangle is sqrt(3)*s^2)/4
        return (Math.sqrt(3.0) * Math.pow(getSide(), 2.0)) / 4.0;
    }

    @Override
    public String toString() {
        return "A Triangle with sideLength of " + getSide();
    }
}
