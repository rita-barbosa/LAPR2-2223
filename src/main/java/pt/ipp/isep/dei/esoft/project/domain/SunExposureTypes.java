package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Represents the sun exposure directions.
 */
public  enum SunExposureTypes implements Serializable {

    /**
     * The directions.
     */
    NORTH{   @Override public String toString() { return "North"; } },
    SOUTH { @Override public String toString() { return "South"; } },
    EAST {     @Override public String toString() { return "East"; } },
    WEST {     @Override public String toString() { return "West"; } };

    }