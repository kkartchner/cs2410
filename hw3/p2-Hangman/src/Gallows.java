import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;

/**
 * Class definition for the Gallows pane. Individual functions are called for each piece in order to represent the full
 * gallows. A return function is given for obtaining the precise location of the end of the rope so a Man pane object
 * can be assigned hang on this gallows.
 *
 * @author Ky Kartchner
 */
class Gallows extends Pane {
    /* Gallows Attributes */
    private final double V_BEAM_LENGTH = 225;
    private final double H_BEAM_LENGTH = 100;
    private final double ROPE_LENGTH = 25;
    private final double BASE_WIDTH = 50;
    private final double BASE_HEIGHT = 25;
    private final double LINE_THICKNESS = 2;

    /* Store key locations */
    private double vBeamStartX;
    private double vBeamStartY;
    private Point2D localEndOfRope;


    /**
     * Called the individual functions for each part of the gallows to construct a gallows
     * with the given offset of the panel location.
     *
     * @param xOffset Horizontal offset of panel
     * @param yOffset Vertical offset of panel
     */
    Gallows(double xOffset, double yOffset) {
        super.setHeight(V_BEAM_LENGTH);
        super.setLayoutX(xOffset);
        super.setLayoutY(yOffset);

        drawBase();
        drawVerticalBeam();
        drawHorizontalBeam();
        drawRope();
    }

    /**
     * Function for creating a curve shape object to represent the base of the gallows.
     */
    private void drawBase() {
        Arc base = new Arc(BASE_WIDTH + 20, super.getHeight() + BASE_HEIGHT,
                BASE_WIDTH, BASE_HEIGHT, 10.0, 160.0); // (centerX, centerY, radiusX, radiusY, startAngle, length)
        base.setType(ArcType.OPEN);
        base.setFill(Color.TRANSPARENT);
        base.setStroke(Color.BLACK);
        base.setStrokeWidth(LINE_THICKNESS);
        super.getChildren().add(base);

        vBeamStartX = base.getCenterX();
        vBeamStartY = base.getCenterY() - base.getRadiusY();
    }

    /**
     * Function for creating a line shape object for drawing the vertical beam of the gallows.
     */
    private void drawVerticalBeam() {
        Line vBeam = new Line(vBeamStartX, vBeamStartY,
                vBeamStartX, vBeamStartY - V_BEAM_LENGTH); // (startX, startY, endX, endY)
        vBeam.setStroke(Color.BLACK);
        vBeam.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(vBeam);
    }

    /**
     * Function for creating a line shape object for drawing the horizontal beam of the gallows.
     */
    private void drawHorizontalBeam() {
        double startY = vBeamStartY - V_BEAM_LENGTH;
        Line hBeam = new Line(vBeamStartX, startY,
                vBeamStartX + H_BEAM_LENGTH, startY); //(startX, startY, endX, endY
        hBeam.setStroke(Color.BLACK);
        hBeam.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(hBeam);
    }

    /**
     * Function for creating a line shape object for drawing the rope.
     */
    private void drawRope() {
        localEndOfRope = new Point2D(vBeamStartX + H_BEAM_LENGTH,
                vBeamStartY - V_BEAM_LENGTH + ROPE_LENGTH);
        Line rope = new Line(localEndOfRope.getX(), localEndOfRope.getY() - ROPE_LENGTH,
                localEndOfRope.getX(), localEndOfRope.getY()); // (startX, startY, endX, endY)
        rope.setStroke(Color.BLACK);
        rope.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(rope);
    }

    /**
     * Returns the end of the rope taking into account any offset of the panel.
     *
     * @return localEndOfRope + panel offset
     */
    Point2D endOfRope() {
        return localEndOfRope.add(new Point2D(super.getLayoutX(), super.getLayoutY()));
    }
}
