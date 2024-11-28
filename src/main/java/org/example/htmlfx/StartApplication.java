package org.example.htmlfx;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.example.htmlfx.dashboard.Notification_Service;

public class StartApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("user/Members.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("book/Book.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("borrow/Borrow.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("income/Income.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("dashboard/Dashboard.fxml"));
        Scene scene = new Scene(root);  // Đặt kích thước cửa sổ
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        Notification_Service notificationService = new Notification_Service();
        notificationService.start();

        launch(args);
    }
}