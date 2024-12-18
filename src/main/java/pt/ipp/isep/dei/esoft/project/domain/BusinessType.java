package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class BusinessType implements Serializable {
    /**
     * The designation of business type.
     */
    private final String designation;

    /**
     * This method initializes a newly created BusinessType object with the received designation.
     *
     * @param designation
     */
    public BusinessType(String designation) {
        this.designation = designation;
    }

    /**
     * This method returns the designation of the business type.
     *
     * @return the designation of the business type
     */
    public String getDesignation() {
        return designation;
    }


    /**
     * This method indicates whether a Business Type is "equal to" another.
     *
     * @param o -  the business type wih which to compare.
     * @return {@code true} if the Business Types are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessType)) return false;
        try {
            BusinessType that = (BusinessType) o;
            return designation.equalsIgnoreCase(that.designation);
        } catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * @return
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
    public BusinessType clone() {
        return new BusinessType(this.designation);
    }

    /**
     * To string
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "" + designation;
    }
}