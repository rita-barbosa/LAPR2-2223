package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class House extends Residence {
    private final Boolean basement;
    private final Boolean inhabitableLoft;
    private final Enum<SunExposureTypes> sunExposure;

    public House(PropertyType propertyType, ArrayList<AvailableEquipment> availableEquipment, Double area,
                 String streetName, String city, String district, String state, String zipCode,
                 Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                 Integer numberBedroom, Integer numberBathroom, Double distanceCityCenter,
                 ArrayList<Photograph> photograph) {
        super(propertyType,area, availableEquipment, streetName, city, district, state, zipCode, parkingSpace, numberBedroom,
                numberBathroom, distanceCityCenter, photograph);
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }
}
