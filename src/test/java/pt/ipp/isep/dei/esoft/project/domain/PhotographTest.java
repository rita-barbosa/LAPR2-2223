package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PhotographTest {

    @Test
    void ensureGetUriWorks() {
        Photograph av1 = new Photograph("Available Equipment");

        assertEquals("Available Equipment", av1.getDescription());
    }


    @Test
    void ensureCloneWorks() {
        Photograph av1 = new Photograph("Available Equipment");
        Photograph clone = av1.clone();

        assertEquals(av1, clone);
    }

    @Test
    void testEqualsSameObject() {
        Photograph av1 = new Photograph("Available Equipment");

        assertEquals(av1, av1);
    }

    @Test
    void testEqualsDifferentClass() {
        Photograph av1 = new Photograph("Available Equipment");

        assertNotEquals("", av1);
    }

    @Test
    void testEqualsNull() {
        Photograph av1 = new Photograph("Available Equipment");

        assertNotEquals(null, av1);
    }

    @Test
    void testEqualsDifferentObject() {
        Photograph av1 = new Photograph("Available Equipment");
        Photograph av2 = new Photograph("Available Equipment 1");
        assertNotEquals(av1, av2);
    }

    @Test
    void testEqualsDifferentObjectSameDesignation() {
        Photograph av1 = new Photograph("Available Equipment 1");
        Photograph av2 = new Photograph("Available Equipment 1");

        assertEquals(av1, av2);
    }

    @Test
    void testEqualsForDifferentObjectType() {
        Photograph av1 = new Photograph("Available Equipment 1");

        assertNotEquals(av1, new Object());
    }

    @Test
    void testHashCodeSameObject() {
        Photograph av1 = new Photograph("Available Equipment 1");

        assertEquals(av1.hashCode(), av1.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Photograph av1 = new Photograph("Available Equipment");
        Photograph av2 = new Photograph("Available Equipment 1");

        assertNotEquals(av1.hashCode(), av2.hashCode());
    }

}