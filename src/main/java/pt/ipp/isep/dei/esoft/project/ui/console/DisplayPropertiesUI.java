package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

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


    private DisplayPropertiesController getController() {
        return controller;
    }

    public void run() {
        List<Announcement> announcementList = controller.sortAnnouncementsByMostRecentAdded();

        System.out.println("Listed Properties:\n");
        displayAnnouncements(announcementList);
        boolean option = askOption();
        do {
            if (option) {
                List<String> criterias = getController().getCriteriaRepository().getCriteriaList();
                switch (displayAndSelectCriteriaList(criterias)) {
                    case 1:
                        businessType = displayAndSelectBusinessType().toLowerCase();
                        announcementList = controller.getAnnouncementsByBusinessType(announcementList, businessType);
                        System.out.println("Announcements by type of business:\n");
                        displayAnnouncements(announcementList);
                        break;

                    case 2:
                        propertyType = displayAndSelectPropertyType().toLowerCase();
                        announcementList = controller.getAnnouncementsByPropertyType(announcementList, propertyType);
                        System.out.println("Announcements by type of property:\n");
                        displayAnnouncements(announcementList);
                        break;

                    case 3:
                        if (!Objects.equals(propertyType, "land")) {
                            numberBedrooms = displayAndSelectNumberBedrooms();
                            announcementList = controller.getAnnouncementsByNumberBedrooms(announcementList, numberBedrooms);
                            displayAnnouncements(announcementList);
                        } else {
                            System.out.println("That option isn't available");
                        }
                        break;

                    case 4:
                        priceSorting = displayAndSelectPrice();
                        announcementList = controller.getAnnouncementsByPrice(announcementList, priceSorting);
                        displayAnnouncements(announcementList);
                        break;

                    case 5:
                        citySorting = displayAndSelectCity();
                        announcementList = controller.getAnnouncementsByCity(announcementList, citySorting);
                        displayAnnouncements(announcementList);
                        break;
                    case 6:
                        stateSorting = displayAndSelectState();
                        announcementList = controller.getAnnouncementsByState(announcementList, stateSorting);
                        displayAnnouncements(announcementList);
                        break;
                }
            } else {
                System.out.println("Thank you for using our application.");
            }
            option = askOption();
        } while (option);
    }

    private void displayAnnouncements(List<Announcement> announcementList) {
        for (Announcement announcement : announcementList) {
            System.out.println(announcement.toString());
        }
    }

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
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);

        return option == 1;
    }


    private Integer displayAndSelectCriteriaList(List<String> criterias) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("Criteria available:");
        for (String criteria : criterias) {
            count++;
            System.out.printf("%d - %s\n", count, criteria);
        }
        System.out.println("\n");

        int option = 0;
        boolean invalid = true;
        do {
            try {
                System.out.println("Which one do you want to choose?\n");
                while (option < 1 || option > 6) {
                    option = sc.nextInt();
                    return option;
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return null;

    }

    private String displayAndSelectBusinessType() {
        Scanner sc = new Scanner(System.in);
        List<BusinessType> businessTypes = controller.getBusinessTypes();
        displayBusinessTypeOptions(businessTypes);

        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > 2) {
                    option = sc.nextInt();
                    if (option == 1) {
                        return "Sale";
                    } else if (option == 2) {
                        return "Lease";
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return null;
    }

    private String displayAndSelectPropertyType() {
        Scanner sc = new Scanner(System.in);
        List<PropertyType> propertyTypes = controller.getPropertyTypes();
        displayPropertiesTypeOptions(propertyTypes);

        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > 3) {
                    option = sc.nextInt();
                    if (option == 1) {
                        return "Land";
                    } else if (option == 2) {
                        return "Apartment";
                    } else if (option == 3) {
                        return "House";
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return null;
    }

    private Integer displayAndSelectNumberBedrooms() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean invalid = true;
        do {
            try {
                while (option < -1) {
                    option = sc.nextInt();
                    if (option > 0) {
                        return option;
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return null;

    }

    private String displayAndSelectPrice() {
        System.out.println("Price:");
        return sortSelection();
    }

    private String displayAndSelectCity() {
        System.out.println("City:");
        return sortSelection();
    }

    private String displayAndSelectState() {
        System.out.println("State:");
        return sortSelection();
    }

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
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return null;
    }

    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        int count = 0;
        System.out.println("Type of business:");
        for (BusinessType businessType : businessTypes) {
            count++;
            System.out.printf("%d - %s\n", count, businessType);
        }
    }

    private void displayPropertiesTypeOptions(List<PropertyType> propertyTypes) {
        int count = 0;
        System.out.println("Type of properties:");
        for (PropertyType propertyType : propertyTypes) {
            count++;
            System.out.printf("%d - %s\n", count, propertyType);
        }
    }

}
