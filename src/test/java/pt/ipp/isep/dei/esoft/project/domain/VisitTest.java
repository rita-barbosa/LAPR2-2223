package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class VisitTest {

    @Test
    void getVisitDate() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        LocalDate date = LocalDate.of(2023, 5, 12);
        String visitDate = visit.getVisitDate().toString();
        assertEquals(date.toString(), visitDate);
    }

    @Test
    void getStartHour() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(12, visit.getStartHour());
    }

    @Test
    void getEndHour() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(15, visit.getEndHour());
    }

    @Test
    void ensureSendNotificationTrue() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertTrue(visit.sendNotification("employee@this.app"));
    }

    @Test
    void testEqualsFalse() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit visit1 = new Visit(12, 5, 2023, 12, 15, "Hailey Jackson",
                "555-897-5555");
        assertFalse(visit.equals(visit1));

    }

    @Test
    void ensureCloneWorks() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit clone = visit.clone();
        assertEquals(visit, clone);
    }

    @Test
    void getUserName() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals("Jake Moon", visit.getUserName());
    }

    @Test
    void getUserPhoneNumber() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals("555-775-5555", visit.getUserPhoneNumber());
    }

    @Test
    void ensureVisitIsCreatedSuccessfully() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
    }

    @Test
    void ensureVisitDoesNotEqualNull() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertNotEquals(visit, null);
    }

    @Test
    void ensureVisitDoesNotEqualOtherObject() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertNotEquals(visit, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(visit, visit);
    }

    @Test
    void testEqualsDifferentObject() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit visit1 = new Visit(12, 5, 2023, 12, 15, "Hailey Jackson",
                "555-897-5555");
        assertNotEquals(visit, visit1);
    }

    @Test
    void testHashCodeSameObject() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        assertEquals(visit.hashCode(), visit.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit visit1 = new Visit(12, 5, 2023, 12, 15, "Hailey Jackson",
                "555-897-5555");

        assertNotEquals(visit.hashCode(), visit1.hashCode());
    }

}