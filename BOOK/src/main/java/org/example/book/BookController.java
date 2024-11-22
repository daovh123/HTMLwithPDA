package org.example.book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Insets;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class BookController implements Initializable {

    @FXML
    private GridPane Case;

    @FXML
    private ImageView bookImageView;

    @FXML
    private HBox cardCase;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label detailsLabel;

    @FXML
    private Button downloadButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button previewButton;

    @FXML
    private TextField searchBook;

    @FXML
    private Pane showBook;

    @FXML
    private VBox showSearch;

    @FXML
    private Label titleLabel;


    private Book book;
    private List<Book> recentlyAdded;
    private List<Book> BookCase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recentlyAdded = new ArrayList<>(fetchBooksFromAPI(3)); // Fetch 3 books for "recently added"
        BookCase = new ArrayList<>(fetchBooksFromAPI(6)); // Fetch 18 books for "bookcase"
        SearchBar searchBar = new SearchBar();
        listView.toFront();
        loadRecentlyAddedBooks();
        loadBookCaseBooks();
        searchBar.setupSearchField(searchBook, listView);
       // setBook();
    }

    public void AfterClick(Book book) {
        bookImageView.setImage(new Image (book.getImageLink()));
        detailsLabel.setText((
                "Author(s): " + book.getAuthors() + "\n" +
                        "Publisher: " + book.getPublisher() + "\n" +
                        "Published Date: " + book.getPublicationDate() + "\n" +
                        "Language: " + book.getLanguage() + "\n" +
                        "Categories: " + book.getSubject() + "\n" +
                        "Page Count: " + book.getNumberOfPages()
        ));
        descriptionArea.setText(book.getDescription());
        previewButton.setOnAction(e -> openPreviewLink(book));
        downloadButton.setOnAction(e -> downloadBook(book));
        setShowBook();
        setShowSearch();
    }
    private static final String GOOGLE_BOOKS_API_BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    // Fetch books from Google Books API
    private List<Book> fetchBooksFromAPI(int maxResults) {
        Random random = new Random(); // Bỏ seed để random thực sự ngẫu nhiên
        List<Book> books = new ArrayList<>();
        try {
            String query = "?q=programming&maxResults=" + maxResults;
            URI uri = URI.create(GOOGLE_BOOKS_API_BASE_URL + query);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray items = jsonResponse.optJSONArray("items");

                if (items != null) {
                    // Select a number of books
                    for (int i = 0; i < Math.min(maxResults, items.length()); i++) {
                        int randomIndex = random.nextInt(items.length());
                        JSONObject bookJson = items.getJSONObject(randomIndex);
                        JSONObject volumeInfo = bookJson.optJSONObject("volumeInfo");
                        JSONObject accessInfo = bookJson.optJSONObject("accessInfo");

                        if (volumeInfo != null) {
                            String imageLink = "";
                            JSONObject imageLinks = volumeInfo.optJSONObject("imageLinks");
                            if (imageLinks != null) {
                                imageLink = imageLinks.optString("thumbnail", ""); // Fallback if image is missing
                            }

                            // Lấy link preview
                            String previewLink = volumeInfo.optString("previewLink", "");

                            // Lấy link download
                            String downloadLink = "";
                            if (accessInfo != null) {
                                JSONObject pdfInfo = accessInfo.optJSONObject("pdf");
                                if (pdfInfo != null) {
                                    downloadLink = pdfInfo.optString("downloadLink", "");
                                }
                            }

                            // Lấy mô tả
                            String description = volumeInfo.optString("description", "No description available.");

                            Book book = new Book(
                                    volumeInfo.optJSONArray("industryIdentifiers") != null
                                            ? volumeInfo.optJSONArray("industryIdentifiers").optJSONObject(0).optString("identifier", "N/A")
                                            : "N/A",
                                    volumeInfo.optString("title", "Unknown"),
                                    volumeInfo.optJSONArray("categories") != null
                                            ? volumeInfo.optJSONArray("categories").join(", ").replace("\"", "")
                                            : "N/A",
                                    volumeInfo.optString("publisher", "Unknown"),
                                    volumeInfo.optString("publishedDate", "Unknown"),
                                    volumeInfo.optString("language", "Unknown"),
                                    volumeInfo.optInt("pageCount", 0),
                                    volumeInfo.optString("printType", "Unknown"),
                                    volumeInfo.optJSONArray("authors") != null
                                            ? volumeInfo.optJSONArray("authors").join(", ").replace("\"", "")
                                            : "Unknown",
                                    imageLink,
                                    previewLink,
                                    downloadLink,
                                    description // Thêm phần mô tả
                            );
                            books.add(book);
                        }
                    }
                }
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error fetching data from Google Books API: " + e.getMessage());
            e.printStackTrace();
        }

        return books;
    }



    private void loadRecentlyAddedBooks() {
        try {
            for (Book book : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(book);
                cardBox.setUserData(book);
                cardCase.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            System.out.println("Error loading FXML for recently added books: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadBookCaseBooks() {
        int column = 0;
        int row = 1;
        try {
            for (Book book : BookCase) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("BookCase.fxml"));
                VBox bookCaseBox = fxmlLoader.load();
                BookCaseController bookCaseController = fxmlLoader.getController();
                bookCaseController.setData(book);
                bookCaseBox.setUserData(book);
                if (column == 6) {
                    column = 0;
                    ++row;
                }
                Case.add(bookCaseBox, column++, row);
                GridPane.setMargin(bookCaseBox, new Insets(10));
            }
        } catch (IOException e) {
            System.out.println("Error loading FXML for bookcase: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void setShowBook() {
        showBook.setVisible(!showBook.isVisible());
    }
    public void setShowSearch() {
        showSearch.setVisible(!showSearch.isVisible());
    }

    @FXML
    public void handleBacktosearchBook2(MouseEvent mouseEvent) {
        setShowSearch();
        setShowBook();
        //setBook(book);
        AfterClick(book);
    }
    private void openPreviewLink(Book book) {
        if (book.getPreviewLink() != null && !book.getPreviewLink().isEmpty()) {
            try {
                Desktop.getDesktop().browse(URI.create(book.getPreviewLink()));
            } catch (Exception e) {
                System.out.println("Error opening preview link: " + e.getMessage());
            }
        } else {
            showAlert("Preview Not Available", "This book does not have a preview link.");
        }
    }
    // Phương thức tải sách
    private void downloadBook(Book book) {
        if (book.getDownloadLink() != null && !book.getDownloadLink().isEmpty()) {
            try {
                Desktop.getDesktop().browse(URI.create(book.getDownloadLink()));
            } catch (Exception e) {
                System.out.println("Error downloading book: " + e.getMessage());
            }
        } else {
            showAlert("Download Not Available", "This book does not have a download link.");
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}