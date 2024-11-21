package org.example.htmlfx.book;


import java.util.Date;

public class BookItem {
    private boolean isReferenceOnly;
    private Date borrowed;
    private Date dueDate;
    private double price;
    private String status;
    private Date dateOfPurchase;
    private Book book;

    // Constructors
    public BookItem(boolean isReferenceOnly, Date borrowed, Date dueDate, double price, String status,
                    Date dateOfPurchase, Book book) {
        this.isReferenceOnly = isReferenceOnly;
        this.borrowed = borrowed;
        this.dueDate = dueDate;
        this.price = price;
        this.status = status;
        this.dateOfPurchase = dateOfPurchase;
        this.book = book;
    }

    // Getters and Setters
    public boolean isReferenceOnly() {
        return isReferenceOnly;
    }

    public void setReferenceOnly(boolean referenceOnly) {
        isReferenceOnly = referenceOnly;
    }

    public Date getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Date borrowed) {
        this.borrowed = borrowed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // Methods
    public boolean checkout(String memberId) {
        if (isReferenceOnly) {
            System.out.println("This book is for reference only and cannot be borrowed.");
            return false;
        }
        this.borrowed = new Date(); // Set borrowed date to current date
        this.dueDate = new Date(System.currentTimeMillis() + (14L * 24 * 60 * 60 * 1000)); // Due date 14 days later
        return true;
    }
}
