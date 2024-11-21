package org.example.htmlfx.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.htmlfx.DatabaseConnection;
import org.example.htmlfx.SwitchScene;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User_controller {
    @FXML
    private TableView<Users> tableView;

    @FXML
    private TableColumn<Users, String> EmailColumn;

    @FXML
    private TableColumn<Users, String> PhoneColumn;

    @FXML
    private TableColumn<Users, Integer> UserIDColumn;

    @FXML
    private TableColumn<Users, String> UsernameColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButtom;

    @FXML
    private Button editButtom;

    @FXML
    private Button viewButton;

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getDeleteButtom() {
        return deleteButtom;
    }

    public void setDeleteButtom(Button deleteButtom) {
        this.deleteButtom = deleteButtom;
    }

    public Button getEditButtom() {
        return editButtom;
    }

    public void setEditButtom(Button editButtom) {
        this.editButtom = editButtom;
    }

    public Button getViewButton() {
        return viewButton;
    }

    public void setViewButton(Button viewButton) {
        this.viewButton = viewButton;
    }

    public static List<Users> getUsers() {
        List<Users> users = new ArrayList<Users>();
        String sql = "select * from members";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                int userID = resultSet.getInt("userID");
                String userName = resultSet.getString("username");
                users.add(new Users(email, phone, userID, userName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void initialize() {
        EmailColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("phone"));
        UserIDColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("userID"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("username"));

        ObservableList<Users> users = FXCollections.observableArrayList(User_controller.getUsers());
        tableView.setItems(users);
    }

    public void addUser(ActionEvent event) {
        SwitchScene sw = new SwitchScene();
        sw.switchScene(event, "user/User_Add.fxml");
    }

    public void deleteUser() {
        System.out.println("Deleting user");
    }

    public void viewUser() {
        System.out.println("View user");
    }

    public void editUser() {
        System.out.println("Editing user");
    }
}
