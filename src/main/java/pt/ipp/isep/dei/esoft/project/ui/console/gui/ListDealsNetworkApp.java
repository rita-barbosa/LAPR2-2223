package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;

public class ListDealsNetworkApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListDealsNetworkApp.class.getResource("ListDealsNetworkScene.fxml"));
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