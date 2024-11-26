package org.example.htmlfx.menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.example.htmlfx.DatabaseConnection;
import org.example.htmlfx.dashboard.DashboardControl;
import org.example.htmlfx.dashboard.MostBorrowed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dashboard_Income_Control {
    @FXML
    private HBox root;

    @FXML
    private AnchorPane toolbar_pane;

    @FXML
    private TableColumn<Payment, String> date;

    @FXML
    private TableColumn<Payment, String> id_book;

    @FXML
    private TableColumn<Payment, String> id_member;

    @FXML
    private TableColumn<Payment, String> payment;

    @FXML
    private TableColumn<Payment, String> id_purchase;

    @FXML
    private TableView<Payment> payment_table;

    @FXML
    public void initialize() {
        id_purchase.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_member.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        id_book.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        payment.setCellValueFactory(new PropertyValueFactory<>("price"));
        date.setCellValueFactory(new PropertyValueFactory<>("order_date"));

        ObservableList<Payment> data= FXCollections.observableArrayList(Dashboard_Income_Control.getIncome());
        payment_table.setItems(data);
    }
    public static List<Payment> getIncome() {
        List<Payment> payments = new ArrayList<>();
        // Sửa câu truy vấn để lấy 10 cuốn sách có time_of_borrow cao nhất
        String sql = "SELECT * FROM payment ORDER BY order_date DESC ";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String memberid = resultSet.getString("member_id");
                String bookid = resultSet.getString("book_id");
                String payment = resultSet.getString("price");
                String date = resultSet.getString("order_date");
                payments.add(new Payment(id, memberid, bookid,payment,date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
