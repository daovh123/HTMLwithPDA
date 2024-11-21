module org.example.book {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    requires okhttp3;
    requires google.api.client;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires com.google.gson;

    requires javafx.web;
    requires org.json;
    requires java.net.http;
    opens org.example.book to javafx.fxml;
    exports org.example.book;
}