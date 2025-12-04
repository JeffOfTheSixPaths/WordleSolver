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

    @FXML private ImageView logoImage;

    @FXML private Button wordlistButton;
    @FXML private Button solverButton;
    @FXML private Button normalButton;

    @FXML private Label usernameDisp;
    @FXML private Label solvesDisp;
    @FXML private Label cheatedDisp;
    @FXML private Label percentDisp;

    private UserManager manager;
    private User currentUser;
    private Stage mainStage;

    @FXML
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
    public void onSolverClick() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/SolverController-screen.fxml"));
//            Scene scene = new Scene(loader.load());
//            Stage stage = new Stage();
//
//            SolverController game = loader.getController();
//            game.setStage(stage);
//            game.setMainMenuController(this);
//
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(mainStage);
//
//            stage.setScene(scene);
//            stage.setTitle("Play Wordle");
//            stage.showAndWait();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
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

    public void refreshStats() {
        currentUser = manager.getCurrentUser();
        solvesDisp.setText(String.valueOf(currentUser.getSolvedWordles()));
        cheatedDisp.setText(String.valueOf(currentUser.getHelpedWordles()));
        percentDisp.setText("(" + currentUser.getCheatedWordles() + "%)");
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    private MainScreenController mainMenuController;

    public void setMainMenuController(MainScreenController controller) {
        this.mainMenuController = controller;
    }
}



