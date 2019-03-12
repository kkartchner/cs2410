package prev_assigns.hw2.p1_Polymorphism;

/**
 * Class definition for a Square.
 *
 * @author Ky Kartchner
 */
public class Square extends Shape {
    /* Constructors */
    Square() {
        this((int) (Math.random() * 19 + 2)); // Random side length between 2 and 20 inclusive
    }

    Square(double sideLength) {
        this.setSide(sideLength);
    }

    /* Overridden Methods */
    @Override
    public double getArea() {
        return Math.pow(getSide(), 2); // Area of a square is side length squared.
    }

    @Override
    public String toString() {
        return "A Square with sideLength of " + getSide();
    }
}
