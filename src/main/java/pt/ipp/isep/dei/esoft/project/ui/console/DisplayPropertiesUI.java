package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Residence;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

public class DisplayPropertiesUI implements Runnable {

    private final DisplayPropertiesController controller = new DisplayPropertiesController();

    private String businessTypeDesignation;
    private String propertyTypeDesignation;
    private Integer numberBedroomsDesignation;
    private Double priceDesignation;
    private String priceSorting;
    private String cityDesignation;
    private String stateDesignation;


    private DisplayPropertiesController getController(){
        return controller;
    }

    public void run() {
        List<Announcement> announcementList = controller.sortAnnouncementsByMostRecentAdded();

        System.out.println("Display Listed Properties");

        displayAnnouncements(announcementList);

        List<Integer> options = displayAndSelectCriteriaList();

        //List<CriteriaItem> options = new ArrayList<CriteriaItem>();
//        int option = 0;
//        do {
//            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");
//
//            if ((option >= 0) && (option < options.size())) {
//                options.get(option).run();
//            }
//        } while (option != -1);

        for (Integer option : options) {
            switch (option){
                case 1:
                    businessTypeDesignation = displayAndSelectBusinessType();
                    for (Announcement announcement : announcementList) {

                    }
                    break; //check this part again!
                case 2:
                    propertyTypeDesignation = displayAndSelectPropertyType();
                    for (Announcement announcement : announcementList) {

                    }
                    break;
                case 3:
                    numberBedroomsDesignation = displayAndSelectNumberBedrooms();
                    for (Announcement announcement : announcementList) {

                    }
                    break;
                case 4:
                    //priceDesignation = displayAndSelectPrice();
                    priceSorting = displayAndSelectPrice();
                    for (Announcement announcement : announcementList) {

                    }
                    break;
                case 5:
                    cityDesignation = displayAndSelectCity();
                    for (Announcement announcement : announcementList) {

                    }
                    break;
                case 6:
                    stateDesignation = displayAndSelectState();
                    for (Announcement announcement : announcementList) {

                    }
                    break;
            }
        }
    }

    private void displayAnnouncements(List <Announcement> announcementList){
        for (Announcement announcement : announcementList) {
            System.out.println(announcement.toString());
        }
    }

    private List<Integer> displayAndSelectCriteriaList(){
        Scanner sc = new Scanner(System.in);
        List<Integer> options = new ArrayList<>();

        System.out.println("\n");
        System.out.println("Criteria available");
        System.out.println("1 - Business Type");
        System.out.println("2 - Property Type");
        System.out.println("3 - Number of Bedrooms");
        System.out.println("4 - Sort by Price");
        System.out.println("5 - Sort by City");
        System.out.println("6 - Sort by State");
        System.out.println("-1 - Go back to the Menu");
        System.out.println("\n");

        int option = 0;
        while (option >= 0){
            option = sc.nextInt();
            options.add(option);
        }
        return options;

    }

    private String displayAndSelectBusinessType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Types of Business:");
        System.out.println("1 - Sale");
        System.out.println("2 - Lease");

        int option = 0;
        option = sc.nextInt();
        //boolean valid = true;

        if (option == 1){
            return "sale";
        }else {
            return "Lease";
        }

//        do {
//            switch (option) {
//                case 1:
//                    return "sale";
//                break;
//                case 2:
//                    return "lease";
//                break;
//                default:
//                    System.out.println("Invalid input. Please select a valid option.");
//                    return null;
//                break;
//            }
//        }while (true);
    }

    private String displayAndSelectPropertyType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Types of Properties:");
        System.out.println("1 - Land");
        System.out.println("2 - Apartment");
        System.out.println("3 - House");

        int option = 0;
        option = sc.nextInt();
        //boolean valid = true;

        if (option == 1){
            return "Land";
        } else if (option == 2) {
            return "Apartment";
        } else {
            return "House";
        }

//        switch (option){
//            case 1:
//                //return "land";
//            break;
//            case 2:
//                //return "apartment";
//            break;
//            case 3:
//                //return "house";
//            break;
//        }
//        return null;
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
        Scanner sc = new Scanner(System.in);

        return sortSelection();
    }

    private String displayAndSelectCity(){
        Scanner sc = new Scanner(System.in);

        return sortSelection();
    }

    private String displayAndSelectState(){
        Scanner sc = new Scanner(System.in);

        return sortSelection();
    }

    private String sortSelection(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort types available:");
        System.out.println("1 - Ascending");
        System.out.println("2 - Descending");

        int option = 0;
        option = sc.nextInt();

        if (option == 1){
            return "Ascending";
        }else {
            return "Descending";
        }
    }

}
