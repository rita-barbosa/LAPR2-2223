package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
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
    private StatisticDto statistic;
    private Integer variableValue;
    private Integer confidenceLevel;
    private List<Integer> values;
    @FXML
    private Label lblVariable;
    @FXML
    private TextField txtConfidenceLevel;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtDcc;
    @FXML
    private TextField txtBedrooms;
    @FXML
    private TextField txtBathrooms;
    @FXML
    private TextField txtParking;
    @FXML
    private ComboBox<String> cmbVariable;

    @FXML
    private ComboBox<String> cmbRegressionModel;

    @FXML
    private Label lblWarning;

    @FXML
    private ListView<String> listViewStatistics;
    @FXML
    private ListView<String> listViewForecastValue;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new AnalyzeDealsController();
        initComboBoxRegression();
        initComboBoxVariable();
        txtArea.setDisable(true);
        txtBedrooms.setDisable(true);
        txtBathrooms.setDisable(true);
        txtParking.setDisable(true);
        txtDcc.setDisable(true);
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
        List<Integer> values = new ArrayList<>();
        Set<String> regressionModelTypes = getRegressionModelTypes();
        ObservableList<String> options = FXCollections.observableArrayList(regressionModelTypes);
        cmbRegressionModel.setItems(options);
        regressionModel = cmbRegressionModel.getSelectionModel().getSelectedItem();
        if (regressionModel != null && regressionModel.equalsIgnoreCase("multilinear")) {
            cmbVariable.setDisable(true);
            txtArea.setDisable(false);
            txtBedrooms.setDisable(false);
            txtBathrooms.setDisable(false);
            txtParking.setDisable(false);
            txtDcc.setDisable(false);
        }
    }

    @FXML
    public void initComboBoxVariable() {
        Set<String> variables = getIndependentVariables();
        ObservableList<String> options = FXCollections.observableArrayList(variables);
        cmbVariable.setItems(options);
        variable = cmbVariable.getSelectionModel().getSelectedItem();
        if (regressionModel != null && regressionModel.equalsIgnoreCase("simple linear")) {
            switch (variable) {
                case "Area":
                    txtArea.setDisable(false);
                    break;
                case "Number of Bedrooms":
                    txtBedrooms.setDisable(false);
                    break;
                case "Number of Bathrooms":
                    txtBathrooms.setDisable(false);
                    break;
                case "Number of Parking Spaces":
                    txtParking.setDisable(false);
                    break;
                case "Distance of City Centre":
                    txtDcc.setDisable(false);
                    break;
            }
        }
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
        values = new ArrayList<>();
        Boolean valid = false;

        regressionModel = cmbRegressionModel.getSelectionModel().getSelectedItem();
        if (validateRegressionModel(regressionModel)) {
            if (regressionModel.equalsIgnoreCase("simple linear")) {
                variable = cmbVariable.getSelectionModel().getSelectedItem();
                if (validateVariable(variable)) {
                    switch (variable) {
                        case "Area":
                            valid = validateInteger(txtArea.getText());
                            break;
                        case "Number of Bedrooms":
                            valid = validateInteger(txtBedrooms.getText());
                            break;
                        case "Number of Bathrooms":
                            valid = validateInteger(txtBathrooms.getText());
                            break;
                        case "Number of Parking Spaces":
                            valid = validateInteger(txtParking.getText());
                            break;
                        case "Distance of City Centre":
                            valid = validateInteger(txtDcc.getText());
                            break;
                    }
                    if (valid && validateConfidenceValue(txtConfidenceLevel.getText())) {
                        updateList(regressionModel, variable);
                    }
                }
            } else {
                cmbVariable.setDisable(true);
                if (validateInteger(txtArea.getText()) && validateInteger(txtBedrooms.getText()) && validateInteger(txtBathrooms.getText())
                        && validateInteger(txtParking.getText()) && validateInteger(txtDcc.getText()) && validateConfidenceValue(txtConfidenceLevel.getText())) {
                    updateList(regressionModel, variable);
                }
            }
        }
    }

    private boolean validateInteger(String s) {
        if ((Objects.isNull(s) || s.isEmpty() || s.isBlank())) {
            lblWarning.setText("ERROR: Variable value must be inputted.");
            return false;
        } else {
            try {
                int i = Integer.parseInt(s);
                values.add(i);
                return true;
            } catch (NumberFormatException e) {
                lblWarning.setText("ERROR: Invalid value.");
            }
        }
        return false;
    }

    private boolean validateConfidenceValue(String txtVariableValues) {
        if (Objects.isNull(txtVariableValues) || txtVariableValues.isEmpty() || txtVariableValues.isBlank()) {
            lblWarning.setText("ERROR: Confidence level value must be inputted.");
            return false;
        } else {
            try {
                confidenceLevel = Integer.parseInt(txtVariableValues);
                return true;
            } catch (NumberFormatException e) {
                lblWarning.setText("ERROR: Invalid variable value.");
            }
        }
        return false;
    }


    private void getStatisticReport(String regressionModel, String variable) {
        try {
            this.statistic = controller.getStatisticsAndForecastValues(new RegressionModelTypeDto(regressionModel), variable, confidenceLevel, values);
        } catch (ReflectiveOperationException e) {
            lblWarning.setText("ERROR: " + e.getMessage());
        }
    }

    private void updateList(String regressionModel, String variable) {
        try {
            getStatisticReport(regressionModel, variable);
            listViewStatistics.getItems().add(this.statistic.getReport());
            listViewForecastValue.getItems().add(this.statistic.getValuesComparison());
        } catch (NullPointerException e) {
            lblWarning.setText("ERROR: No sale (apartment/house) deals in the system." + e.getMessage());
        } catch (MathIllegalArgumentException e) {
            lblWarning.setText("ERROR: Not enough data to generate statistics." + e.getMessage());
            System.out.println(e.getMessage());
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

    @FXML
    private void btnCloseApp(ActionEvent event) {
        Platform.exit();
    }

    public void btnResetAction(ActionEvent event) {
        this.regressionModel = null;
        this.variable = null;
        lblWarning.setText("");
        cmbVariable.getItems().clear();
        cmbVariable.setDisable(false);
        initComboBoxVariable();
        cmbRegressionModel.getItems().clear();
        initComboBoxRegression();
        listViewStatistics.getItems().clear();
        listViewForecastValue.getItems().clear();
        txtArea.setDisable(true);
        txtBedrooms.setDisable(true);
        txtBathrooms.setDisable(true);
        txtParking.setDisable(true);
        txtDcc.setDisable(true);
        txtConfidenceLevel.setText("");
    }


}

