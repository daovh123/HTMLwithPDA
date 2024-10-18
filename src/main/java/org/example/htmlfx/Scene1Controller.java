package org.example.htmlfx;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Scene1Controller {

    private boolean isAtRight = true;

    @FXML
    private Button signUpButton;
    @FXML
    private Button signInButton;
    @FXML
    private StackPane stackPane1;
    @FXML
    private StackPane stackPane2;
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private TranslateTransition transition1;
    private TranslateTransition transition2;


    public void initialize() {
        stackPane1.setStyle("-fx-background-color: #57BEF9;");
        transition1 = new TranslateTransition(Duration.seconds(1), stackPane1);
        transition2 = new TranslateTransition(Duration.seconds(1), stackPane2);

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
    }

    // Common function to toggle the position
    private void togglePosition() {
        if (isAtRight) {
            transition1.setToX(550);
            transition2.setToX(-400);
            signUpButton.setText("Sign Up");
            signInButton.setText("Sign In");

        } else {
            transition1.setToX(0);
            transition2.setToX(0);
            signUpButton.setText("Sign In");
            signInButton.setText("Sign Up");

        }
        //stackPane1.toFront();

        transition1.play();
        transition2.play();

        isAtRight = !isAtRight;
    }

    public void handleSignUp(javafx.event.ActionEvent actionEvent) {
        togglePosition();
    }

    public void handleSignIn(javafx.event.ActionEvent actionEvent) {
        togglePosition();
    }
}
