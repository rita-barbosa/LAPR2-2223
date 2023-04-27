package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
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
    private String cityDesignation;
    private String stateDesignation;


    private DisplayPropertiesController getController(){
        return controller;
    }

    public void run() {
        System.out.println("Display Listed Properties");

        displayAnnouncements();

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

        List<MenuItem> options = new ArrayList<MenuItem>();
        //List<CriteriaItem> options = new ArrayList<CriteriaItem>();
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

        switch (option){
            case 1:
                businessTypeDesignation = displayAndSelectBusinessType();
                break; //check this part again!
            case 2:
                propertyTypeDesignation = displayAndSelectPropertyType();
                break;
            case 3:
                numberBedroomsDesignation = displayAndSelectNumberBedrooms();
                break;
            case 4:
                priceDesignation = displayAndSelectPrice();
                break;
            case 5:
                cityDesignation = displayAndSelectCity();
                break;
            case 6:
                stateDesignation = displayAndSelectState();
                break;
        }
    }

    private String displayAnnouncements(){
        return null;

    }

    private String displayAndSelectBusinessType() {
        System.out.println("1 - Sale");
        System.out.println("2 - Lease");

        List<MenuItem> options = new ArrayList<MenuItem>();
        //List<CriteriaItem> options = new ArrayList<CriteriaItem>();
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

        switch (option){
            case 1:
                //return "sale";
                break;
            case 2:
                //return "lease";
                break;
        }

        return null;
    }

    private String displayAndSelectPropertyType() {
        System.out.println("1 - Land");
        System.out.println("2 - Apartment");
        System.out.println("3 - House");

        List<MenuItem> options = new ArrayList<MenuItem>();
        //List<CriteriaItem> options = new ArrayList<CriteriaItem>();
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);

        switch (option){
            case 1:
                //return "land";
            break;
            case 2:
                //return "apartment";
            break;
            case 3:
                //return "house";
            break;
        }
        return null;
    }

    private Integer displayAndSelectNumberBedrooms() {
        Integer option;
        Scanner sc = new Scanner(in);

        option = 1; //option = sc.nextLine(); //!!!
        if (option < 0){
            System.out.println("That option isn't available");
        } else {
            return option;
        }
    }

    private Double displayAndSelectPrice(){
      sortSelection();

        return null;
    }

    private String displayAndSelectCity(){
        sortSelection();

        return null;
    }

    private String displayAndSelectState(){
        sortSelection();

        return null;
    }

    private Integer sortSelection(){
        System.out.println("1 - Ascending");
        System.out.println("2 - Descending");

        return 1;
    }



}
