package controllers;

import edu.utsa.cs3443.wordsolverjavafx.model.Wordle.LoadWordlist;
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



        try
        {
            wordManager = LoadWordlist.getInstance();
            wordlistPath.setText(wordManager.getWordlistFileName());
        }
        catch (IOException e)
        {
            System.out.println("Error at Wordlist screen");
            e.printStackTrace();
        }

        toggleGroup = new ToggleGroup();
        addRadio.setToggleGroup(toggleGroup);
        deleteRadio.setToggleGroup(toggleGroup);

    }

    @FXML
    public void onGoClick()
    {
        String wordToAdd = addWordField.getText().trim();
        String wordToDelete = deleteWordField.getText().trim();
        wordToAdd.toLowerCase();
        wordToDelete.toLowerCase();


        if(wordToAdd.isEmpty() && wordToDelete.isEmpty())
        {
            alert.setTitle("Error");
            alert.setHeaderText("Please enter a word");
            alert.showAndWait();
        }
        else
        {
            if (!addRadio.isSelected() && !deleteRadio.isSelected())
            {
                alert.setTitle("Error");
                alert.setHeaderText("Please select an option");
                alert.showAndWait();
            }
            else if (addRadio.isSelected())
            {
                char[] inWordArr = wordToAdd.toCharArray();

                if(inWordArr.length != 5)
                {
                    alert.setTitle("Error");
                    alert.setHeaderText("The word can only be a 5 letter word");
                    alert.setContentText("Please try again.");
                    alert.showAndWait();
                }
                else
                {
                    String found = wordManager.findWord(wordToAdd);
                    if (found != null) {
                        alert.setTitle("Error");
                        alert.setHeaderText("The word is already on the list.");
                        alert.showAndWait();
                    }
                    else
                        wordManager.addWord(wordToAdd);
                }

            }
            else if (deleteRadio.isSelected())
            {
                char[] inWordArr = wordToDelete.toCharArray();

                if(inWordArr.length != 5)
                {
                    alert.setTitle("Error");
                    alert.setHeaderText("The word can only be a 5 letter word");
                    alert.setContentText("Please try again.");
                    alert.showAndWait();
                }
                else
                {
                    if (wordManager.isWordExists(wordToDelete))
                    {

                        boolean deleted = wordManager.deleteWord(wordManager.findWord(wordToDelete));
                        if(deleted)
                        {
                            alert.setTitle("Success");
                            alert.setHeaderText("Word deleted from the list correctly");
                            alert.showAndWait();
                        }
                        else
                        {
                            alert.setTitle("Error");
                            alert.setHeaderText("Error trying to delete word");
                            alert.showAndWait();
                        }
                    }
                    else
                    {
                        alert.setTitle("Error");
                        alert.setHeaderText("The provided word is not on the list.");
                        alert.showAndWait();
                    }
                }

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
