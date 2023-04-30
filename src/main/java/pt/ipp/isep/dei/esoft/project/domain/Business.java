package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The type Business.
 */
public class Business {
    /**
     * The Business type.
     */
    private BusinessType businessType;
    /**
     * The Price.
     */
    private Double price;

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return Objects.equals(businessType, business.businessType) && Objects.equals(price, business.price);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(businessType, price);
    }

    /**
     * Instantiates a new Business.
     */
    public Business() {
    }

    /**
     * Instantiates a new Business.
     *
     * @param businessTypeDesignation the business type designation
     * @param amount                  the amount
     */
    public Business(String businessTypeDesignation, Double amount) {
        this.businessType = new BusinessType(businessTypeDesignation);
        this.price = amount;
    }


    /**
     * Gets business type.
     *
     * @return the business type
     */
    public BusinessType getBusinessType() {
        return businessType;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }


    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return String.format("Type of Business: %s\nPrice: %.2f$\n", businessType, price);
    }
}
