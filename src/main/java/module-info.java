module com.example.ptm1orgproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
//    requires org.kordamp.bootstrapfx.core;


    opens com.example.ptm1orgproject to javafx.fxml;
    exports com.example.ptm1orgproject;
}