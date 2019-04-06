package hw6.WheelOfFortune;

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


public class SpinWheel extends Pane {
    private Wheel wheel;
    private Marker marker;

    public SpinWheel(double radius) {
        wheel = new Wheel(radius);
        marker = new Marker(88, 16, 13);

        super.getChildren().addAll(wheel, marker);

    }

    public void spin(EventHandler<ActionEvent> onFinished) {
        wheel.spin(onFinished);
    }
}

class Marker extends Polygon {
    public Marker(double posX, double posY, double sideLength) {
        super(posX, posY,
                posX, posY - sideLength,
                posX + sideLength, posY);

        super.setStrokeWidth(2);
        super.setLayoutX(posX);
        super.setLayoutY(posY);
        super.setStyle("-fx-fill: orange; -fx-stroke: black");
    }
}

class Wheel extends Pane {
    private Circle borderCircle;
    private Segment[] segments;

    public Wheel(double radius) {
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

    private void generateSegments(int segNum, int[] pointValues) {
        segments = new Segment[segNum];
        for (int i = 0; i < segNum; ++i) {
            Text text = new Text(Integer.toString(pointValues[i]));
            text.setFont(Font.font("Calibri", 20));

            double numberRadius = borderCircle.getRadius() * .75;
            double theta = 2 * i * Math.PI / segNum;

            segments[i] = new Segment(theta, pointValues[i]);

            text.xProperty().bind(borderCircle.centerXProperty()
                    .add(numberRadius * Math.cos(theta) - text.getLayoutBounds().getWidth() / 2));
            text.yProperty().bind(borderCircle.centerYProperty().add(numberRadius * Math.sin(theta)));

            super.getChildren().add(text);

            Line sliceLine = new Line();
            sliceLine.startXProperty().bind(borderCircle.centerXProperty());
            sliceLine.startYProperty().bind(borderCircle.centerYProperty());

            sliceLine.endXProperty().bind(borderCircle.centerXProperty()
                    .add(borderCircle.getRadius() * Math.cos(theta)));
            sliceLine.endYProperty().bind(borderCircle.centerYProperty()
                    .add(borderCircle.getRadius() * Math.sin(theta)));

//            Label prizePoints = new Label(Integer.toString(points[i]), sliceLine);

            super.getChildren().addAll(sliceLine);

        }

        for (Segment s : segments) {
            System.out.println(s.toString());
        }
    }

    public void spin(EventHandler<ActionEvent> onFinished) {
        double randomSeconds = 3 + Math.random() * 10;

        RotateTransition rotateTransition = new RotateTransition(new Duration(randomSeconds * 1000));
        rotateTransition.setNode(this);

        rotateTransition.playFromStart();


        rotateTransition.setOnFinished(onFinished);
    }
}

class Segment {
    private double theta;
    private int pointValue;

    public Segment(double theta, int pointValue) {
        this.theta = theta;
        this.pointValue = pointValue;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "theta=" + theta +
                ", pointValue=" + pointValue +
                '}';
    }

    public double getTheta() {
        return theta;
    }

    public int getPointValue() {
        return pointValue;
    }
}


