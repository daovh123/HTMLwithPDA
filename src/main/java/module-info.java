module org.example.htmlfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires okhttp3;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires com.google.gson;
    requires org.json;
    requires java.net.http;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    // Xuất các gói cần thiết cho module khác
    exports org.example.htmlfx;
    exports org.example.htmlfx.book;
    exports org.example.htmlfx.user;
    exports org.example.htmlfx.dashboard;
    exports org.example.htmlfx.borrow;
    exports org.example.htmlfx.income;

    // Mở các gói cần thiết cho JavaFX FXML
    opens org.example.htmlfx to javafx.fxml;
    opens org.example.htmlfx.book to javafx.fxml;
    opens org.example.htmlfx.user to javafx.fxml;
    opens org.example.htmlfx.dashboard to javafx.fxml;
    opens org.example.htmlfx.income to javafx.fxml;
    opens org.example.htmlfx.borrow to javafx.fxml;
    exports org.example.htmlfx.toolkits;
    opens org.example.htmlfx.toolkits to javafx.fxml;
}
