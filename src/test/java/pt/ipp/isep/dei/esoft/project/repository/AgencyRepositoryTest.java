package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Location;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgencyRepositoryTest {

    @Test
    void testAddAgency() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234);

        Optional<Agency> returnAgency = agencyRepository.add(agency);

        assertEquals(agency, returnAgency.get());
    }

    @Test
    void ensureGetAgencyByEmailWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234);
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Location location =new Location("street","city","district","state","12345");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345 345 3456",location);

        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@this.app.com");

        assertEquals(agency, returnAgency.get());
    }

    @Test
    void ensureAddAgencyWorks() {

        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Location location =new Location("street","city","district","state","12345");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345 345 3456",location);
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234);
        agency.addEmployee(employee);

        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@this.app.com");

        assertEquals(agency, returnAgency.get());
        assertNotSame(agency, returnAgency.get());
    }

    @Test
    void ensureAddAgencyDuplicateFails() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234);
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Location location =new Location("street","city","district","state","12345");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345 345 3456",location);
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.add(agency);

        assertTrue(result.isEmpty());

    }
}