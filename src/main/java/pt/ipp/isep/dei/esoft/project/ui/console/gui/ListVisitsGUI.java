package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.ListVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListVisitsGUI implements Initializable {

    private ListVisitsController controller;

    private LocalDate beginDate = null;

    private LocalDate endDate = null;

    @FXML
    private ListView<String> visitsListDisplay;

    @FXML
    private DatePicker btnBeginDate;

    @FXML
    private DatePicker btnEndDate;

    @FXML
    private Label lblWarning;

    @FXML
    private Button btnSortButton;

    @FXML
    private Button btnReset;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new ListVisitsController();
    }

    public void updateListToDisplay() {
        Optional<List<VisitDto>> allVisitDtoList;
        try {
            allVisitDtoList = controller.getVisitRequestsList(beginDate, endDate);
            if (allVisitDtoList.isPresent()){
                for (VisitDto visit : allVisitDtoList.get()){
                    visitsListDisplay.getItems().add(visit.toString());
                    btnSortButton.setDisable(true);
                }
            }
            lblWarning.setText("");
        } catch (Exception e) {
            lblWarning.setText("ERROR: INVALID CONFIGURATION FILE!!\n *CONTACT SYSTEM ADMINISTRATOR!!\n\n *CLICK IN THE RETURN BUTTON!!");
            btnSortButton.setDisable(true);
            btnReset.setDisable(true);
//            throw new RuntimeException(e);
        }

//        if (allVisitDtoList.isPresent()){
//            for (VisitDto visit : allVisitDtoList.get()){
//                visitsListDisplay.getItems().add(visit.toString());
//                btnSortButton.setDisable(true);
//            }
//        }


    }

    @FXML
    public void btnGetList(ActionEvent event) {
        beginDate = btnBeginDate.getValue();
        endDate = btnEndDate.getValue();
        if (validateDates(beginDate, endDate)){
            updateListToDisplay();
//            lblWarning.setText("");
            btnSortButton.setDisable(true);
        }
    }

    @FXML
    public void setBeginDate(ActionEvent event){
        beginDate = btnBeginDate.getValue();
        if (beginDate != null) {
            btnBeginDate.setDisable(true);
        }
    }

    @FXML
    public void setEndDate(ActionEvent event){
        endDate = btnEndDate.getValue();
        if (endDate != null) {
            btnEndDate.setDisable(true);
        }
    }


    public void btnResetAction(ActionEvent event){
        this.beginDate = null;
        this.endDate = null;
        btnBeginDate.getEditor().clear();
        btnEndDate.getEditor().clear();
        btnBeginDate.setDisable(false);
        btnEndDate.setDisable(false);
        lblWarning.setText("");
        visitsListDisplay.getItems().clear();
        btnSortButton.setDisable(false);
    }


    private boolean validateDates(LocalDate beginDate, LocalDate endDate){
        if (beginDate == null && endDate == null){
            lblWarning.setText("ERROR: Begin date must be selected. \nERROR: End date must be selected. ");
            return false;
        } else if (beginDate == null){
            lblWarning.setText("ERROR: Begin date must be selected.");
            return false;
        } else if (endDate == null){
            lblWarning.setText("ERROR: End date must be selected.");
            return false;
        } else if (endDate.isBefore(beginDate)){
            lblWarning.setText("ERROR: The end date can't be before \nthe begin date. \nPlease reset the dates.");
            return false;
        }
        return true;
    }

    @FXML
    private void btnCloseApp(ActionEvent event){
        Platform.exit();
    }

}