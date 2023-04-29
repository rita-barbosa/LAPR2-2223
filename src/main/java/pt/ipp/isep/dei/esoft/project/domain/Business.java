package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Business {
    private BusinessType businessType;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return Objects.equals(businessType, business.businessType) && Objects.equals(price, business.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessType, price);
    }

    public Business() {
    }

    public Business(String businessTypeDesignation, Double amount) {
        this.businessType = new BusinessType(businessTypeDesignation);
        this.price = amount;
    }


    public BusinessType getBusinessType() {
        return businessType;
    }

    public Double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return String.format("Type of Business: %s\nPrice: %.2f\n", businessType, price);
    }
}
