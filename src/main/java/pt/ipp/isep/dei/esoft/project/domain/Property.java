package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Optional;

public class Property {

    private PropertyType propertyType;
    private Double area;
    private Double distanceCityCenter;
    private Location location;

    private ArrayList<Photograph> photograph;

    public Property(PropertyType propertyType, Double area, Double distanceCityCenter, ArrayList<Photograph> photograph, String streetName,
                    String city, String district, String state, String zipCode) {
        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.photograph = photograph;
        this.location = new Location(streetName, city, district, state, zipCode);
    }

}
