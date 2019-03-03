import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Functions and variables for management of gameplay.
 *
 * @author Ky Kartchner
 */
public class Gameplay {
    public static GameGrid gameGrid;
    public static Circle floatingDisk;
    public static Label winText;

    public static Paint currentPlayerFill;
    public static boolean isGameOver;

    public static void initialize() {
        gameGrid = new GameGrid(6, 7);
        floatingDisk = new Circle(DisplaySettings.SLOT_DISK_RADIUS);
        winText = new Label();
        winText.setFont(DisplaySettings.WIN_FONT);
        winText.setTextFill(DisplaySettings.WIN_COLOR);
    }

    public static void startNewGame() {
        gameGrid.clearSlots();
        currentPlayerFill = DisplaySettings.DISK_FILL_RED;
        floatingDisk.setFill(currentPlayerFill);
        winText.setVisible(false);
        isGameOver = false;
    }

    public static void nextPlayerTurn() {
        if (currentPlayerFill == DisplaySettings.DISK_FILL_RED) {
            currentPlayerFill = DisplaySettings.DISK_FILL_YELLOW;
        } else {
            currentPlayerFill = DisplaySettings.DISK_FILL_RED;
        }
        floatingDisk.setFill(currentPlayerFill);
    }

    public static void weHaveAWinner() {
        floatingDisk.setFill(Color.TRANSPARENT);
        String playerName = (currentPlayerFill.equals(DisplaySettings.DISK_FILL_RED)) ? "Red" : "Yellow";
        winText.setText(playerName + " wins! Click screen to play again :)");
        winText.setVisible(true);
        isGameOver = true;
    }
}
