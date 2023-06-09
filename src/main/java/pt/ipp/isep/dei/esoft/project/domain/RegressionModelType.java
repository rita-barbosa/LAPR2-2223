package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Regression model type.
 */
public class RegressionModelType implements Serializable {

    /**
     * The RegressionModelType designation;
     */
    private String designation;


    /**
     * Creates instance of RegressionModelType.
     *
     * @param designation the designation
     */
    public RegressionModelType(String designation) {
        this.designation = designation;
    }

    /**
     * Returns the instance designation.
     *
     * @return the string
     */
    public String getDesignation() {
        return this.designation;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the o argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegressionModelType that = (RegressionModelType) o;
        return Objects.equals(designation, that.designation);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "RegressionModelType: " + designation + "\n";
    }

    /**
     * Checks if the regressionModelType is simple linear.
     *
     * @return {@code true} if the regressionModelType is simple linear; {@code false} otherwise;
     */
    public boolean isSimpleLinear() {
        return this.designation.equalsIgnoreCase("simple linear");
    }

    /**
     * Checks if the regressionModelType is multilinear.
     *
     * @return {@code true} if the regressionModelType is multilinear; {@code false} otherwise;
     */
    public boolean isMultilinear() {
        return this.designation.equalsIgnoreCase("multilinear");
    }

    /**
     * This method creates and returns a copy of a regression model type instance.
     *
     * @return clone of regression model type instance.
     */
    public RegressionModelType clone() {
        return new RegressionModelType(this.designation);
    }
}
