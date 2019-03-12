package prev_assigns.hw4.ConnectFourGame;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * @author Ky Kartchner
 */
public class GridSlot extends StackPane {
    private Boolean slotEmpty;
    private Circle circle;
    private int rowIndex;
    private int columnIndex;

    GridSlot(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        circle = new Circle(DisplaySettings.SLOT_DISK_RADIUS, DisplaySettings.BLANK_SLOT_COLOR); // Construct with Circle(double radius, Paint fill)
        slotEmpty = true;

        super.setPadding(new Insets(10));
        super.getChildren().add(circle);
    }

    public void addDisk(Paint color) {
        circle.setFill(color);
        slotEmpty = false;
    }

    public void removeDisk() {
        circle.setFill(DisplaySettings.BLANK_SLOT_COLOR);
        slotEmpty = true;
    }

    public void flash() {
        circle.setFill(Color.GREEN);
    }

    public Boolean isEmpty() {
        return this.slotEmpty;
    }

    public Paint diskFill() {
        return isEmpty() ? null : circle.getFill();
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
