package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Request {

    private final LocalDate requestDate;
    private final Agent agent;
    private final Business business;
    private Optional<Lease> lease;
    private Property property;
    private String ownerEmail;


    public Request(String ownerEmail, PropertyType propertyType, BusinessType businessType, Double amount,
                   Double area, Integer contractDuration, Optional<ArrayList<AvailableEquipment>> availableEquipment,
                   String streetName, String city, String district, String state, String zipCode,
                   Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Optional<Enum<SunExposureTypes>> sunExposure,
                   Integer numberBedroom, Optional<Integer> numberBathroom, Agent agent, Double distanceCityCenter,
                   ArrayList<Photograph> photograph) {
        this.ownerEmail = ownerEmail;
        this.requestDate = LocalDate.now();
        this.business = new Business(businessType, amount);
        if (businessType.toString().equals("Lease")) {
            this.lease = Optional.of(new Lease(contractDuration, businessType, amount));
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

    public Request(String ownerEmail, Property property, Business business, Optional<Lease> lease, LocalDate requestDate, Agent agent) {
        this.ownerEmail = ownerEmail;
        this.requestDate = requestDate;
        this.business = business;
        if (business.getBusinessType().toString().equals("Lease")) {
            this.lease = lease;
        }
        this.agent = agent;
        this.property = property;
    }

    public Request clone() {
        return new Request(this.ownerEmail, this.property, this.business, this.lease, this.requestDate, this.agent);
    }
}
