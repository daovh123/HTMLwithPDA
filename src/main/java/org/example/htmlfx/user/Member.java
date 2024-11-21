package org.example.htmlfx.user;

import org.example.htmlfx.book.BookItem;

import java.util.ArrayList;
import java.util.List;

public class Member extends Users {
    private int totalBooksCheckedOut = 0;
    private List<BookItem> listBorrowed = new ArrayList<>();

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public void setTotalBooksCheckedOut(int totalBooksCheckedOut) {
        this.totalBooksCheckedOut = totalBooksCheckedOut;
    }

    public void addBorrowed() {

    }

    public List<BookItem> showListBorrowed() {

        return listBorrowed;
    }

    public List<BookItem> booksBorrowed() {
        return listBorrowed;
    }
}
