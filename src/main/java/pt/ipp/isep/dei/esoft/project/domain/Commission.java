package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.CommissionTypeRepository;

import java.io.Serializable;
import java.util.Objects;

public class Commission implements Serializable {
    /**
     * The type of the commission
     */
    private CommissionType commissionType;
    /**
     * The value of the commission.
     */
    private Double commissionValue;

    /**
     * Constructs a new Commission instance with the specified commission type and value.
     *
     * @param commissionType
     * @param commissionValue
     */
    public Commission(CommissionType commissionType, Double commissionValue) {
        this.commissionValue = commissionValue;
        this.commissionType = commissionType;
    }

    /**
     * This method creates and returns a copy of a commission instance.
     *
     * @return clone of commission instance.
     */
    public Commission clone() {
        return new Commission(this.commissionType, this.commissionValue);
    }


    /**
     * This method indicates whether a Commission is "equal to" another.
     *
     * @param o - the commission wih which to compare.
     * @return {@code true} if the Commissions are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commission that = (Commission) o;
        return Objects.equals(commissionType, that.commissionType) && Objects.equals(commissionValue, that.commissionValue);
    }

    /**
     * Returns a hash code value for the commission object.
     *
     * @return a hash code value for the commission object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(commissionType, commissionValue);
    }

    public String toString() {
        StringBuilder s = new StringBuilder("Commission Type: " + commissionType.getDesignation() + "\nCommission Value: " + commissionValue);
        if (commissionType.getDesignation().equalsIgnoreCase("percentual")) {
            s.append("%");
        } else {
            s.append("$");
        }
        return s.toString();
    }

}
