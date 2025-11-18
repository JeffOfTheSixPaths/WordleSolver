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
    @FXML private Label accAgeDisp;
    @FXML private Label solvesDisp;
    @FXML private Label cheatedDisp;
    @FXML private Label percentDisp;



    private UserManager manager;
    private User currentUser;
    private Stage mainStage;

    @FXML
    public void initialize()
    {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/edu/utsa/cs3443/wordsolverjavafx/images/logo.png")));





        try
        {
            manager = UserManager.getInstance();
            currentUser = manager.getCurrentUser();
        }
        catch (IOException e)
        {
            e.getMessage();
        }

        usernameDisp.setText(currentUser.getUserName());
        accAgeDisp.setText(String.valueOf(currentUser.getAccAge()) + " days");
        solvesDisp.setText(String.valueOf(currentUser.getSolvedWordles()));
        cheatedDisp.setText(String.valueOf(currentUser.getHelpedWordles()));
        percentDisp.setText("(" + String.valueOf(currentUser.getCheatedWordles()) + "%)");
    }

    @FXML
    public void onWordlistClick()
    {



        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/wordlist-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();


            WordListController wordListController = fxmlLoader.getController();
            wordListController.setStage(stage);
            wordListController.setMainStage(mainStage);

            stage.initModality(Modality.WINDOW_MODAL);  // or Modality.APPLICATION_MODAL
            stage.initOwner(this.mainStage);

            stage.setTitle("WordList Settings");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }


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

            stage.initModality(Modality.WINDOW_MODAL);  // or Modality.APPLICATION_MODAL
            stage.initOwner(this.mainStage);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e){
            e.printStackTrace();
        }
    }





    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }






}


