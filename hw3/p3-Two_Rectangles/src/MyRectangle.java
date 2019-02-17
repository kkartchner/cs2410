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

    public int pointsContainedCount(MyRectangle otherRect) {
        int count = 0;

        Point2D topLeftCorner = new Point2D(otherRect.getX(),
                otherRect.getY());
        Point2D topRightCorner = new Point2D(otherRect.getX() + otherRect.getWidth(),
                otherRect.getY());
        Point2D bottomLeftCorner = new Point2D(otherRect.getX(),
                otherRect.getY() + otherRect.getHeight());
        Point2D bottomRightCorner = new Point2D(otherRect.getX() + otherRect.getWidth(),
                otherRect.getY() + otherRect.getHeight());

        Point2D[] points = new Point2D[4]{topLeftCorner, topRightCorner,
            bottomLeftCorner, bottomRightCorner};

        for (var point in points){
            if (otherRect.contains(point){
                ++count;
            }
        }


        return count;
    }
}
