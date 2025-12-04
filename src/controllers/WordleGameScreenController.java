package controllers;

import Wordle.Algorithm;
import Wordle.Seeds;
import Wordle.Validator;
import Wordle.letter_color;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.User;
import util.UserManager;

public class WordleGameScreenController {

    /**
     * This is the collection of all @FXML objects
     */
    @FXML private GridPane wordleGrid;
    @FXML private TextField guessInput;
    @FXML private Label dispLabel;


    /**
        Creating the wordle table objects. Setting the max sizes and creation a Label 2d array
     */
    private static final int MAX_ROWS = 6;
    private static final int MAX_COLS = 5;

    private Label[][] tiles = new Label[MAX_ROWS][MAX_COLS];
    private String currWord;
    private int currentRow = 0;
    private Stage mainStage;

    @FXML
    /**
     * Initializes the stage the game will be played on, setting the defaults of the game logic, added the answer being sent to terminal for testing.
     * Maps the GridPane tiles into our array
     * Set the default styling for each tile in the array. The reason that originally had black lines was because the border was being set but it had no letter or size to hold it open.
     * Fixed the above problem
     */
    public void initialize() {
        currWord = Seeds.getRandomWord(Seeds.getConstantSeededRandom());
        System.out.println("DEBUG — Correct word: " + currWord);

        int row = 0;
        int col = 0;

        for (var node : wordleGrid.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);

            if (r != null && c != null && node instanceof Label label) {
                tiles[r][c] = label;


                label.setStyle("-fx-border-color: black;"
                        + "-fx-border-width: 2px;"
                        + "-fx-font-size: 32px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-alignment: center;"
                        + "-fx-background-color: white;");



                label.setMinSize(40, 40);
                label.setPrefSize(40, 40);
                label.setMaxSize(40, 40);
            }
        }
    }

    @FXML
    /**
     * this is the main submit button logic where we check the word that was guessed with the word that we have stored as answer.
     * updates the screen to show the comparison of guess v. answer
     * if win, show popup refresh mainscreen stats
     * if the new next row is the max row then show fail popup
     * clears the text field
     */
    public void onSubmitGuess() {


        if (currentRow >= MAX_ROWS) return;


        String guess = guessInput.getText().toUpperCase().trim();
        if (guess.length() != 5) {
            dispLabel.setText("Guess must be 5 letters");
            System.out.println("Guess must be 5 letters");
            return;
        }

        dispLabel.setText("");
        letter_color[] colors = Validator.checkGuessColors(currWord, guess);
        updateRow(currentRow, guess, colors); //


        if (guess.equals(currWord)) {
            User currentUser = UserManager.getInstance().getCurrentUser();
            currentUser.incrementSolvedWordles();
            UserManager.getInstance().saveDataToFile();

            showWinPopup();

            if (mainMenuController != null) {
                mainMenuController.refreshStats();
            }

            return;
        }


        currentRow++;



        //
        if (currentRow == MAX_ROWS) {
            User currentUser = UserManager.getInstance().getCurrentUser();
            currentUser.incrementFailedWordles();
            UserManager.getInstance().saveDataToFile();

            showFailPopup();

            if (mainMenuController != null) {
                mainMenuController.refreshStats();
            }

            return;
        }

        //
        guessInput.clear();
    }


    /**
     * updating the style on each tile in the current row
     * color switch for the tile backgrounds
     * @param row
     * @param guess
     * @param colors
     */
    private void updateRow(int row, String guess, letter_color[] colors) {
        for (int col = 0; col < MAX_COLS; col++) {
            Label tile = tiles[row][col];

            tile.setText(String.valueOf(guess.charAt(col)));



            String bgColor = switch (colors[col]) {
                case GREEN -> "#6aaa64";
                case YELLOW -> "#c9b458";
                case RED -> "#787c7e";
            };

            //
            tile.setStyle("-fx-border-color: black;"
                    + "-fx-border-width: 2px;"
                    + "-fx-font-size: 32px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-alignment: center;"
                    + "-fx-background-color: " + bgColor + ";");
        }
    }


    /**
     * sets current stage to main stage
     * @param mainStage
     */
    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    /**
     * creates the show win popup that is called in  on submit
     * makes it so the user cant type and submit on guess input same for the failpop up
     */
    private void showWinPopup() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("You Win!");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("You guessed the word!");

        ButtonType playAgainBtn = new ButtonType("Play Again");
        ButtonType exitBtn = new ButtonType("Exit");

        alert.getButtonTypes().setAll(playAgainBtn, exitBtn);

        alert.showAndWait().ifPresent(response -> {
            if (response == playAgainBtn) {
                resetGame();
            } else {

                mainStage.close();
                guessInput.setDisable(true);
            }
        });
    }

    /**
     * creates the show fail popup that is called in  on submit
     */
    private void showFailPopup() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Out of Tries!");
        alert.setHeaderText("You failed to guess the word.");
        alert.setContentText("The correct word was: " + currWord);

        ButtonType playAgainBtn = new ButtonType("Play Again");
        ButtonType exitBtn = new ButtonType("Exit");

        alert.getButtonTypes().setAll(playAgainBtn, exitBtn);

        alert.showAndWait().ifPresent(response -> {
            if (response == playAgainBtn) {
                resetGame();
            } else {
                mainStage.close();
                guessInput.setDisable(true);
            }
        });
    }


    /**
     * resets everything to default and gets a new word
     */
    private void resetGame() {

        // resets chosen word
        currWord = Seeds.getRandomWord(Seeds.getConstantSeededRandom());
        System.out.println("DEBUG — New word: " + currWord);

        // resets tiles
        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c = 0; c < MAX_COLS; c++) {
                Label tile = tiles[r][c];
                tile.setText("");
                tile.setStyle("-fx-border-color: black;"
                        + "-fx-border-width: 2px;"
                        + "-fx-font-size: 32px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-alignment: center;"
                        + "-fx-background-color: white;");
            }
        }

        // reset row count
        currentRow = 0;

        //turns guessing back on
        guessInput.clear();
        guessInput.setDisable(false);
    }


    /**
     * sets the controller for the current popup window
     */
    private MainScreenController mainMenuController;
    public void setMainMenuController(MainScreenController controller) {
        this.mainMenuController = controller;
    }
}





