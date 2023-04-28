package pt.ipp.isep.dei.esoft.project.domain;

public class Business {
    private BusinessType businessType;
    private Double amount;

    public Business() {
    }

    public Business(BusinessType businessType, Double amount) {
        this.businessType = businessType;
        this.amount = amount;
    }


    public BusinessType getBusinessType() {
        return businessType;
    }

    public Double getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return String.format("Type of Business: %s\nPrice: %f\n", businessType, amount);
    }
}
