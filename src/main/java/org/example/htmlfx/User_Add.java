package org.example.htmlfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Add {

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

        String checkUserSQL = "SELECT COUNT(*) FROM members WHERE username = ?";
        String insertUserSQL = "INSERT INTO members (username, firstname, lastname, phone, email) " +
                                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement checkUserStatement = connection.prepareStatement(checkUserSQL);
            PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSQL)) {
                checkUserStatement.setString(1, username.getText());
                ResultSet resultSet = checkUserStatement.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    showAlert(AlertType.WARNING, "Warning", "Username already exists.");
                    return;
                }

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
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
            }
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "Users.fxml");
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
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "Users.fxml");
    }

}
