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
    exports pt.ipp.isep.dei.esoft.project.ui.console.menu.gui;
    opens pt.ipp.isep.dei.esoft.project.ui.console.menu.gui to javafx.fxml;
}