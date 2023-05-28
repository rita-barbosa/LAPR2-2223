package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The type Commission type dto.
 */
public class CommissionTypeDto {

    /**
     * The Commission type.
     */
    private String commissionType;

    /**
     * Instantiates a new Commission type dto.
     *
     * @param commissionType the commission type
     */
    public CommissionTypeDto(String commissionType) {
        this.commissionType = commissionType;
    }

    /**
     * Get commission type string.
     *
     * @return the string
     */
    public String getCommissionType(){
        return commissionType;
    }

    /**
     * Returns a string representation of the CommissionTypeDto object.
     * The string contains the commission types
     *
     * @return a string representation of the CommissionTypeDto object.
     */
    @Override
    public String toString() {
        return String.format("Commission Type: %s\n", commissionType);
    }

    /**
     * This method checks if two instances are equal.
     *
     * @param o - object to be compared to
     * @return {@code true} if objects are equal;{@code false}otherwise;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommissionTypeDto that = (CommissionTypeDto) o;
        return Objects.equals(commissionType, that.commissionType);
    }

    /**
     * Returns a hash code value for the object.
     * If two objects are equal, according to the equals(Object) method, then calling the hashCode method on each
     * of the two objects must produce the same integer result.
     *
     * @return the hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(commissionType);
    }
}
