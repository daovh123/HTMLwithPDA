package org.example.htmlfx.dashboard;

import com.almasb.fxgl.notification.NotificationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.htmlfx.toolkits.DatabaseConnection;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardControl {
    @FXML
    private TableView<MostBorrowed> tableView;
    @FXML
    private TableColumn<MostBorrowed, Integer> id;
    @FXML
    private TableColumn<MostBorrowed, String> name;
    @FXML
    private TableColumn<MostBorrowed, String> borrowedTimes;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private Label getIncome;

    @FXML
    private ListView<Notification> notificationListView;

    private Notification_Service notificationService = new Notification_Service();

    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        borrowedTimes.setCellValueFactory(new PropertyValueFactory<>("borrowedTimes"));

        ObservableList<MostBorrowed> data = FXCollections.observableArrayList(DashboardControl.getBorrowed());
        tableView.setItems(data);
        setLineChart();
        setIncome();

        notificationService.start();
        notificationListView.setItems(FXCollections.observableArrayList(notificationService.getNotifications()));
        notificationListView.setVisible(false);
    }

    @FXML
    private void checkNotification() {
        // Kiểm tra trạng thái hiện tại của ListView
        if (notificationListView.isVisible()) {
            // Nếu ListView đang hiển thị, ẩn nó đi
            notificationListView.setVisible(false);
        } else {
            // Nếu ListView đang bị ẩn, hiển thị nó và cập nhật nội dung
            ObservableList<Notification> notifications = FXCollections.observableArrayList(notificationService.getNotifications());
            notificationListView.setItems(notifications);
            notificationListView.setVisible(true);
        }
    }


    public static List<MostBorrowed> getBorrowed() {
        List<MostBorrowed> mostBorrowed = new ArrayList<>();
        // Sửa câu truy vấn để lấy 10 cuốn sách có time_of_borrow cao nhất
        String sql = "SELECT * FROM books ORDER BY time_of_borrow DESC LIMIT 10";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("book_id");
                String name = resultSet.getString("book_name");
                String borrowedTimes = resultSet.getString("time_of_borrow");
                mostBorrowed.add(new MostBorrowed(id, name, borrowedTimes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostBorrowed;
    }

    @FXML
    private void setLineChart() {
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Borrow Count");
        String query = """
                    SELECT 
                        DAYNAME(DATE(borrow_date)) AS day_name, 
                        COUNT(*) AS borrow_count
                    FROM borrow
                    WHERE borrow_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 6 DAY) AND CURDATE()
                    GROUP BY borrow_date
                    ORDER BY DATE(borrow_date);
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Duyệt qua ResultSet để thêm dữ liệu vào series
            while (rs.next()) {
                String dayName = rs.getString("day_name"); // Tên thứ
                int borrowCount = rs.getInt("borrow_count"); // Số lượt mượn

                // Thêm dữ liệu vào series
                series.getData().add(new XYChart.Data<>(dayName, borrowCount));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Thêm series vào LineChart
        lineChart.getData().clear(); // Xóa dữ liệu cũ (nếu có)
        lineChart.getData().add(series);
    }

    private void setIncome() {
        double totalPayment = 0.0;
        String sql = "SELECT SUM(price) AS total_payment FROM payment";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                totalPayment = resultSet.getDouble("total_payment");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn tổng giá trị payment: " + e.getMessage());
            e.printStackTrace();
        }
        getIncome.setText(String.valueOf(totalPayment) + "$");

    }

}