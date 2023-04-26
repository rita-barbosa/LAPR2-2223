package pt.ipp.isep.dei.esoft.project.domain;

public class Location {

    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;

    public Location(String streetName, String city, String district, String state, String zipCode) {
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.zipCode = zipCode;
    }
}
