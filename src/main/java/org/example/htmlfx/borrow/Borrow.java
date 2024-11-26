package org.example.htmlfx.borrow;

import org.example.htmlfx.book.Book;
import org.example.htmlfx.user.Member;

import java.sql.Date;

public class Borrow {
    private String id;
    private Member borrower;
    private Book bookBorrowed;
    private String borrowDate;
    private String returnDate;
    private String status;

    public Borrow() {
    }

    public Borrow(String id, Member borrower, Book bookBorrowed, String borrowDate, String returnDate, String status) {
        this.id = id;
        this.borrower = borrower;
        this.bookBorrowed = bookBorrowed;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getBorrower() {
        return borrower;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    public Book getBookBorrowed() {
        return bookBorrowed;
    }

    public void setBookBorrowed(Book bookBorrowed) {
        this.bookBorrowed = bookBorrowed;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
