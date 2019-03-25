package hw5.HangmanGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main application for setting up the game.
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
        mainPane.setPrefSize(480, 280);

        // Create Gallows and Man panes and add to main pane:
        Gallows gallows = new Gallows(0, 20);
        Man man = new Man(gallows.endOfRope());

        Board board = new Board(265, 80, man);

        mainPane.getChildren().addAll(gallows, man, board);

        // Primary stage setup:
        primaryStage.setTitle("Don't get strung up!");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void showEndOfGameDialog(String message){
        System.out.println(message);
    }
}
