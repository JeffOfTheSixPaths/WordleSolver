package controllers;

import Wordle.Seeds;
import Wordle.Validator;
import Wordle.letter_color;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WordleGameScreenController {

    @FXML private GridPane wordleGrid;
    @FXML private TextField guessInput;

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
            }
        }
    }

    @FXML
    public void onSubmitGuess() {
        if (currentRow >= MAX_ROWS) return; // game is over

        String guess = guessInput.getText().toUpperCase().trim();

        if (guess.length() != 5) {
            System.out.println("Guess must be exactly 5 letters.");
            return;
        }

        // Get color results from Validator
        letter_color[] colors = Validator.checkGuessColors(currWord, guess);

        // Update UI tiles for this row
        updateRow(currentRow, guess, colors);

        // Win check
        if (guess.equals(currWord)) {
            System.out.println("YOU WIN!");
            guessInput.setDisable(true);
            return;
        }

        currentRow++;

        // Out of tries
        if (currentRow == MAX_ROWS) {
            System.out.println("OUT OF TRIES — The word was " + currWord);
            guessInput.setDisable(true);
        }

        guessInput.clear();
    }

    private void updateRow(int row, String guess, letter_color[] colors) {
        for (int col = 0; col < MAX_COLS; col++) {
            Label tile = tiles[row][col];

            tile.setText(String.valueOf(guess.charAt(col)));
            tile.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-alignment: center;");

            switch (colors[col]) {
                case GREEN -> tile.setStyle(tile.getStyle() + "-fx-background-color: #6aaa64;");
                case YELLOW -> tile.setStyle(tile.getStyle() + "-fx-background-color: #c9b458;");
                case RED -> tile.setStyle(tile.getStyle() + "-fx-background-color: #787c7e;");
            }
        }
    }
    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}





