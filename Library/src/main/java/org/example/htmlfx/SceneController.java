package org.example.htmlfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class SceneController implements Initializable {
    private boolean isAtRight = true;


    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField userfield;

    @FXML
    private StackPane stackPaneLeft;  //trang

    @FXML
    private StackPane stackPaneRight; // xanh

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private VBox createVbox; // đăng ký

    @FXML
    private VBox signInVbox; // đăng nhập

    @FXML
    private VBox HelloVbox; // text

    @FXML
    private VBox BackVbox; // text

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private PasswordField passswordres;

    @FXML
    private TextField usernameres;

    @FXML
    public void initialize() {
        createVbox.setVisible(false);
        BackVbox.setVisible(false);
        signInVbox.setVisible(true);
        HelloVbox.setVisible(true);
    }


    @FXML
    private Label noticeLogin;

    @FXML
    private Label noticeSignUp;


    private void togglePosition() {
        TranslateTransition transitionRight = new TranslateTransition(Duration.seconds(0.5), stackPaneRight);
        TranslateTransition transitionLeft = new TranslateTransition(Duration.seconds(0.5), stackPaneLeft);

        if (isAtRight) {
            transitionRight.setByX(-500);
            transitionLeft.setByX(400);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.25), event -> {
                        signInVbox.setVisible(false);
                        createVbox.setVisible(true);
                        HelloVbox.setVisible(false);
                        BackVbox.setVisible(true);
                    })
            );


            transitionRight.play();
            transitionLeft.play();
            timeline.play();

        } else {

            transitionRight.setByX(500);
            transitionLeft.setByX(-400);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.25), event -> {

                        signInVbox.setVisible(true);
                        createVbox.setVisible(false);
                        HelloVbox.setVisible(true);
                        BackVbox.setVisible(false);
                    })
            );


            transitionRight.play();
            transitionLeft.play();
            timeline.play();
        }


        isAtRight = !isAtRight;
    }


    @FXML
    private void handleSignIn() {
        noticeLogin.setVisible(true);
        if (!isAtRight) {
            togglePosition();

        }
        if (userfield.getText().isBlank() == true || passwordfield.getText().isBlank() == true) {
            noticeLogin.setText("Login failed, Try again!!!");
        } else {
            ValidateLogin();
        }


    }

    @FXML
    private void handleSignUp() {
        togglePosition();

    }

    public void ValidateLogin() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        String verityLogin = "SELECT count(1) FROM user_account WHERE username ='"
                + userfield.getText() + "' and password = '" + passwordfield.getText() + "';";

        try {
            System.out.println(123);
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verityLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    noticeLogin.setText("Login successful");
                } else {
                    noticeLogin.setText("Login failed");
                }
            }

        } catch (Exception e) {
            System.out.println(122);
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SignUpNewUser(ActionEvent actionEvent) {
        noticeSignUp.setVisible(true);
        ValidateRegister();
    }

    public void ValidateRegister() {
        DatabaseConnection connection1 = new DatabaseConnection();
        Connection connectDB = connection1.getConnection();

        String verityRegister = "INSERT INTO user_account(firstname,lastname,username,password) VALUES ('" + firstname.getText()
                + "','" + lastname.getText() + "','" + usernameres.getText() + "','" + passswordres.getText() + "');";

        try {
            if(lastname.getText().isBlank() == false || firstname.getText().isBlank() == false
                    ||usernameres.getText().isBlank() == false || passswordres.getText().isBlank() == false ) {
                noticeSignUp.setText("Register successful");
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(verityRegister);
            } else {
                noticeSignUp.setText("Register failed!!!!!");
            }

        } catch (Exception e) {
            noticeSignUp.setText("Register failed");
            System.out.println(2);
            e.printStackTrace();
            e.getCause();
        }
    }
}
