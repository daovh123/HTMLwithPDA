package org.example.htmlfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.htmlfx.user.Member_controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SwitchScene {
    private static final Map<Stage, String> openStages = new HashMap<>();

    public void openNewScene(ActionEvent event, String scenePath, Member_controller parentController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(scenePath));
        Parent root = loader.load();

        // Lấy controller và kiểm tra nếu nó implement ParentControllerAware
        Object controller = loader.getController();
        if (controller instanceof ParentControllerAware) {
            ((ParentControllerAware) controller).setParentController(parentController);
        } else {
            System.err.println("Controller does not implement ParentControllerAware: " + controller.getClass().getName());
        }

        // Tạo scene và hiển thị stage mới
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }

    public void switchScene(ActionEvent event, String scenePath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(scenePath));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeScene(ActionEvent event) {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        Set<Map.Entry<Stage, String>> stages = openStages.entrySet();

        openStages.remove(currentStage);

        stages.forEach(entry -> {
            Stage stage = entry.getKey();
            String scenePath = entry.getValue();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(scenePath));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stages.forEach(entry -> {
            Stage stage = entry.getKey();
                stage.show();
        });
    }
}
