package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Request {

    private final LocalDate requestDate;
    private final Agent agent;
    private Business business;
    private Property property;
    private String ownerEmail;


    public Request(String ownerEmail, PropertyType propertyType, BusinessType businessType, Double amount,
                   Double area, Integer contractDuration, ArrayList<AvailableEquipment> availableEquipment,
                   String streetName, String city, String district, String state, String zipCode, Boolean basement,
                   Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                   Integer numberBedroom, Integer numberBathroom, Agent agent, Double distanceCityCenter,
                   ArrayList<Photograph> photograph) {
        this.ownerEmail = ownerEmail;
        this.requestDate = LocalDate.now();
        if (businessType.toString().equals("Lease")) {
            this.business = new Lease(contractDuration, businessType, amount);
        }
        this.agent = agent;

        switch (propertyType.toString()) {
            case "Land":
                this.property = new Property(propertyType, area, distanceCityCenter, photograph, streetName, city,
                        district, state, zipCode);
                break;
            case "Apartment":
                this.property = new Residence(propertyType, area, availableEquipment, streetName, city, district, state,
                        zipCode, parkingSpace, numberBedroom, numberBathroom, distanceCityCenter, photograph);
                break;
            case "House":
                this.property = new House(propertyType, availableEquipment, area, streetName, city, district, state,
                        zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom,
                        distanceCityCenter, photograph);
                break;
            default:
                break;
        }
    }

    public Request(String ownerEmail, PropertyType propertyType, BusinessType businessType, Double amount, Double area,
                   ArrayList<AvailableEquipment> availableEquipment, String streetName, String city, String district,
                   String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace,
                   Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Agent agent,
                   Double distanceCityCenter, ArrayList<Photograph> photograph) {
        this.ownerEmail = ownerEmail;
        this.requestDate = LocalDate.now();
        this.business = new Business(businessType, amount);
        this.agent = agent;

        switch (propertyType.toString()) {
            case "Land":
                this.property = new Property(propertyType, area, distanceCityCenter, photograph, streetName, city,
                        district, state, zipCode);
                break;
            case "Apartment":
                this.property = new Residence(propertyType, area, availableEquipment, streetName, city, district, state,
                        zipCode, parkingSpace, numberBedroom, numberBathroom, distanceCityCenter, photograph);
                break;
            case "House":
                this.property = new House(propertyType, availableEquipment, area, streetName, city, district, state,
                        zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom,
                        distanceCityCenter, photograph);
                break;
            default:
                break;
        }
    }

    public Request(String ownerEmail, Property property, Business business, LocalDate requestDate, Agent agent) {
        this.ownerEmail = ownerEmail;
        this.requestDate = requestDate;
        this.business = business;
        this.agent = agent;
        this.property = property;
    }

    public Request clone() {
        return new Request(this.ownerEmail, this.property, this.business, this.requestDate, this.agent);
    }
}
