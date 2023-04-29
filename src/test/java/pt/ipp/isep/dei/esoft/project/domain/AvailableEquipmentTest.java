package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableEquipmentTest {

    @Test
    void ensureGetDescriptionWorks() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");

        assertEquals("Available Equipment", av1.getDescription());
    }


    @Test
    void ensureCloneWorks() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");
        AvailableEquipment clone = av1.clone();

        assertEquals(av1, clone);
    }

    @Test
    void testEqualsSameObject() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");

        assertEquals(av1, av1);
    }

    @Test
    void testEqualsDifferentClass() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");

        assertNotEquals("", av1);
    }

    @Test
    void testEqualsNull() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");

        assertNotEquals(null, av1);
    }

    @Test
    void testEqualsDifferentObject() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");
        AvailableEquipment av2 = new AvailableEquipment("Available Equipment 1");
        assertNotEquals(av1, av2);
    }

    @Test
    void testEqualsDifferentObjectSameDesignation() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment 1");
        AvailableEquipment av2 = new AvailableEquipment("Available Equipment 1");

        assertEquals(av1, av2);
    }

    @Test
    void testEqualsForDifferentObjectType() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment 1");

        assertNotEquals(av1, new Object());
    }

    @Test
    void testHashCodeSameObject() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment 1");

        assertEquals(av1.hashCode(), av1.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        AvailableEquipment av1 = new AvailableEquipment("Available Equipment");
        AvailableEquipment av2 = new AvailableEquipment("Available Equipment 1");

        assertNotEquals(av1.hashCode(), av2.hashCode());
    }

}