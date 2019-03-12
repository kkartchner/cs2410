package prev_assigns.hw4.ConnectFourGame;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Ky Kartchner
 */
public class ConnectFourMain extends Application {
    private DoubleProperty cursorPositionX = new SimpleDoubleProperty();
    private DoubleProperty cursorPositionY = new SimpleDoubleProperty();

    public Pane mainPane = new Pane();
    public static int cursorColumn = 0;

    @Override
    public void start(Stage primaryStage) {
        String imageURL = "/blue_sky.jpg";
        mainPane.setStyle("-fx-background-image: url('" + imageURL + "');" +
                " -fx-background-position: center center;" +
                " -fx-background-size: stretch");

        Gameplay.initialize();

        Gameplay.winText.layoutXProperty().bind(mainPane.widthProperty().divide(2).subtract(100));
        Gameplay.winText.setLayoutY(100);

        mainPane.setOnMouseMoved(e -> { // Update stored cursor position whenever cursor is moved.
            cursorPositionX.setValue(e.getSceneX());
            cursorPositionY.setValue(e.getSceneY());
        });

        mainPane.setOnMouseClicked(e -> { // Add a disk to current column or start a new game if game is over
            if (!Gameplay.isGameOver) {
                Gameplay.gameGrid.addDiskAndCheckWin(cursorColumn);
            } else {
                Gameplay.startNewGame();
            }

        });
        /* Bind the "floating disk" to just below the cursor position */
        Gameplay.floatingDisk.centerXProperty().bind(cursorPositionX);
        Gameplay.floatingDisk.centerYProperty().bind(cursorPositionY.add(DisplaySettings.SLOT_DISK_RADIUS + 10));
        /*----------------- */
        mainPane.getChildren().addAll(Gameplay.gameGrid, Gameplay.floatingDisk, Gameplay.winText);

        Gameplay.startNewGame();

        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.getScene().setCursor(Cursor.NONE); // Hide cursor

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
