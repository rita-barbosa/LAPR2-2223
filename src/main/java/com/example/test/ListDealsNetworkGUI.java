package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsNetworkController;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;

public class ListDealsNetworkGUI implements Initializable {

    private ListDealsNetworkController controller;

    private String algorithm = null;

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
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ListDealsNetworkScene.fxml"));
//            Parent root = loader.load();
//
//            Scene scene = new Scene(root);
//
//            announcementDealsDisplayStage = new Stage();
//            announcementDealsDisplayStage.initModality(Modality.APPLICATION_MODAL);
//            announcementDealsDisplayStage.setTitle("Announcement Deals in Network");
//            announcementDealsDisplayStage.setResizable(false);
//            announcementDealsDisplayStage.setScene(scene);

        controller = new ListDealsNetworkController();
        atualizarListViewDealsDisplay();
    }

    public void atualizarListViewDealsDisplay() {
        //  listViewDealsDisplay.getItems().addAll(controller.toDto(controller.getAllDealsList()).toString());
        Optional<List<Announcement>> allAnnouncementsList = controller.getAgencyRepository().getAllAnnouncementsList();
        if (allAnnouncementsList.isPresent()) {
            for (Announcement announcement : allAnnouncementsList.get()) {
                listViewDealsDisplay.getItems().add(announcement.toString());
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
        ToggleGroup orderToggleGroup = radiobtnAscendingOrder.getToggleGroup();
        orderToggleGroup.selectToggle(null);
        ToggleGroup algorithmToggleGroup = radiobtnBubbleSort.getToggleGroup();
        algorithmToggleGroup.selectToggle(null);
        lblWarning.setText("");
        atualizarListViewDealsDisplay();
    }

    @FXML
    void sortList(ActionEvent event) {
        checkWarningMessage();
        if (algorithm != null && sortingOrder != null) {
            listViewDealsDisplay.getItems().clear();
            Optional<List<AnnouncementDto>> sortedList = controller.getListSortedByAlgorithm(sortingOrder, algorithm);
            if (sortedList.isPresent()) {
                for (Object announcement : sortedList.get().toArray()) {
                    listViewDealsDisplay.getItems().add(announcement.toString());
                }
            }
            lblWarning.setText("");
        }
    }

    private void checkWarningMessage() {
        if (algorithm == null && sortingOrder == null){
            lblWarning.setText("Sorting algorithm must be selected.\nSorting order must be selected.");
        }else if (algorithm == null ){
            lblWarning.setText("Sorting algorithm must be selected.");
        }else {
            lblWarning.setText("Sorting order must be selected.");
        }
    }
}