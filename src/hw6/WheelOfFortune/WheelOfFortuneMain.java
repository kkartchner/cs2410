package hw6.WheelOfFortune;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        // Create main pane and specify its size:
        GridPane mainPane = new GridPane();
        mainPane.setPrefSize(480, 280);
        mainPane.setAlignment(Pos.CENTER);

        Label scoreTxt = new Label("Score: 0");
        scoreTxt.setStyle("-fx-font: 15 ComicSans;");
        currentScore = new SimpleIntegerProperty(0);
        currentScore.addListener(change -> scoreTxt.setText("Score: " + change.toString()));

        mainPane.add(scoreTxt, 1, 0, 2, 1);
        GridPane.setHalignment(scoreTxt, HPos.CENTER);

        // Create spin wheel and add to main pane:
        wheel = new SpinWheel(100);

        board = new Board();
        mainPane.addRow(1, wheel, board);

        ActionButton actionBtn = new ActionButton(wheel, board);

        mainPane.add(actionBtn, 0, 2, 2, 1);
        GridPane.setHalignment(actionBtn, HPos.CENTER);

        // Primary stage setup:
        primaryStage.setTitle("Wheel of Fortune!");
        primaryStage.setScene(new Scene(mainPane));
//        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

class ActionButton extends Button {
    private boolean guessing;

    public ActionButton(SpinWheel wheel, Board board) {
        super("Spin");
        super.setFont(WheelOfFortuneMain.mainFont);
        guessing = false;


        super.setOnMouseClicked(e -> {
            if (guessing) {
                if (board.submitGuess() == true) {
                    super.setText("Spin");
                    guessing = false;
                }
            } else {
                super.setDisable(true);
                wheel.spin(e2 -> {
                    super.setText("Guess");
                    super.setDisable(false);
                    guessing = true;
                });
            }
        });
    }
}
