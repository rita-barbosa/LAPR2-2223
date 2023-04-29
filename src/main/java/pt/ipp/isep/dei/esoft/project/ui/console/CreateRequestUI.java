package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;

public class CreateRequestUI implements Runnable {

    private final CreateRequestController controller = new CreateRequestController();
    private String propertyTypeDesignation;
    public static final String LEASE_BUSINESS_TYPE = "Lease";
    public static final Integer PHOTOGRAPH_SIZE_LIMIT = 30;
    private String businessTypeDesignation;
    public static final String HOUSE_PROPERTY_TYPE = "House";
    private Double amount;
    private Double area;
    private Integer contractDuration;
    private List<String> availableEquipmentDescription;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;
    private Boolean basement;
    private Boolean inhabitableLoft;
    private Integer parkingSpace;
    private Enum<SunExposureTypes> sunExposure;
    private Integer numberBedroom;
    private Integer numberBathroom;
    private Employee agent;
    private Agency agency;
    private Double distanceCityCenter;
    private List<String> uri;

    private CreateRequestController getController() {
        return controller;
    }

    public void run() {
        System.out.println("Create Request for Property Announcement");

        businessTypeDesignation = displayAndSelectBusinessType();

        if (businessTypeDesignation.equalsIgnoreCase(LEASE_BUSINESS_TYPE)) {
            contractDuration = requestRequestContractDuration();
        }

        propertyTypeDesignation = displayAndSelectPropertyType();

        requestData();

        agency = displayAndSelectAgenciesList();

        agent = displayAndSelectAgentsList(agency);

        displaysData();

        submitData();
    }

    private void displaysData() {
        String amountString;
        String numberBathroomString = "Number of bathrooms: No Information";
        String sunExposureString = "";
        if (businessTypeDesignation.equalsIgnoreCase("Lease")) {
            amountString = String.format("Rent: %f.2 US$", amount);
        } else {
            amountString = String.format("Price: %.2f US$", amount);
        }
        if (sunExposure != null) {
            sunExposureString = String.format("Sun exposure direction: %s", sunExposure);
        }
        if (numberBathroom != null) {
            numberBathroomString = String.format("Number of bathrooms: %d", numberBathroom);
        }
        System.out.printf("%n###Property Data###%n=================%n** General Data **%n%s%nArea:  %.2f m²%nDistance from City Center:  %.2f m%n" +
                        "=================%n** Location Data **%nStreet Name: %s%nCity: %s%nDistrict: %s%nState: %s%nZip Code: %s%n" +
                        "=================%n** Other Data **%nParking Spaces: %d%nNumber of Bedrooms: %d%n%s%nBasement: %s%nInhabitable Loft: %s%n%s",
                amountString, area, distanceCityCenter, streetName, city, district, state, zipCode, parkingSpace, numberBedroom,
                numberBathroomString, getStringFromBoolean(basement), getStringFromBoolean(inhabitableLoft), sunExposureString);
        if (availableEquipmentDescription != null) {
            System.out.println("=================");
            displayListContent(availableEquipmentDescription, "available equipments");
        }
        System.out.println("=================");
        displayListContent(uri, "photographs' uris");
    }

    private void displayListContent(List<String> list, String attribute) {
        System.out.printf("** Property's %s list **%n", attribute);
        int i = 1;
        for (String listItem : list) {
            System.out.printf("%d. %s%n", i++, listItem);
        }
    }

    private String getStringFromBoolean(Boolean booleanVar) {
        if (booleanVar) {
            return "Yes";
        }
        return "No";
    }

    private void submitData() {
        Optional<Request> request = getController().createRequest(propertyTypeDesignation, businessTypeDesignation, amount,
                area, contractDuration, availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft,
                parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uri, agency);

        if (request.isPresent()) {
            System.out.println("Request successfully created!");
        } else {
            System.out.println("Request not created!");
        }
    }

    private void requestData() {
        amount = requestRequestAmount();
        area = requestRequestArea();
        distanceCityCenter = requestRequestDistanceCityCenter();
        streetName = requestRequestStreetName();
        city = requestRequestCity();
        district = requestRequestDistrict();
        state = requestRequestState();
        zipCode = requestRequestZipCode();
        parkingSpace = requestRequestParkingSpace();
        numberBedroom = requestRequestNumberBedroom();
        if (askOptionalData("number of bathrooms")) {
            numberBathroom = requestRequestNumberBathroom();
        }
        if (askOptionalData("available equipments")) {
            availableEquipmentDescription = requestRequestAvailableEquipmentDescription();
        }
        basement = requestRequestInhabitableLoft();
        inhabitableLoft = requestRequestBasement();
        if (businessTypeDesignation.equalsIgnoreCase(HOUSE_PROPERTY_TYPE) && askOptionalData("sun exposure")) {
            sunExposure = requestRequestSunExposure();
        }
        uri = requestRequestPhotographURI();
    }

    private boolean askOptionalData(String optionalData) {
        System.out.printf("Would you like to provide data about the Property's %s%n1. Yes%n2. No%n", optionalData);
        boolean invalid = true;
        int answer = -1;
        Scanner input = new Scanner(System.in);
        do {
            try {
                answer = input.nextInt();
                if (answer != 1 && answer != 2) {
                    System.out.println("\nERROR: Option selected is invalid. (1 or 2)");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is not a number. (1 or 2)");
                input.nextLine();
            }
        } while (invalid);
        return answer == 1;
    }

    private Integer requestRequestContractDuration() {
        Scanner input = new Scanner(System.in);
        System.out.println("Rental Contact's durantion (in months):");
        return input.nextInt();
    }


    private Double requestRequestAmount() {
        Scanner input = new Scanner(System.in);
        if (businessTypeDesignation.equalsIgnoreCase("Lease")) {
            System.out.println("Property Rent (in Dollars - US$):");
        } else {
            System.out.println("Property Price (in Dollars - US$):");
        }
        String inputString = input.next().trim();
        return Double.parseDouble(inputString.replace(",", "."));
    }

    private Double requestRequestArea() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property area (in squared meters - m²):");
        String inputString = input.next().trim();
        return Double.parseDouble(inputString.replace(",", "."));
    }

    private Double requestRequestDistanceCityCenter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's distance from City Center (in meters - m):");
        String inputString = input.next().trim();
        return Double.parseDouble(inputString.replace(",", "."));
    }

    private String requestRequestStreetName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's street name:");
        String inputString = input.nextLine();
        while (inputString.trim().isBlank()) {
            System.out.println("Property's street name invalid. Provide a new one.");
            inputString = input.nextLine();
        }
        return inputString;
    }

    private String requestRequestCity() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's city:");
        String inputString = input.nextLine();
        while (inputString.trim().isBlank()) {
            System.out.println("Property's city invalid. Provide a new one.");
            inputString = input.nextLine();
        }
        return inputString;
    }

    private String requestRequestDistrict() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's district:");
        String inputString = input.nextLine();
        while (inputString.trim().isBlank()) {
            System.out.println("Property's district invalid. Provide a new one.");
            inputString = input.nextLine();
        }
        return inputString;
    }

    private String requestRequestState() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's state:");
        String inputString = input.nextLine();
        while (inputString.trim().isBlank()) {
            System.out.println("Property's state invalid. Provide a new one.");
            inputString = input.nextLine();
        }
        return inputString;
    }

    private String requestRequestZipCode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's zip code:");
        String inputString = input.nextLine();
        while (inputString.trim().isBlank()) {
            System.out.println("Property's zip code invalid. Provide a new one.");
            inputString = input.nextLine();
        }
        return inputString;
    }

    private Integer requestRequestParkingSpace() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's number of parking spaces:");
        return input.nextInt();
    }

    private Integer requestRequestNumberBedroom() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's number of bedrooms:");
        return input.nextInt();
    }

    private Integer requestRequestNumberBathroom() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's number of bathrooms:");
        return input.nextInt();
    }

    private Boolean requestRequestInhabitableLoft() {
        System.out.printf("Does your property have an inhabitable loft?%n1. Yes%n2. No%n");
        boolean invalid = true;
        int answer = -1;
        Scanner input = new Scanner(System.in);
        do {
            try {
                answer = input.nextInt();
                if (answer != 1 && answer != 2) {
                    System.out.println("\nERROR: Option selected is invalid. (1 or 2)");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is not a number. (1 or 2)");
                input.nextLine();
            }
        } while (invalid);
        return answer == 1;
    }

    private Boolean requestRequestBasement() {
        System.out.printf("Does your property have a basement?%n1. Yes%n2. No%n");
        boolean invalid = true;
        int answer = -1;
        Scanner input = new Scanner(System.in);
        do {
            try {
                answer = input.nextInt();
                if (answer != 1 && answer != 2) {
                    System.out.println("\nERROR: Option selected is invalid. (1 or 2)");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is not a number. (1 or 2)");
                input.nextLine();
            }
        } while (invalid);
        return answer == 1;
    }

    private Enum<SunExposureTypes> requestRequestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Please select the sun exposure direction:%n1. North%n2. South%n3. West%n4. East%n");
        int inputOption;
        do {
            System.out.println("1. North\n2. South\n3. West\n4. East");
            inputOption = input.nextInt();
            switch (inputOption) {
                case 1:
                    return SunExposureTypes.NORTH;
                case 2:
                    return SunExposureTypes.SOUTH;
                case 3:
                    return SunExposureTypes.WEST;
                case 4:
                    return SunExposureTypes.EAST;
                default:
                    System.out.println("Invalid input. Please select a valid option.");
                    break;
            }
        } while (true);
    }


    private List<String> requestRequestAvailableEquipmentDescription() {
        List<String> availableEquipment = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Property's available equipements: (when you are done, type DONE)");
        String equipment;
        do {
            equipment = input.nextLine();
            if (!(equipment.equalsIgnoreCase("DONE")) && !(equipment.trim().isBlank())) {
                availableEquipment.add(equipment);
            }
        } while (!equipment.equalsIgnoreCase("DONE"));
        return availableEquipment;
    }

    private List<String> requestRequestPhotographURI() {
        List<String> uri = new ArrayList<>();
        Scanner uriInput = new Scanner(System.in);
        System.out.println("Property photographs' uri: (when you are done, type DONE | Maximum 30 entries.)");
        String typedUri = "";
        while (!(typedUri.equalsIgnoreCase("DONE") || uri.size() == PHOTOGRAPH_SIZE_LIMIT)) {
            typedUri = uriInput.nextLine();
            if (!(typedUri.equalsIgnoreCase("DONE")) && !(typedUri.trim().isBlank())) {
                uri.add(typedUri);
            }
        }
        if (uri.size() == PHOTOGRAPH_SIZE_LIMIT) {
            System.out.println("Maximum of entries reached.");
        }
        return uri;
    }

    private String displayAndSelectBusinessType() {
        List<BusinessType> businessTypes = controller.getBusinessTypes();
        int listSize = businessTypes.size();
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayBusinessTypeOptions(businessTypes);
                    System.out.println("Select a business type:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return businessTypes.get(answer - 1).getDesignation();
    }

    private String displayAndSelectPropertyType() {
        List<PropertyType> propertyTypes = controller.getPropertyTypes();
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
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (propertyTypes.get(answer - 1).getDesignation());
    }


    private Agency displayAndSelectAgenciesList() {
        List<Agency> agencies = controller.getAgenciesList();
        int listSize = agencies.size();
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayAgenciesListOptions(agencies);
                    System.out.println("Select an agency:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return agencies.get(answer - 1);
    }

    private Employee displayAndSelectAgentsList(Agency agency) {
        List<Employee> agents = controller.getAgents(agency);
        int listSize = agents.size();
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayAgentsListOptions(agents);
                    System.out.println("Select an agent of previously chosen agency:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);

        return agents.get(answer - 1);
    }

    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        //display the business types as a menu with number options to select
        int i = 1;
        for (BusinessType businessType : businessTypes) {
            System.out.println(i + " - " + businessType.getDesignation());
            i++;
        }
    }

    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the property types as a menu with number options to select
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }

    private void displayAgenciesListOptions(List<Agency> agencies) {
        //display the agencies as a menu with number options to select
        int i = 1;
        for (Agency agency : agencies) {
            System.out.println(i + " - " + agency.getDescription());
            i++;
        }
    }

    private void displayAgentsListOptions(List<Employee> agents) {
        //display the agents of chosen agency as a menu with number options to select
        int i = 1;
        for (Employee agent : agents) {
            System.out.println(i + " - " + agent.getName());
            i++;
        }
    }

}
