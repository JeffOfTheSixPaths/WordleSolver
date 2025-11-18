package controllers;

import Wordle.Seeds;
import Wordle.Validator;
import Wordle.letter_color;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.User;
import util.UserManager;

public class WordleGameScreenController {

    @FXML private GridPane wordleGrid;
    @FXML private TextField guessInput;


    private MainScreenController mainMenuController;

    private static final int MAX_ROWS = 6;
    private static final int MAX_COLS = 5;

    private Label[][] tiles = new Label[MAX_ROWS][MAX_COLS];
    private String currWord;
    private int currentRow = 0;
    private Stage mainStage;

    @FXML
    public void initialize() {
        currWord = Seeds.getRandomWord(Seeds.getConstantSeededRandom());
        System.out.println("DEBUG — Correct word: " + currWord);

        int row = 0;
        int col = 0;

        // Map GridPane tiles into our array
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
            }
        }
    }

    @FXML
    public void onSubmitGuess() {
        if (currentRow >= MAX_ROWS) return;

        String guess = guessInput.getText().toUpperCase().trim();
        if (guess.length() != 5) {
            System.out.println("Guess must be 5 letters");
            return;
        }

        letter_color[] colors = Validator.checkGuessColors(currWord, guess);
        updateRow(currentRow, guess, colors);

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

        guessInput.clear();
    }

    private void updateRow(int row, String guess, letter_color[] colors) {
        for (int col = 0; col < MAX_COLS; col++) {
            Label tile = tiles[row][col];

            tile.setText(String.valueOf(guess.charAt(col)));

            String bgColor = switch (colors[col]) {
                case GREEN -> "#6aaa64";
                case YELLOW -> "#c9b458";
                case RED -> "#787c7e";
            };

            tile.setStyle("-fx-border-color: black;"
                    + "-fx-border-width: 2px;"
                    + "-fx-font-size: 32px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-alignment: center;"
                    + "-fx-background-color: " + bgColor + ";");
        }
    }

    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


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
                guessInput.setDisable(true);
            }
        });
    }

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
                guessInput.setDisable(true);
            }
        });
    }

    private void resetGame() {
        // reset chosen word
        currWord = Seeds.getRandomWord(Seeds.getConstantSeededRandom());
        System.out.println("DEBUG — New word: " + currWord);

        // reset tiles
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

        // re-enable guessing
        guessInput.clear();
        guessInput.setDisable(false);
    }

    public void setMainMenuController(MainScreenController controller) {
        this.mainMenuController = controller;
    }
}





