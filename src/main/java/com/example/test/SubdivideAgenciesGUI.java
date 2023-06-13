package com.example.test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.SubdivideAgenciesController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SubdivideAgenciesGUI implements Initializable {
    private SubdivideAgenciesController controller;

    @FXML
    private ListView<String> listViewPartitionsDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new SubdivideAgenciesController();
        fillListViewDealsDisplay();
    }

    private void fillListViewDealsDisplay() {
        List<String> sublistStringList = controller.getAgenciesPartitions();
        for (String partition : sublistStringList) {
            listViewPartitionsDisplay.getItems().add(partition);
        }
    }
}
