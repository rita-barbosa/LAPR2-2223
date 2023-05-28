package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ensureTwoEmployeesWithSameAttributesEquals() {
        Employee employee1 = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        Employee employee2 = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");


        assertEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeWithDifferentAttributesNotEquals() {
        Employee employee1 = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        Employee employee2 = new Employee(1294, "Elsa", "C19045678", "245-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");


        assertNotEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeDoesNotEqualNull() {
        Employee employee1 = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        assertNotEquals(employee1, null);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        Employee employee1 = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");

        assertNotEquals(employee1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        Employee employee1 = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        assertEquals(employee1, employee1);
    }


    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "elsa1@email.com";
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        assertTrue(employee.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmail() {
        String email = "olaf@email.com";
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");

        assertFalse(employee.hasEmail(email));

    }

    @Test
    void ensureCloneWorks() {
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Location location = new Location("street", "city", "district", "state", "12345");
        Employee employee = new Employee(2425, "Employee", "C12345678", "123-23-1234", new Email(email), roles, "345-345-3456", location);
        Employee clone = employee.clone();

        assertEquals(employee, clone);
    }

    @Test
    void ensureIsAgentWorks() {
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "elsa1@email.com","agent", "423-423-2345","city", "district", "AK", "12345", "street");

        assertTrue(employee.isAgent());
    }
}