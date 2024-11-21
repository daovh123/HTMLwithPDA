package org.example.htmlfx.book;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;

import java.io.IOException;
import java.net.URI;

public class CardController {
    @FXML
    private Label author;

    @FXML
    private Label borrowtimes;

    @FXML
    private ImageView imageBook;

    @FXML
    private Label nameBook;

    public void setData(Book book) {
        if (book != null) {
            nameBook.setText(book.getTitle());
            author.setText(book.getAuthors());
            borrowtimes.setText("Borrowed: 897 times");

            if (book.getImageLink() != null && !book.getImageLink().isEmpty()) {
                imageBook.setImage(new Image(book.getImageLink()));
            } else {
                imageBook.setImage(new Image("/path/to/default/image.jpg")); // Đường dẫn ảnh mặc định
            }
        } else {
            System.out.println("Book object is null");
        }
    }

    @FXML
    private void handleCardClick(javafx.scene.input.MouseEvent event) {
        HBox source = (HBox) event.getSource();
        Book book = (Book) source.getUserData(); // Lấy đối tượng Book từ UserData
        showBookDetails(book); // Hiển thị thông tin chi tiết sách
    }

    private void showBookDetails(Book book) {
        if (book == null) {
            System.out.println("Book object is null");
            return;
        }

        // Tạo Stage mới để hiển thị chi tiết sách
        Stage stage = new Stage();
        stage.setTitle("Book Details");

        // Tạo VBox chứa nội dung
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Tiêu đề sách
        Label titleLabel = new Label("Title: " + book.getTitle());
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Ảnh sách
        ImageView bookImageView = new ImageView();
        bookImageView.setFitHeight(150);
        bookImageView.setFitWidth(100);
        if (book.getImageLink() != null && !book.getImageLink().isEmpty()) {
            bookImageView.setImage(new Image(book.getImageLink()));
        } else {
            bookImageView.setImage(new Image("/path/to/default/image.jpg")); // Đường dẫn ảnh mặc định
        }

        // Mô tả sách (Sử dụng TextArea để tự động xuống dòng)
        TextArea descriptionArea = new TextArea(book.getDescription() != null ? book.getDescription() : "No description available.");
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false); // Không cho phép chỉnh sửa
        descriptionArea.setPrefHeight(100); // Cài đặt chiều cao cho TextArea

        // Các thông tin sách khác
        Label detailsLabel = new Label(
                "Author(s): " + book.getAuthors() + "\n" +
                        "Publisher: " + book.getPublisher() + "\n" +
                        "Published Date: " + book.getPublicationDate() + "\n" +
                        "Language: " + book.getLanguage() + "\n" +
                        "Categories: " + book.getSubject() + "\n" +
                        "Page Count: " + book.getNumberOfPages()
        );

        // Nút Preview Book
        Button previewButton = new Button("Preview Book");
        previewButton.setOnAction(e -> openPreviewLink(book));

        // Nút Download Book
        Button downloadButton = new Button("Download Book");
        downloadButton.setOnAction(e -> downloadBook(book));

        // Thêm các phần tử vào VBox
        root.getChildren().addAll(bookImageView, titleLabel, descriptionArea, detailsLabel, previewButton, downloadButton);

        // Tạo Scene và hiển thị Stage
        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.show();
    }


    private void openPreviewLink(Book book) {
        if (book.getPreviewLink() != null && !book.getPreviewLink().isEmpty()) {
            try {
                java.awt.Desktop.getDesktop().browse(URI.create(book.getPreviewLink()));
            } catch (IOException e) {
                System.out.println("Error opening preview link: " + e.getMessage());
            }
        } else {
            System.out.println("Preview link is not available for this book.");
        }
    }

    private void downloadBook(Book book) {
        if (book.getDownloadLink() != null && !book.getDownloadLink().isEmpty()) {
            try {
                java.awt.Desktop.getDesktop().browse(URI.create(book.getDownloadLink()));
            } catch (IOException e) {
                System.out.println("Error downloading book: " + e.getMessage());
            }
        } else {
            System.out.println("Download link is not available for this book.");
        }
    }
}
