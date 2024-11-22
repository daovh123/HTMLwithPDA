package org.example.book;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookCaseController {

    @FXML
    private Label authorCase;

    @FXML
    private ImageView imageCase;

    @FXML
    private Label nameCase;

    public void setData(Book book,Label titleLabel,ImageView bookImageView,
                        TextArea descriptionArea,Label detailsLabel,
                        Button previewButton,Button downloadButton
                        ) {
        if (book != null) {
            nameCase.setText(book.getTitle());
            authorCase.setText(book.getAuthors());

            if (book.getImageLink() != null && !book.getImageLink().isEmpty()) {
                imageCase.setImage(new Image(book.getImageLink()));
            } else {
                imageCase.setImage(new Image("/path/to/default/image.jpg")); // Ảnh mặc định nếu không có ảnh
            }
        } else {
            System.out.println("Book object is null");
        }
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        if (book.getImageLink() != null && !book.getImageLink().isEmpty()) {
            bookImageView.setImage(new Image(book.getImageLink()));
        } else {
            bookImageView.setImage(new Image("/path/to/default/image.jpg")); // Đường dẫn ảnh mặc định
        }
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false); // Không cho phép chỉnh sửa
        detailsLabel.setText(
                "Author(s): " + book.getAuthors() + "\n" +
                        "Publisher: " + book.getPublisher() + "\n" +
                        "Published Date: " + book.getPublicationDate() + "\n" +
                        "Language: " + book.getLanguage() + "\n" +
                        "Categories: " + book.getSubject() + "\n" +
                        "Page Count: " + book.getNumberOfPages()
        );
        previewButton.setOnAction(e -> openPreviewLink(book));
        downloadButton.setOnAction(e -> downloadBook(book));

    }

    // Phương thức xử lý khi người dùng nhấn vào sách trong BookCase
    @FXML
    private void handleBookCaseClick(javafx.scene.input.MouseEvent event) {
        VBox source = (VBox) event.getSource();
        Book book = (Book) source.getUserData();// Giả sử bạn đã lưu book vào userData của VBox
        BookController bookController = new BookController();
        bookController.handleBacktosearchBook(event);
    }

    // Phương thức hiển thị thông tin chi tiết sách
    private void showBookDetails(Book book,Label titleLabel,ImageView bookImageView,
                                 TextArea descriptionArea,Label detailsLabel,
                                 Button previewButton,Button downloadButton
    ) {
        if (book == null) {
            System.out.println("Book object is null");
            return;
        }
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        if (book.getImageLink() != null && !book.getImageLink().isEmpty()) {
            bookImageView.setImage(new Image(book.getImageLink()));
        } else {
            bookImageView.setImage(new Image("/path/to/default/image.jpg")); // Đường dẫn ảnh mặc định
        }
        descriptionArea.setWrapText(true);
        descriptionArea.setEditable(false); // Không cho phép chỉnh sửa
        detailsLabel.setText(
                "Author(s): " + book.getAuthors() + "\n" +
                        "Publisher: " + book.getPublisher() + "\n" +
                        "Published Date: " + book.getPublicationDate() + "\n" +
                        "Language: " + book.getLanguage() + "\n" +
                        "Categories: " + book.getSubject() + "\n" +
                        "Page Count: " + book.getNumberOfPages()
        );
        previewButton.setOnAction(e -> openPreviewLink(book));
        downloadButton.setOnAction(e -> downloadBook(book));

    }



    // Phương thức mở liên kết Preview
    private void openPreviewLink(Book book) {
        if (book.getPreviewLink() != null && !book.getPreviewLink().isEmpty()) {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(book.getPreviewLink()));
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
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(book.getDownloadLink()));
            } catch (Exception e) {
                System.out.println("Error downloading book: " + e.getMessage());
            }
        } else {
            showAlert("Download Not Available", "This book does not have a download link.");
        }
    }

    // Phương thức hiển thị cảnh báo
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
