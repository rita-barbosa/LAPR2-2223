package pt.ipp.isep.dei.esoft.project.domain;

public class Business {
    private BusinessType businessType;
    private Double price;

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
        return String.format("Type of Business: %s\nPrice: %f\n", businessType, price);
    }
}
