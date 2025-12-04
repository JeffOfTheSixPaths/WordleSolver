package controllers;

import Wordle.LoadWordlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WordListController {

    @FXML private TextField addOrdeleteWordField;
    @FXML private RadioButton addRadio;
    @FXML private RadioButton deleteRadio;
    @FXML private ToggleGroup toggleGroup;
    @FXML private Label wordlistPath;
    @FXML private Label errorLabel;

    @FXML private Button goButton;
    @FXML private Button solverButton;
    @FXML private Button normalButton;

    private Alert alert;

    private Stage wordlistStage;  // This modal window
    private Stage mainStage;      // Parent main window

    private LoadWordlist wordManager;

    // THE FIX — must be MainScreenController, NOT WordListController
    private MainScreenController mainMenuController;

    public void setMainMenuController(MainScreenController controller) {
        this.mainMenuController = controller;
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    public void setWordlistStage(Stage stage) {
        this.wordlistStage = stage;
    }

    @FXML
    public void initialize() {
        alert = new Alert(Alert.AlertType.INFORMATION);

        wordManager = LoadWordlist.getInstance();
        wordlistPath.setText(wordManager.getWordlistFileName());

        toggleGroup = new ToggleGroup();
        addRadio.setToggleGroup(toggleGroup);
        deleteRadio.setToggleGroup(toggleGroup);
    }

    @FXML
    public void onSolverClick() {
        System.out.println("Solver clicked!");
        // TODO: attach solver UI later
    }

    @FXML
    public void onGoClick() {
        String wordToAddorDelete = addOrdeleteWordField.getText().trim().toLowerCase();

        if (wordToAddorDelete.isEmpty()) {
            errorLabel.setText("Please enter a word");
            return;
        }

        if (!addRadio.isSelected() && !deleteRadio.isSelected()) {
            errorLabel.setText("Please select Add or Delete");
            return;
        }

        if (addRadio.isSelected()) {
            handleAdd(wordToAddorDelete);
        }

        if (deleteRadio.isSelected()) {
            handleDelete(wordToAddorDelete);
        }
    }

    private void handleAdd(String word) {
        if (word.length() != 5) {
            errorLabel.setText("The word must be 5 letters.");
            return;
        }

        if (wordManager.findWord(word)) {
            errorLabel.setText("This word already exists.");
            return;
        }

        boolean added = wordManager.addWord(word);
        errorLabel.setText(added ? "Word added." : "Could not add word.");
    }

    private void handleDelete(String word) {
        if (word.length() != 5) {
            errorLabel.setText("The word must be 5 letters.");
            return;
        }

        if (!wordManager.findWord(word)) {
            errorLabel.setText("Word is not in the list.");
            return;
        }

        boolean deleted = wordManager.removeWord(word);
        errorLabel.setText(deleted ? "Word deleted." : "Could not delete word.");
    }

    @FXML
    public void onNormalClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/WordleGameScreen.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            WordleGameScreenController game = loader.getController();
            game.setStage(stage);

            // WE PASS THE MAIN MENU, NOT THIS CONTROLLER
            game.setMainMenuController(mainMenuController);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            stage.setTitle("Play Wordle");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
