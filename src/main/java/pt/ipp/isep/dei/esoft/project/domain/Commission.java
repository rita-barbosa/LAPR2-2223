package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Commission {
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
     * @param commissionType
     * @param commissionValue
     */
    public Commission(CommissionType commissionType, Double commissionValue){
        this.commissionValue = commissionValue;
        this.commissionType = commissionType;
    }

    /**
     * This method creates and returns a copy of a commission instance.
     * @return  clone of commission instance.
     */
    public Commission clone(){
        return new Commission(this.commissionType,this.commissionValue);
    }

}
