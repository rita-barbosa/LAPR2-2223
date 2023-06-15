package com.example.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;

public class SubdivideAgenciesApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        FXMLLoader fxmlLoader = new FXMLLoader(SubdivideAgenciesApp.class.getResource("SubdivideAgenciesScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 715, 575);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}