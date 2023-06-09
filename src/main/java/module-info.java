module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires AuthLib;
    requires commons.math3;
    requires java.desktop;
    requires java.logging;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
}