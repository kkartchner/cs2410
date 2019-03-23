package hw5.HangmanGame;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.event.KeyEvent;

class Board extends VBox {
    private String phrase;
    private Text[] letterSlots;
    private HBox guessBox;

    public Board(String phrase, double layoutX, double layoutY) {
        this.phrase = phrase;
        createLetterSlots(phrase);

        FlowPane phraseBox = new FlowPane();
        phraseBox.getChildren().addAll(letterSlots);
        phraseBox.setHgap(3);

        createGuessBox();

        super.setLayoutX(layoutX);
        super.setLayoutY(layoutY);
        super.getChildren().addAll(phraseBox, guessBox);
    }

    private void createLetterSlots(String phrase) {
        letterSlots = new Text[phrase.length()];

        for (int i = 0; i < phrase.length(); ++i) {
            Text l = new Text();
            l.setFont(DisplaySettings.mainFont);

            if (phrase.charAt(i) == ' ') {
                l.setText(" ");
            } else {
                l.setText("_");
            }
            letterSlots[i] = l;
        }
    }

    private void createGuessBox() {
        guessBox = new HBox();

        Text text = new Text("Enter a letter: ");
        text.setFont(DisplaySettings.mainFont);

        TextField textField = new TextField();
        textField.setFont(DisplaySettings.mainFont);
        textField.textProperty().addListener(((ov, oldValue, newValue) -> {
            int indexOfLast = newValue.length() - 1;
            if (indexOfLast >= 0) {
                char val = newValue.charAt(indexOfLast);
                if (Character.isLetter(val)){
                    textField.setText(Character.toString(val).toUpperCase());
                } else {
                    textField.setText(oldValue);
                }
            }
        }));
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER){
                checkGuess(textField.getText().charAt(0));
            }
        } );

        Button button = new Button("Guess!");
        button.setFont(DisplaySettings.mainFont);
        button.setOnMouseClicked(e -> checkGuess(textField.getText().charAt(0)));

        guessBox.getChildren().addAll(text, textField, button);
    }

    public boolean checkGuess(Character letter) {
        boolean guessFound = false;
        String uppercasePhrase = phrase.toUpperCase();
        int occurrenceIndex = uppercasePhrase.indexOf(letter); // Get index of first occurrence of letter if it exists.
        while (occurrenceIndex >= 0) {
            letterSlots[occurrenceIndex].setText(Character.toString(phrase.charAt(occurrenceIndex)));
            guessFound = true;

            occurrenceIndex = uppercasePhrase.indexOf(letter, occurrenceIndex + 1);
        }

        System.out.printf("You guessed '%c'!\n", letter);
        return guessFound;
    }
}
