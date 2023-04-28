package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommissionTypeTest {

    @Test
    void ensureGetDesignationWorks() {
        CommissionType commissionType1 = new CommissionType("Commission Type");
        assertEquals("Commission Type", commissionType1.getDesignation());
    }


    @Test
    void testEqualsSameObject() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        assertEquals(commissionType1, commissionType1);
    }

    @Test
    void testEqualsDifferentClass() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        assertNotEquals("", commissionType1);
    }

    @Test
    void testEqualsNull() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        assertNotEquals(null, commissionType1);
    }

    @Test
    void testEqualsDifferentObject() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        CommissionType commissionType2 = new CommissionType("Commission Type 2");
        assertNotEquals(commissionType1, commissionType2);
    }

    @Test
    void testEqualsDifferentObjectSameDesignation() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        CommissionType commissionType2 = new CommissionType("Commission Type 1");
        assertEquals(commissionType2, commissionType1);
    }

    @Test
    void testEqualsForDifferentObjectType() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        assertNotEquals(commissionType1, new Object());
    }

    @Test
    void testHashCodeSameObject() {
        CommissionType commissionType2 = new CommissionType("Commission Type 2");
        assertEquals(commissionType2.hashCode(), commissionType2.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        CommissionType commissionType1 = new CommissionType("Commission Type 1");
        CommissionType commissionType2 = new CommissionType("Commission Type 2");
        assertNotEquals(commissionType2.hashCode(), commissionType1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        CommissionType commissionType = new CommissionType("Commission Type");
        CommissionType clone = commissionType.clone();
        assertEquals(commissionType, clone);
    }
}