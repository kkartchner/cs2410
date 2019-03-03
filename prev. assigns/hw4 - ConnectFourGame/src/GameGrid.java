import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Construction and definition of the game grid.
 *
 * @author Ky Kartchner
 */
public class GameGrid extends GridPane {
    private GridSlot[][] slots;

    /**
     * Construct a grid array of GridSlots of the specified row and column length.
     *
     * @param rowNum Number of rows the grid should have
     * @param colNum Number of columns the grid should have
     */
    public GameGrid(int rowNum, int colNum) {
        slots = new GridSlot[rowNum][colNum];
        for (int j = 0; j < rowNum; ++j) {
            for (int i = 0; i < colNum; ++i) {
                slots[j][i] = new GridSlot(j, i);
                GridSlot s = slots[j][i];

                s.setOnMouseEntered(e -> {
                    ConnectFourMain.cursorColumn = s.getColumnIndex();
                });

                super.add(s, i, j);
            }
        }
    }

    /**
     * Check for any complete sequence (four disks of the same fill next to each other) using the specified slot as the
     * starting point then simultaneosly iterating in each direction (up/down diagonals, left/right horizontal, and
     * vertical) to see if the placed disk makes four in a row in any of the discovered sequences.
     *
     * @param slot The slot to start the sequence check from
     * @return
     */
    public boolean hasSequence(GridSlot slot) {
        List<LinkedList<GridSlot>> sequences = new ArrayList<>();
        List<Boolean> bools = new ArrayList<>();
        for (int i = 0; i < 7; ++i) {
            bools.add(true);
        }
        for (int i = 0; i < 4; ++i) {
            sequences.add(new LinkedList<>(Arrays.asList(slot)));
        }

        int x = slot.getColumnIndex();
        int y = slot.getRowIndex();

        int sequenceLength = 4;
        for (int i = 1; i < sequenceLength; ++i) {
            // TODO: Proper checking and highlighting of sequences
            bools.set(0, addIfContinues(sequences.get(0), x + i, y, bools.get(0))); // Check to right
            bools.set(1, addIfContinues(sequences.get(0), x - i, y, bools.get(1))); // Check to left

            bools.set(2, addIfContinues(sequences.get(1), x - i, y - i, bools.get(2))); // Check up left diagonal
            bools.set(3, addIfContinues(sequences.get(1), x + i, y + i, bools.get(3))); // Check down left diagonal
            bools.set(4, addIfContinues(sequences.get(2), x + i, y - i, bools.get(4))); // Check up right diagonal
            bools.set(5, addIfContinues(sequences.get(2), x - i, y + i, bools.get(5))); // Check down right diagonal

            bools.set(6, addIfContinues(sequences.get(3), x, y + i, bools.get(6))); // Check down vertical
            // Break if all bools are false
        }

        boolean completeSequenceFound = false;
        for (LinkedList<GridSlot> sequence : sequences) {
            if (sequence.size() == sequenceLength) { // If a sequence is sequenceLength long
                for (GridSlot s : sequence) { // Flash the disks
                    s.flash();
                }
                completeSequenceFound = true;
            }
        }

        return completeSequenceFound;
    }

    /**
     * Adds slots[y][x] to the specified sequence if it is a valid slot and contains the same color disk
     * as the disk that was just placed, therefore continuing the current sequence.
     *
     * @param sequence The sequence to add to if valid
     * @param x        X location of the slot to check
     * @param y        Y location of the slot to check
     */
    private Boolean addIfContinues(LinkedList<GridSlot> sequence, int x, int y, Boolean keepSearching) {
        if (keepSearching) {
            boolean xInBounds = (0 <= x && x < slots[0].length);
            boolean yInBounds = (0 <= y && y < slots.length);

            if (xInBounds && yInBounds && slots[y][x].diskFill() == Gameplay.currentPlayerFill) {
                sequence.add(slots[y][x]);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a disk of the current player's color to the specified column if room is available, starting at the
     * bottom row and moving up until an open spot is found. If a disk is successfully added, hasSequence function is
     * called to check if the disk placed was a winning move. If not a win, advances to the next player's turn.
     *
     * @param col The column to add a disk to.
     */
    public void addDiskAndCheckWin(int col) {
        int bottomRow = super.getRowCount() - 1;
        for (int row = bottomRow; row >= 0; --row) {// Start at the bottom row and move up until an open spot is found
            GridSlot slot = slots[row][col];
            if (slot.isEmpty()) {
                slot.addDisk(Gameplay.currentPlayerFill);
                if (hasSequence(slot) == false) {
                    Gameplay.nextPlayerTurn();
                } else {
                    Gameplay.weHaveAWinner();
                }
                return;
            }
        }
    }

    /**
     * Set all slots back to the default slot color and remove the disk (set isEmpty to true)
     * by calling the removeDisk() function on each slot.
     */
    public void clearSlots() {
        for (GridSlot[] row : slots) {
            for (GridSlot slot : row) {
                slot.removeDisk();
            }
        }
    }
}


