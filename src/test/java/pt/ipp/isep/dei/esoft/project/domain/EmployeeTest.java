package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ensureTwoEmployeesWithSameEmailEquals() {
        Employee employee1 = new Employee("employee@this.app.com");
        Employee employee2 = new Employee("employee@this.app.com");

        assertEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeWithDifferentEmailNotEquals() {
        Employee employee1 = new Employee("employee@this.app.com");
        Employee employee2 = new Employee("employee1@this.app.com");

        assertNotEquals(employee1, employee2);
    }

    @Test
    void ensureEmployeeDoesNotEqualNull() {
        Employee employee1 = new Employee("employee@this.app.com");

        assertNotEquals(employee1, null);
    }

    @Test
    void ensureEmployeeDoesNotEqualOtherObject() {
        Employee employee1 = new Employee("employee@this.app.com");

        assertNotEquals(employee1, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        Employee employee1 = new Employee("employee@this.app.com");

        assertEquals(employee1, employee1);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String email = "employee@this.app.com";
        Employee employee1 = new Employee(email);
        Employee employee2 = new Employee(email);

        assertEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {
        Employee employee1 = new Employee("employee@this.app.com");
        Employee employee2 = new Employee("employee1@this.app.com");

        assertNotEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    void ensureHasEmailWorksForTheSameEmail() {
        String email = "employee@this.app.com";
        Employee employee = new Employee(email);

        assertTrue(employee.hasEmail(email));

    }

    @Test
    void ensureHasEmailFailsForDifferentEmails() {
        String email = "employee@this.app.com";
        Employee employee = new Employee(email);

        assertFalse(employee.hasEmail("employee1@this.app.com"));

    }

    @Test
    void ensureCloneWorks() {
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Location  location =new Location("street","city","district","state","12345");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345 345 3456",location);
        Employee clone = employee.clone();

        assertEquals(employee, clone);
    }

    @Test
    void ensureIsAgentWorks() {
        Employee employee = new Employee("employee@this.app.com", "agent");

        assertTrue(employee.isAgent());
    }
}