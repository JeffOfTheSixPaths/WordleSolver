package edu.utsa.cs3443.wordsolverjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;

public class LogInApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/login-screen.fxml"));

        Scene scene = new Scene(loader.load());
        stage.setTitle("Log In Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
