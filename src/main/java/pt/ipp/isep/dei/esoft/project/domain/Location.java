package pt.ipp.isep.dei.esoft.project.domain;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Location {
    private final Integer STATE_STRING_MAX_LENGTH = 6;
    private final Integer ZIPCODE_STRING_MAX_LENGTH = 5;

    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;

    public Location(String streetName, String city, String district, String state, String zipCode) {

        if (!(validateLocation(streetName, city, district, state, zipCode))) {
            System.out.println("Location is not correct. Please submit new data. Zip Code (5 caracters) | State (max. 6 characters)");
            String[] newValues = getNewLocation(streetName, city, district, state, zipCode);
            streetName = newValues[0];
            city = newValues[1];
            district = newValues[2];
            state = newValues[3];
            zipCode = newValues[4];
        }

        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.zipCode = zipCode;
    }

    private String[] getNewLocation(String streetName, String city, String district, String state, String zipCode) {
        String[] newValues = {streetName, city, district, state, zipCode};
        Scanner input = new Scanner(System.in);
        while (streetName.isBlank()) {
            System.out.println("Invalid Street Name. Provide a new one.");
            streetName = input.nextLine();
        }
        while (city.isBlank()) {
            System.out.println("Invalid City. Provide a new one.");
            city = input.nextLine();
        }
        while (district.isBlank()) {
            System.out.println("Invalid District. Provide a new one.");
            district = input.nextLine();
        }
        while (!validateState(state)) {
            System.out.println("Invalid State Abbreviation. Provide a new one.");
            state = input.nextLine();
        }
        while (!validateZipCode(zipCode)) {
            System.out.println("Invalid Zip Code. Provide a new one.");
            zipCode = input.nextLine();
        }
        return newValues;
    }

    private boolean validateLocation(String streetName, String city, String district, String state, String zipCode) {
        if (streetName.isBlank() && city.isBlank() && district.isBlank() && state.isBlank() && zipCode.isBlank()){
            return false;
        }else if (streetName.isEmpty() && city.isEmpty() && district.isEmpty() && state.isEmpty() && zipCode.isEmpty()){
            return true;
        }
        return validateZipCode(zipCode) && validateState(state);
    }


    private boolean validateZipCode(String zipCode) {
        return zipCode.length() == ZIPCODE_STRING_MAX_LENGTH;
    }

    private boolean validateState(String state) {
        return state.length() > STATE_STRING_MAX_LENGTH;
    }

    /**
     * This method converts to string an instance of location.
     *
     * @return a string with said location attributes.
     */
    @Override
    public String toString() {
        return String.format("Location: %s, %s, %s, %s, %s", this.streetName, this.city, this.district, this.state, this.zipCode);
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
