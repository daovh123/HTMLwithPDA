package org.example.htmlfx.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.htmlfx.DatabaseConnection;
import org.example.htmlfx.user.Member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        borrowedTimes.setCellValueFactory(new PropertyValueFactory<>("borrowedTimes"));

        ObservableList<MostBorrowed> data= FXCollections.observableArrayList(DashboardControl.getBorrowed());
        tableView.setItems(data);
        setLineChart();
        setIncome();
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
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Mon",20));
        series.getData().add(new XYChart.Data("Tue",40));
        series.getData().add(new XYChart.Data("Wed",60));
        series.getData().add(new XYChart.Data("Thu",50));
        series.getData().add(new XYChart.Data("Fri",10));
        series.getData().add(new XYChart.Data("Sat",20));
        series.getData().add(new XYChart.Data("Sun",30));
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
             getIncome.setText(String.valueOf(totalPayment)+"$");

     }
}