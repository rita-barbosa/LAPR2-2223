package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Optional;

public class House extends Residence {
    private Boolean basement;
    private Boolean inhabitableLoft;
    private Optional<String> sunExposure;
    private Boolean BASEMENT_BY_OMISSION = false;
    private Boolean INHABITABLELOFT_BY_OMISSION = false;
    private Optional<String> SUNEXPOSURE_BY_OMISSION = null;


    public House(PropertyType propertyType, Optional<ArrayList<AvailableEquipment>> availableEquipment, Double area,
                 String streetName, String city, String district, String state, String zipCode,
                 Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Optional<String> sunExposure,
                 Integer numberBedroom, Optional<Integer> numberBathroom, Double distanceCityCenter,
                 ArrayList<Photograph> photograph) {
        super(propertyType,area, availableEquipment, streetName, city, district, state, zipCode, parkingSpace, numberBedroom,
                numberBathroom, distanceCityCenter, photograph);
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }
}
