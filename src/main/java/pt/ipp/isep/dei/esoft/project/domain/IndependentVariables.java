package pt.ipp.isep.dei.esoft.project.domain;

public enum IndependentVariables {
    /**
     * The directions.
     */
    AREA{   @Override public String toString() { return "Area"; } },
    DISTANCE_CITY_CENTER { @Override public String toString() { return "Distance of City Centre"; } },
    NUMBER_BEDROOMS {     @Override public String toString() { return "Number of Bedrooms"; } },
    NUMBER_BATHROOMS {     @Override public String toString() { return "Number of Bathrooms"; } },
    NUMBER_PARKING_SPACES {     @Override public String toString() { return "Number of Parking Spaces"; } };
}
