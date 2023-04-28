package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

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
        Employee employee = new Employee("employee@thisapp.com", "agent");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@thisapp.com");

        assertEquals(agency, returnAgency.get());
    }

    @Test
    void ensureAddAgencyWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234);
        Employee employee = new Employee("employee@thisapp.com", "agent");
        agency.addEmployee(employee);

        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@thisapp.com");

        assertEquals(agency, returnAgency.get());
        assertNotSame(agency, returnAgency.get());
    }

    @Test
    void ensureAddAgencyDuplicateFails() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234);
        Employee employee = new Employee("employee@thisapp.com", "agent");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.add(agency);

        assertTrue(result.isEmpty());

    }
}