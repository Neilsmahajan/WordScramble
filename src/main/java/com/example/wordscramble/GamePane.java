package com.example.wordscramble;

/**
 *  CSE205 Honor's Project: Word Scramble
 *  Name: Neil Mahajan
 *  Lecture: MWF 10:10am
 *	Description: GamePane for Word Scramble that extends BorderPane. It creates the HBoxes and VBoxes for the BorderPane
 *  and defines the WordBanks along with a word. GamePane contains a ButtonHandler class.
 *
 *  Steps of Execution:
 * 1. The user chooses a word bank
 * 2. A random word is chosen from the selected word bank
 * 3. The current word bank text is updated
 * 4. The original word is scrambled
 * 5. The scrambled word is shown on the screen
 * 6. When the player presses the hint button, a random letter from the word is shown in the correct position and the
 * score is decreased
 * 7. The player attempts to guess the word
 * 8. The number of guesses count is increased and displayed
 * 9. The player submits and the answer is compared to the unscrambled word checking to see if they are the same
 * 10. If they are the same, a congratulations message is displayed, the number of words guessed count and number of
 * guesses count is set to zero, the score increases, and the player chooses a new word from a word bank
 * 11. If they are not the same, an incorrect guess message is displayed, the number of guesses count increases, the
 * score decreases, and the player can choose a new word or guess again
 * 12. When the player chooses a word bank from the drop down and clicks the new word button, it checks to see if the
 * previous word was guessed or not
 * 13. If it was not guessed, then the number of words not guessed count increases and the score decreases
 * 14. A new word is chosen from the selected word bank, and the instruction message is displayed, the current word
 * bank text is updated, the word object is set to play again
 **/

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePane extends BorderPane
{
    /**
     * Array lists of words for each word bank of food, animals, Christmas,and celebrities
     */
    private ArrayList<String> foodBank,
            animalsBank,
            christmasBank;

    /**
     * Array list of word bank options
     */
    private ArrayList<WordBank> wordBankList;

    /**
     * word bank index, number of guesses, words guessed, words not guessed, and hint count
     */
    private int wordBankIndex, numberOfGuesses, numberOfWordsGuessed, numberOfWordsNotGuessed, hintCount;

    /**
     * Word bank string array for the three word bank names
     */
    private String[] wordBankStringArr;

    /**
     * Labels for message, scrambled word top, scrambled word bottom, guess word top, revealed word top, revealed word
     * bottom, previous guesses top, previous guesses bottom, hint, revealed letters, current word bank, number of words
     * guessed, number of words not guessed, score, and choose new word
     */
    private Label messageLbl, scrambledWordTopLbl, scrambledWordBotLbl, guessWordTopLbl, revealedLettersTopLbl,
            revealedLettersBotLbl, previousGuessesTopLbl, previousGuessesBotLbl, numberOfGuessesLbl,
            currentWordBankLbl, numberOfWordsGuessedLbl, numberOfWordsNotGuessedLbl, scoreLbl, chooseNewWordLbl;

    /**
     * Text field for guess word bottom that player inputs to guess the word
     */
    private TextField guessWordBotTxt;

    /**
     * Word bank selected by player
     */
    private WordBank currentWordBank;

    /**
     * Radio buttons for food, animals, Christmas, and celebrities
     */
    private RadioButton foodRadioBtn, animalsRadioBtn, christmasRadioBtn;

    /**
     * Buttons for hint, get new word, and submit
     */

    private Button hintBtn, getNewWordBtn, submitBtn;

    /**
     * Toggle group bankGroup for different word banks
     */
    private ToggleGroup bankGroup;

    /**
     * Constructor that initializes variables and sets up gui
     */
    public GamePane()
    {
        // Initialize word banks and adds them to word bank list
        wordBankList = new ArrayList<>();
        foodBank = new ArrayList<>(Arrays.asList("sandwich", "spatula", "pizza", "zest", "cheese", "artichoke", "grape",
                "milk", "McDonalds", "lasagna", "toaster", "pretzel", "bread", "jellybeans", "mushroom", "cupcakes",
                "wok", "oven", "corn", "avocado", "whisk", "dairy", "poultry", "noodles", "banana", "simmer", "freeze",
                "blender", "cornbread", "produce", "oven", "poach", "apple", "vinegar", "meatballs"));
        wordBankList.add(new WordBank(foodBank));
        animalsBank = new ArrayList<>(Arrays.asList("panda", "housecat", "moth", "moose", "water buffalo",
                "caterpillar","albatross", "duck", "mouse", "salamander", "goose", "rat", "aardvark", "joey",
                "robin", "horse", "koala", "hippopotamus", "camel", "cougar", "giraffe", "bald eagle",
                "crocodile", "dog", "bird", "penguin", "pony", "bat", "otter", "porcupine", "dolphin", "hamster",
                "chameleon", "swordfish", "groundhog", "cricket", "clownfish", "leopard", "rabbit",
                "buffalo", "shark", "stallion", "wombat", "kitten", "reindeer", "wolf", "dragon", "hyena",
                "unicorn", "tuna", "platypus", "shrimp", "skunk", "bulldog", "ladybug", "beaver"));
        wordBankList.add(new WordBank(animalsBank));
        christmasBank = new ArrayList<>(Arrays.asList("gifts", "cookies", "Santa", "reindeer", "gingerbread", "eggnog",
                "elves", "stocking", "soup", "snowman", "snow", "cinnamon", "snowball", "decorations", "angel",
                "snowflake", "chimney", "star", "Rudolph", "sledding", "bells", "scarf", "lights", "doll", "Grinch",
                "shepherds", "Nutcracker", "mistletoe", "mittens", "toys"));
        wordBankList.add(new WordBank(christmasBank));
        // Initialize word bank index, number of guesses, number of words guessed, number of words not guessed, and hint count
        wordBankIndex = 0;
        numberOfGuesses = 0;
        numberOfWordsGuessed = 0;
        numberOfWordsNotGuessed = 0;
        hintCount = 0;
        // Initialize word string array
        wordBankStringArr = new String[]{"Food", "Animals", "Christmas"};
        // Initialize message label and sets font for message
        messageLbl = new Label("Instructions: Guess the correct word from the scrambled word or choose a new word.");
        messageLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // Initialize scrambled word labels and sets font for bottom label
        scrambledWordTopLbl = new Label("Scrambled Word: ");
        scrambledWordBotLbl = new Label();
        scrambledWordBotLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        // Initialize guess word label and revealed letters labels, and sets font for revealed letters bottom label
        guessWordTopLbl = new Label("\nGuess Word: ");
        revealedLettersTopLbl = new Label("\nRevealed Letters: ");
        revealedLettersBotLbl = new Label();
        revealedLettersBotLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        // Initialize previous guesses labels and sets font for previous guesses bottom label
        previousGuessesTopLbl = new Label("Previous Guesses: ");
        previousGuessesBotLbl = new Label();
        previousGuessesBotLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        // Initialize number of guesses label, curent word bank label, number of words guessed label, number of words
        // not guessed label, score label, and choose new word label
        numberOfGuessesLbl = new Label("Number of Guesses: " + numberOfGuesses);
        currentWordBankLbl = new Label("Current Word Bank: " + wordBankStringArr[0]);
        numberOfWordsGuessedLbl = new Label("Number of Words Guessed: " + numberOfWordsGuessed);
        numberOfWordsNotGuessedLbl = new Label("Number of Words Not Guessed: " + numberOfWordsNotGuessed);
        scoreLbl = new Label("Score: " + Score.points);
        chooseNewWordLbl = new Label("Choose new word from a word bank: ");
        // Initializes guess word bottom text field
        guessWordBotTxt = new TextField();
        // Initializes current word bank as food bank
        currentWordBank = wordBankList.get(0);
        // Initializes food, animals and Christmas radio buttons
        foodRadioBtn = new RadioButton("Food");
        animalsRadioBtn = new RadioButton("Animals");
        christmasRadioBtn = new RadioButton("Christmas");
        // Initializes hint, submit, get new word buttons, and sets hint, submit, and get new word buttons to button handler
        hintBtn = new Button("Hint ");
        submitBtn = new Button("Submit ");
        getNewWordBtn = new Button("Get New Word" );
        hintBtn.setOnAction(new ButtonHandler());
        submitBtn.setOnAction(new ButtonHandler());
        getNewWordBtn.setOnAction(new ButtonHandler());
        // Initializes bank toggle group and adds food, animals, and Christmas radio buttons to group. Sets food as selected
        bankGroup = new ToggleGroup();
        foodRadioBtn.setToggleGroup(bankGroup);
        animalsRadioBtn.setToggleGroup(bankGroup);
        christmasRadioBtn.setToggleGroup(bankGroup);
        foodRadioBtn.setSelected(true);
        // Create Top HBox with message
        HBox topHBox = new HBox(20);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setMinSize(20, 40);
        topHBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        topHBox.getChildren().add(messageLbl);
        // Create bottom HBox with submit button, food radio button, animals radio button, Christmas radio button, and
        // get new word button
        HBox bottomHBox = new HBox(20);
        bottomHBox.setAlignment(Pos.CENTER);
        //bottomHBox.setMinSize(20, 40);
        bottomHBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        bottomHBox.getChildren().addAll(chooseNewWordLbl, foodRadioBtn, animalsRadioBtn, christmasRadioBtn, getNewWordBtn);
        // Create Left VBox with number of guesses label, current word bank label, number of words guessed label, number
        // of words not guessed label, and score label
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setSpacing(10);
        leftVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        leftVBox.getChildren().addAll(numberOfGuessesLbl, currentWordBankLbl, numberOfWordsGuessedLbl,
                numberOfWordsNotGuessedLbl, scoreLbl);
        // Create Right VBox with hint button, previous guesses
        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(10);
        rightVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        rightVBox.getChildren().addAll(hintBtn, previousGuessesTopLbl, previousGuessesBotLbl);
        // Create Center VBox with
        // Create Right VBox with hint button, previous guesses
        VBox centerVBox = new VBox();
        centerVBox.setAlignment(Pos.CENTER);
        //centerVBox.setSpacing(10);
        centerVBox.getChildren().addAll(scrambledWordTopLbl, scrambledWordBotLbl, revealedLettersTopLbl, revealedLettersBotLbl, guessWordTopLbl, guessWordBotTxt, submitBtn);
        // Add the HBox and VBox Panes to the BorderPane
        super.setTop(topHBox);
        super.setBottom(bottomHBox);
        super.setLeft(leftVBox);
        super.setRight(rightVBox);
        super.setCenter(centerVBox);
        // Sets scramble word bottom label text to scrambled word
        scrambledWordBotLbl.setText(wordBankList.get(wordBankIndex).getCurrentWord().getScrambledWord());
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // TASK 4: Implement the button handler

            // If the source of the event is the hint button
            if (event.getSource() == hintBtn)
            {
                // If the hint count is greater than the number of letters in the word minus three then deny the player
                if (hintCount > currentWordBank.getCurrentWord().getWordLength() - 3)
                {
                    messageLbl.setText("Too many hints used!");
                }
                // Else provide hint
                else
                {
                    hintCount++;
                    revealedLettersBotLbl.setText(currentWordBank.getCurrentWord().revealHint());
                    Score.decreasePoints(10);
                    scoreLbl.setText("Score: " + Score.points);
                }
            }
            // If the source of the event is the submit button
            if (event.getSource() == submitBtn && !guessWordBotTxt.getText().isEmpty())
            {
                // If the guess is correct
                if (currentWordBank.getCurrentWord().checkGuess(guessWordBotTxt.getText()))
                {
                    messageLbl.setText("Correct Guess! The word was " + currentWordBank.getCurrentWord().getWordStr() + ". Click on Get New Word to get a new word.");
                    Score.increasePoints(100);
                    scoreLbl.setText("Score: " + Score.points);
                    numberOfWordsGuessed++;
                    numberOfWordsGuessedLbl.setText("Number of Words Guessed: " + numberOfWordsGuessed);
                    numberOfGuesses = 0;
                    numberOfGuessesLbl.setText("Number of Guesses: " + numberOfGuesses);
                    scrambledWordBotLbl.setText(currentWordBank.getCurrentWord().getWordStr());
                    guessWordBotTxt.clear();
                    guessWordBotTxt.setEditable(false);
                }
                // Else the guess is wrong
                else
                {
                    messageLbl.setText("Incorrect Guess! Try again or select a new word.");
                    Score.decreasePoints(10);
                    scoreLbl.setText("Score: " + Score.points);
                    numberOfGuesses++;
                    previousGuessesBotLbl.setText(previousGuessesBotLbl.getText().concat("\n" + guessWordBotTxt.getText().toLowerCase()));
                }
                numberOfGuessesLbl.setText("Number of Guesses: " + numberOfGuesses);
            }
            // If the source of the event is the get new word button
            if (event.getSource() == getNewWordBtn)
            {
                guessWordBotTxt.setEditable(true);
                guessWordBotTxt.clear();
                // Finds what word bank is selected to get a new word from
                int newWordBankIndex = 0;
                if (bankGroup.getSelectedToggle().equals(foodRadioBtn)) newWordBankIndex = 0;
                else if (bankGroup.getSelectedToggle().equals(animalsRadioBtn)) newWordBankIndex = 1;
                else if (bankGroup.getSelectedToggle().equals(christmasRadioBtn)) newWordBankIndex = 2;
                currentWordBankLbl.setText("Current Word Bank: " + wordBankStringArr[newWordBankIndex]);
                if (newWordBankIndex != wordBankIndex) currentWordBank = wordBankList.get(newWordBankIndex);
                wordBankIndex = newWordBankIndex;
                // If the player guessed correctly or didn't try guessing yet
                if (numberOfGuesses != 0)
                {
                    numberOfWordsNotGuessed++;
                    numberOfWordsNotGuessedLbl.setText("Number of Words Not Guessed: " + numberOfWordsNotGuessed);
                    Score.decreasePoints(20);
                    scoreLbl.setText("Score: " + Score.points);
                }
                currentWordBank.getRandomNewWord();
                numberOfGuesses = 0;
                numberOfGuessesLbl.setText("Number of Guesses: " + numberOfGuesses);
                messageLbl.setText("Guess the correct word from the scrambled word or choose a new word.");
                currentWordBank.getRandomNewWord();
                scrambledWordBotLbl.setText(currentWordBank.getCurrentWord().getScrambledWord());
                revealedLettersBotLbl.setText("");
                previousGuessesBotLbl.setText("");
                hintCount = 0;
            }
        }
    }
}
