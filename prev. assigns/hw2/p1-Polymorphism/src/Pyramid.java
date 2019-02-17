/**
 * Class definition for equilateral triangular pyramid (tetrahedron).
 *
 * @author Ky Kartchner
 */
public class Pyramid extends Triangle {
    /* Constructors */
    Pyramid() {
        this((int) (Math.random() * 19 + 2)); // Random side length between 2 and 20 inclusive
    }

    private Pyramid(double sideLength) {
        super(sideLength); // Set super.side to sideLength by calling super assignment constructor.
    }

    /* Overridden Methods */
    @Override
    public double getArea() {
        return 4.0 * super.getArea(); // Surface area of tetrahedron is 4 times the area of one triangle.
    }

    @Override
    public String areaType() {
        return "surface area";
    }

    @Override
    public String toString() {
        return "A Pyramid with sideLength of " + getSide();
    }
}
