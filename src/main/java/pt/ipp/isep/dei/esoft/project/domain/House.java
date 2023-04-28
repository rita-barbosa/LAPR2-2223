package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class House extends Residence {
    private final Boolean basement;
    private final Boolean inhabitableLoft;
    private final Enum<SunExposureTypes> sunExposure;

    public House(PropertyType propertyType, List<String> availableEquipmentDescription, Double area,
                 String streetName, String city, String district, String state, String zipCode,
                 Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                 Integer numberBedroom, Integer numberBathroom, Double distanceCityCenter,
                 List<String> uri) {
        super(propertyType, area, availableEquipmentDescription, streetName, city, district, state, zipCode, parkingSpace,
                numberBedroom, numberBathroom, distanceCityCenter, uri);
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }

    public String toString(){
        return super.toString() + String.format("Basement: %b\n Inhabitable Loft: %b\n Sun Exposure: %s\n", basement, inhabitableLoft, sunExposure);
    }
}
