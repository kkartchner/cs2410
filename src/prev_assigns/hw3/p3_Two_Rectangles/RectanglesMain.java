package prev_assigns.hw3.p3_Two_Rectangles;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Prompts the user to enter the x and y coordinates, width, and the height of two rectangles. Upon the okay button
 * being clicked, the rectangles are drawn in a new window and their relationship is stated. Either one rectangle
 * contains the other, the two rectangles overlap, or they do not touch at all.
 *
 * @author Ky Kartchner
 */
public class RectanglesMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main window of the program. Draws the rectangles with the given dimensions and coordinates and states
     * and a label stating relationship.
     *
     * @param primaryStage The primaryStage being used.
     */
    @Override
    public void start(Stage primaryStage) {
        final double SCREEN_WIDTH = 400;
        final double SCREEN_HEIGHT = 300;

        /* Define the main layout pane */
        Pane mainPane = new Pane();
        mainPane.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        /* Create two rectangle objects, and prompt for rectangle attributes */
        MyRectangle rect1 = new MyRectangle(Color.RED);
        MyRectangle rect2 = new MyRectangle(Color.BLUE);

        setRectangleAttributes(rect1, rect2);

        /* Add the rectangles in to the grid if it contains valid attributes */
        mainPane.getChildren().add(rect1);
        mainPane.getChildren().add(rect2);

        /* Create the message label and add to bottom center of pane */
        Label message = new Label(relationshipOf(rect1, rect2));
        message.setLayoutX(50);
        message.setLayoutY(SCREEN_HEIGHT - 50);
        message.setFont(new Font(15));

        mainPane.getChildren().add(message);

        /* Set title and scene then show the primaryStage */
        primaryStage.setTitle("Exercise 14_23 - Ky Kartchner");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }

    /**
     * Displays a prompt for entering the dimensions and locations of two rectangles.
     *
     * @param rect1 The first rectangle to declare.
     * @param rect2 The second rectangle to declare.
     */
    private void setRectangleAttributes(MyRectangle rect1, MyRectangle rect2) {
        /* Create a GridPane */
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setVgap(2);

        /* Initialize title label and add to gridPane */
        Label title = new Label("Define Rectangles");
        title.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
        gridPane.add(title, 0, 0, 5, 1);

        /* Add attribute header */
        gridPane.addRow(1, new Label(), new Label("x"), new Label("y"),
                new Label("width"), new Label("height")
        );
        /* Initialize attribute TextField arrays */
        TextField[] rect1AttributeFields = new TextField[4];
        TextField[] rect2AttributeFields = new TextField[4];

        for (int i = 0; i < 4; ++i) {
            rect1AttributeFields[i] = MyRectangle.newTextField("50");
            rect2AttributeFields[i] = MyRectangle.newTextField("100");
        }

        /* Add attribute fields to gridPane */
        gridPane.add(MyRectangle.newLabel("rect1: ", Color.RED), 0, 2); // rect1 row of attributes
        gridPane.addRow(2, rect1AttributeFields);

        gridPane.add(MyRectangle.newLabel("rect2: ", Color.BLUE), 0, 3); // rect2 row of attributes
        gridPane.addRow(3, rect2AttributeFields);

        /* Initialize stage */
        Stage inputDialog = new Stage();
        inputDialog.setTitle("Define Rectangles");

        /* Initialize and add okay button */
        Button okBtn = new Button("Ok");
        okBtn.setOnMouseClicked(e -> { // Set okay button's onClick to apply specified attributes to the rectangles.
            double[] attributes1 = new double[4];
            double[] attributes2 = new double[4];

            for (int i = 0; i < 4; ++i) { // Parse the text in the TextFields and store into double arrays
                attributes1[i] = Double.parseDouble(rect1AttributeFields[i].getText());
                attributes2[i] = Double.parseDouble(rect2AttributeFields[i].getText());
            }

            rect1.setAttributes(attributes1); // Apply specified attributes to rect1 and rect2:
            rect2.setAttributes(attributes2);

            inputDialog.hide(); // Hide the dialog

        });
        gridPane.add(okBtn, 0, 4, 5, 1);

        /* Center all nodes in the grid horizontally in their respective cell */
        for (int i = 0; i < gridPane.getChildren().size(); ++i) {
            GridPane.setHalignment(gridPane.getChildren().get(i), HPos.CENTER);
        }

        /* Set scene, show stage, and wait for user to click okay or close */
        inputDialog.setScene(new Scene(gridPane));
        inputDialog.showAndWait();
    }

    /**
     * Determines the relationship between the two specified rectangles, namely if one is contained in
     * the other or whether or not they overlap.
     *
     * @param rect1 The first rectangle for comparing.
     * @param rect2 The second rectangle for comparing.
     * @return Returns a String containing a description of the relationship
     */
    private String relationshipOf(MyRectangle rect1, MyRectangle rect2) {
        /* Store the number of corners each rectangle contains of the other's */
        int count1 = rect1.pointsContainedCount(rect2);
        int count2 = rect2.pointsContainedCount(rect1);

        String relationship;
        if (count1 > 0 || count2 > 0) { // One rectangle contains at least one corner of the other's
            if (count1 == 4 || count2 == 4) { // One contains all 4 corners of the other's
                relationship = "One rectangle is contained in another";
            } else { // They both contain a corner or 2
                relationship = "The rectangles overlap";
            }
        } else { // Neither rectangle contain corners of the other's
            relationship = "The rectangles do not overlap";
        }

        return relationship;
    }
}
