package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.SunExposureTypes;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.List;
import java.util.Scanner;

public class PublishAnnouncementUI {
    private final PublishAnnouncementController controller = new PublishAnnouncementController();

    private String propertyTypeDesignation;
    private String commissionTypeDesignation;
    private Double commissionValue;
    private String ownerEmail;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipcode;
    private Double area;
    private Double distanceCityCenter;
    private Double price;
    private String uri;
    private Integer numberBedroom;
    private Integer numberParkingSpace;
    private Integer numberBathroom;
    private String availableEquipmentDescription;
    private boolean existenceBasement;
    private boolean inhabitableLoft;
    private SunExposureTypes sunExposure;

    private PublishAnnouncementController getController() {
        return controller;
    }

    public void run() {
        System.out.println("Publish Announcement");

        commissionTypeDesignation = displayAndSelectCommissionType();
        commissionValue = requestCommissionValue();
        ownerEmail = requestOwnerEmail();
        propertyTypeDesignation = displayAndSelectPropertyType();
        requestLocation();
        area = requestArea();
    //finish the request of other attributes
    }

    private Double requestArea() {
        Scanner input = new Scanner(System.in);
        System.out.println("Area:");
        return  input.nextDouble();
    }

    private void requestLocation() {
        streetName = requestStreetName();
        state = requestState();
        city = requestCity();
        district = requestDistrict();
        zipcode = requestZipcode();
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

    private String requestZipcode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Zipcode:");
        return input.nextLine();
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
        Scanner input = new Scanner(System.in);
        System.out.println("Commission Value:");
        return input.nextDouble();
    }

    private String displayAndSelectCommissionType() {
        List<CommissionType> commissionTypes = controller.getCommissionTypeList();

        int listSize = commissionTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayCommissionTypeOptions(commissionTypes);
            System.out.println("Select a type of commission:");
            answer = input.nextInt();
        }
        return (commissionTypes.get(answer - 1).getDesignation());
    }

    private String displayAndSelectPropertyType() {
        List<PropertyType> propertyTypes = controller.getPropertyTypeList();

        int listSize = propertyTypes.size();
        int answer = -1;
        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayPropertyTypeOptions(propertyTypes);
            System.out.println("Select type of property:");
            answer = input.nextInt();
        }
        return (propertyTypes.get(answer - 1).getDesignation());
    }

    private void displayCommissionTypeOptions(List<CommissionType> commissionTypes) {
        int i = 1;
        for (CommissionType commissionType : commissionTypes) {
            System.out.println(i + " - " + commissionType.getDesignation());
            i++;
        }
    }

    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }

    }

}
