package controllers;

import javafx.scene.control.Label;
import util.User;
import util.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This class will handle all the controls need for the login-screen.fxml
 * The main use for this class is to validate the credentials given by the user in order to prepare and launch (log in) into the main menu screen
 */

public class LogInController {

    /**
     * This is the collection of all @FXML objects need for the login-screen.fxml
     */
    @FXML private ImageView logoImage;
    @FXML private Button logInButton;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label errorLabel;

    /**
     * UserManager object used to obtain and validate data given by the user and the data in the Users.csv file
     */
    private UserManager manager;
    /**
     * This Stage object would be used to have a track of the desired stage for each screen.
     */
    private Stage mainStage;

    @FXML
    /**
     * This function is used to initialize and load the image to the view as well as setting the UserManager for future use.
     * This function will launch when the start() functions is call using this controller's id.
     */
    public void initialize()
    {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/images/logo.png")));

        manager = UserManager.getInstance();


    }

    @FXML
    /**
     * This function is used when the Log In Button is pressed.
     * This function will validate that the user provide valid data and use the model classes (User/UserManager) to validate that the user data matches the data stored.
     * If everything is correct it will call the login() function and pass the matched User object with it.
     */
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

    /**
     * This function will start the main-screen.fxml by using its controller class
     * @param user This function take in a User object that is passed to the next screen in order to obtain the necessary data for the user.
     */
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


    /**
     * This function is used to maintain track of the mainStage initialize at the beginning of the application to prevent multiple windows to be opened.
     * @param mainStage This parameter would be used by the caller to have a track of the desired stage for each screen.
     */
    public void setStage(Stage mainStage) {
        this.mainStage = mainStage;
    }






}


