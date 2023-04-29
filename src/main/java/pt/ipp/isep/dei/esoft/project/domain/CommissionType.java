package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Objects;

public class CommissionType {
    /**
     * The designation of the commission type
     */
    private final String designation;

    /**
     * This method initializes a newly created Commission Type object with the received designation.
     *
     * @param designation - the designation of the commission type
     */
    public CommissionType(String designation) {
        this.designation = designation;
    }

    /**
     * This method returns the designation of the commission type.
     *
     * @return the designation of the commission type
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * This method indicates whether a Commission Type is "equal to" another.
     *
     * @param o - the commission type wih which to compare.
     * @return {@code true} if the Commission Types are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommissionType)) return false;
        try {
            CommissionType that = (CommissionType) o;
            return Objects.equals(designation, that.designation);
        } catch (ClassCastException e) {
            return false;
        }
    }
    /**
     * Returns a hash code value for the commission type object.
     *
     * @return a hash code value for the commission type object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }


    /**
     * This method creates and returns a copy of a commission type instance.
     *
     * @return clone of commission type instance.
     */
    public CommissionType clone() {
        return new CommissionType(this.designation);
    }
}
