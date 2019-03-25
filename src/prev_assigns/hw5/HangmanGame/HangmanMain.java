package prev_assigns.hw5.HangmanGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Main application for setting up the game.
 *
 * @author Ky Kartchner
 */
public class HangmanMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static Board board;
    private static Man man;

    @Override
    public void start(Stage primaryStage) {
        // Create main pane and specify its size:
        Pane mainPane = new Pane();
        mainPane.setPrefSize(480, 280);

        // Create Gallows and Man panes and add to main pane:
        Gallows gallows = new Gallows(0, 20);

        man = new Man(gallows.endOfRope());
        board = new Board(265, 80, man);

        mainPane.getChildren().addAll(gallows, man, board);

        // Primary stage setup:
        primaryStage.setTitle("Don't get strung up!");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static void showEndOfGameDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.NONE, message + "\nPlay again?", ButtonType.YES,
                ButtonType.NO);
        dialog.setTitle("Game Over");

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) { // Ensure alert hasn't exited yet
            if (result.get() == ButtonType.YES) {
                man.clear();
                board.loadNewPhrase();

            } else if (result.get() == ButtonType.NO) {
                System.exit(1);
            }
        }
    }
}
