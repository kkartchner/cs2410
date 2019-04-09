package prev_assigns.hw6.WheelOfFortune;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class for Wheel of Fortune Game. Defines the stage and main layout of the game.
 *
 * @author Ky Kartchner
 */
public class WheelOfFortuneMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static SimpleIntegerProperty currentScore;
    private static Board board;
    private static SpinWheel wheel;

    static final Font mainFont = new Font("Comic Sans", 20);

    @Override
    public void start(Stage primaryStage) {
        /* Create main pane and specify its size */
        GridPane mainPane = new GridPane();
        mainPane.setPrefSize(480, 280);
        mainPane.setAlignment(Pos.CENTER);

        /* Create and add score label */
        Label scoreTxt = new Label("Score: 0");
        scoreTxt.setStyle("-fx-font: 15 ComicSans;");
        currentScore = new SimpleIntegerProperty(0);
        currentScore.addListener((ov, oldVal, newVal) -> scoreTxt.setText("Score: " + newVal));

        mainPane.add(scoreTxt, 1, 0, 2, 1);
        GridPane.setHalignment(scoreTxt, HPos.CENTER);

        /* Create spin wheel and add to main pane */
        wheel = new SpinWheel(100);

        /* Initialize and add a board */
        board = new Board();
        board.disableGuessBox(true);

        board.setOnCorrect(() -> {
            currentScore.setValue(currentScore.get() + wheel.getMarkedScore());
            return 0; // throw away value
        });
        board.setOnIncorrect(() -> {
            currentScore.setValue(0);
            return 0;
        });

        board.setOnValidGuess(() -> {
            board.disableGuessBox(true);
            return 0;
        });

        mainPane.addRow(1, wheel, board);

        /* Add action button */
        ActionButton actionBtn = new ActionButton(wheel, board);
        mainPane.add(actionBtn, 0, 2, 2, 1);
        GridPane.setHalignment(actionBtn, HPos.CENTER);

        // Primary stage setup:
        primaryStage.setTitle("Wheel of Fortune!");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
