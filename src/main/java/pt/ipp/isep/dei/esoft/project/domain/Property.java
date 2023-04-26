package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Optional;

public class Property {

    private PropertyType propertyType;
    private double area;
    private double distanceCityCenter;
    private boolean basement;
    private boolean inhabitableLoft;
    private boolean  parkingSpace;
    private Optional<String> sunExposure;
    private int numberBedroom;
    private Optional<Integer> numberBathroom;
    private ArrayList<Photograph> photograph;
    private Optional<ArrayList<AvailableEquipment>> availableEquipment;
    private Location location;

    // LAND CONSTRUCTOR
    public Property(PropertyType propertyType, double area, String streetName, String city, String district,
                    String state, String zipCode, double distanceCityCenter, ArrayList<Photograph> photograph) {

        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.photograph = photograph;
        this.location = new Location(streetName, city, district, state, zipCode);
    }

    public Property(PropertyType propertyType, double area, String streetName, String city, String district,
                    String state, String zipCode, boolean parkingSpace, int numberBedroom, Optional<Integer> numberBathroom,
                    double distanceCityCenter, ArrayList<Photograph> photograph, Optional<ArrayList<AvailableEquipment>> availableEquipment) {
        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.parkingSpace = parkingSpace;
        this.numberBedroom = numberBedroom;
        this.numberBathroom = numberBathroom;
        this.photograph = photograph;
        this.availableEquipment = availableEquipment;
        this.location = new Location(streetName, city, district, state, zipCode);
    }

    public Property(PropertyType propertyType, double area, String streetName, String city, String district, String state,
                    String zipCode, boolean basement, boolean inhabitableLoft, boolean parkingSpace, Optional<String> sunExposure,
                    int numberBedroom, Optional<Integer> numberBathroom, double distanceCityCenter, ArrayList<Photograph> photograph,
                    Optional<ArrayList<AvailableEquipment>> availableEquipment) {
        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.parkingSpace = parkingSpace;
        this.sunExposure = sunExposure;
        this.numberBedroom = numberBedroom;
        this.numberBathroom = numberBathroom;
        this.photograph = photograph;
        this.availableEquipment = availableEquipment;
        this.location = new Location(streetName, city, district, state, zipCode);
    }
}
