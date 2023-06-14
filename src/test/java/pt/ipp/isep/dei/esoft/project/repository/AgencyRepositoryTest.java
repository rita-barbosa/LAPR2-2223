package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
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
        Location location =new Location("street","city","district","ST","12345");
        Agency agency = new Agency(1234,"Description","agency@email.com","345-567-3456",location);
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345-345-3456",location);

        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@this.app.com");

        assertEquals(agency, returnAgency.get());
    }

    @Test
    void ensureGetAllAnnouncementsListWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (89.3),
                uriList, "street 1", "city 1", "district 1", "ST", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        AnnouncementList expected = new AnnouncementList();
        expected.addAnnouncement(announcement);
        expected.addAnnouncement(announcement1);

        agencyRepository.add(agency);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        assertEquals(agencyRepository.getAllNonDealAnnouncementsList().get().toString(), expected.getList().toString());
    }

    @Test
    void ensureAddAgencyWorks() {
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Location location =new Location("street","city","district","ST","12345");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345-345-3456",location);
        AgencyRepository agencyRepository = new AgencyRepository();
        Agency agency = new Agency(1234,"Description","agency@email.com","345-567-3456",location);
        agency.addEmployee(employee);

        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@this.app.com");

        assertEquals(agency, returnAgency.get());
        assertNotSame(agency, returnAgency.get());
    }

    @Test
    void ensureAddAgencyDuplicateFails() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Location location =new Location("street","city","district","ST","12345");
        Agency agency = new Agency(1234,"Description","agency@email.com","345-567-3456",location);

        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345-345-3456",location);

        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> result = agencyRepository.add(agency);

        assertTrue(result.isEmpty());
    }
}