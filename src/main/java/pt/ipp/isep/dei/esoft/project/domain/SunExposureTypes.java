package pt.ipp.isep.dei.esoft.project.domain;

/**
 * Represents the sun exposure directions.
 */
public  enum SunExposureTypes {

    /**
     * The directions.
     */
    NORTH{   @Override public String toString() { return "North"; } },
    SOUTH { @Override public String toString() { return "South"; } },
    EAST {     @Override public String toString() { return "East"; } },
    WEST {     @Override public String toString() { return "West"; } };

    }