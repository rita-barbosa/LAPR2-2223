package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.lang3.time.StopWatch;
import pt.ipp.isep.dei.esoft.project.application.controller.SubdivideAgenciesController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class SubdivideAgenciesGUI implements Initializable {
    private SubdivideAgenciesController controller;
    private final String TIME_UNIT ="ms";

    @FXML
    private Label txtAreaSubset1;
    @FXML
    private Label txtAreaSubset2;
    @FXML
    private Label txtDifference;
    @FXML
    private Label txtExecutionTime;
    @FXML
    private Label lblWarning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new SubdivideAgenciesController();

    }

    private void fillListViewDealsDisplay() {
        try {
            lblWarning.setText("THIS TASK MIGHT TAKE A WHILE.");
            List<String> sublistStringList = controller.getAgenciesPartitions();
            lblWarning.setText("");
            displaysList(sublistStringList);
        } catch (IndexOutOfBoundsException e) {
            lblWarning.setText("ERROR: " + e.getMessage());
        } catch (Exception e) {
            lblWarning.setText("ERROR: Couldn't execute task.");
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
        StopWatch stopWatch = StopWatch.createStarted();
        fillListViewDealsDisplay();
        stopWatch.stop();
        long executionTime = stopWatch.getTime(TimeUnit.MILLISECONDS);
        txtExecutionTime.setText(executionTime + TIME_UNIT);
    }


    @FXML
    public void btnReset(ActionEvent event) {
        txtAreaSubset1.setText("");
        txtAreaSubset2.setText("");
        txtDifference.setText("");
        txtExecutionTime.setText("");
        lblWarning.setText("");
    }
}
