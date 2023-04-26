package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Request {

    private final LocalDate requestDate = LocalDate.now();
    private double amount;
    private int contractDuration;
    private Agency agency;
    private Agent agent;
    private BusinessType businessType;


    public Request(PropertyType propertyType, BusinessType businessType, double amount,
                   double area, int contractDuration, Optional<ArrayList<AvailableEquipment>> availableEquipment,
                   String streetName, String city, String district, String state, String zipCode,
                   boolean basement, boolean inhabitableLoft, boolean parkingSpace, Optional<String> sunExposure,
                   int numberBedroom, Optional<Integer> numberBathroom, Agent agent, double distanceCityCenter,
                   ArrayList<Photograph> photograph, Agency agency) {
        this.amount = amount;
        this.businessType = businessType;
        this.contractDuration = contractDuration;
        this.agency = agency;
        this.agent = agent;
        Property property;

        switch (propertyType.toString()) {
            case "Land":
                property = new Property(propertyType, area, streetName, city, district, state, zipCode, distanceCityCenter, photograph);
                break;
            case "Apartment":
                property = new Property(propertyType, area, streetName, city, district, state, zipCode, parkingSpace,
                        numberBedroom, numberBathroom, distanceCityCenter, photograph, availableEquipment);
                break;
            case "House":
                property = new Property(propertyType, area, streetName, city, district, state, zipCode, basement, inhabitableLoft,
                        parkingSpace, sunExposure, numberBedroom, numberBathroom, distanceCityCenter, photograph, availableEquipment);
                break;
            default:
                break;
        }
    }
}
