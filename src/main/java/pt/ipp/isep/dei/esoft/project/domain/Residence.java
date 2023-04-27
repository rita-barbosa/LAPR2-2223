package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Residence extends Property {

    private final Integer numberBedroom;
    private final Integer numberBathroom;
    private final Integer parkingSpace;
    private final ArrayList<AvailableEquipment> availableEquipment;

    public Residence(PropertyType propertyType, Double area, ArrayList<AvailableEquipment> availableEquipment,
                     String streetName, String city, String district, String state, String zipCode, Integer parkingSpace,
                     Integer numberBedroom, Integer numberBathroom, Double distanceCityCenter, ArrayList<Photograph> photograph) {
        
        super(propertyType, area, distanceCityCenter, photograph, streetName, city, district, state, zipCode);
        this.parkingSpace = parkingSpace;
        this.numberBathroom = numberBathroom;
        this.numberBedroom = numberBedroom;
        this.availableEquipment = availableEquipment;
    }

    public Integer getNumberBedroom() {
        return numberBedroom;
    }

    public Integer getNumberBathroom() {
        return numberBathroom;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public ArrayList<AvailableEquipment> getAvailableEquipment() {
        return availableEquipment;
    }
}
