package prev_assigns.hw6.WheelOfFortune;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

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
    private TextField textField;
    private Set<Character> unguessedLetters;
    private ComboBox<String> comboBox;

    private Supplier<Integer> onCorrect;
    private Supplier<Integer> onIncorrect;
    private Supplier<Integer> onValidGuess;

    /**
     * Construct a default board.
     */
    public Board() {
        super.setStyle("-fx-alignment: center; -fx-pref-width: 200;" +
                "-fx-pref-height: 150;");

        loadPhrases();
        loadNewPhrase();
    }

    /**
     * Load the phrases from phrases text file.
     */
    private void loadPhrases() {
        unguessedLetters = new HashSet<>();
        try {
            phrases = new ArrayList<>(Files.readAllLines(
                    Path.of(this.getClass().getResource("phrases.txt").toURI())));
        } catch (Exception ex) {
            System.out.println("Error in function loadPhrases: " + ex);
        }
    }

    /**
     * Load a new random phrase into the letter slots
     */
    private void loadNewPhrase() {
        super.getChildren().clear();
        phrase = getRandomPhrase();
        unguessedLetters.clear();
        for (char letter : phrase.toCharArray()) {
            if (letter != ' ')
                unguessedLetters.add(Character.toUpperCase(letter));
        }

        createLetterSlots(phrase);

        FlowPane phraseBox = new FlowPane(); // ToDo: Make it so no letters from individual words remain on the same line
        phraseBox.setStyle("-fx-alignment: center; -fx-hgap: 3.0; -fx-padding: 0 0 25 0;");

        phraseBox.getChildren().addAll(letterSlots);

        super.getChildren().add(phraseBox);

        correctnessText = new Text();

        super.getChildren().add(correctnessText);

        createComboBox();
        createGuessBox();
    }

    /**
     * Return a random phrase from the collection of phrases.
     */
    private String getRandomPhrase() {
        int randomIndex = (int) (Math.random() * phrases.size());

        /* Ensure that the phrase just used is not used again */
        if (phrase != null && phrase.equals(phrases.get(randomIndex))) { // If the same phrase was picked
            return getRandomPhrase();                 //    Pick another
        } else {
            return phrases.get(randomIndex);          // Else return the randomly selected one
        }
    }

    /**
     * Create the slots for phrase letters to be stored in. Default non-blank characters to
     * a slot with an underscore ("_") as the text. Insert spaces as a slot with a space.
     *
     * @param phrase The phrase to populate the board with.
     */
    private void createLetterSlots(String phrase) {
        letterSlots = new Text[phrase.length()];

        for (int i = 0; i < phrase.length(); ++i) {
            Text l = new Text();
            l.setFont(WheelOfFortuneMain.mainFont);

            if (phrase.charAt(i) == ' ') {
                l.setText(" ");
            } else {
                l.setText("_");
            }
            letterSlots[i] = l;
        }
    }

    /**
     * Create the area of the board for guessing a letter. Includes a textbox for entering a guess,
     * a label next to it, and a button for submitting.
     */
    private void createGuessBox() {
        HBox guessBox = new HBox();
        guessBox.setAlignment(Pos.CENTER);

        Text text = new Text("Enter a letter: ");
        text.setFont(WheelOfFortuneMain.mainFont);

        /* Add textField */
        textField = new TextField();
        textField.setPrefColumnCount(1);
        textField.setMaxWidth(45);
        textField.setFont(WheelOfFortuneMain.mainFont);

        constrainTextField(textField);

//        textField.setOnKeyPressed(e -> { // Allow for submitting a guess by hitting the enter key.
//            if (e.getCode() == KeyCode.ENTER) {
//                submitGuess();
//            }
//        });

        guessBox.getChildren().addAll(text, textField);

        super.getChildren().addAll(guessBox);

    }

    /**
     * Create the combo box that is displayed at the end of a game.
     */
    private void createComboBox() {
        comboBox = new ComboBox<>(FXCollections.observableArrayList("Reset Board"));
        comboBox.setVisible(false);
        super.getChildren().add(comboBox);

        comboBox.valueProperty().addListener((ov, oldVal, newVal) -> {
            if (newVal.equals("Reset Board")) {
                this.loadNewPhrase();
                comboBox.setVisible(false);
                WheelOfFortuneMain.currentScore.setValue(0);
            }
        });
    }

    /**
     * Add a listener to the specified TextField to restrict it to a max of one letter-only character.
     *
     * @param textField The TextField to constrain
     */
    private void constrainTextField(TextField textField) {
        textField.textProperty().addListener(((ov, oldValue, newValue) -> {
            int indexOfLast = newValue.length() - 1;
            if (indexOfLast >= 0) {
                char val = newValue.charAt(indexOfLast);
                if (Character.isLetter(val)) {
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
     */
    boolean submitGuess() {
        if (textField.getText().length() > 0) {
            char letter = textField.getText().charAt(0);
            return guessLetter(letter);
        }
        return false;
    }

    /**
     * Checks if the specified letter is contained in the phrase. If so, appropriate empty letter
     * slots are filled with the letter.
     *
     * @param letter The letter to check
     */
    private boolean guessLetter(Character letter) {
        String uppercasePhrase = phrase.toUpperCase();
        boolean guessFound = false;

        int occurrenceIndex = uppercasePhrase.indexOf(letter); // Get index of first occurrence of letter if it exists.
        while (occurrenceIndex >= 0) {
            letterSlots[occurrenceIndex].setText(Character.toString(phrase.charAt(occurrenceIndex)));
            guessFound = true;

            occurrenceIndex = uppercasePhrase.indexOf(letter, occurrenceIndex + 1);
        }

        if (guessFound) {
            correctnessText.setStyle("-fx-font: 18 Calibri; -fx-fill: green; -fx-rotate: 15");
            if (unguessedLetters.contains(letter)) {
                correctnessText.setText("Correct!");
                unguessedLetters.remove(letter);

                if (onCorrect != null) {
                    onCorrect.get();
                }

                if (onValidGuess != null) {
                    onValidGuess.get();
                }

                /* Check to see of all the letters have been guessed. */
                if (unguessedLetters.isEmpty()) {
                    correctnessText.setStyle("-fx-font: 18 Calibri; -fx-fill: blue; -fx-rotate: 0");
                    correctnessText.setText("That's a wrap!");
                    comboBox.setVisible(true);
                }
            } else {
                correctnessText.setText("Already guessed!");
                return false;
            }

        } else {
            correctnessText.setText("Incorrect");
            correctnessText.setStyle("-fx-font: 18 Calibri; -fx-fill: red; -fx-rotate: -15");

            if (onValidGuess != null) {
                onValidGuess.get();
            }
            if (onIncorrect != null) {
                onIncorrect.get();
            }
        }
        return true;
    }

    /**
     * Set the onCorrect method
     */
    void setOnCorrect(Supplier<Integer> onCorrect) {
        this.onCorrect = onCorrect;
    }

    /**
     * Set the onIncorrect method
     */
    void setOnIncorrect(Supplier<Integer> onIncorrect) {
        this.onIncorrect = onIncorrect;
    }

    /**
     * Set the onValidGuess method
     */
    void setOnValidGuess(Supplier<Integer> onGuess) {
        this.onValidGuess = onGuess;
    }

    /**
     * Disable or enable the guessBox.
     *
     * @param b true to disable; false to enable
     */
    void disableGuessBox(boolean b) {
        this.textField.setDisable(b);
    }
}
