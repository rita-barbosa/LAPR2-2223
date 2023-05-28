package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class LegacySystemDto {
    private String ownerName;
    private String ownerPassportNum;
    private String ownerTIN;
    private String ownerEmail;
    private String ownerPhone;
    private String propertyType;
    private double propertyArea;
    private String propertyLocation;

    private double propertyDistanceFromCenter;

    private String propertyNumberBedrooms;
    private String propertyNumberBathrooms;
    private String propertyNumberParkingSpaces;
    private String propertyCentralHeating;
    private String  propertyAirConditioning;
    private String  propertyBasement;
    private String  propertyLoft;
    private String propertySunExposure;
    private double propertyRequestedPrice;

    private double propertyPrice;

    private double commission;

    private String contractDuration;

    private String propertyDateAnnounceRequest;
    private String propertyDateSale;
    private String typeBusiness;
    private int agencyID;
    private String agencyName;
    private String agencyLocation;
    private String agencyPhoneNumber;
    private String agencyEmailAddress;



    public LegacySystemDto(List<String> legacySystemInformationList) {
        this.ownerName = legacySystemInformationList.get(0);
        this.ownerPassportNum = legacySystemInformationList.get(1);
        this.ownerTIN = legacySystemInformationList.get(2);
        this.ownerEmail = legacySystemInformationList.get(3);
        this.ownerPhone = legacySystemInformationList.get(4);
        this.propertyType = legacySystemInformationList.get(5);
        this.propertyArea = Integer.parseInt(legacySystemInformationList.get(6));
        this.propertyLocation = legacySystemInformationList.get(7);
        this.propertyDistanceFromCenter = Integer.parseInt(legacySystemInformationList.get(8));
        this.propertyNumberBedrooms = legacySystemInformationList.get(9);
        this.propertyNumberBathrooms = legacySystemInformationList.get(10);
        this.propertyNumberParkingSpaces = legacySystemInformationList.get(11);
        this.propertyCentralHeating = legacySystemInformationList.get(12);
        this.propertyAirConditioning = legacySystemInformationList.get(13);
        this.propertyBasement = legacySystemInformationList.get(14);
        this.propertyLoft = legacySystemInformationList.get(15);
        this.propertySunExposure = legacySystemInformationList.get(16);
        this.propertyRequestedPrice = Integer.parseInt(legacySystemInformationList.get(17));
        this.propertyPrice = Integer.parseInt(legacySystemInformationList.get(18));
        this.commission = Integer.parseInt(legacySystemInformationList.get(19));
        this.contractDuration = legacySystemInformationList.get(20);
        this.propertyDateAnnounceRequest = legacySystemInformationList.get(21);
        this.propertyDateSale = legacySystemInformationList.get(22);
        this.typeBusiness = legacySystemInformationList.get(23);
        this.agencyID = Integer.parseInt(legacySystemInformationList.get(24));
        this.agencyName = legacySystemInformationList.get(25);
        this.agencyLocation = legacySystemInformationList.get(26);
        this.agencyPhoneNumber = legacySystemInformationList.get(27);
        this.agencyEmailAddress = legacySystemInformationList.get(28);
    }



    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerPassportNum() {
        return ownerPassportNum;
    }

    public String getOwnerTIN() {
        return ownerTIN;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public double getPropertyArea() {
        return propertyArea;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public double getPropertyDistanceFromCenter() {
        return propertyDistanceFromCenter;
    }

    public String  getPropertyNumberBedrooms() {
        return propertyNumberBedrooms;
    }

    public String  getPropertyNumberBathrooms() {
        return propertyNumberBathrooms;
    }

    public String  getPropertyNumberParkingSpaces() {
        return propertyNumberParkingSpaces;
    }

    public String  getPropertyCentralHeating() {
        return propertyCentralHeating;
    }

    public String  getPropertyAirConditioning() {
        return propertyAirConditioning;
    }

    public String  getPropertyBasement() {
        return propertyBasement;
    }

    public String  getPropertyLoft() {
        return propertyLoft;
    }

    public String getPropertySunExposure() {
        return propertySunExposure;
    }

    public double getPropertyRequestedPrice() {
        return propertyRequestedPrice;
    }

    public double getPropertyPrice() {
        return propertyPrice;
    }

    public double getCommission() {
        return commission;
    }

    public String getContractDuration() {
        return contractDuration;
    }

    public String getPropertyDateAnnounceRequest() {
        return propertyDateAnnounceRequest;
    }

    public String getPropertyDateSale() {
        return propertyDateSale;
    }

    public String getTypeBusiness() {
        return typeBusiness;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getAgencyLocation() {
        return agencyLocation;
    }

    public String getAgencyPhoneNumber() {
        return agencyPhoneNumber;
    }

    public String getAgencyEmailAddress() {
        return agencyEmailAddress;
    }
}
