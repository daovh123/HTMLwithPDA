package com.example.library;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {
    Image newimage = new Image("D:/HTML_Project/HTMLwithPDA/Library/src/main/assets/hello.jpg");
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Label welcomeText;

    @FXML
    private Label welcomeText1;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Niceeeeee!");
        System.out.println("Clicked!");
    }

    @FXML
    void setImageG(MouseEvent event) {
        image.setVisible(!image.isVisible());
        System.out.println("Image visible: " + image.isVisible());
    }

    @FXML
    void initialize() {
        image.setImage(newimage);
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert welcomeText != null : "fx:id=\"welcomeText\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert welcomeText1 != null : "fx:id=\"welcomeText1\" was not injected: check your FXML file 'hello-view.fxml'.";

    }

}
