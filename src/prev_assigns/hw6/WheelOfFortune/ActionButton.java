package prev_assigns.hw6.WheelOfFortune;

import javafx.scene.control.Button;

/**
 * Class definition of an action button that either spins the wheel or submits a guess
 *
 * @author Ky Kartchner
 */
class ActionButton extends Button {
    private boolean guessing;

    ActionButton(SpinWheel wheel, Board board) {
        super("Spin");
        super.setFont(WheelOfFortuneMain.mainFont);
        guessing = false;


        super.setOnMouseClicked(e -> {
            if (guessing) {
                if (board.submitGuess()) {
                    super.setText("Spin");
                    guessing = false;
                }
            } else {
                super.setDisable(true);
                wheel.spin(e2 -> {
                    super.setText("Guess");
                    super.setDisable(false);
                    board.disableGuessBox(false);
                    guessing = true;
                });
            }
        });
    }
}
