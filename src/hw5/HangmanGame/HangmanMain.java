package hw5.HangmanGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main application to demonstrate a Man hanging from a Gallows pane object, to be used for the game "Hangman".
 *
 * @author Ky Kartchner
 */
public class HangmanMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create main pane and specify its size:
        Pane mainPane = new Pane();
        mainPane.setPrefSize(500, 500);

        // Create Gallows and Man panes and add to main pane:
        Gallows gallows = new Gallows(0, 20);
        Man man = new Man(gallows.endOfRope());

        Board board = new Board("Hello there", 100, 200);

        mainPane.getChildren().addAll(gallows, man, board);

        mainPane.setOnMouseClicked(e -> man.drawNextBodyPart());

        // Primary stage setup:
        primaryStage.setTitle("Exercise14_17 - Ky Kartchner");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }
}
