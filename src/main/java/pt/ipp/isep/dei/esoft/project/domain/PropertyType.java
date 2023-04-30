package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;


public class PropertyType {
    /**
     * The designation of the property types.
     */
    private final String designation;

    /**
     * This method initializes a newly created PropertyType object with the received designation.
     *
     * @param designation - the designation of the property type
     */
    public PropertyType(String designation) {
        this.designation = designation;
    }

    /**
     * This method returns the designation of the property type.
     *
     * @return the designation of the property type
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * This method indicates whether a Property Type is "equal to" another object.
     *
     * @param o - the object with which to compare.
     * @return {@code true} if the Property Types are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyType)) return false;
        try {
            PropertyType that = (PropertyType) o;
            return designation.equals(that.designation);
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    /**
     * This method creates and returns a copy of a property type instance.
     *
     * @return clone of property type instance.
     */
    public PropertyType clone() {
        return new PropertyType(this.designation);
    }

    @Override
    public String toString() {
        return "" + designation;
    }
}