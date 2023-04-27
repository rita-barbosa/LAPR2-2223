package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Property {

    private final PropertyType propertyType;
    private final Double area;
    private final Double distanceCityCenter;
    private final Location location;

    private final ArrayList<Photograph> photograph;

    public Property(PropertyType propertyType, Double area, Double distanceCityCenter, ArrayList<Photograph> photograph, String streetName,
                    String city, String district, String state, String zipCode) {
        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.photograph = photograph;
        this.location = new Location(streetName, city, district, state, zipCode);
    }

}
