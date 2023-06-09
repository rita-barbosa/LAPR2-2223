package pt.ipp.isep.dei.esoft.project.repository;



import pt.ipp.isep.dei.esoft.project.domain.CommissionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommissionTypeRepository implements Serializable {

    /**
     * The list with existent commission types.
     */
    private final List<CommissionType> commissionTypes = new ArrayList<>();

    /**
     * This method returns an unalterable copy of the list of commission types.
     *
     * @return The list with existent commission types.
     */
    public List<CommissionType> getCommissionTypeList() {
        return List.copyOf(commissionTypes);
    }

    /**
     * This method returns a Commission Type that already exists based on its designation.
     *
     * @param designation - designation of the commission type requested
     * @return the commission type.
     * @throws IllegalArgumentException
     */
    public Optional<CommissionType> getCommissionTypeByDesignation(String designation) {
        CommissionType newCommissionType = new CommissionType(designation);
        Optional<CommissionType> commissionType = null;
        if(commissionTypes.isEmpty()){
            throw new IllegalArgumentException("List of commission types is empty.");
        }else{
            if (commissionTypes.contains(newCommissionType)) {
                commissionType = Optional.of(commissionTypes.get(commissionTypes.indexOf(newCommissionType)));
            }
            if (commissionType.isEmpty()) {
                throw new IllegalArgumentException("Commission Type requested for -" + designation + "- does not exist.");
            }
        }
        return commissionType;
    }

    /**
     * This method adds a new commission type to the list of property types.
     *
     * @param commissionType - the  commission type to be added
     * @return the commission type that was added to the list
     */
    public Optional<CommissionType> add(CommissionType commissionType) {
        Optional<CommissionType> newCommissionType = Optional.empty();
        boolean success = false;

        if (validateCommissionType(commissionType)) {
            newCommissionType = Optional.of(commissionType.clone());
            success = commissionTypes.add(newCommissionType.get());
        }
        if (!success) {
            newCommissionType = Optional.empty();
        }
        return newCommissionType;
    }

    /**
     * This method verifies if the commission type received is already present in the list of commission types.
     *
     * @param commissionType - the commission type to be validated
     * @return {@code true} if the list of commission types does not contain the received commission type; {@code false} otherwise.
     */
    private boolean validateCommissionType(CommissionType commissionType) {
        return (!(commissionTypes.contains(commissionType)));
    }
}
