package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommissionTest {

    @Test
    void ensureCloneWorks() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);
        Commission clone = c1.clone();

        assertEquals(c1, clone);
    }

    @Test
    void testEqualsSameObject() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);

        assertEquals(c1, c1);
    }

    @Test
    void testEqualsDifferentClass() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);

        assertNotEquals("", c1);
    }

    @Test
    void testEqualsNull() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);

        assertNotEquals(null, c1);
    }

    @Test
    void testEqualsDifferentObject() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);
        Commission c2 = new Commission(new CommissionType("Commission Type 1"), 3.0);

        assertNotEquals(c1, c2);
    }

    @Test
    void testEqualsDifferentObjectSameDesignation() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);
        Commission c2 = new Commission(new CommissionType("Commission Type"), 2.0);

        assertEquals(c1, c2);
    }

    @Test
    void testEqualsForDifferentObjectType() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);

        assertNotEquals(c1, new Object());
    }

    @Test
    void testHashCodeSameObject() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);

        assertEquals(c1.hashCode(), c1.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Commission c1 = new Commission(new CommissionType("Commission Type"), 2.0);
        Commission c2 = new Commission(new CommissionType("Commission Type 1"), 3.0);

        assertNotEquals(c1.hashCode(), c2.hashCode());
    }

}