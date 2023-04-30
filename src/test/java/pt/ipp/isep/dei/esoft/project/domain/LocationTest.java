package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void ensureToStringWorks() {
        Location location = new Location("street", "city", "district", "state", "12345");

        String expected = "street, city, district, state, 12345";

        assertEquals(expected, location.toString());
    }

    @Test
    void ensureTwoLocationsWithSameAttributesEquals() {
        Location location = new Location("street", "city", "district", "state", "12345");
        Location location1 = new Location("street", "city", "district", "state", "12345");

        assertEquals(location, location1);
    }

    @Test
    void ensureLocationWithDifferentAttributesNotEquals() {
        Location location = new Location("street", "city", "district", "state", "12345");
        Location location1 = new Location("street1", "city1", "district1", "state1", "12346");

        assertNotEquals(location, location1);
    }

    @Test
    void ensureLocationDoesNotEqualNull() {
        Location location = new Location("street", "city", "district", "state", "12345");

        assertNotEquals(location, null);
    }

    @Test
    void ensureLocationDoesNotEqualOtherObject() {
        Location location = new Location("street", "city", "district", "state", "12345");

        assertNotEquals(location, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        Location location = new Location("street", "city", "district", "state", "12345");

        assertEquals(location, location);
    }

    @Test
    void testHashCodeSameObject() {
        Location location = new Location("street", "city", "district", "state", "12345");

        assertEquals(location.hashCode(), location.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Location location = new Location("street", "city", "district", "state", "12345");
        Location location1 = new Location("123 Main St", "Anytown", "Anydistrict", "Any", "12348");

        assertNotEquals(location.hashCode(), location1.hashCode());
    }
    @Test
    void ensureGetCityWorks() {
        Location location = new Location("123 Main St", "Anytown", "Anydistrict", "Any", "12348");

        assertEquals("Anytown", location.getCity());
    }


    @Test
    void ensureGetStateWorks() {
        Location location = new Location("123 Main St", "Anytown", "Anydistrict", "Any", "12348");

        assertEquals("Any", location.getState());
    }
}