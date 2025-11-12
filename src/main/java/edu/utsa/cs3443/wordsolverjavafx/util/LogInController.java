package edu.utsa.cs3443.wordsolverjavafx.util;

import edu.utsa.cs3443.wordsolverjavafx.model.utilities.User;
import edu.utsa.cs3443.wordsolverjavafx.model.utilities.UserManager;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LogInController {
    @FXML private ImageView logoImage;
    @FXML private Button logInButton;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    private Alert alert;
    private UserManager manager;

    @FXML
    public void initialize()
    {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/edu/utsa/cs3443/wordsolverjavafx/images/logo.png")));

        alert = new Alert(Alert.AlertType.INFORMATION);

        try
        {
            manager = UserManager.getInstance();
        }
        catch (IOException e)
        {
            e.getMessage();
        }


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
                        // Call next Screen
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
}


