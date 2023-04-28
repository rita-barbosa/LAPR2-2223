package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Optional;

public class Lease extends Business{
    private Integer contractDuration;

    public Lease(Integer contractDuration, String businessTypeDesignation, Double amount) {
        super(businessTypeDesignation, amount);
        this.contractDuration = contractDuration;
    }

    public Integer getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(Integer contractDuration) {
        this.contractDuration = contractDuration;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Contract Duration: %d\n", contractDuration);
    }
}
