package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void ensureGetNameWorks() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals("Elsa", p1.getName());
    }

    @Test
    void ensureGetPassportCardNumberWorks() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals("C12345678", p1.getPassportCardNumber());
    }

    @Test
    void ensureGetTaxNumberWorks() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals("123-23-2345", p1.getTaxNumber());
    }

    @Test
    void ensureGetLocationWorks() {
        Location l1 = new Location("street", "city", "district", "AK", "12345");
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals(l1, p1.getLocation());
    }

    @Test
    void ensureGetEmailAddressWorks() {
        Email e1 = new Email("elsa@email.com");
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals(e1, p1.getEmailAddress());
    }

    @Test
    void ensureGetPhoneNumberWorks() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals("423-423-2345", p1.getPhoneNumber());
    }

    @Test
    void ensureGetRolesWorks() {
        List<String> roles = new ArrayList<>();
        roles.add("client");
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals(roles, p1.getRoles());
    }

    @Test
    void ensureHasEmailWorksForSameEmailAddress() {
        String e1 = "elsa@email.com";
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertTrue(p1.hasEmail(e1));
    }

    @Test
    void ensureHasEmailFailsForDifferentEmailAddress() {
        String e1 = "olaf@email.com";
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertFalse(p1.hasEmail(e1));
    }

    @Test
    void ensureCloneWorks() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");
        Person clone = p1.clone();

        assertEquals(p1, clone);
    }

    @Test
    void ensureSamePersonEquals() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals(p1, p1);
    }

    @Test
    void ensureDifferentPeopleNotEquals() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");
        Person p2 = new Person("Olaf", "C12345678", "723-53-2675", "olaf@email.com", "473-423-2375", "client", "street", "city", "district", "AK", "12345");

        assertNotEquals(p1, p2);
    }

    @Test
    void ensurePersonDoesNotEqualsNull() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertNotEquals(p1, null);
    }

    @Test
    void ensurePersonDoesNotEqualsOtherObject() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertNotEquals(p1, new Object());
    }

    @Test
    void ensureDifferentPeopleSameAttributesEquals() {
        Person p1 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");
        Person p2 = new Person("Elsa", "C12345678", "123-23-2345", "elsa@email.com", "423-423-2345", "client", "street", "city", "district", "AK", "12345");

        assertEquals(p1, p2);
    }
}