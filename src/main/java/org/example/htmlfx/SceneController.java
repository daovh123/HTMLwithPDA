package org.example.htmlfx;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {
    private boolean isAtRight = true;

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
    public void initialize() {
        createVbox.setVisible(false);
        BackVbox.setVisible(false);
        signInVbox.setVisible(true);
        HelloVbox.setVisible(true);
    }

    public void handleSwitchScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    private void handleSignIn(ActionEvent event) {
        if (!isAtRight) {
            togglePosition();
        } else {
            handleSwitchScene(event);
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        togglePosition();
    }
}
