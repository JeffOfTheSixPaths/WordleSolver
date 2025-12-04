package controllers;

import javafx.scene.control.Label;
import util.User;
import util.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    @FXML private ImageView logoImage;
    @FXML private Button logInButton;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label errorLabel;

    private UserManager manager;
    private Stage mainStage;

    @FXML
    public void initialize()
    {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));

        manager = UserManager.getInstance();


    }

    @FXML
    public void onLogInClick()
    {
        String username = usernameField.getText().trim();

        String password = passwordField.getText().trim();

        if(username.isEmpty())
        {
            errorLabel.setText("Please enter an username");
        }
        else
        {

            User found = manager.findUser(username);
            if (found != null) {
                if(password.isEmpty())
                {
                    errorLabel.setText("Please enter a password.");
                }
                else
                {
                    if(password.equals(found.getPassword()))
                    {
                        login(found);
                    }
                    else
                    {
                        errorLabel.setText("Wrong Password!\nTry again.");
                    }
                }
            }
            else
            {
                errorLabel.setText("The given username is not in the database.");
            }
        }
    }

    private void login(User user){

        manager.setCurrentUser(user);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/main-screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            MainScreenController mainMenuController = fxmlLoader.getController();
            mainMenuController.setMainStage(mainStage);

            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e){
            e.printStackTrace();

        }
    }





    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }






}


