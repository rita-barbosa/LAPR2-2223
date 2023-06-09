package pt.ipp.isep.dei.esoft.project.domain.mapper;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Request;
import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;

import java.util.List;

/**
 * The type Legacy system mapper.
 */
public class LegacySystemMapper {
    /**
     * To dto legacy system dto.
     *
     * @param legacySystemInformationList the legacy system information list
     * @return the legacy system dto
     */
    public static LegacySystemDto toDto(List<String> legacySystemInformationList) {
        return new LegacySystemDto(legacySystemInformationList);
    }

    /**
     * To model agency agency.
     *
     * @param dto the dto
     * @return the agency
     * @throws IllegalArgumentException the illegal argument exception
     */
    public static Agency toModelAgency(LegacySystemDto dto) throws IllegalArgumentException{
        int agencyId = dto.getAgencyID();
        String agencyName = dto.getAgencyName();
        String agencyLocation = dto.getAgencyLocation();
        String agencyPhoneNumber = dto.getAgencyPhoneNumber();
        String agencyEmailAddress = dto.getAgencyEmailAddress();

        return new Agency(agencyId, agencyName, agencyEmailAddress, agencyPhoneNumber, agencyLocation);
    }

    /**
     * To model person person.
     *
     * @param dto the dto
     * @return the person
     */
    public static Person toModelPerson(LegacySystemDto dto) {
        String ownerDefaultPassportNumber = "000000000";
        String ownerName = dto.getOwnerName();
        String ownerTIN = dto.getOwnerTIN();
        String ownerPhoneNumber = dto.getOwnerPhone();
        String ownerEmail = dto.getOwnerEmail();

        return new Person(ownerName, ownerTIN, ownerPhoneNumber, ownerEmail,ownerDefaultPassportNumber);
    }

    /**
     * To model request request.
     *
     * @param dto the dto
     * @return the request
     */
    public static Request toModelRequest(LegacySystemDto dto) {
        Request newRequest = null;
        String dateAnnounceRequest = dto.getPropertyDateAnnounceRequest();
        String businessTypeDesignation = dto.getTypeBusiness();
        String contractDuration = dto.getContractDuration();
        Double price = dto.getPropertyRequestedPrice();
        String propertyTypeDesignation = dto.getPropertyType();
        Double area = dto.getPropertyArea();
        String location = dto.getPropertyLocation();
        Double distanceCityCenter = dto.getPropertyDistanceFromCenter();

        if (propertyTypeDesignation.equalsIgnoreCase("land")) {
            newRequest = new Request(businessTypeDesignation, contractDuration, price, propertyTypeDesignation, area, location, distanceCityCenter,
                    dateAnnounceRequest);
        } else if (propertyTypeDesignation.equalsIgnoreCase("apartment") || propertyTypeDesignation.equalsIgnoreCase("house")) {
            String numberBedrooms = dto.getPropertyNumberBedrooms();
            String numberBathrooms = dto.getPropertyNumberBathrooms();
            String numberParkingSpaces = dto.getPropertyNumberParkingSpaces();
            String centralHeating = dto.getPropertyCentralHeating();
            String airConditioning = dto.getPropertyAirConditioning();
            if (propertyTypeDesignation.equalsIgnoreCase("apartment")) {
                newRequest = new Request(businessTypeDesignation, contractDuration, price, propertyTypeDesignation, area, location, distanceCityCenter,
                        numberBedrooms, numberBathrooms, numberParkingSpaces, airConditioning, centralHeating, dateAnnounceRequest);
            }
            if (propertyTypeDesignation.equalsIgnoreCase("house")) {
                String basement = dto.getPropertyBasement();
                String inhabitableLoft = dto.getPropertyLoft();
                String sunExposure = dto.getPropertySunExposure();
                newRequest = new Request(businessTypeDesignation, contractDuration, price, propertyTypeDesignation, area, location, distanceCityCenter,
                        numberBedrooms, numberBathrooms, numberParkingSpaces, airConditioning, centralHeating, basement, inhabitableLoft, sunExposure, dateAnnounceRequest);
            }
        }
        return newRequest;
    }

    /**
     * Gets commission value.
     *
     * @param dto the dto
     * @return the commission value
     */
    public static double getCommissionValue(LegacySystemDto dto) {
        return dto.getCommission();
    }
}

