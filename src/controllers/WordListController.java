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



    @FXML private TextField addWordField;
    @FXML private TextField deleteWordField;
    @FXML private RadioButton addRadio;
    @FXML private RadioButton deleteRadio;
    @FXML private ToggleGroup toggleGroup;
    @FXML private Label wordlistPath;

    @FXML private Button goButton;
    @FXML private Button analyzeButton;
    @FXML private Button solverButton;
    @FXML private Button normalButton;

    private Alert alert;

    private Stage wordlistStage;
    private Stage mainStage;

    private LoadWordlist wordManager;


    @FXML
    public void initialize()
    {
        alert = new Alert(Alert.AlertType.INFORMATION);


        wordManager = LoadWordlist.getInstance();
        wordlistPath.setText(wordManager.getWordlistFileName());

        toggleGroup = new ToggleGroup();
        addRadio.setToggleGroup(toggleGroup);
        deleteRadio.setToggleGroup(toggleGroup);

    }

    @FXML
    public void onGoClick() {

        String wordToAdd = addWordField.getText().trim().toLowerCase();
        String wordToDelete = deleteWordField.getText().trim().toLowerCase();

        if (wordToAdd.isEmpty() && wordToDelete.isEmpty()) {
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word");
            alert.showAndWait();
            return;
        }

        if (!addRadio.isSelected() && !deleteRadio.isSelected()) {
            alert.setTitle("Error");
            alert.setHeaderText("Please select Add or Delete");
            alert.showAndWait();
            return;
        }

        // ---------------------------------------------------------
        // ADD WORD
        // ---------------------------------------------------------
        if (addRadio.isSelected()) {

            if (wordToAdd.length() != 5) {
                alert.setTitle("Error");
                alert.setHeaderText("The word must be 5 letters.");
                alert.showAndWait();
                return;
            }

            if (wordManager.findWord(wordToAdd)) {
                alert.setTitle("Error");
                alert.setHeaderText("This word already exists in the list.");
                alert.showAndWait();
                return;
            }

            boolean added = wordManager.addWord(wordToAdd);
            if (added) {
                alert.setTitle("Success");
                alert.setHeaderText("Word successfully added.");
                alert.showAndWait();
            } else {
                alert.setTitle("Error");
                alert.setHeaderText("Could not add word.");
                alert.showAndWait();
            }

            return;
        }

        // ---------------------------------------------------------
        // DELETE WORD
        // ---------------------------------------------------------
        if (deleteRadio.isSelected()) {

            if (wordToDelete.length() != 5) {
                alert.setTitle("Error");
                alert.setHeaderText("The word must be 5 letters.");
                alert.showAndWait();
                return;
            }

            if (!wordManager.findWord(wordToDelete)) {
                alert.setTitle("Error");
                alert.setHeaderText("That word is not in the list.");
                alert.showAndWait();
                return;
            }

            boolean deleted = wordManager.removeWord(wordToDelete);
            if (deleted) {
                alert.setTitle("Success");
                alert.setHeaderText("Word deleted successfully.");
                alert.showAndWait();
            } else {
                alert.setTitle("Error");
                alert.setHeaderText("Error deleting word.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void onAnalyzeClick()
    {

    }


    @FXML
    public void onSolverClick()
    {

    }

    @FXML
    public void onNormalClick()
    {

    }

















    private void launchScreen(String fxml, String title){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/" + fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.wordlistStage);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setStage(Stage baseStage) { this.wordlistStage = baseStage; }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

}
