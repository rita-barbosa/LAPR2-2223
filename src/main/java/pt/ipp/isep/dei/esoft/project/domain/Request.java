package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.List;

public class Request {

    private final LocalDate requestDate;
    private final Employee agent;
    private final Business business;
    private Property property;
    private final String ownerEmail;


    public Request(String ownerEmail, PropertyType propertyType, BusinessType businessType, Double amount,
                   Double area, Integer contractDuration, List<String> availableEquipmentDescription,
                   String streetName, String city, String district, String state, String zipCode, Boolean basement,
                   Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                   Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter,
                   List<String> uri) {

        this.ownerEmail = ownerEmail;
        this.requestDate = LocalDate.now();
        this.business = new Lease(contractDuration, businessType, amount);

        this.agent = agent;

        switch (propertyType.toString()) {
            case "Land":
                this.property = new Property(propertyType, area, distanceCityCenter, uri, streetName, city,
                        district, state, zipCode);
                break;
            case "Apartment":
                this.property = new Residence(propertyType, area, availableEquipmentDescription, streetName, city, district, state,
                        zipCode, parkingSpace, numberBedroom, numberBathroom, distanceCityCenter, uri);
                break;
            case "House":
                this.property = new House(propertyType, availableEquipmentDescription, area, streetName, city, district, state,
                        zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom,
                        distanceCityCenter, uri);
                break;
            default:
                break;
        }
    }

    public Request(String ownerEmail, PropertyType propertyType, BusinessType businessType, Double amount, Double area,
                   List<String> availableEquipment, String streetName, String city, String district,
                   String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace,
                   Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Employee agent,
                   Double distanceCityCenter, List<String> uri) {
        this.ownerEmail = ownerEmail;
        this.requestDate = LocalDate.now();
        this.business = new Business(businessType, amount);
        this.agent = agent;

        switch (propertyType.toString().toLowerCase()) {
            case "land":
                this.property = new Property(propertyType, area, distanceCityCenter, uri, streetName, city,
                        district, state, zipCode);
                break;
            case "apartment":
                this.property = new Residence(propertyType, area, availableEquipment, streetName, city, district, state,
                        zipCode, parkingSpace, numberBedroom, numberBathroom, distanceCityCenter, uri);
                break;
            case "house":
                this.property = new House(propertyType, availableEquipment, area, streetName, city, district, state,
                        zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom,
                        distanceCityCenter, uri);
                break;
            default:
                break;
        }
    }

    public Request(String ownerEmail, Property property, Business business, LocalDate requestDate, Employee agent) {
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
