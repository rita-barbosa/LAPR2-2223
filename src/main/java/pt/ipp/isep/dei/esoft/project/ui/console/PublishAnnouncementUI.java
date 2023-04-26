package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.SunExposureTypes;


import java.util.*;

public class PublishAnnouncementUI implements Runnable {
    private final PublishAnnouncementController controller = new PublishAnnouncementController();

    private String propertyTypeDesignation;
    private String commissionTypeDesignation;
    private Double commissionValue;
    private String ownerEmail;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;
    private Double area;
    private Double distanceCityCenter;
    private Double price;
    private List<String> uriList;
    private Integer numberBedroom;
    private Integer numberParkingSpace;
    private Integer numberBathroom;
    private List<String> availableEquipmentDescriptionList;
    private Boolean existenceBasement;
    private Boolean inhabitableLoft;
    private SunExposureTypes sunExposure;
    private final int MAX_URIS = 30;

    private PublishAnnouncementController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\nPublish Announcement");

        commissionTypeDesignation = displayAndSelectCommissionType();
        commissionValue = requestCommissionValue();
        ownerEmail = requestOwnerEmail();
        propertyTypeDesignation = displayAndSelectPropertyType();
        requestLocation();
        area = requestArea();
        distanceCityCenter = requestDistanceCityCenter();
        price = requestPrice();
        uriList = requestUri();
        numberBedroom = requestNumberBedroom();
        numberParkingSpace = requestNumberParkingSpace();
        numberBathroom = requestNumberBathroom(); //optional
        availableEquipmentDescriptionList = requestAvailableEquipment(); //optional + loop
        existenceBasement = requestExistenceBasement();
        inhabitableLoft = requestInhabitableLoft();
        sunExposure = requestSunExposure(); // optional
//        requestConfirmation();

        submitData();
    }

    private void submitData() {
//        Optional<Announcement> announcement = getController().publishAnnoucement(commissionValue, commissionTypeDesignation, ownerEmail, propertyTypeDesignation,streetName, city, district, state, zipCode, area, distanceCityCenter,price,numberBedroom,numberParkingSpace,existenceBasement,inhabitableLoft, numberBathroom, availableEquipmentDescriptionList, uriList, sunExposure);
    }

//    private void requestConfirmation();
//            showData();
//    }
//
//    private void showsData() {
//
//    }


    private Integer requestNumberParkingSpace() {
        System.out.println("Number of parking spaces:");
        return getIntegerAnswer();
    }

    private SunExposureTypes requestSunExposure() {
        Scanner input = new Scanner(System.in);
        SunExposureTypes sunExposure = null;
        while (sunExposure == null) {
            System.out.println("Enter the sun exposure (North, South, East or West) or press enter to skip:");
            String answer = input.nextLine().trim();
            if (answer.isEmpty()) {
                return null;
            }
            try {
                sunExposure = SunExposureTypes.valueOf(answer.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter North, South, East or West.");
            }
        }
        return sunExposure;

    }


    private Boolean getBooleanAnswer() {
        Scanner input = new Scanner(System.in);
        String answer;
        Boolean value = null;
        answer = input.nextLine();
        while (value == null) {
            if (answer.equalsIgnoreCase("t")) {
                value = true;
            } else if (answer.equalsIgnoreCase("f")) {
                value = false;
            } else {
                System.out.println("\nERROR: The input provided is not valid. Please try again.");
                answer = input.nextLine();
            }
        }
        return value;

    }

    private Boolean requestInhabitableLoft() {
        System.out.println("Inhabitable loft (T/F):");
        return getBooleanAnswer();

    }

    private Boolean requestExistenceBasement() {
        System.out.println("Existence of a basement (T/F):");
        return getBooleanAnswer();
    }

    private List<String> requestAvailableEquipment() {
        List<String> descriptionList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.print("Enter a description for an available equipment (or 'quit' to exit): ");
            answer = input.nextLine();
            if (answer.equalsIgnoreCase("quit")) {
                break;
            } else {
                descriptionList.add(answer);
            }
        }
        return descriptionList;
    }

    private Integer requestNumberBathroom() {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        String answer;
        Integer value = null;

        System.out.println("Enter number of bathrooms (or press enter to skip): ");
        answer = input.nextLine().trim();

        while (invalid) {
            if (answer.isEmpty()) {
                return null;
            }
            try {
                System.out.println("Number of bathrooms: ");
                answer = input.nextLine();
                value = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        }
        return value;
    }

    private Integer requestNumberBedroom() {
        System.out.println("Number of Bedrooms:");
        return getIntegerAnswer();
    }

    private List<String> requestUri() {
        List<String> uris = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("Enter a photograph URI: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                uris.add(input);
            } else {
                System.out.println("ERROR: Necessary to at least introduce one URI.");
            }
        } while (uris.size() < 1);

        while (uris.size() < MAX_URIS) {
            System.out.print("Enter a photograph URI or press enter to skip: ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return uris;
            } else {
                uris.add(input);
            }
        }
        System.out.println("Maximum limit of 30 URIs reached.");
        return uris;
    }

    private Double requestPrice() {
        System.out.println("Price:");
        return getDoubleAnswer();
    }

    private Double requestDistanceCityCenter() {
        System.out.println("Distance City Center:");
        return getDoubleAnswer();
    }

    private Double requestArea() {
        System.out.println("Area Value:");
        return getDoubleAnswer();
    }

    private Double getDoubleAnswer() {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        Double value = null;
        do {
            try {
                value = input.nextDouble();
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);

        return value;
    }

    private Integer getIntegerAnswer() {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        Integer value = null;
        do {
            try {
                value = input.nextInt();
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return value;
    }


    private void requestLocation() {
        streetName = requestStreetName();
        state = requestState();
        city = requestCity();
        district = requestDistrict();
        zipCode = requestZipCode();
    }

    private String requestState() {
        Scanner input = new Scanner(System.in);
        System.out.println("State:");
        return input.nextLine();
    }

    private String requestCity() {
        Scanner input = new Scanner(System.in);
        System.out.println("City:");
        return input.nextLine();
    }

    private String requestDistrict() {
        Scanner input = new Scanner(System.in);
        System.out.println("District:");
        return input.nextLine();
    }

    private String requestZipCode() {
        Scanner input = new Scanner(System.in);
        String answer = null;
        System.out.println("Zipcode:");
        boolean invalid = true;
        do {
            try {
                answer = input.nextLine();
               Integer value = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
            }
        } while (invalid);
        return answer;
    }

    private String requestStreetName() {
        Scanner input = new Scanner(System.in);
        System.out.println("StreetName:");
        return input.nextLine();
    }

    private String requestOwnerEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Owner Email:");
        return input.nextLine();
    }

    private Double requestCommissionValue() {
        System.out.println("Commission Value:");
        return getDoubleAnswer();
    }

    private String displayAndSelectCommissionType() {
        List<CommissionType> commissionTypes = controller.getCommissionTypeList();
        boolean invalid = true;
        int listSize = commissionTypes.size();
        int answer = -1;
        Scanner input = new Scanner(System.in);

        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayCommissionTypeOptions(commissionTypes);
                    System.out.println("Select a type of commission:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option select is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (commissionTypes.get(answer - 1).getDesignation());
    }

    private String displayAndSelectPropertyType() {
        List<PropertyType> propertyTypes = controller.getPropertyTypeList();
        int listSize = propertyTypes.size();
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayPropertyTypeOptions(propertyTypes);
                    System.out.println("Select type of property:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option select is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (propertyTypes.get(answer - 1).getDesignation());
    }

    private void displayCommissionTypeOptions(List<CommissionType> commissionTypes) {
        int i = 1;
        System.out.println();
        for (CommissionType commissionType : commissionTypes) {
            System.out.println(i + " - " + commissionType.getDesignation());
            i++;
        }
    }

    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        int i = 1;
        System.out.println();
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }

    }

}
