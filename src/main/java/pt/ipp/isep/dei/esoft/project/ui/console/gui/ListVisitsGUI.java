package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.ListVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListVisitsGUI implements Initializable {

    private ListVisitsController controller;

//    private LocalDate beginDate = LocalDate.of(2023, 7, 1);

//    private LocalDate endDate = LocalDate.of(2024, 1, 1);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new ListVisitsController();
//        if (beginDate != null && endDate != null){
//            updateListToDisplay();
//        }
    }

    public void updateListToDisplay(){
        Optional<List<VisitDto>> allVisitDtoList;
        try {
            allVisitDtoList = controller.getVisitRequestsList(beginDate, endDate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (allVisitDtoList.isPresent()){
           for (VisitDto visit : allVisitDtoList.get()){
               visitsListDisplay.getItems().add(visit.toString());
           }
       }
    }

    @FXML
    public void btnGetList(ActionEvent event){
        beginDate = btnBeginDate.getValue();
        endDate = btnEndDate.getValue();
        if (validateDates(beginDate, endDate)){
            updateListToDisplay();
            lblWarning.setText("");
        }
    }

//    @FXML
//    public void resetValues(ActionEvent event) {
//        beginDate = null;
//        btnBeginDate.getEditor().clear();
//        btnBeginDate.setDisable(false);
//        endDate = null;
//        btnEndDate.getEditor().clear();
//        btnEndDate.setDisable(false);
//        lblWarning.setText("");
//        updateListToDisplay();
//    }

    @FXML
    void setBeginDate(ActionEvent event){
        beginDate = btnBeginDate.getValue();
        btnBeginDate.setDisable(true);
    }

    @FXML
    void setEndDate(ActionEvent event){
        endDate = btnEndDate.getValue();
        btnEndDate.setDisable(true);

    }

    @FXML
    void sortList(ActionEvent event){

    }



    private void checkWarningMessage(){
        if (beginDate == null && endDate == null){
            lblWarning.setText("Begin date must be selected. \nEnd date must be selected. ");
        } else if (beginDate == null) {
            lblWarning.setText("Begin date must be selected.");
        } else {
            lblWarning.setText("End date must be selected.");
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
    }

    private void updateList(LocalDate beginDate, LocalDate endDate){

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
