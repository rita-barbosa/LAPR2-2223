package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * The type Legacy system dto.
 */
public class LegacySystemDto {
    /**
     * The Owner name.
     */
    private String ownerName;
    /**
     * The Owner passport number.
     */
    private String ownerPassportNum;
    /**
     * The Owner tax identification number.
     */
    private String ownerTIN;
    /**
     * The Owner email.
     */
    private String ownerEmail;
    /**
     * The Owner phone.
     */
    private String ownerPhone;
    /**
     * The Property type.
     */
    private String propertyType;
    /**
     * The Property area.
     */
    private Double propertyArea;
    /**
     * The Property location.
     */
    private String propertyLocation;

    /**
     * The Property distance from center.
     */
    private double propertyDistanceFromCenter;

    /**
     * The Property number bedrooms.
     */
    private String propertyNumberBedrooms;
    /**
     * The Property number bathrooms.
     */
    private String propertyNumberBathrooms;
    /**
     * The Property number parking spaces.
     */
    private String propertyNumberParkingSpaces;
    /**
     * The Property central heating.
     */
    private String propertyCentralHeating;
    /**
     * The Property air conditioning.
     */
    private String propertyAirConditioning;
    /**
     * The Property basement.
     */
    private String propertyBasement;
    /**
     * The Property loft.
     */
    private String propertyLoft;
    /**
     * The Property sun exposure.
     */
    private String propertySunExposure;
    /**
     * The Property requested price.
     */
    private double propertyRequestedPrice;

    /**
     * The Property price.
     */
    private double propertyPrice;

    /**
     * The Commission.
     */
    private double commission;

    /**
     * The Contract duration.
     */
    private String contractDuration;

    /**
     * The Property date announce request.
     */
    private String propertyDateAnnounceRequest;
    /**
     * The Property date sale.
     */
    private String propertyDateSale;
    /**
     * The Type business.
     */
    private String typeBusiness;
    /**
     * The Agency id.
     */
    private int agencyID;
    /**
     * The Agency name.
     */
    private String agencyName;
    /**
     * The Agency location.
     */
    private String agencyLocation;
    /**
     * The Agency phone number.
     */
    private String agencyPhoneNumber;
    /**
     * The Agency email address.
     */
    private String agencyEmailAddress;


    /**
     * Instantiates a new Legacy system dto.
     *
     * @param legacySystemInformationList the legacy system information list
     */
    public LegacySystemDto(List<String> legacySystemInformationList) {
        this.ownerName = legacySystemInformationList.get(0);
        this.ownerPassportNum = legacySystemInformationList.get(1);
        this.ownerTIN = legacySystemInformationList.get(2);
        this.ownerEmail = legacySystemInformationList.get(3);
        if (this.ownerEmail.contains("'")) {
            this.ownerEmail = this.ownerEmail.replace("'", "");
        }
        this.ownerPhone = legacySystemInformationList.get(4);
        this.propertyType = legacySystemInformationList.get(5);
        this.propertyArea = (double) Integer.parseInt(legacySystemInformationList.get(6));
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
        this.propertyRequestedPrice = (double) Integer.parseInt(legacySystemInformationList.get(17));
        this.propertyPrice = (double) Integer.parseInt(legacySystemInformationList.get(18));
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

    /**
     * Gets owner name.
     *
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Gets owner passport num.
     *
     * @return the owner passport num
     */
    public String getOwnerPassportNum() {
        return ownerPassportNum;
    }

    /**
     * Gets owner tin.
     *
     * @return the owner tin
     */
    public String getOwnerTIN() {
        return ownerTIN;
    }

    /**
     * Gets owner email.
     *
     * @return the owner email
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Gets owner phone.
     *
     * @return the owner phone
     */
    public String getOwnerPhone() {
        return ownerPhone;
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * Gets property area.
     *
     * @return the property area
     */
    public double getPropertyArea() {
        return propertyArea;
    }

    /**
     * Gets property location.
     *
     * @return the property location
     */
    public String getPropertyLocation() {
        return propertyLocation;
    }

    /**
     * Gets property distance from center.
     *
     * @return the property distance from center
     */
    public double getPropertyDistanceFromCenter() {
        return propertyDistanceFromCenter;
    }

    /**
     * Gets property number bedrooms.
     *
     * @return the property number bedrooms
     */
    public String getPropertyNumberBedrooms() {
        return propertyNumberBedrooms;
    }

    /**
     * Gets property number bathrooms.
     *
     * @return the property number bathrooms
     */
    public String getPropertyNumberBathrooms() {
        return propertyNumberBathrooms;
    }

    /**
     * Gets property number parking spaces.
     *
     * @return the property number parking spaces
     */
    public String getPropertyNumberParkingSpaces() {
        return propertyNumberParkingSpaces;
    }

    /**
     * Gets property central heating.
     *
     * @return the property central heating
     */
    public String getPropertyCentralHeating() {
        return propertyCentralHeating;
    }

    /**
     * Gets property air conditioning.
     *
     * @return the property air conditioning
     */
    public String getPropertyAirConditioning() {
        return propertyAirConditioning;
    }

    /**
     * Gets property basement.
     *
     * @return the property basement
     */
    public String getPropertyBasement() {
        return propertyBasement;
    }

    /**
     * Gets property loft.
     *
     * @return the property loft
     */
    public String getPropertyLoft() {
        return propertyLoft;
    }

    /**
     * Gets property sun exposure.
     *
     * @return the property sun exposure
     */
    public String getPropertySunExposure() {
        return propertySunExposure;
    }

    /**
     * Gets property requested price.
     *
     * @return the property requested price
     */
    public double getPropertyRequestedPrice() {
        return propertyRequestedPrice;
    }

    /**
     * Gets property price.
     *
     * @return the property price
     */
    public double getPropertyPrice() {
        return propertyPrice;
    }

    /**
     * Gets commission.
     *
     * @return the commission
     */
    public double getCommission() {
        return commission;
    }

    /**
     * Gets contract duration.
     *
     * @return the contract duration
     */
    public String getContractDuration() {
        return contractDuration;
    }

    /**
     * Gets property date announce request.
     *
     * @return the property date announce request
     */
    public String getPropertyDateAnnounceRequest() {
        return propertyDateAnnounceRequest;
    }

    /**
     * Gets property date sale.
     *
     * @return the property date sale
     */
    public String getPropertyDateSale() {
        return propertyDateSale;
    }

    /**
     * Gets type business.
     *
     * @return the type business
     */
    public String getTypeBusiness() {
        return typeBusiness;
    }

    /**
     * Gets agency id.
     *
     * @return the agency id
     */
    public int getAgencyID() {
        return agencyID;
    }

    /**
     * Gets agency name.
     *
     * @return the agency name
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * Gets agency location.
     *
     * @return the agency location
     */
    public String getAgencyLocation() {
        return agencyLocation;
    }

    /**
     * Gets agency phone number.
     *
     * @return the agency phone number
     */
    public String getAgencyPhoneNumber() {
        return agencyPhoneNumber;
    }

    /**
     * Gets agency email address.
     *
     * @return the agency email address
     */
    public String getAgencyEmailAddress() {
        return agencyEmailAddress;
    }
}
