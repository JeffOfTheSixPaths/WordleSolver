package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import Wordle.Validator.*;
import Wordle.Wordle_Game.*;

import static Wordle.Validator.checkGuess;
import static Wordle.Wordle_Game.currWord;
import static Wordle.Wordle_Game.playGame;

import Wordle.Wordle_Game.*;


public class WordleGameScreenController {
    @FXML private GridPane wordleGrid;
    @FXML private TextField guessInput;
    private String strGuessInput;


    private Label[][] tiles = new Label[6][5];
    private int currentRow = 0;

    @FXML
    public void initialize() {

        for (Node node : wordleGrid.getChildren()) {

            Integer row = GridPane.getRowIndex(node);
            Integer col = GridPane.getColumnIndex(node);

            if (row == null) row = 0;
            if (col == null) col = 0;

            if (node instanceof Label label) {
                tiles[row][col] = label;
            }
        }



    }

    @FXML
    private void onSubmitGuess() {
        strGuessInput = guessInput.getText();
        String[] resultCheckOnSubmit = checkGuess(currWord, strGuessInput);
        //there


    }

    private void updateRow(int row, String guess) {

        }

}







