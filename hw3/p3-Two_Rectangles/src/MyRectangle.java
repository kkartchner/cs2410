import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends Rectangle {
    MyRectangle(double width, double height, double x, double y, Paint color) {
        super(width, height, x, y);
        super.setFill(Color.TRANSPARENT);
        super.setStroke(color);
        super.setStrokeWidth(2);
    }

    public int pointsContainedOf(MyRectangle otherRect) {
        int count = 0;

        Point2D topLeftCorner = new Point2D(otherRect.getX(),
                otherRect.getY());

        return count;
    }
}
