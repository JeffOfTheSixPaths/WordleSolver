package controllers;

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

    private Alert alert;
    private UserManager manager;
    private Stage mainStage;

    @FXML
    public void initialize()
    {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));

        alert = new Alert(Alert.AlertType.INFORMATION);

        manager = UserManager.getInstance();


    }

    @FXML
    public void onLogInClick()
    {
        String username = usernameField.getText().trim();

        String password = passwordField.getText().trim();

        if(username.isEmpty())
        {
            alert.setTitle("Username");
            alert.setHeaderText("Please enter a valid username");
            alert.showAndWait();

        }
        else
        {

            User found = manager.findUser(username);
            if (found != null) {
                if(password.isEmpty())
                {
                    alert.setTitle("Password");
                    alert.setHeaderText("Please enter a valid password");
                    alert.showAndWait();
                }
                else
                {
                    if(password.equals(found.getPassword()))
                    {
                        login(found);
                    }
                    else
                    {
                        alert.setTitle("Wrong Password!");
                        alert.setHeaderText("The password does not match the one stored in the database for the given username: " + username);
                        alert.setContentText("Please try again.");
                        alert.showAndWait();
                    }
                }
            }
            else
            {
                alert.setTitle("Wrong Username!");
                alert.setHeaderText("The given username is not in the database.");
                alert.setContentText("Please try again.");
                alert.showAndWait();
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


