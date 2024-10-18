module org.example.htmlfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens org.example.htmlfx to javafx.fxml;
    exports org.example.htmlfx;
}