module com.example.grant1_v1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
//    requires jcaptcha.all;
    requires simplecaptcha;
    requires javafx.swing;
    requires java.prefs;
    requires org.apache.pdfbox;


    opens com.example.grant1_v1 to javafx.fxml;
    exports com.example.grant1_v1;
    exports com.example.grant1_v1.models;
    opens com.example.grant1_v1.models to javafx.fxml;
    exports com.example.grant1_v1.controllers;
    opens com.example.grant1_v1.controllers to javafx.fxml;
}