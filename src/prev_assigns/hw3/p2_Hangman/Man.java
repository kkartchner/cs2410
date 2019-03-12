package prev_assigns.hw3.p2_Hangman;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Class definition for the hangman Man. Each limb is its own function allowing for flexibility later on for drawing
 * only part of the man each time a letter is missed when actual game play is implemented.
 *
 * @author Ky Kartchner
 */
class Man extends Pane {
    /* Attributes of the man: */
    private final double HEAD_RADIUS = 20;
    private final double ARM_LENGTH = 50;
    private final double TORSO_LENGTH = 65;
    private final double LEG_LENGTH = 50;
    private final double LINE_THICKNESS = 1;
    /* Variables for storing key starting points: */
    private double baseOfHeadY;
    private double hipsY;
    private Point2D endOfRope;

    /**
     * Calls each body part function to construct a man panel object who is "hanging" from the rope at the specified
     * location. Later on, once game play is incorporated, body part functions will be called separately.
     *
     * @param endOfRope The location of the rope-end to hang on.
     */
    Man(Point2D endOfRope) {
        this.endOfRope = endOfRope;
        drawHead();
        drawLeftArm();
        drawRightArm();
        drawTorso();
        drawLeftLeg();
        drawRightLeg();
    }

    /**
     * Function for creating a Circle for the man's head.
     */
    private void drawHead() {
        Circle head = new Circle(endOfRope.getX(), endOfRope.getY() + HEAD_RADIUS,
                HEAD_RADIUS, Color.TRANSPARENT); // (centerX, centerY, radius, fill)
        head.setStroke(Color.BLACK);
        head.setStrokeWidth(1);

        baseOfHeadY = endOfRope.getY() + 2 * HEAD_RADIUS;
        super.getChildren().add(head);
    }

    /**
     * Function for creating a Line shape for the left arm.
     */
    private void drawLeftArm() {
        Line lArm = new Line(endOfRope.getX(), baseOfHeadY,
                endOfRope.getX() - ARM_LENGTH, baseOfHeadY + ARM_LENGTH); // (startX, startY, endX, endY)
        lArm.setStroke(Color.BLACK);
        lArm.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(lArm);
    }
    /**
     * Function for creating a Line shape for the right arm.
     */
    private void drawRightArm() {
        Line rArm = new Line(endOfRope.getX(), baseOfHeadY,
                endOfRope.getX() + ARM_LENGTH, baseOfHeadY + ARM_LENGTH); // (startX, startY, endX, endY)
        rArm.setStroke(Color.BLACK);
        rArm.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(rArm);
    }

    /**
     * Function for creating a Line shape for the torso.
     */
    private void drawTorso() {
        hipsY = baseOfHeadY + TORSO_LENGTH;
        Line torso = new Line(endOfRope.getX(), baseOfHeadY,
                endOfRope.getX(), hipsY); // (startX, startY, endX, endY)
        torso.setStroke(Color.BLACK);
        torso.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(torso);

    }


    /**
     * Function for creating a Line shape for the left leg.
     */
    private void drawLeftLeg() {
        Line lLeg = new Line(endOfRope.getX(), hipsY,
                endOfRope.getX() - LEG_LENGTH, hipsY + LEG_LENGTH); // (startX, startY, endX, endY)
        lLeg.setStroke(Color.BLACK);
        lLeg.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(lLeg);
    }

    /**
     * Function for creating a Line shape for the right leg.
     */
    private void drawRightLeg() {
        Line rLeg = new Line(endOfRope.getX(), hipsY,
                endOfRope.getX() + LEG_LENGTH, hipsY + LEG_LENGTH); // (startX, startY, endX, endY)
        rLeg.setStroke(Color.BLACK);
        rLeg.setStrokeWidth(LINE_THICKNESS);

        super.getChildren().add(rLeg);
    }
}
