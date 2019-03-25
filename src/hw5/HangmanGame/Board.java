package hw5.HangmanGame;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.Math;
import java.nio.file.Path;
import java.nio.file.Files;
import javafx.scene.text.Font;

/**
 * Class definition of the board that contains the phrase and the guessbox.
 *
 * @author Ky Kartchner
 */
class Board extends VBox {
    private ArrayList<String> phrases;
    private String phrase;
    private Text correctnessText;
    private Text[] letterSlots;
    private final Font mainFont;

    private Man man;
    /**
     * Construct a board at the specified layout position.
     *
     * @param layoutX The value for LayoutX
     * @param layoutY The value for LayoutY
     * @param man A reference to the man being hanged
     */
    public Board(double layoutX, double layoutY, Man man) {
        mainFont = new Font("Comic Sans", 20);

        super.setLayoutX(layoutX);
        super.setLayoutY(layoutY);
        super.setStyle("-fx-alignment: center; -fx-pref-width: 200;" +
                "-fx-pref-height: 150; -fx-border-color: black;");

        this.man = man;
        
        loadPhrases();
        phrase = getRandomPhrase();

        createLetterSlots(phrase);

        FlowPane phraseBox = new FlowPane();
        phraseBox.setStyle("-fx-alignment: center; -fx-hgap: 3.0; -fx-padding: 0 0 25 0;");
        
        phraseBox.getChildren().addAll(letterSlots);

        super.getChildren().add(phraseBox);
        
        correctnessText = new Text();

        super.getChildren().add(correctnessText);

        createGuessBox();

    }

    /**
     * Load the phrases from phrases text file.
     */
    private void loadPhrases(){
        try {
            phrases = new ArrayList<>(Files.readAllLines(Path.of("hw5", "HangmanGame", "phrases.txt")));

        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    /**
     * Return a random phrase from the collection of phrases.
     */
    private String getRandomPhrase(){
        int randomIndex = (int)(Math.random()*phrases.size());

        return phrases.get(randomIndex);
    }

    /**
     * Create the slots for phrase letters to be stored in. Default non-blank characters to a slot with an underscore ("_") as the text. Insert spaces as spaces.
     */
    private void createLetterSlots(String phrase) {
        letterSlots = new Text[phrase.length()];

        for (int i = 0; i < phrase.length(); ++i) {
            Text l = new Text();
            l.setFont(mainFont);

            if (phrase.charAt(i) == ' ') {
                l.setText(" ");
            } else {
                l.setText("_");
            }
            letterSlots[i] = l;
        }
    }
/**
 * Create the area of the board for guessing a letter. Includes a textbox for entering a guess, a label next to it, and a button for submitting.
 */
    private void createGuessBox() {
        HBox guessBox = new HBox();
        guessBox.setAlignment(Pos.CENTER);

        Text text = new Text("Enter a letter: ");
        text.setFont(mainFont);

        /* Add textField */
        TextField textField = new TextField();
        textField.setPrefColumnCount(1);
        textField.setMaxWidth(40);
        textField.setFont(mainFont);

        constrainTextField(textField);

        textField.setOnKeyPressed(e -> { // Allow for submitting a guess by hitting the enter key.
            if (e.getCode() == KeyCode.ENTER){
                submitGuessFrom(textField);
            }
        } );

        guessBox.getChildren().addAll(text, textField);

        /* Add button */
        Button button = new Button("Guess");
        button.setFont(mainFont);
        button.setOnMouseClicked(e -> submitGuessFrom(textField)); 

        super.getChildren().addAll(guessBox, button);

    }

    /**
     * Add a listener to the specified TextField to restrict it to a max of one letter-only character.
     *
     * @param textField The TextField to constrain
     */
    private void constrainTextField(TextField textField){
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
    }

    /**
     * Submit the letter currently in the specified TextField if there is one. Otherwise prompt
     * the user to enter one.
     *
     * @param textField The textfield to check
     */
    private void submitGuessFrom(TextField textField){
        if (textField.getText().length() > 0){
            guessLetter(textField.getText().charAt(0));
        } else {
            // Display "enter a letter to guess" toast
        }
    }

    /**
     * Checks if the specified letter is contained in the phrase. If so, appropriate empty letter slots are filled with the letter.
     */
    public void guessLetter(Character letter) {
        String uppercasePhrase = phrase.toUpperCase();
        boolean guessFound = false;

        int occurrenceIndex = uppercasePhrase.indexOf(letter); // Get index of first occurrence of letter if it exists.
        while (occurrenceIndex >= 0) { 
            letterSlots[occurrenceIndex].setText(Character.toString(phrase.charAt(occurrenceIndex)));
            guessFound = true;

            occurrenceIndex = uppercasePhrase.indexOf(letter, occurrenceIndex + 1);
        }

        if (guessFound){
            correctnessText.setText("Correct!");
            correctnessText.setStyle("-fx-font: 18 Calibri; -fx-fill: green; -fx-rotate: 15");
            
            // If end of game:
            HangmanMain.showEndOfGameDialog("You have shown mercy to the rival! You're a true winner!");
        } else {
            correctnessText.setText("Incorrect");
            correctnessText.setStyle("-fx-font: 18 Calibri; -fx-fill: red; -fx-rotate: -15");
            this.man.drawNextBodyPart();
        }
    }
}
