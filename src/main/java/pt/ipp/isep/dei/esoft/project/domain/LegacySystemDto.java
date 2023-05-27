package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class LegacySystemDto {
    private String ownerName;
    private String ownerPassportNum;
    private String ownerTIN;
    private String ownerEmail;
    private String ownerPhone;
    private String propertyType;
    private int propertyArea;
    private String propertyLocation;

    private int propertyDistanceFromCenter;

    private int propertyNumberBedrooms;
    private int propertyNumberBathrooms;
    private int propertyNumberParkingSpaces;
    private boolean propertyCentralHeating;
    private boolean propertyAirConditioning;
    private boolean propertyBasement;
    private boolean propertyLoft;
    private String propertySunExposure;
    private int propertyRequestedPrice;

    private int propertyPrice;

    private int commission;

    private int contractDuration;

    private String propertyDateAnnounceRequest;
    private String propertyDateSale;
    private String typeBusiness;
    private int agencyID;
    private String agencyName;
    private String agencyLocation;
    private String agencyPhoneNumber;
    private String agencyEmailAddress;

    private final String FILE_BOOLEAN_VALUE = "Y";

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
        this.propertyNumberBedrooms = Integer.parseInt(legacySystemInformationList.get(9));
        this.propertyNumberBathrooms = Integer.parseInt(legacySystemInformationList.get(10));
        this.propertyNumberParkingSpaces = Integer.parseInt(legacySystemInformationList.get(11));
        this.propertyCentralHeating = mapStringToBoolean(legacySystemInformationList.get(12));
        this.propertyAirConditioning = mapStringToBoolean(legacySystemInformationList.get(13));
        this.propertyBasement = mapStringToBoolean(legacySystemInformationList.get(14));
        this.propertyLoft = mapStringToBoolean(legacySystemInformationList.get(15));
        this.propertySunExposure = legacySystemInformationList.get(16);
        this.propertyRequestedPrice = Integer.parseInt(legacySystemInformationList.get(17));
        this.propertyPrice = Integer.parseInt(legacySystemInformationList.get(18));
        this.commission = Integer.parseInt(legacySystemInformationList.get(19));
        this.contractDuration = Integer.parseInt(legacySystemInformationList.get(20));
        this.propertyDateAnnounceRequest = legacySystemInformationList.get(21);
        this.propertyDateSale = legacySystemInformationList.get(22);
        this.typeBusiness = legacySystemInformationList.get(23);
        this.agencyID = Integer.parseInt(legacySystemInformationList.get(24));
        this.agencyName = legacySystemInformationList.get(25);
        this.agencyLocation = legacySystemInformationList.get(26);
        this.agencyPhoneNumber = legacySystemInformationList.get(27);
        this.agencyEmailAddress = legacySystemInformationList.get(28);
    }

    private boolean mapStringToBoolean(String value) {
        return value.equalsIgnoreCase(FILE_BOOLEAN_VALUE);
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

    public int getPropertyArea() {
        return propertyArea;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public int getPropertyDistanceFromCenter() {
        return propertyDistanceFromCenter;
    }

    public int getPropertyNumberBedrooms() {
        return propertyNumberBedrooms;
    }

    public int getPropertyNumberBathrooms() {
        return propertyNumberBathrooms;
    }

    public int getPropertyNumberParkingSpaces() {
        return propertyNumberParkingSpaces;
    }

    public boolean isPropertyCentralHeating() {
        return propertyCentralHeating;
    }

    public boolean isPropertyAirConditioning() {
        return propertyAirConditioning;
    }

    public boolean isPropertyBasement() {
        return propertyBasement;
    }

    public boolean isPropertyLoft() {
        return propertyLoft;
    }

    public String getPropertySunExposure() {
        return propertySunExposure;
    }

    public int getPropertyRequestedPrice() {
        return propertyRequestedPrice;
    }

    public int getPropertyPrice() {
        return propertyPrice;
    }

    public int getCommission() {
        return commission;
    }

    public int getContractDuration() {
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
