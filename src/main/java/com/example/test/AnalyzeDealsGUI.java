package com.example.test;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsNetworkController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.IndependentVariables;
import pt.ipp.isep.dei.esoft.project.domain.dto.RegressionModelTypeDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.StatisticDto;

import java.util.ResourceBundle;

import java.net.URL;
import java.util.*;

public class AnalyzeDealsGUI implements Initializable {
    private AnalyzeDealsController controller;
    private String regressionModel;
    private String variable;
    @FXML
    private ComboBox<String> cmbVariable;

    @FXML
    private ComboBox<String> cmbRegressionModel;

    @FXML
    private Label lblWarning;

    @FXML
    private ListView<String> listViewDealsDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new AnalyzeDealsController();
        initComboBoxRegression();
        initComboBoxVariable();
    }

    private Set<String> getRegressionModelTypes() {
        Set<String> options = new HashSet<>();
        for (RegressionModelTypeDto r : controller.getRegressionModelTypeList()) {
            options.add(r.getDesignation());
        }
        return options;
    }

    @FXML
    public void initComboBoxRegression() {
        Set<String> regressionModelTypes = getRegressionModelTypes();
        ObservableList<String> options = FXCollections.observableArrayList(regressionModelTypes);
        cmbRegressionModel.setItems(options);
    }

    @FXML
    public void initComboBoxVariable() {
        Set<String> variables = getIndependentVariables();
        ObservableList<String> options = FXCollections.observableArrayList(variables);
        cmbVariable.setItems(options);
    }

    private Set<String> getIndependentVariables() {
        Set<String> values = new HashSet<>();
        for (IndependentVariables i :
                IndependentVariables.values()) {
            values.add(i.toString());
        }
        return values;
    }

    @FXML
    public void btnNextAction(ActionEvent event) {
        regressionModel = cmbRegressionModel.getSelectionModel().getSelectedItem();
        if (validateRegressionModel(regressionModel)) {
            if (regressionModel.equalsIgnoreCase("simple linear")) {
                variable = cmbVariable.getSelectionModel().getSelectedItem();
                if (validateVariable(variable)) {
                    updateList(regressionModel, variable);
                }
            } else {
                cmbVariable.setDisable(true);
                updateList(regressionModel, variable);
            }
        }
    }

    private String getStatisticReport(String regressionModel, String variable) {
        try {
            StatisticDto s = controller.getStatisticsAndForecastValues(new RegressionModelTypeDto(regressionModel), variable);
            return s.getReport();
        } catch (ReflectiveOperationException | NullPointerException e) {
            lblWarning.setText("ERROR: " + e.getMessage());
        }
        return "No data available";
    }

    private void updateList(String regressionModel, String variable) {
        try {
            listViewDealsDisplay.getItems().add(getStatisticReport(regressionModel, variable));
        } catch (NullPointerException e) {
            lblWarning.setText("ERROR: No sale (apartment/house) deals in the system.");
        } catch (MathIllegalArgumentException e) {
            lblWarning.setText("ERROR: Not enough data to generate statistics.");
        }
    }

    private boolean validateRegressionModel(String value) {
        if (Objects.isNull(value) || value.isEmpty() || value.isBlank()) {
            lblWarning.setText("ERROR: Regression Model must be chosen.");
            return false;
        }
        return true;
    }

    private boolean validateVariable(String value) {
        if (Objects.isNull(value) || value.isEmpty() || value.isBlank()) {
            lblWarning.setText("ERROR: Independent Variable must be chosen");
            return false;
        }
        return true;
    }


    public void btnResetAction(ActionEvent event) {
        this.regressionModel = null;
        this.variable = null;
        cmbVariable.getItems().clear();
        cmbVariable.setDisable(false);
        initComboBoxVariable();
        cmbRegressionModel.getItems().clear();
        listViewDealsDisplay.getItems().clear();
    }
}
