package pt.ipp.isep.dei.esoft.project.domain;

public class Commission {
    private CommissionType commissionType;
    private Double commissionValue;

    public Commission(CommissionType commissionType, Double commissionValue){
        this.commissionValue = commissionValue;
        this.commissionType = commissionType;
    }

    public Commission clone(){
        return new Commission(this.commissionType,this.commissionValue);
    }
}
