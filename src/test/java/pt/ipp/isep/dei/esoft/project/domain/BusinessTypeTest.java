package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTypeTest {

    @Test
    void ensureGetDesignationWorks() {
        BusinessType businessType1 = new BusinessType("Business Type");
        assertEquals("Business Type", businessType1.getDesignation());
    }


    @Test
    void testEqualsSameObject() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        assertEquals(businessType1, businessType1);
    }

    @Test
    void testEqualsDifferentClass() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        assertNotEquals("", businessType1);
    }

    @Test
    void testEqualsNull() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        assertNotEquals(null, businessType1);
    }

    @Test
    void testEqualsDifferentObject() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        BusinessType businessType2 = new BusinessType("Business Type 2");
        assertNotEquals(businessType1, businessType2);
    }

    @Test
    void testEqualsDifferentObjectSameDesignation() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        BusinessType businessType2 = new BusinessType("Business Type 1");
        assertEquals(businessType1, businessType2);
    }

    @Test
    void testEqualsForDifferentObjectType() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        assertNotEquals(businessType1, new Object());
    }

    @Test
    void testHashCodeSameObject() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        assertEquals(businessType1.hashCode(), businessType1.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        BusinessType businessType1 = new BusinessType("Business Type 1");
        BusinessType businessType2 = new BusinessType("Business Type 2");
        assertNotEquals(businessType1.hashCode(), businessType2.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        BusinessType businessType = new BusinessType("Business Type");
        BusinessType clone = businessType.clone();
        assertEquals(businessType, clone);
    }
}