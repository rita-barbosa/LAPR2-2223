package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;

import java.util.*;

public class DisplayPropertiesUI implements Runnable {

    private final DisplayPropertiesController controller = new DisplayPropertiesController();

    private String businessType;
    private String propertyType;
    private Integer numberBedrooms;
    private Double price;
    private String priceSorting;
    private String city;
    private String citySorting;
    private String state;
    private String stateSorting;


    private DisplayPropertiesController getController(){
        return controller;
    }

    public void run() {
        List<Announcement> announcementList = controller.sortAnnouncementsByMostRecentAdded();

        System.out.println("Display Listed Properties");
        displayAnnouncements(announcementList);

        List<String> criterias = getController().getCriteriaRepository().getCriteriaList();
        displayCriteriaList(criterias);

        if (askOptionalData("Type Of Business")){
            businessType = displayAndSelectBusinessType().toLowerCase();
            announcementList = controller.getAnnouncementsByBusinessType(announcementList, businessType);
            displayAnnouncements(announcementList);

        }

        if (askOptionalData("Type Of Property")){
            propertyType = displayAndSelectPropertyType().toLowerCase();
            announcementList = controller.getAnnouncementsByPropertyType(announcementList, propertyType);
            displayAnnouncements(announcementList);

        }

        if (!Objects.equals(propertyType, "land")){
            if (askOptionalData("Number Of Bedrooms")){
                numberBedrooms = displayAndSelectNumberBedrooms();
                announcementList = controller.getAnnouncementsByNumberBedrooms(announcementList, numberBedrooms);
                displayAnnouncements(announcementList);
            }
        }

        if (askOptionalData("Price")){
            priceSorting = displayAndSelectPrice();
            announcementList = controller.getAnnouncementsByPrice(announcementList, priceSorting);
            displayAnnouncements(announcementList);
        }

        if (askOptionalData("City")){
            citySorting = displayAndSelectCity();
            announcementList = controller.getAnnouncementsByCity(announcementList, citySorting);
            displayAnnouncements(announcementList);

        }

        if (askOptionalData("State")){
            stateSorting = displayAndSelectState();
            announcementList = controller.getAnnouncementsByState(announcementList, stateSorting);
            displayAnnouncements(announcementList);
        }

    }

    private void displayAnnouncements(List <Announcement> announcementList){
        for (Announcement announcement : announcementList) {
            System.out.println(announcement.toString());
        }
    }


    private boolean askOptionalData(String optionalData) {
        System.out.printf("Would you like to select %s to search for Announcements: \n1 - Yes \n2 - No\n", optionalData);
        Scanner sc = new Scanner(System.in);
        int inputDataOption = 0;
        while (inputDataOption != 1 && inputDataOption != 2) {
            inputDataOption = sc.nextInt();
            if (inputDataOption != 1 && inputDataOption != 2) {
                throw new InputMismatchException("Please select one option\n");
            }
        }
        sc.close();
        return inputDataOption == 1;
    }

    private void displayCriteriaList(List<String> criterias){
        int count = 0;
        System.out.println("\n");
        System.out.println("Criteria available");
        for (String criteria : criterias) {
            count++;
            System.out.printf("%d - %s\n%n", count, criteria );
        }
        System.out.println("\n");
    }


    private String displayAndSelectBusinessType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Types of Business:");
        System.out.println("1 - Sale");
        System.out.println("2 - Lease");

        int option = 0;
        option = sc.nextInt();

        do{
            if (option == 1){
                return "Sale";
            } else if (option == 2) {
                return "Lease";
            } else {
                System.out.println("This option is invalid, select another.");
            }
            option = sc.nextInt();
        } while (option < 1 || option > 2);
        return null;
    }

    private String displayAndSelectPropertyType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Types of Properties:");
        System.out.println("1 - Land");
        System.out.println("2 - Apartment");
        System.out.println("3 - House");

        int option = 0;
        option = sc.nextInt();

        do {
            if (option == 1){
                return "Land";
            } else if (option == 2) {
                return "Apartment";
            } else if (option == 3){
                return "House";
            } else {
                System.out.println("This option is invalid, select another.");
            }
            option = sc.nextInt();
        } while (option < 1 || option > 3);
        return null;
    }

    private Integer displayAndSelectNumberBedrooms() {
        Scanner sc = new Scanner(System.in);
        Integer option;

        option = Integer.parseInt(sc.nextLine());
        if (option < 0){
            System.out.println("That option isn't available");
            return null;
        } else {
            return option;
        }
    }

    private String displayAndSelectPrice(){
        return sortSelection();
    }

    private String displayAndSelectCity(){
        return sortSelection();
    }

    private String displayAndSelectState(){
       return sortSelection();
    }

    private String sortSelection(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort types available:");
        System.out.println("1 - Ascending");
        System.out.println("2 - Descending");

        int option = 0;
        option = sc.nextInt();

        do {
            if (option == 1){
                return "Ascending";
            }else if (option == 2){
                return "Descending";
            } else {
                System.out.println("This option is invalid, select another.");
            }
            option = sc.nextInt();
        } while (option < 1 || option > 2);
        return null;
    }

}
