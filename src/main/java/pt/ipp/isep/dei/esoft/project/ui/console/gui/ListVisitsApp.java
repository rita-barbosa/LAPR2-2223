package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class ListVisitsApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(Locale.UK);
        FXMLLoader fxmlLoader = new FXMLLoader(ListVisitsApp.class.getResource("ListVisitsUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 715, 575);
        stage.setResizable(false);
        stage.setTitle("Real Estate USA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
