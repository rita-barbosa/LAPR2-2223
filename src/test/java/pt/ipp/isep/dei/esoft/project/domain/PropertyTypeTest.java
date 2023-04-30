package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PropertyTypeTest {

    @Test
    void ensureGetDesignationWorks() {
        PropertyType propertyType = new PropertyType("Property Type");
        assertEquals("Property Type", propertyType.getDesignation());
    }


    @Test
    void testEqualsSameObject() {
        PropertyType propertyType = new PropertyType("Property Type");
        assertEquals(propertyType, propertyType);
    }

    @Test
    void testEqualsDifferentClass() {
        PropertyType propertyType = new PropertyType("Property Type");
        assertNotEquals("", propertyType);
    }

    @Test
    void testEqualsNull() {
        PropertyType propertyType = new PropertyType("Property Type");
        assertNotEquals(null, propertyType);
    }

    @Test
    void testEqualsDifferentObject() {
        PropertyType propertyType1 = new PropertyType("Property Type 1");
        PropertyType propertyType2 = new PropertyType("Property Type 2");
        assertNotEquals(propertyType2, propertyType1);
    }

    @Test
    void testEqualsDifferentObjectSameDesignation() {
        PropertyType propertyType1 = new PropertyType("Property Type 1");
        PropertyType propertyType2 = new PropertyType("Property Type 1");
        assertEquals(propertyType1, propertyType2);
    }

    @Test
    void testEqualsForDifferentObjectType() {
        PropertyType propertyType1 = new PropertyType("Property Type 1");
        assertNotEquals(propertyType1, new Object());
    }

    @Test
    void testHashCodeSameObject() {
        PropertyType propertyType1 = new PropertyType("Property Type 1");
        assertEquals(propertyType1.hashCode(), propertyType1.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        PropertyType propertyType1 = new PropertyType("Property Type 1");
        PropertyType propertyType2 = new PropertyType("Property Type 2");
        assertNotEquals(propertyType2.hashCode(), propertyType1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        PropertyType propertyType = new PropertyType("Property Type");
        PropertyType clone = propertyType.clone();
        assertEquals(propertyType, clone);
    }
}