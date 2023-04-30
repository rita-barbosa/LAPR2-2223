package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTypeRepositoryTest {

    @Test
    void ensureGetPropertyTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        PropertyType p1 = new PropertyType("Property Type");
        propertyTypeRepository.add(p1);
        int expectedSize = 1;

        int size = propertyTypeRepository.getPropertyTypeList().size();

        assertEquals(expectedSize, size);
        assertEquals(p1, propertyTypeRepository.getPropertyTypeList().get(size - 1));
    }

    @Test
    void getPropertyTypeListEmptyList() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        String propertyTypeDesignation = "Property Type";

        assertThrows(IllegalArgumentException.class,
                () -> propertyTypeRepository.getPropertyTypeByDesignation(propertyTypeDesignation));
    }

    @Test
    void ensureGetPropertyTypeByDesignationWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        PropertyType p1 = new PropertyType("Property Type");
        propertyTypeRepository.add(p1);

        Optional<PropertyType> returnP1 = propertyTypeRepository.getPropertyTypeByDesignation("Property Type");

        assertEquals(p1,returnP1.get());
    }

    @Test
    void ensureAddPropertyTypeWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        PropertyType p1 = new PropertyType("Property Type");
        propertyTypeRepository.add(p1);

        Optional<PropertyType> returnP1 = propertyTypeRepository.getPropertyTypeByDesignation("Property Type");

        assertEquals(p1,returnP1.get());
    }
    @Test
    void ensureAddDuplicatedPropertyTypeFails() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        PropertyType p1 = new PropertyType("Property Type");
        propertyTypeRepository.add(p1);

        Optional<PropertyType> result = propertyTypeRepository.add(p1);

        assertTrue(result.isEmpty());
    }

}