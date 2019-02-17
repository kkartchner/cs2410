/**
 * Class definition for a Cube. Inherits from Square.
 *
 * @author Ky Kartchner
 */
public class Cube extends Square {
    /* Constructors */
    Cube() {
        this((int) (Math.random() * 19 + 2)); // Random side length between 2 and 20 inclusive
    }

    private Cube(double sideLength) {
        super(sideLength); // Set super.side to sideLength by calling super assignment constructor.
    }

    /* Overridden Methods */
    @Override
    public double getArea() {
        return 6.0 * super.getArea(); // Surface area of a cube is 6 times the side length squared.
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
