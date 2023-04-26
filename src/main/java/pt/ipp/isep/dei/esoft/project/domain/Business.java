package pt.ipp.isep.dei.esoft.project.domain;

public class Business {
    private BusinessType businessType;
    private Double amount;

    public Business(BusinessType businessType, Double amount) {
        this.businessType = businessType;
        this.amount = amount;
    }

    public Business() {
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public Double getAmount() {
        return amount;
    }
}
