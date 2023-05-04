package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

/**
 * The CreateRequestUI class implements the Runnable interface and represents the user interface for creating a Request.
 */
public class CreateRequestUI implements Runnable {

    /**
     * The CreateRequestController associated with this UI
     */
    private final CreateRequestController controller = new CreateRequestController();
    /**
     * The designation of the type of property in the request.
     */
    private String propertyTypeDesignation;
    /**
     * Lease business type designation for a property request.
     */
    private static final String LEASE_BUSINESS_TYPE = "Lease";
    /**
     * Size limit for the property's photographs' uri list.
     */
    private static final Integer PHOTOGRAPH_SIZE_LIMIT = 30;
    /**
     * The designation of the type of business in the request.
     */
    private String businessTypeDesignation;
    /**
     * House property type designation for a property request.
     */
    private static final String HOUSE_PROPERTY_TYPE = "House";
    /**
     * Money amont that represents the price or the rent of a property, in American Dollars.
     */
    private Double amount;
    /**
     * Area of the property in square meters.
     */
    private Double area;
    /**
     * Property's rental contract duration in months.
     */
    private Integer contractDuration;
    /**
     * Available equipments list that the property has.
     */
    private List<String> availableEquipmentDescription;
    /**
     * Street Name of the property's location.
     */
    private String streetName;
    /**
     * City of the property's location.
     */
    private String city;
    /**
     * District of the property's location.
     */
    private String district;
    /**
     * State of the property's location.
     */
    private String state;
    /**
     * Zip code of the property's location.
     */
    private String zipCode;
    /**
     * Confirmation of existence of basement in a property.
     */
    private Boolean basement;
    /**
     * Confirmation of existence of inhabitable loft in a property.
     */
    private Boolean inhabitableLoft;
    /**
     * Number of parking spaces of a property.
     */
    private Integer parkingSpace;


    /**
     * The Max uris.
     */
    private final Integer MAX_URIS = 30;

    /**
     * Values of possible sun direction of a property.
     */
    private Enum<SunExposureTypes> sunExposure;
    /**
     * Property's number of bedrooms.
     */
    private Integer numberBedroom;
    /**
     * Property's number of bathrooms.
     */
    private Integer numberBathroom;
    /**
     * Agent in charge of the Property's Request.
     */
    private Employee agent;
    /**
     * Agency responsible for the Property's Request.
     */
    private Agency agency;
    /**
     * Property's distance from City Center.
     */
    private Double distanceCityCenter;
    /**
     * Property's photographs' URI list.
     */
    private List<String> uri;

    /**
     * Gets controller.
     *
     * @return the controller
     */
    private CreateRequestController getController() {
        return controller;
    }

    /**
     * This runs the UI and displays the prompts to the user to obtain the necessary
     * data to publish create a request.
     * If the user confirms the data, the Property's request is submitted to the system.
     */

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

    /**
     * Displays data.
     */
    private void displaysData() {
        String amountString;
        String numberBathroomString = "";
        String sunExposureString = "";
        if (businessTypeDesignation.equalsIgnoreCase("Lease")) {
            amountString = String.format("Rent: %.2f $US", amount);
        } else {
            amountString = String.format("Price: %.2f $US", amount);
        }
        if (sunExposure != null) {
            sunExposureString = String.format("Sun exposure direction: %s", sunExposure);
        }
        if (numberBathroom != null) {
            numberBathroomString = String.format("Number of bathrooms: %d%n", numberBathroom);
        }
        System.out.printf("%n###Property Data###%n=================%n** General Data **%nProperty Type: %s%nBusiness Type: %s%n" +
                        "Agency responsible for your Property's Request: %s%nAgent in charge of Request: %s%n%s%nArea: %.2f m²%nDistance from City Center: %.2f m%n" +
                        "=================%n** Location Data **%nStreet Name: %s%nCity: %s%nDistrict: %s%nState: %s%nZip Code: %s%n" +
                        "=================%n** Other Data **%nParking Spaces: %d%nNumber of Bedrooms: %d%n%sBasement: %s%nInhabitable Loft: %s%n%s",
                propertyTypeDesignation, businessTypeDesignation, agency.getDescription(), agent.getName(),
                amountString, area, distanceCityCenter, streetName, city, district, state, zipCode, parkingSpace, numberBedroom,
                numberBathroomString, getStringFromBoolean(basement), getStringFromBoolean(inhabitableLoft), sunExposureString);
        if (availableEquipmentDescription != null) {
            System.out.println("\n=================");
            displayListContent(availableEquipmentDescription, "available equipments");
        }
        System.out.println("\n=================");
        displayListContent(uri, "photographs' uris");
    }

    /**
     * Display list content.
     *
     * @param list      the list
     * @param attribute the attribute
     */
    private void displayListContent(List<String> list, String attribute) {
        System.out.printf("** Property's %s list **%n", attribute);
        int i = 1;
        for (String listItem : list) {
            System.out.printf("%d. %s%n", i++, listItem);
        }
    }

    /**
     * Gets string from boolean.
     *
     * @param booleanVar the boolean variable
     * @return the string from boolean
     */
    private String getStringFromBoolean(Boolean booleanVar) {
        if (booleanVar) {
            return "Yes";
        }
        return "No";
    }

    /**
     * Submit data for Property's Request creation.
     */
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

    /**
     * Request data for Property's Request creation.
     */
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
        inhabitableLoft = requestRequestInhabitableLoft();
        basement = requestRequestBasement();
        if (propertyTypeDesignation.equalsIgnoreCase(HOUSE_PROPERTY_TYPE) && askOptionalData("sun exposure")) {
            sunExposure = requestRequestSunExposure();
        }
        uri = requestRequestPhotographURI();
    }

    /**
     * Ask optional data boolean.
     *
     * @param optionalData the optional data for Property's Request creation.
     * @return the boolean for positive response
     */
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

    /**
     * Requests contract duration integer.
     *
     * @return the number of months in the contract
     */
    private Integer requestRequestContractDuration() {
        System.out.println("Rental Contact's durantion (in months):");
        return getIntAnswer();
    }


    /**
     * Requests the Property's price/rent of property.
     *
     * @return the double (amount of money)
     */
    private Double requestRequestAmount() {
        if (businessTypeDesignation.equalsIgnoreCase("Lease")) {
            System.out.println("Property Rent (in Dollars - $US):");
        } else {
            System.out.println("Property Price (in Dollars - $US):");
        }
        return getDoubleAnswer();
    }

    /**
     * Requests the Property's area double.
     *
     * @return the area
     */
    private Double requestRequestArea() {
        System.out.println("Property area (in squared meters - m²):");
        return getDoubleAnswer();
    }

    /**
     * Requests the Property's distance from city center double.
     *
     * @return the distance from city center
     */
    private Double requestRequestDistanceCityCenter() {
        System.out.println("Property's distance from City Center (in meters - m):");
        return getDoubleAnswer();
    }

    /**
     * Requests the Property's street name string.
     *
     * @return the street name
     */
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

    /**
     * Requests the Property's city string.
     *
     * @return the city
     */
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

    /**
     * Requests the Property's district string.
     *
     * @return the district
     */
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

    /**
     * Requests the Property's state string.
     *
     * @return the state
     */
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

    /**
     * Requests the Property's zip code string.
     *
     * @return the zip code
     */
    private String requestRequestZipCode() {
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

    /**
     * Requests the Property's number of parking space integer.
     *
     * @return the parking spaces
     */
    private Integer requestRequestParkingSpace() {
        System.out.println("Property's number of parking spaces:");
        return getIntAnswer();
    }

    /**
     * Requests the Property's number of bedrooms integer.
     *
     * @return the number of bedrooms
     */
    private Integer requestRequestNumberBedroom() {
        System.out.println("Property's number of bedrooms:");
        return getIntAnswer();
    }

    /**
     * Requests the Property's number of bathrooms integer.
     *
     * @return the number of bathrooms
     */
    private Integer requestRequestNumberBathroom() {
        System.out.println("Property's number of bathrooms:");
        return getIntAnswer();
    }

    /**
     * Requests the Property's existence of inhabitable loft boolean.
     *
     * @return the confirmation.
     */
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

    /**
     * Requests the Property's existence of basement boolean.
     *
     * @return the confirmation
     */
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

    /**
     * Requests the Property's sun exposure direction.
     *
     * @return the direction enum
     */
    private Enum<SunExposureTypes> requestRequestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Please select the sun exposure direction:%n1. North%n2. South%n3. West%n4. East%n");
        int inputOption;
        do {

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
                    System.out.println("1. North\n2. South\n3. West\n4. East");
                    break;
            }
        } while (true);
    }


    /**
     * Requests the Property's available equipments' descriptions list.
     *
     * @return the available equiments' description list
     */
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

    /**
     * Requests the Property's photograph uri list.
     *
     * @return the uri list
     */
    private List<String> requestRequestPhotographURI() {
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

    /**
     * Display and select business types.
     *
     * @return the chosen business type designation
     */
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

    /**
     * Display and select property types.
     *
     * @return the choosen property type designation
     */
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


    /**
     * Display and select an agency from agencies list.
     *
     * @return the agency
     */
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

    /**
     * Display and select agent from agents list of chosen agency.
     *
     * @param agency the chosen agency
     * @return the agent
     */
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

    /**
     * Display business type options.
     *
     * @param businessTypes the business types list
     */
    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        //display the business types as a menu with number options to select
        int i = 1;
        for (BusinessType businessType : businessTypes) {
            System.out.println(i + " - " + businessType.getDesignation());
            i++;
        }
    }

    /**
     * Display property type options.
     *
     * @param propertyTypes the property types list
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the property types as a menu with number options to select
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }

    /**
     * Display agencies list options.
     *
     * @param agencies the agencies list
     */
    private void displayAgenciesListOptions(List<Agency> agencies) {
        //display the agencies as a menu with number options to select
        int i = 1;
        for (Agency agency : agencies) {
            System.out.println(i + " - " + agency.getDescription());
            i++;
        }
    }

    /**
     * Display agents list options.
     *
     * @param agents the agents list
     */
    private void displayAgentsListOptions(List<Employee> agents) {
        //display the agents of chosen agency as a menu with number options to select
        int i = 1;
        for (Employee agent : agents) {
            System.out.println(i + " - " + agent.getName());
            i++;
        }
    }

    /**
     * This method obtains and verifies a Double user input.
     * If the user doesn't type in a Double, then an Input Mismatch Exception will be "catched".
     *
     * @return the Double user inputted answer.
     */
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
                        + " (" + e.getClass().getSimpleName() + " - Decimal separator is a coma)");
                input.nextLine();
            }
        } while (invalid);
      //  String inputString = value.toString().trim();
        //Double.parseDouble(inputString.replace(",", "."));
        return value;
    }

    /**
     * Gets int answer.
     *
     * @return the int answer
     */
    private Integer getIntAnswer() {
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

}
