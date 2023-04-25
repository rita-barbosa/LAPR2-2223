package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.BusinessType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BusinessTypeRepository {

    /**
     * The list with existent business types.
     */
    private final List<BusinessType> businessTypes = new ArrayList<>();

    /**
     * This method returns an unalterable copy of the list of business types.
     *
     * @return The list with existent business types.
     */
    public List<BusinessType> getBusinessTypeList() {
        return List.copyOf(businessTypes);
    }

    /**
     * This method returns a Business Type that already exists based on its designation.
     *
     * @param designation - designation of the business type requested
     * @return the business type.
     * @throws IllegalArgumentException
     */
    public BusinessType getBusinessTypeByDesignation(String designation)  {
        BusinessType newBusinessType = new BusinessType(designation);
        BusinessType businessType = null;
        if (businessTypes.contains(newBusinessType)) {
            businessType = businessTypes.get(businessTypes.indexOf(newBusinessType));
        }
        if (businessType == null) {
            throw new IllegalArgumentException("Business Type requested for -" + designation + "- does not exist.");
        }
        return businessType;
    }

    /**
     * This method adds a new business type to the list of business types.
     *
     * @param businessType - the  business type to be added
     * @return the business type that was added to the list
     */
    public Optional<BusinessType> add(BusinessType businessType) {
        Optional<BusinessType> newBusinessType = Optional.empty();
        boolean success = false;

        if (validateBusinessType(businessType)) {
            newBusinessType = Optional.of(businessType.clone());
            success = businessTypes.add(newBusinessType.get());
        }

        if (!success) {
            newBusinessType = Optional.empty();
        }

        return newBusinessType;
    }

    /**
     * This method verifies if the business type received is already present in the list of business types.
     *
     * @param businessType - the business type to be validated
     * @return {@code true} if the list of business types does not contain the received business type; {@code false} otherwise.
     */
    private boolean validateBusinessType(BusinessType businessType) {
        return (!(businessTypes.contains(businessType)));
    }

}


