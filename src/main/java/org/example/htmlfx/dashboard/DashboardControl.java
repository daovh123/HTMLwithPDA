package org.example.htmlfx.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        borrowedTimes.setCellValueFactory(new PropertyValueFactory<>("borrowedTimes"));

    ObservableList<MostBorrowed> data= FXCollections.observableArrayList(
            new MostBorrowed(1234,"Ngoi nha hanh phuc","100"),
            new MostBorrowed(1234,"Ngoi nha hanh phuc","100"),
            new MostBorrowed(1234,"Ngoi nha hanh phuc","100"),
            new MostBorrowed(1235,"Con ma trong long thanh pho","200")

            );
    tableView.setItems(data);
    setLineChart();
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

}
