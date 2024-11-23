package org.example.htmlfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import com.google.gson.*;
import org.example.htmlfx.user.Member_controller;

public class SearchBar {

    // Phương thức để xử lý TextField khi tìm kiếm sách
    public void setupSearchField(TextField searchField, ListView<String> suggestionList) {
        // Xử lý sự kiện khi người dùng nhập vào TextField
        searchField.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            String query = searchField.getText().trim();
            if (query.isEmpty()) {
                suggestionList.setVisible(false); // Ẩn khi không có văn bản
            } else {
                List<String> suggestions = fetchBookTitlesFromGoogle(query);
                if (!suggestions.isEmpty()) {
                    updateSuggestions(suggestions, suggestionList);
                } else {
                    suggestionList.setVisible(false);
                }
            }
        });

        // Chọn một gợi ý khi người dùng click vào
        suggestionList.setOnMouseClicked(event -> {
            String selectedItem = suggestionList.getSelectionModel().getSelectedItem();
            searchField.setText(selectedItem); // Điền vào TextField
            suggestionList.setVisible(false); // Ẩn danh sách gợi ý
        });
    }

    public void setupSearchFieldForDatabase(TextField searchField, ListView<String> suggestionList, Member_controller controller) {
        // Xử lý sự kiện khi người dùng nhập vào TextField
        searchField.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            String query = searchField.getText().trim();
            if (query.isEmpty()) {
                suggestionList.setVisible(false); // Ẩn khi không có văn bản
            } else {
                List<String> suggestions = fetchMemberIDFromDatabase(query);
                if (!suggestions.isEmpty()) {
                    updateSuggestions(suggestions, suggestionList);
                } else {
                    suggestionList.setVisible(false);
                }
            }
        });

        // Chọn một gợi ý khi người dùng click vào
        suggestionList.setOnMouseClicked(event -> {
            String selectedItem = suggestionList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                searchField.setText(selectedItem); // Điền vào TextField
                suggestionList.setVisible(false); // Ẩn danh sách gợi ý

                // Gọi phương thức selectItemInSgList trong controller
                controller.selectItemInSgList();
                searchField.setText("");
            }
        });
    }

    private List<String> fetchMemberIDFromDatabase(String query) {
        List<String> memberID = new ArrayList<>();
        String sql = "SELECT member_id FROM members WHERE member_id LIKE '%" + query + "%'";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String ID = resultSet.getString("member_id");
                memberID.add(ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberID;
    }

    // Phương thức cập nhật danh sách gợi ý
    private void updateSuggestions(List<String> suggestions, ListView<String> suggestionList) {
        ObservableList<String> observableSuggestions = FXCollections.observableArrayList(suggestions);
        suggestionList.setItems(observableSuggestions);
        suggestionList.setVisible(true); // Hiển thị lại danh sách gợi ý
    }

    // Phương thức lấy danh sách sách từ Google Books API
    private List<String> fetchBookTitlesFromGoogle(String query) {
        List<String> bookTitles = new ArrayList<>();
        try {
            String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + query.replace(" ", "+");
            HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
                JsonArray items = jsonResponse.getAsJsonArray("items");

                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        JsonObject book = items.get(i).getAsJsonObject();
                        JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");
                        String title = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "No title";
                        bookTitles.add(title);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookTitles;
    }
}