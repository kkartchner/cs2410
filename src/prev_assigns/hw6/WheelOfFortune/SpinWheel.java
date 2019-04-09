package prev_assigns.hw6.WheelOfFortune;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Combination of wheel and marker to create a SpinWheel.
 *
 * @author Ky Kartchner
 */
class SpinWheel extends Pane {
    private Wheel wheel;
    private Marker marker;

    SpinWheel(double radius) {
        wheel = new Wheel(radius);
        marker = new Marker(88, 16, 13);

        super.getChildren().addAll(wheel, marker);

    }

    void spin(EventHandler<ActionEvent> onFinished) {
        wheel.spin(onFinished);
    }

    int getMarkedScore() {
        return wheel.getMarkedPoints();
    }
}

/**
 * Atributes and functions for wheel marker.
 */
class Marker extends Polygon {
    Marker(double posX, double posY, double sideLength) {
        super(posX, posY,
                posX, posY - sideLength,
                posX + sideLength, posY);

        super.setStrokeWidth(2);
        super.setLayoutX(posX);
        super.setLayoutY(posY);
        super.setStyle("-fx-fill: orange; -fx-stroke: black");
    }
}

/**
 * Attributes and functions for wheel itself.
 */
class Wheel extends Pane {
    private Circle borderCircle;
    private Segment[] segments;
    private int markedPoints;

    /**
     * Construct a wheel of provided radius.
     *
     * @param radius Radius of wheel
     */
    Wheel(double radius) {
        borderCircle = new Circle(radius, Color.TRANSPARENT);
        borderCircle.setStroke(Color.BLACK);
        borderCircle.centerXProperty().bind(super.widthProperty()
                .divide(2));

        borderCircle.centerYProperty().bind(super.heightProperty()
                .divide(2));

        super.getChildren().add((borderCircle));

        int[] pointValues = new int[]{400, 600, 200, 800, 300, 700, 100, 500};
        generateSegments(8, pointValues);
    }

    /**
     * Generate the segment lines and points of the wheel.
     *
     * @param segNum      Number of segments to assign
     * @param pointValues The point values to use for the segments
     */
    private void generateSegments(int segNum, int[] pointValues) {
        segments = new Segment[segNum];
        for (int i = 0; i < segNum; ++i) {
            Text text = new Text(Integer.toString(pointValues[i]));
            text.setFont(Font.font("Calibri", 20));

            double numberRadius = borderCircle.getRadius() * .75;
            double theta = 2 * i * Math.PI / segNum;

            double offset = -Math.PI / 8;

            segments[i] = new Segment(theta, pointValues[i]);

            text.xProperty().bind(borderCircle.centerXProperty()
                    .add(numberRadius * Math.cos(theta + offset) - text.getLayoutBounds().getWidth() / 2));
            text.yProperty().bind(borderCircle.centerYProperty().add(numberRadius * Math.sin(theta + offset)));

            super.getChildren().add(text);

            /* Create line segments */
            Line sliceLine = new Line();
            sliceLine.startXProperty().bind(borderCircle.centerXProperty());
            sliceLine.startYProperty().bind(borderCircle.centerYProperty());

            sliceLine.endXProperty().bind(borderCircle.centerXProperty()
                    .add(borderCircle.getRadius() * Math.cos(theta)));
            sliceLine.endYProperty().bind(borderCircle.centerYProperty()
                    .add(borderCircle.getRadius() * Math.sin(theta)));

            super.getChildren().addAll(sliceLine);
        }
    }

    /**
     * Spin the wheel at least ones around.
     *
     * @param onFinished Action to perform once spin is complete.
     */
    void spin(EventHandler<ActionEvent> onFinished) {
        int randomIndex = (int) (Math.random() * segments.length);
        double randomToAngle = -Math.toDegrees(segments[randomIndex].getTheta());
        markedPoints = segments[randomIndex].getPointValue();

        RotateTransition initialRotation = new RotateTransition(new Duration(1000));
        RotateTransition randomRotation = new RotateTransition(new Duration(1000));

        initialRotation.setByAngle(-360.0); // Ensure at least one full spin
        initialRotation.setNode(this);

        randomRotation.setToAngle(randomToAngle);
        randomRotation.setNode(this);
        randomRotation.setOnFinished(onFinished);

        initialRotation.setOnFinished(e -> {
            super.setRotate(super.getRotate() + 360);
            randomRotation.playFromStart();
        });
        initialRotation.playFromStart();
    }

    /**
     * @return Get the points that the marker points at.
     */
    int getMarkedPoints() {
        return markedPoints;
    }
}


/**
 * Class for storing segment angles and pointValues.
 */
class Segment {
    private double theta;
    private int pointValue;

    Segment(double theta, int pointValue) {
        this.theta = theta;
        this.pointValue = pointValue;
    }

    double getTheta() {
        return theta;
    }

    int getPointValue() {
        return pointValue;
    }
}


