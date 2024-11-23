package org.example.htmlfx.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.example.htmlfx.DatabaseConnection;
import org.example.htmlfx.SearchBar;
import org.example.htmlfx.SwitchScene;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Member_controller implements Initializable {
    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, String> EmailColumn;

    @FXML
    private TableColumn<Member, String> PhoneColumn;

    @FXML
    private TableColumn<Member, String> NameColumn;

    @FXML
    private TableColumn<Member, String> IDColumn;

    @FXML
    private TableColumn<Member, String> GenderColumn;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField searchID;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Text info_birthday;

    @FXML
    private Text info_email;

    @FXML
    private Text info_gender;

    @FXML
    private Text info_id;

    @FXML
    private Text info_name;

    @FXML
    private Text info_phone;

    private Member selectedMember = new Member();

    private static Member currentMember = new Member();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));


        Callback<TableColumn<Member, String>, TableCell<Member, String>> cellFactory = new Callback<TableColumn<Member, String>, TableCell<Member, String>>() {
            @Override
            public TableCell<Member, String> call(TableColumn<Member, String> param) {
                return new TableCell<Member, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty); if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item);
                            setStyle("-fx-alignment: CENTER;");
                        }
                    }
                };
            }
        };

        IDColumn.setCellFactory(cellFactory);
        PhoneColumn.setCellFactory(cellFactory);

        ObservableList<Member> members = FXCollections.observableArrayList(Member_controller.getMember());
        if (members.isEmpty()) {
            System.out.println("No members found in the database.");
        } else {
            tableView.setItems(members);
        }

        // Thêm sự kiện nhấn đúp chuột vào TableView
        tableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                selectedMember = tableView.getSelectionModel().getSelectedItem();
                if (selectedMember != null) {
                    handleDoubleClick(selectedMember);
                }
            }
        });

        SearchBar search = new SearchBar();
        search.setupSearchFieldForDatabase(searchID, listView);

        pane1.setVisible(true);
        pane2.setVisible(false);
    }

    private void handleDoubleClick(Member member) {
        info_id.setText(member.getId());
        info_name.setText(member.getFirstname() + ' ' + member.getLastname());
        info_phone.setText(member.getPhone());
        info_email.setText(member.getEmail());
        info_gender.setText(member.getGender());
        info_birthday.setText(member.getBirthday());

        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    public void addMember(ActionEvent event) throws IOException {
        SwitchScene sw = new SwitchScene();
        sw.openNewScene(event, "user/Member_Add.fxml", this);
    }

    public void updateTableView() {
        ObservableList<Member> members = FXCollections.observableArrayList(Member_controller.getMember());
        tableView.setItems(members);
    }

    public static List<Member> getMember() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int ID = resultSet.getInt("member_id");
                String id = String.format("%06d", ID);
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                String birthday = resultSet.getString("birth");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                members.add((new Member(id, firstName, lastName, gender, birthday, email, phone)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void deleteMember(ActionEvent event) throws IOException {
        SwitchScene sw = new SwitchScene();
        sw.openNewScene(event, "user/Member_Delete.fxml", this);
    }

    public void back(ActionEvent event) throws IOException {
        pane1.setVisible(true);
        pane2.setVisible(false);
    }

    public void editMember(ActionEvent event) throws IOException {
        currentMember = selectedMember;
        SwitchScene sw = new SwitchScene();
        sw.openNewScene(event, "user/Member_Edit.fxml", this);
    }

    public static Member getCurrentMember() {
        return currentMember;
    }

    public void updatePane2(Member updatedMember) {
        // Hiển thị thông tin mới trong pane2
        info_id.setText(updatedMember.getId());
        info_name.setText(updatedMember.getFirstname() + ' ' + updatedMember.getLastname());
        info_phone.setText(updatedMember.getPhone());
        info_email.setText(updatedMember.getEmail());
        info_gender.setText(updatedMember.getGender());
        info_birthday.setText(updatedMember.getBirthday());
    }

}
