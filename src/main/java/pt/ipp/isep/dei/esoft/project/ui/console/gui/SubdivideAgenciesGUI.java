package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.SubdivideAgenciesController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class SubdivideAgenciesGUI implements Initializable {
    private SubdivideAgenciesController controller;

    @FXML
    private Label txtAreaSubset1;
    @FXML
    private Label txtAreaSubset2;
    @FXML
    private Label txtDifference;
    @FXML
    private Label lblWarning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new SubdivideAgenciesController();

    }

    private void fillListViewDealsDisplay() {
        try {
//            displayTimeExecutionWarningMessage();
            List<String> sublistStringList = controller.getAgenciesPartitions();
            displaysList(sublistStringList);
        } catch (IndexOutOfBoundsException e) {
            lblWarning.setText("ERROR: " + e.getMessage());
        } catch (Exception e) {
            lblWarning.setText("ERROR: Couldn't generate subsets.");
        }
    }

    private void displaysList(List<String> sublistStringList) {
        txtAreaSubset1.setText(sublistStringList.get(0));
        txtAreaSubset2.setText(sublistStringList.get(1));
        txtDifference.setText(sublistStringList.get(2));
    }


    @FXML
    public void btnExit(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    public void btnDisplaySubset(ActionEvent event) {
        fillListViewDealsDisplay();
    }


    @FXML
    public void btnReset(ActionEvent event) {
        txtAreaSubset1.setText("");
        txtAreaSubset2.setText("");
        txtDifference.setText("");
    }
}
