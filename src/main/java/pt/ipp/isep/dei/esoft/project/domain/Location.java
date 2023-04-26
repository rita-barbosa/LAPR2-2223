package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Location {

    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;

    public Location(String streetName, String city, String district, String state, String zipCode) {
        if (validateLocation(streetName, city, district, state, zipCode)) {
            this.streetName = streetName;
            this.city = city;
            this.district = district;
            this.state = state;
            this.zipCode = zipCode;
        } else {
            System.out.println("Location is not correct. Please submit new data.");
        }

    }

    private boolean validateLocation(String streetName, String city, String district, String state, String zipCode) {
        if (streetName.equals(null) || city.equals(null) || district.equals(null) || state.equals(null) || zipCode.equals(null)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method converts to string an instance of location.
     * @return a string with said location attributes.
     */
    @Override
    public String toString() {
        return String.format("Location { %s, %s, %s, %s, %s }", this.streetName, this.city, this.district, this.state, this.zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return streetName.equals(location.streetName) && city.equals(location.city) && district.equals(location.district) && state.equals(location.state) && zipCode.equals(location.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, city, district, state, zipCode);
    }
}
