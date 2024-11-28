package org.example.htmlfx.toolkits;

import javafx.event.ActionEvent;
import org.example.htmlfx.SwitchScene;

public class MenuEvent {
    public static void gotoHome(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "dashboard/Dashboard.fxml");
    }

    public static void gotoBook(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "book/Book.fxml");
    }

    public static void gotoMember(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "member/Member.fxml");
    }

    public static void gotoBorrow(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "borrow/Borrow.fxml");
    }

    public static void gotoIncome(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "income/Income.fxml");
    }

    public static void gotoLogin(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "Start.fxml");
    }

}
