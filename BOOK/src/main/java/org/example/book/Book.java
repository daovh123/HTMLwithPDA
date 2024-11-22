package org.example.book;

import java.util.Date;
import java.util.Objects;

public class Book {
    private String ID;
    private String ISBN;
    private String title;
    private String subject;
    private String publisher;
    private String publicationDate;
    private String language;
    private int numberOfPages;
    private String format;
    private String authors;
    private String imageLink;  // Thêm trường imageLink
    private String downloadLink;
    private String previewLink;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public Book(String title, String author) {
        this.title = title;
        this.authors = author;
    }
    public Book(String ISBN, String title, String subject, String publisher, String publicationDate, String language, int numberOfPages, String format, String authors, String imageLink, String downloadLink, String previewLink, String description) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.format = format;
        this.authors = authors;
        this.imageLink = imageLink;
        this.downloadLink = downloadLink;
        this.previewLink = previewLink;
        this.description = description;
    }

    // Getters and setters
    public String getImageLink() { return imageLink; }
    public void setImageLink(String imageLink) { this.imageLink = imageLink; }

    // Getters and setters for all fields
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getPublicationDate() { return publicationDate; }
    public void setPublicationDate(String publicationDate) { this.publicationDate = publicationDate; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getNumberOfPages() { return numberOfPages; }
    public void setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(ID, book.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}