package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Optional;

public class Residence extends Property {

    private Integer numberBedroom;
    private Optional<Integer> numberBathroom;
    private Integer parkingSpace;
    private Optional<ArrayList<AvailableEquipment>> availableEquipment;

    public Residence(PropertyType propertyType, Double area, Optional<ArrayList<AvailableEquipment>> availableEquipment,
                     String streetName, String city, String district, String state, String zipCode, Integer parkingSpace,
                     Integer numberBedroom, Optional<Integer> numberBathroom, Double distanceCityCenter, ArrayList<Photograph> photograph) {
        super(propertyType, area, distanceCityCenter, photograph, streetName, city, district, state, zipCode);
        this.parkingSpace = parkingSpace;
        this.numberBathroom = numberBathroom;
        this.numberBedroom = numberBedroom;
        this.availableEquipment = availableEquipment;
    }


}
