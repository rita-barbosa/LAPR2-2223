package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.*;

/**
 * The DisplayPropertiesUI class implements the Runnable interface and represents the user interface for display the listed properties.
 */
public class DisplayPropertiesUI implements Runnable {

    /**
     * The DisplayPropertiesController associated with this UI.
     */
    private final DisplayPropertiesController controller = new DisplayPropertiesController();

    /**
     * The type of business of a property.
     */
    private String businessType;
    /**
     * The type of property.
     */
    private String propertyType;
    /**
     * The number of bedrooms in the property.
     */
    private Integer numberBedrooms;
    /**
     * The type of sorting for price.
     */
    private String priceSorting;
    /**
     * The type of sorting for city.
     */
    private String citySorting;
    /**
     * The type of sorting for state.
     */
    private String stateSorting;


    /**
     * This method returns the DisplayPropertiesController instance associated with this UI.
     *
     * @return the DisplayAnnouncementsController instance.
     */
    private DisplayPropertiesController getController() {
        return controller;
    }

    /**
     * This runs the UI and displays the prompts to the user to obtain the necessary
     * data to display the listed properties.
     *
     */
    public void run() {
        Optional<List<Announcement>> announcementList = controller.getAllAnnouncementsList();
        if (announcementList.isPresent()) {
            List<Announcement> copyList = new ArrayList<>(announcementList.get());
            if (copyList.isEmpty()) {
                System.out.println("\nThere aren't any announcements available! Returning to the menu!");
            } else {
                System.out.println("Listed Properties:\n");
                displayAnnouncements(announcementList.get());
                boolean continueLoop = true;
                do {
                    boolean option = askOption();
                    if (option) {
                        List<String> criterias = getController().getCriteriaRepository().getCriteriaList();
                        switch (displayAndSelectCriteriaList(criterias)) {
                            case 1:
                                businessType = displayAndSelectBusinessType().toLowerCase();
                                copyList = controller.getAnnouncementsByBusinessType(businessType, copyList);
                                System.out.println("\nAnnouncements by type of business:\n");
                                displayAnnouncements(copyList);
                                break;

                            case 2:
                                propertyType = displayAndSelectPropertyType().toLowerCase();
                                copyList = controller.getAnnouncementsByPropertyType(propertyType, copyList);
                                System.out.println("\nAnnouncements by type of property:\n");
                                displayAnnouncements(copyList);
                                break;

                            case 3:
                                if (!Objects.equals(propertyType, "land")) {
                                    numberBedrooms = displayAndSelectNumberBedrooms();
                                    copyList = controller.getAnnouncementsByNumberBedrooms(numberBedrooms, copyList);
                                    System.out.println("\nAnnouncements by number of bedrooms:\n");
                                    displayAnnouncements(copyList);
                                } else {
                                    System.out.println("That option isn't available\n");
                                }
                                break;

                            case 4:
                                priceSorting = displayAndSelectPrice();
                                copyList = controller.getAnnouncementsByPrice(priceSorting, copyList);
                                System.out.println("\nAnnouncements by " + priceSorting.toLowerCase() + " price:\n");
                                displayAnnouncements(copyList);
                                break;

                            case 5:
                                citySorting = displayAndSelectCity();
                                copyList = controller.getAnnouncementsByCity(citySorting, copyList);
                                System.out.println("\nAnnouncements by " + citySorting.toLowerCase() + " city:\n");
                                displayAnnouncements(copyList);
                                break;
                            case 6:
                                stateSorting = displayAndSelectState();
                                copyList = controller.getAnnouncementsByState(stateSorting, copyList);
                                System.out.println("\nAnnouncements by " + stateSorting.toLowerCase() + " state:\n");
                                displayAnnouncements(copyList);
                                break;
                        }
                    } else {
                        continueLoop = false;
                    }
                } while (continueLoop);
//            }
            }
        }
    }

    /**
     * This method displays announcements.
     *
     * @param announcementList the announcement list
     */
    private void displayAnnouncements(List<Announcement> announcementList) {
        for (Announcement announcement : announcementList) {
            if (!announcement.isDeal()){
                System.out.println(announcement.toString());
            }
        }
    }
    /**
     * This method asks user if he wants to select any criteria.
     *
     * @return {@code true} if user wants to select any criteria;  {@code false} otherwise;
     */
    private boolean askOption() {
        System.out.println("Do you want to select any criteria? \n1 - Yes \n2 - No");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        boolean invalid = true;

        do {
            try {
                while (option != 1 && option != 2) {
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid");
                sc.nextLine();
            }
        } while (invalid);

        return option == 1;
    }


    /**
     * This method display and aks to select criteria from the criteria list.
     *
     * @param criterias the list of criterias
     * @return the criteria chosen
     */
    private Integer displayAndSelectCriteriaList(List<String> criterias) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("\nCriteria available:");
        for (String criteria : criterias) {
            count++;
            System.out.printf("%d - %s\n", count, criteria);
        }

        int option = 0;
        boolean invalid = true;
        do {
            try {
                System.out.println("Which one do you want to choose?");
                while (option < 1 || option > 6) {
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid");
                sc.nextLine();
            }
        } while (invalid);
        return option;

    }

    /**
     * This method display and aks to select a type of business.
     *
     * @return the type of business chosen
     */
    private String displayAndSelectBusinessType() {
        Scanner sc = new Scanner(System.in);
        List<BusinessType> businessTypes = controller.getBusinessTypeList();
        displayBusinessTypeOptions(businessTypes);

        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > businessTypes.size()) {
                    System.out.println("Select type of business:");
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid");
                sc.nextLine();
            }
        } while (invalid);
        return (businessTypes.get(option - 1).getDesignation());
    }

    /**
     * This method display and aks to select a type of property.
     *
     * @return the type of property chosen
     */
    private String displayAndSelectPropertyType() {
        Scanner sc = new Scanner(System.in);
        List<PropertyType> propertyTypes = controller.getPropertyTypeList();
        displayPropertiesTypeOptions(propertyTypes);

        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > propertyTypes.size()) {
                    System.out.println("Select type of property:");
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid");
                sc.nextLine();
            }
        } while (invalid);
        return (propertyTypes.get(option - 1).getDesignation());
    }

    /**
     * This method display and aks to select number of bedrooms.
     *
     * @return the number of bedrooms chosen
     */
    private Integer displayAndSelectNumberBedrooms() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean invalid = true;
        do {
            try {
                while (option < 0) {
                    System.out.println("\nEnter number of bedrooms:");
                    option = sc.nextInt();
                    if (option <= 0) {
                        System.out.println("\nERROR: Number of bedrooms is invalid.");
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid");
                sc.nextLine();
            }
        } while (invalid);
        return option;

    }

    /**
     * This method display and aks to select a type of sorting to price.
     *
     * @return the type of sorting select to price
     */
    private String displayAndSelectPrice() {
        System.out.println("\nPrice:");
        return sortSelection();
    }

    /**
     * This method display and aks to select a type of sorting to city.
     *
     * @return the type of sorting select to city
     */
    private String displayAndSelectCity() {
        System.out.println("\nCity:");
        return sortSelection();
    }

    /**
     * This method display and aks to select a type of sorting to state.
     *
     * @return the type of sorting select to state
     */
    private String displayAndSelectState() {
        System.out.println("\nState:");
        return sortSelection();
    }

    /**
     * This method display and aks to select a type of sorting.
     *
     * @return the type of sorting chosen
     */
    private String sortSelection() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort types available:");
        System.out.println("1 - Ascending");
        System.out.println("2 - Descending");

        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > 2) {
                    option = sc.nextInt();
                    if (option == 1) {
                        return "Ascending";
                    } else if (option == 2) {
                        return "Descending";
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid");
                sc.nextLine();
            }
        } while (invalid);
        return null;
    }

    /**
     * This method display the type of business options.
     *
     * @param businessTypes the business types
     */
    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        int count = 0;
        System.out.println("\nType of business:");
        for (BusinessType businessType : businessTypes) {
            count++;
            System.out.printf("%d - %s\n", count, businessType);
        }
    }

    /**
     * This method display the type of properties options.
     *
     * @param propertyTypes the property types
     */
    private void displayPropertiesTypeOptions(List<PropertyType> propertyTypes) {
        int count = 0;
        System.out.println("\nType of properties:");
        for (PropertyType propertyType : propertyTypes) {
            count++;
            System.out.printf("%d - %s\n", count, propertyType);
        }
    }

}