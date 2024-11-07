package org.example.htmlfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MostBorrowed {
    private final SimpleIntegerProperty id;
    //SimpleStringProperty là một lớp trong javaFX để lưu trữ các thay đổi của giá trị kiểu String
    private final SimpleStringProperty name;
    private final SimpleStringProperty borrowedTimes;


    public MostBorrowed(int id,String name,String borrowedTimes) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.borrowedTimes = new SimpleStringProperty(borrowedTimes);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

     public String getBorrowedTimes() {
        return borrowedTimes.get();
    }

}
