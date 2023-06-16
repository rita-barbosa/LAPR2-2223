module pt.ipp.isep.dei.esoft.project.ui.console.menu.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires AuthLib;
    requires commons.math3;
    requires java.desktop;
    requires java.logging;


    opens pt.ipp.isep.dei.esoft.project.ui.console.gui to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.console.gui;
}