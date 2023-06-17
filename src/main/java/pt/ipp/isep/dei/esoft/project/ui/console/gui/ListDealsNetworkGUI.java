package pt.ipp.isep.dei.esoft.project.ui.console.gui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsNetworkController;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;

public class ListDealsNetworkGUI implements Initializable {

    private ListDealsNetworkController controller;

    private String algorithm = null;

    private Boolean isItSorted = false;

    private String sortingOrder = null;

    @FXML
    private ListView<String> listViewDealsDisplay;

    @FXML
    private RadioButton radiobtnAscendingOrder;

    @FXML
    private RadioButton radiobtnBubbleSort;

    @FXML
    private RadioButton radiobtnDescendingOrder;

    @FXML
    private RadioButton radiobtnMergeSort;

    @FXML
    private Label lblWarning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new ListDealsNetworkController();
        atualizarListViewDealsDisplay();
    }

    public void atualizarListViewDealsDisplay() {
        if (controller.toDto(controller.getAllDealsList()).isPresent()) {
            List<AnnouncementDto> announcementDtos = controller.toDto(controller.getAllDealsList()).get();
            for (AnnouncementDto announcement : announcementDtos) {
                listViewDealsDisplay.getItems().add(announcement.toDealString());
            }
        }
    }

    @FXML
    void setSortingOrder(ActionEvent event) {
        if (radiobtnAscendingOrder.isSelected()) {
            sortingOrder = "Ascending";
        } else if (radiobtnDescendingOrder.isSelected()) {
            sortingOrder = "Descending";
        }
    }

    @FXML
    void setSortingAlgorithm(ActionEvent event) {
        if (radiobtnMergeSort.isSelected()) {
            algorithm = "Merge Sort";
        } else if (radiobtnBubbleSort.isSelected()) {
            algorithm = "Bubble Sort";
        }
    }

    @FXML
    void resetValues(ActionEvent event) {
        algorithm = null;
        sortingOrder = null;
        isItSorted = false;
        ToggleGroup orderToggleGroup = radiobtnAscendingOrder.getToggleGroup();
        orderToggleGroup.selectToggle(null);
        ToggleGroup algorithmToggleGroup = radiobtnBubbleSort.getToggleGroup();
        algorithmToggleGroup.selectToggle(null);
        lblWarning.setText("");
        resetListView();
        atualizarListViewDealsDisplay();
    }


    void resetListView() {
        listViewDealsDisplay.getItems().clear();
    }

    @FXML
    void sortList(ActionEvent event) {
        checkWarningMessage();
        if (algorithm != null && sortingOrder != null && !isItSorted) {
            resetListView();
            Optional<List<AnnouncementDto>> sortedList = controller.getListSortedByAlgorithm(sortingOrder, algorithm);
            if (sortedList.isPresent()) {
                isItSorted = true;
                for (AnnouncementDto dto : sortedList.get()) {
                    listViewDealsDisplay.getItems().add(dto.toDealString());
                }
            }
            lblWarning.setText("");
        }
    }

    private void checkWarningMessage() {
        if (algorithm == null && sortingOrder == null) {
            lblWarning.setText("Sorting algorithm must be selected.\nSorting order must be selected.");
        } else if (algorithm == null) {
            lblWarning.setText("Sorting algorithm must be selected.");
        } else {
            lblWarning.setText("Sorting order must be selected.");
        }
        if (isItSorted) {
            lblWarning.setText("Must click 'Reset' button.");
        }
    }
}
