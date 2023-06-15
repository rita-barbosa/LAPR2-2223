package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.SubdivideAgenciesController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SubdivideAgenciesGUI implements Initializable {
    private SubdivideAgenciesController controller;

    @FXML
    private ListView<String> listViewSubset1;
    @FXML
    private ListView<String> listViewSubset2;
    @FXML
    private TextField txtDifference;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new SubdivideAgenciesController();
        txtDifference.setDisable(true);

    }

    private void fillListViewDealsDisplay() {
        List<String> sublistStringList = controller.getAgenciesPartitions();
        displaysList(sublistStringList);
    }

    private void displaysList(List<String> sublistStringList) {
        listViewSubset1.getItems().add(sublistStringList.get(0));
        listViewSubset2.getItems().add(sublistStringList.get(1));
        txtDifference.setDisable(false);
        txtDifference.setText(sublistStringList.get(2));
    }

    @FXML
    public void btnExit(ActionEvent event) {

    }

    @FXML
    public void btnReset(ActionEvent event) {
        fillListViewDealsDisplay();
    }
}
