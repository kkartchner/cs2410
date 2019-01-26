import java.util.ArrayList;

/**
 * Test program for demonstrating polymorphism using the various Shape derived classes. The getArea() method is called
 * on each shape in the shapes list. The abstractness of the Shape base class and base getArea() method leaves no room
 * for doubt that the shapes are using their individual derived getArea() method.
 *
 * @author Ky Kartchner
 */
public class TestShape {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        // Add two of each shape to the shapes list using the random side length constructor:
        shapes.add(new Square());
        shapes.add(new Square());

        shapes.add(new Cube());
        shapes.add(new Cube());

        shapes.add(new Triangle());
        shapes.add(new Triangle());

        shapes.add(new Pyramid());
        shapes.add(new Pyramid());

        for (Shape s : shapes){
            System.out.println(s.toString() + " has " + s.areaType() + " of " + s.getArea());
        }
    }
}
