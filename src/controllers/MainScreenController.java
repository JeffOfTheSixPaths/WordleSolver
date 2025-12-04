package controllers;

import util.User;
import util.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    /**
     * This is the collection of all @FXML objects need for the main-screen.fxml. This includes all the Labels, Buttons, and Images needed for the view.
     */
    @FXML private ImageView logoImage;
    @FXML private Button wordlistButton;
    @FXML private Button solverButton;
    @FXML private Button normalButton;
    @FXML private Label usernameDisp;
    @FXML private Label solvesDisp;
    @FXML private Label cheatedDisp;
    @FXML private Label percentDisp;

    /**
     * UserManager object used to obtain and validate data given by the user and the data in the Users.csv file
     */
    private UserManager manager;
    /**
     * User object used to obtain data from the Users.csv file and used to be displayed.
     */
    private User currentUser;
    /**
     * This Stage object would be used to have a track of the desired stage for each screen.
     */
    private Stage mainStage;

    @FXML
    /**
     * This function is used to initialize and load the image to the view as well as setting the UserManager and User objects to display its attributes.
     * This function will launch when the start() functions is call using this controller's id.
     */
    public void initialize() {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));

        manager = UserManager.getInstance();
        currentUser = manager.getCurrentUser();

        usernameDisp.setText(currentUser.getUserName());
        solvesDisp.setText(String.valueOf(currentUser.getSolvedWordles()));
        cheatedDisp.setText(String.valueOf(currentUser.getHelpedWordles()));
        percentDisp.setText("(" + currentUser.getCheatedWordles() + "%)");
    }

    @FXML
    /**
     * This function is used when the WordList Button is pressed.
     * This function will create a new scene and open the wordlist-screen.fxml in a separate screen but will lock the use of the main screen to prevent multiple screens being open at the same time.
     */
    public void onWordlistClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/wordlist-screen.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            WordListController controller = loader.getController();
            controller.setMainStage(mainStage);     // parent stage
            controller.setWordlistStage(stage);     // this modal window
            controller.setMainMenuController(this); // callback

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            stage.setScene(scene);
            stage.setTitle("Word List Settings");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * This function is used when the Solver Button is pressed.
     * This function will create a new scene and open the solver-screen.fxml in a separate screen but will lock the use of the main screen to prevent multiple screens being open at the same time.
     */
    public void onSolverClick() {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/SolverController-screen.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

           SolverController game = loader.getController();
           game.setStage(stage);
            game.setMainMenuController(this);

           stage.initModality(Modality.WINDOW_MODAL);
           stage.initOwner(mainStage);

            stage.setScene(scene);
            stage.setTitle("Play Wordle");
           stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
            game.setMainMenuController(this);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            stage.setScene(scene);
            stage.setTitle("Play Wordle");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is will update all the displayed attributes for the current user. This function is mainly used by other controllers that may update the user data.
     */
    public void refreshStats() {
        currentUser = manager.getCurrentUser();
        solvesDisp.setText(String.valueOf(currentUser.getSolvedWordles()));
        cheatedDisp.setText(String.valueOf(currentUser.getHelpedWordles()));
        percentDisp.setText("(" + currentUser.getCheatedWordles() + "%)");
    }

    /**
     * This function is used to maintain track of the mainStage initialize at the beginning of the application to prevent multiple windows to be opened.
     * @param stage This parameter would be used by the caller to have a track of the desired stage for each screen.
     */
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    /**
     * This MainScreenController object is used to conserve the main menu dominance of stages and scenes over any other screens.
     * This MainScreenController object is necessary since multiple screens are handled by this controller.
     */
    private MainScreenController mainMenuController;

    /**
     * This function is used keep track to the controller's id for any future self-calls if necessary.
     * @param controller This parameter would be used by the caller to assign this controller with its own id.
     */
    public void setMainMenuController(MainScreenController controller) {
        this.mainMenuController = controller;
    }
}



