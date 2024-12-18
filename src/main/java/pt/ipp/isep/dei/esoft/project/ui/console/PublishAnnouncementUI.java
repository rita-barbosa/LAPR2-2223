package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.SunExposureTypes;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.util.*;

/**
 * The PublishAnnouncementUI class implements the Runnable interface and represents the user interface for publishing an announcement.
 */
public class PublishAnnouncementUI implements Runnable {
    /**
     * The PublishAnnouncementController associated with this UI.
     */
    private final PublishAnnouncementController controller;

    /**
     * The designation of the type of property being announced.
     */
    private String propertyTypeDesignation;

    /**
     * The designation of the commission type.
     */
    private String commissionTypeDesignation;

    /**
     * The commission value.
     */
    private Double commissionValue;

    /**
     * The email address of the owner of the property.
     */
    private String ownerEmail;

    /**
     * The name of the street where the property is located.
     */
    private String streetName;

    /**
     * The name of the city where the property is located.
     */
    private String city;

    /**
     * The name of the district where the property is located.
     */
    private String district;

    /**
     * The name of the state where the property is located.
     */
    private String state;

    /**
     * The zip code of the property.
     */
    private String zipCode;

    /**
     * The area of the property.
     */
    private Double area;

    /**
     * The distance from the city center.
     */
    private Double distanceCityCenter;

    /**
     * The price of the property.
     */
    private Double price;

    /**
     * The list of URIs to the photographs of the property.
     */
    private List<String> uriList;

    /**
     * The number of bedrooms in the property.
     */
    private Integer numberBedroom;

    /**
     * The number of parking spaces in the property.
     */
    private Integer numberParkingSpace;

    /**
     * The number of bathrooms in the property.
     */
    private Integer numberBathroom;

    /**
     * The list of available equipment descriptions.
     */
    private List<String> availableEquipmentDescriptionList;

    /**
     * Indicates whether the property has a basement.
     */
    private Boolean existenceBasement;

    /**
     * Indicates whether the property has an inhabitable loft.
     */
    private Boolean inhabitableLoft;

    /**
     * The sun exposure of the property.
     */
    private SunExposureTypes sunExposure;


    private AnnouncementDto announcementDto;

    /**
     * The maximum number of URIs allowed for the images of the property.
     */
    private final Integer MAX_URIS = 30;
    /**
     * The property type designation of an apartment.
     */
    private final String APARTMENT_DESIGNATION = "apartment";
    /**
     * The property type designation of a house.
     */
    private final String HOUSE_DESIGNATION = "house";

    /**
     * Constructs the PublishAnnouncementUI and initializes it with a new PublishAnnouncementController instance.
     */
    public PublishAnnouncementUI() {
        this.controller = new PublishAnnouncementController();
        this.announcementDto = new AnnouncementDto();
    }

    /**
     * This method returns the PublishAnnouncementController instance associated with this UI.
     *
     * @return the publishAnnouncementController instance.
     */
    private PublishAnnouncementController getController() {
        return controller;
    }

    /**
     * This runs the UI and displays the prompts to the user to obtain the necessary
     * data to publish a new announcement.
     * If the user confirms the data, the announcement is submitted to the system.
     */
    public void run() {
        System.out.println("\nPublish Announcement");


        requestData();
        if (requestConfirmation()) {
            submitData();
        }
    }

    /**
     * This method requests necessary data from the user to create a new request.
     */
    private void requestData() {
        announcementDto.setCommissionTypeDesignation(displayAndSelectCommissionType());
        announcementDto.setCommissionValue(requestCommissionValue());
        announcementDto.setOwnerEmail(requestOwnerEmail());
        announcementDto.setPropertyTypeDesignation(displayAndSelectPropertyType());
        announcementDto.setPrice(requestPrice());
        announcementDto.setArea(requestArea());
        announcementDto.setDistanceCityCenter(requestDistanceCityCenter());
        announcementDto.setStreetName(requestStreetName());
        announcementDto.setCity(requestCity());
        announcementDto.setDistrict(requestDistrict());
        announcementDto.setState(requestState());
        announcementDto.setZipCode(requestZipCode());
        announcementDto.setUriList(requestUri());
        if (announcementDto.getPropertyTypeDesignation().equalsIgnoreCase(APARTMENT_DESIGNATION) || announcementDto.getPropertyTypeDesignation().equalsIgnoreCase(HOUSE_DESIGNATION)) {
            announcementDto.setNumberParkingSpace(requestNumberParkingSpace());
            announcementDto.setNumberBedroom(requestNumberBedroom());
            if (Utils.askOptionalData("number of bathrooms")) {
                announcementDto.setNumberBathroom(requestNumberBathroom());
            }
            if (Utils.askOptionalData("available equipments")) {
                announcementDto.setAvailableEquipmentDescriptionList(requestAvailableEquipment());
            }
            if (announcementDto.getPropertyTypeDesignation().equalsIgnoreCase(HOUSE_DESIGNATION)) {
                announcementDto.setInhabitableLoft(requestInhabitableLoft());
                announcementDto.setExistenceBasement(requestExistenceBasement());
                if (Utils.askOptionalData("sun exposure")) {
                    announcementDto.setSunExposure(requestSunExposure().toString());
                }
            }
        }
    }

    /**
     * This method retrieves all data entered by the user and submits it to the controller to publish the announcement.
     * It also prints a message indicating if the announcement was successfully published or not.
     */
    private void submitData() {
        try {
            Boolean success = getController().publishAnnouncement(announcementDto);
            if (success) {
                System.out.println("\nAnnouncement published successfully.");
            } else {
                System.out.println("\nERROR: Announcement wasn't published.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR: Announcement wasn't published.");
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    /**
     * This method asks the user if they want to submit the data that was typed/selected.
     *
     * @return if {@code true} user wants the data to be submitted; {@code false} otherwise
     */
    private boolean requestConfirmation() {
        Scanner input = new Scanner(System.in);
        Boolean value = null;

        System.out.println("\nDo you want to submit the information (Y/N)?");
        String answer = input.nextLine();
        while (value == null) {
            if (answer.equalsIgnoreCase("y")) {
                value = true;
            } else if (answer.equalsIgnoreCase("n")) {
                value = false;
            } else {
                System.out.println("\nERROR: The input provided is not valid. Please try again.");
                answer = input.nextLine();
            }
        }
        return value;
    }


    /**
     * This method request the number of parking spaces of the property.
     *
     * @return the number of parking spaces.
     */
    private Integer requestNumberParkingSpace() {
        System.out.println("Number of parking spaces:");
        return getIntegerAnswer();
    }

    /**
     * This method requests the sun exposure of the property.
     *
     * @return the sun exposure; or null value if user doesn't type the sun exposure.
     */
    private SunExposureTypes requestSunExposure() {
        Scanner input = new Scanner(System.in);
        SunExposureTypes sunExposure = null;
        while (sunExposure == null) {
            System.out.println("Enter the sun exposure (North, South, East or West):");
            String answer = input.nextLine().trim();
            try {
                sunExposure = SunExposureTypes.valueOf(answer.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter North, South, East or West.");
            }
        }
        return sunExposure;
    }


    /**
     * This method requests if the property has an inhabitable loft.
     *
     * @return {@code true} if property has an inhabitable loft;  {@code false} otherwise;
     */
    private Boolean requestInhabitableLoft() {
        return Utils.askDirectQuestion("Does the property have an Inhabitable Loft?");
    }

    /**
     * This method requests if the property has a basement.
     *
     * @return {@code true} if property has a basement;  {@code false} otherwise;
     */
    private Boolean requestExistenceBasement() {
        return Utils.askDirectQuestion("Does the property have a basement?");
    }

    /**
     * This method asks the user for an available equipment description.
     * The user can choose to not input any available equipment; or can input multiple available equipments.
     *
     * @return a list of the available equipments description; if the user didn't input any available equipment then the list is empty.
     */
    private List<String> requestAvailableEquipment() {
        List<String> descriptionList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.print("Enter a description for an available equipment or press to skip: ");
            answer = input.nextLine().trim();
            if (answer.isEmpty()) {
                return descriptionList;
            } else {
                descriptionList.add(answer);
            }
        }
    }

    /**
     * This method requests the number of bathrooms in the property.
     * The user has the option to either input the number of bathrooms or move to the next step by pressing the enter key.
     *
     * @return number of bathrooms in the property.
     */
    private Integer requestNumberBathroom() {
        System.out.println("Property's number of bathrooms:");
        return getIntegerAnswer();
    }

    /**
     * This method requests the number of bedrooms in the property.
     *
     * @return the number of bedrooms in the property.
     */
    private Integer requestNumberBedroom() {
        System.out.println("Number of Bedrooms:");
        return getIntegerAnswer();
    }

    /**
     * This method requests a photograph URI.
     * The user has input at least one photograph URI.Then they can either move to the next step or continue inputting URI's.
     * A user can only input a maximum of 30 photograph URI's.
     *
     * @return list with the photograph URI's.
     */
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

    /**
     * This method requests the price of the property.
     *
     * @return price of the property.
     */
    private Double requestPrice() {
        System.out.println("Price:");
        return getDoubleAnswer();
    }

    /**
     * This method requests the distance from the city center of the property.
     *
     * @return distance from the city center of the property.
     */
    private Double requestDistanceCityCenter() {
        System.out.println("Distance City Center:");
        return getDoubleAnswer();
    }

    /**
     * This method requests the area of the property in m² .
     *
     * @return area of the property.
     */
    private Double requestArea() {
        System.out.println("Area (in m²):");
        return getDoubleAnswer();
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
                System.out.println("\nERROR: Value typed is invalid.");
                input.nextLine();
            }
        } while (invalid);

        return value;
    }


    /**
     * This method obtains and verifies an Integer user input.
     * If the user doesn't type in an Integer, then an Input Mismatch Exception will be "catched".
     *
     * @return the Integer user inputted answer.
     */
    private Integer getIntegerAnswer() {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        Integer value = null;
        do {
            try {
                value = input.nextInt();
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Value typed is invalid.");
                input.nextLine();
            }
        } while (invalid);
        return value;
    }

    /**
     * This method requests the state where the property is located in.
     *
     * @return state where the property is located in.
     */
    private String requestState() {
        Scanner input = new Scanner(System.in);
        System.out.println("State:");
        return input.nextLine();
    }

    /**
     * This method requests the city where the property is located in.
     *
     * @return city where the property is located in.
     */
    private String requestCity() {
        Scanner input = new Scanner(System.in);
        System.out.println("City:");
        return input.nextLine();
    }

    /**
     * This method requests the district where the property is located in.
     *
     * @return district where the property is located in.
     */
    private String requestDistrict() {
        Scanner input = new Scanner(System.in);
        System.out.println("District:");
        return input.nextLine();
    }

    /**
     * This method requests the zipcode of area the property is located in.
     * If the user doesn't input digits, then a then an Input Mismatch Exception will be "catched".
     *
     * @return zipcode of area the property is located in.
     */
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
                System.out.println("\nERROR: Value typed is invalid.");
            }
        } while (invalid);
        return answer;
    }

    /**
     * This method requests the street name of where the property is located in.
     *
     * @return street name of where the property is located in.
     */
    private String requestStreetName() {
        Scanner input = new Scanner(System.in);
        System.out.println("StreetName:");
        return input.nextLine();
    }

    /**
     * This method requests the property owner email.
     *
     * @return property owner email.
     */
    private String requestOwnerEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Owner Email:");
        return input.nextLine();
    }

    /**
     * This method requests the commission value of the agent.
     *
     * @return commission value of the agent.
     */
    private Double requestCommissionValue() {
        System.out.println("Commission Value:");
        return getDoubleAnswer();
    }

    /**
     * This method requests the user to select commission type.
     * An Input Mismatch Exception will be thrown, if the user doesn't type in an Integer.
     *
     * @return description of commission type.
     */
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
                System.out.println("\nERROR: Option selected is invalid.");
                input.nextLine();
            }
        } while (invalid);
        return (commissionTypes.get(answer - 1).getDesignation());
    }

    /**
     * This method requests the user to select commission type.
     * An Input Mismatch Exception will be thrown, if the user doesn't type in an Integer.
     *
     * @return description of commission type.
     */
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
                System.out.println("\nERROR: Option selected is invalid.");
                input.nextLine();
            }
        } while (invalid);
        return (propertyTypes.get(answer - 1).getDesignation());
    }

    /**
     * This method displays the commission type options.
     *
     * @param commissionTypes - the list of commission types.
     */
    private void displayCommissionTypeOptions(List<CommissionType> commissionTypes) {
        int i = 1;
        System.out.println();
        for (CommissionType commissionType : commissionTypes) {
            System.out.println(i + " - " + commissionType.getDesignation());
            i++;
        }
    }

    /**
     * This method displays the property type options.
     *
     * @param propertyTypes - the list of property types.
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        int i = 1;
        System.out.println();
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }

    }

}
