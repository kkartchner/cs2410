import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Extension of the Rectangle class that adds needed helper functions for checking how many points of another
 * rectangle's that this one contains, easily assigning attributes, and creating new Nodes with specific defaults.
 *
 * @author Ky Kartchner
 */
class MyRectangle extends Rectangle {
    /**
     * Constructs a new MyRectangle object with the specified stroke color.
     *
     * @param color The color to assign to the edges of the rectangle.
     */
    MyRectangle(Paint color) {
        super.setFill(Color.TRANSPARENT);
        super.setStroke(color);
        super.setStrokeWidth(2);
    }

    /**
     * Sets the width, height, x, and y attributes to the specified values.
     *
     * @param attributes Values to assign to the attributes
     */
    void setAttributes(double... attributes) {
        super.setWidth(attributes[2]);
        super.setHeight(attributes[3]);
        /* The coordinates received are center coordinates, so half of the width or height is subtracted below to
        properly offset the rectangle's location to account for this*/
        super.setX(attributes[0] - (super.getWidth() / 2.0));
        super.setY(attributes[1] - (super.getHeight() / 2.0));
    }


    /**
     * Count the number of otherRect's corners that are contained within this rectangle. This is a helper
     * function that is used in RectanglesMain to determine (based on the number of corners each rectangle contains)
     * the relationship between the two rectangles.
     *
     * @param otherRect The rectangle whose corners are being checked.
     * @return Returns the number of otherRect's corners that are contained in this rectangle
     */
    int pointsContainedCount(MyRectangle otherRect) {
        int count = 0;

        /* Define corner points */
        Point2D topLeftCorner = new Point2D(otherRect.getX(),
                otherRect.getY());
        Point2D topRightCorner = new Point2D(otherRect.getX() + otherRect.getWidth(),
                otherRect.getY());
        Point2D bottomLeftCorner = new Point2D(otherRect.getX(),
                otherRect.getY() + otherRect.getHeight());
        Point2D bottomRightCorner = new Point2D(otherRect.getX() + otherRect.getWidth(),
                otherRect.getY() + otherRect.getHeight());

        /* Add points to array for easier comparison. For each corner this rectangle contains, increment the count*/
        Point2D[] points = {topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner};

        for (var point : points) {
            if (super.contains(point)) {
                ++count;
            }
        }

        return count;
    }

    /**
     * Helper for generating TextFields with specified and stated default values.
     *
     * @param text The default text of the TextField
     * @return Returns a newly created TextField
     */
    static TextField newTextField(String text) {
        TextField textField = new TextField();
        textField.setPrefWidth(35);
        textField.setText(text);

        return textField;
    }

    /**
     * Helper for creating a new Label with the specified text and font color.
     *
     * @param text  The text to assign
     * @param color The font color to assign
     * @return Returns a newly created Label object.
     */
    static Label newLabel(String text, Paint color) {
        Label label = new Label(text);
        label.setTextFill(color);

        return label;
    }
}
