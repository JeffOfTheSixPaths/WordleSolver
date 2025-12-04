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

    /**
     * This is the collection of all @FXML objects need for the main-screen.fxml. This includes all the Labels, Buttons, and RadioButtons needed for the view.
     */
    @FXML private TextField addOrdeleteWordField;
    @FXML private RadioButton addRadio;
    @FXML private RadioButton deleteRadio;
    @FXML private ToggleGroup toggleGroup;
    @FXML private Label wordlistPath;
    @FXML private Label errorLabel;
    @FXML private Button goButton;
    @FXML private Button solverButton;
    @FXML private Button normalButton;

    /**
     * The following 2 Stage object are used to keep track of who is calling each screen since this screen and the main menu screen can both utilize the Normal Game and Solver Game.
     */
    private Stage wordlistStage;  // This modal window
    private Stage mainStage;      // Parent main window

    /**
     * LoadWordlist object used to obtain and validate data given by the user and the data in the valid-wordle-words.csv file
     */
    private LoadWordlist wordManager;

    // THE FIX — must be MainScreenController, NOT WordListController
    private MainScreenController mainMenuController;

    /**
     * This function is used keep track to the main menu controller's id for any future calls if necessary.
     * @param controller This parameter would be used by the caller to assign this controller with the id of the main menu controller.
     */
    public void setMainMenuController(MainScreenController controller) {
        this.mainMenuController = controller;
    }

    /**
     * This function is used to maintain track of the mainStage initialize at the beginning of the application to prevent multiple windows to be opened.
     * @param stage This parameter would be used by the caller to have a track of the desired stage for each screen.
     */
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    /**
     * This function is used to maintain track of this screen Stage for any other screen display that needs to be attached to this screen.
     * @param stage This parameter would be used by the caller to have a track of the desired stage for each screen.
     */
    public void setWordlistStage(Stage stage) {
        this.wordlistStage = stage;
    }

    @FXML
    /**
     * This function is used to initialize, load and set the LoadWordlist object make any necessary changes to the data.
     * This function will launch when the start() functions is call using this controller's id.
     */
    public void initialize() {

        wordManager = LoadWordlist.getInstance();
        wordlistPath.setText(wordManager.getWordlistFileName());

        toggleGroup = new ToggleGroup();
        addRadio.setToggleGroup(toggleGroup);
        deleteRadio.setToggleGroup(toggleGroup);
    }

    @FXML
    /**
     * This function is used when the Solver Button is pressed.
     * This function will create a new scene and open the solver-screen.fxml in a separate screen but will lock the use of the main screen to prevent multiple screens being open at the same time.
     */
    public void onSolverClick() {
        System.out.println("Solver clicked!");
        // TODO: attach solver UI later
    }

    @FXML
    /**
     * This function is used when the Normal Button is pressed.
     * This function will create a new scene and open the WordleGameScreen.fxml in a separate screen but will lock the use of the main screen to prevent multiple screens being open at the same time.
     */
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

    @FXML
    /**
     * This function is used when the Add/Delete Button is pressed.
     * If the Delete RadioButton is selected it will call the handleDelete() with the entered word.
     * If the Add RadioButton is selected it will call the handleAdd() with the entered word.
     * If everything is correct it will Add/Delete by calling the desired function and pass the matched word entered by the user.
     */
    /**
     * This function is used when the Add/Delete Button is pressed.
     * If the Delete RadioButton is selected it will verify if the word is on the list and call the handleDelete if a word is found in the data files.
     * If the Add RadioButton is selected it will verify that the word is not on the list already and call the handleAdd if a word is not found in the data files and the entered word is a 5 letter word.
     * If everything is correct it will Add/Delete by calling the desired function and pass the matched word entered by the user.
     */
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

    /**
     * This function will validate that the user provide valid data and use the model classes (LoadWordlist) to validate that the user data matches the data stored in the file.
     * It will verify that the word is not on the list already, if the word is not found in the data files and the entered word is a 5 letter word it will add the word to the list.
     * @param word String use to search and add a new word
     */
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

    /**
     * This function will validate that the user provide valid data and use the model classes (LoadWordlist) to validate that the user data matches the data stored in the file.
     * It will verify if the word is on the list and if a word is found in the data files it will remove it.
     * @param word String use to search and delete a word if found.
     */
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

}
