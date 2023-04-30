package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Optional;

/**
 * The type Lease.
 */
public class Lease extends Business{
    /**
     * The Contract duration.
     */
    private Integer contractDuration;

    /**
     * Instantiates a new Lease.
     *
     * @param contractDuration        the contract duration
     * @param businessTypeDesignation the business type designation
     * @param amount                  the amount (price or rent)
     */
    public Lease(Integer contractDuration, String businessTypeDesignation, Double amount) {
        super(businessTypeDesignation, amount);
        this.contractDuration = contractDuration;
    }

    /**
     * Gets contract duration.
     *
     * @return the contract duration
     */
    public Integer getContractDuration() {
        return contractDuration;
    }

    /**
     * Sets contract duration.
     *
     * @param contractDuration the contract duration
     */
    public void setContractDuration(Integer contractDuration) {
        this.contractDuration = contractDuration;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Contract Duration: %d\n", contractDuration);
    }
}
