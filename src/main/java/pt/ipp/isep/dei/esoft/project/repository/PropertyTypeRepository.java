package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyTypeRepository {

    /**
     * The list with existent property types.
     */
    private final List<PropertyType> propertyTypes = new ArrayList<>();

    /**
     * This method returns an unalterable copy of the list of property types.
     *
     * @return The list with existent property types.
     */
    public List<PropertyType> getPropertyTypeList() {
        return List.copyOf(propertyTypes);
    }

    /**
     * This method returns a Property Type that already exists based on its designation.
     *
     * @param designation - designation of the property type requested
     * @return the property type.
     * @throws IllegalArgumentException
     */
    public PropertyType getPropertyTypeByDesignation(String designation) {
        PropertyType newPropertyType = new PropertyType(designation);
        PropertyType propertyType = null;
        if (propertyTypes.contains(newPropertyType)) {
            propertyType = propertyTypes.get(propertyTypes.indexOf(newPropertyType));
        }
        if (propertyType == null) {
            throw new IllegalArgumentException("Property Type requested for -" + designation + "- does not exist.");
        }
        return propertyType;
    }

    /**
     * This method adds a new property type to the list of property types.
     *
     * @param propertyType - the  property type to be added
     * @return the property type that was added to the list
     */
    public Optional<PropertyType> add(PropertyType propertyType) {
        Optional<PropertyType> newPropertyType = Optional.empty();
        boolean success = false;

        if (validatePropertyType(propertyType)) {
            newPropertyType = Optional.of(propertyType.clone());
            success = propertyTypes.add(newPropertyType.get());
        }

        if (!success) {
            newPropertyType = Optional.empty();
        }

        return newPropertyType;
    }

    /**
     * This method verifies if the property type received is already present in the list of property types.
     *
     * @param propertyType - the property type to be validated
     * @return {@code true} if the list of property types does not contain the received property type; {@code false} otherwise.
     */
    private boolean validatePropertyType(PropertyType propertyType) {
        return (!(propertyTypes.contains(propertyType)));
    }

}

