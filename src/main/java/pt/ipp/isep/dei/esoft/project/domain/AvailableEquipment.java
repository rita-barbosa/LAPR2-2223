package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents the available equipment of a property (Apartment or House).
 */
public class AvailableEquipment {
    /**
     * The description of the Available Equipment.
     */
    private String description;

    /**
     * The constant for central heating.
     */
    public static final String CENTRAL_HEATING = "central heating";
    /**
     * The constant for air conditioning.
     */
    public static final String AIR_CONDITIONING = "air conditioning";

    /**
     * Constructs an Available Equipment instance.
     */
    public AvailableEquipment() {
    }

    /**
     * Constructs an Available Equipment instance with the specified description.
     *
     * @param description - the description of the available equipment.
     */
    public AvailableEquipment(String description) {
        this.description = description;
    }

    /**
     * This method returns the description of the available equipment.
     *
     * @return the description of the available equipment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method creates and returns a copy of a Available Equipment instance.
     *
     * @return clone of Available Equipment instance.
     */
    public AvailableEquipment clone() {
        return new AvailableEquipment(this.description);
    }

    /**
     *  This method indicates whether Available Equipment is "equal to" another object.
     *
     * @param o - the object with which to compare.
     * @return {@code true} if they are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableEquipment that = (AvailableEquipment) o;
        return Objects.equals(description, that.description);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }


    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "    * " + description;
    }
}
