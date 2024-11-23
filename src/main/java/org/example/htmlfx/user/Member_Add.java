package org.example.htmlfx.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.example.htmlfx.DatabaseConnection;
import org.example.htmlfx.ParentControllerAware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Member_Add implements ParentControllerAware {
    private Member_controller parentController;

    @Override
    public void setParentController(Member_controller parentController) {
        this.parentController = parentController;
    }

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;

    @FXML
    void addNewUser(ActionEvent event) {
        if (username.getText().isEmpty() ||
                firstname.getText().isEmpty() ||
                lastname.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                email.getText().isEmpty()) {
            showAlert(AlertType.WARNING, "Warning", "All fields must be filled.");
            return;
        }

        String insertUserSQL = "INSERT INTO members (username, firstname, lastname, phone, email) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSQL)) {

            insertUserStatement.setString(1, username.getText());
            insertUserStatement.setString(2, firstname.getText());
            insertUserStatement.setString(3, lastname.getText());
            insertUserStatement.setString(4, phone.getText());
            insertUserStatement.setString(5, email.getText());

            int rowsAffected = insertUserStatement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(AlertType.INFORMATION, "Success", "User added successfully.");
            } else {
                showAlert(AlertType.ERROR, "Error", "Failed to add user.");
            }

            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();

            if (parentController != null) {
                parentController.updateTableView();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void backToUsers(ActionEvent event) {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }
}
