/**
 * Class definition for a Cube.
 *
 * @author Ky Kartchner
 */
public class Cube extends Shape {
    /* Constructors */
    public Cube() {
        this((int) (Math.random() * 19 + 2)); // Random side length between 2 and 20 inclusive
    }

    public Cube(double sideLength) {
        this.setSide(sideLength);
    }

    /* Overridden Methods */
    @Override
    public double getArea() {
        return 6.0 * Math.pow(getSide(), 2.0); // Surface area of a cube is 6 times the side length squared.
    }

    @Override
    public String areaType() {
        return "surface area";
    }

    @Override
    public String toString() {
        return "A Cube with sideLength of " + getSide();
    }
}
