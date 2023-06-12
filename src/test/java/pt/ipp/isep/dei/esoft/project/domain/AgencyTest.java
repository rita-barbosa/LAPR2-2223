package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgencyTest {

    @Test
    void ensureAnyAgentHasEmailWorks() {
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency1.addEmployee(employee);

        assertTrue(agency1.anyAgentHasEmail("employee@this.app"));
    }

    @Test
    void ensureGetAgentByEmailWorks() {
        Employee employee1 = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        Employee employee2 = new Employee(1234, "Annie Smith", "C04567089",
                "927-65-4091", "employee1@this.app", "agent", "555-555-5555",
                "New York", "Manhattan", "NY", "10001", "Broadway St");
        Employee employee3 = new Employee(1234, "Maria Silva", "C12339878",
                "134-23-2555", "employee2@this.app", "agent", "911-234-5567",
                "Porto", "Porto", "Porto", "43005", "Rua da Alegria, 123");

        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency1.addEmployee(employee1);
        agency1.addEmployee(employee2);
        agency1.addEmployee(employee3);

        assertEquals(agency1.getAgentByEmail("employee@this.app"), employee1);
    }

    //Test is failing due to this.property being null
    @Test
    void ensureCreateRequestWorks() {
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency1.addEmployee(employee);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        Property property = new Property(new PropertyType("land"), 35.5, 89.3, uriList, "street",
                "city", "district", "ST", "12345");

        Request expected = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Optional<Request> request = agency1.createRequest(ownerEmail, new PropertyType("land"), "sale", 2345.0,
                35.5, 0, av, "street", "city", "district", "ST", "12345", null,
                null, 0, null, 0, 0, employee, 89.3, uriList);

        assertNotNull(request);
        assertTrue(request.isPresent());
        assertEquals(request.get(), expected);
    }

    //Test is failing due to this.property being null
    @Test
    void ensureCreateSaleRequestWorks() {
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency.addEmployee(employee);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        Property property = new Property(new PropertyType("land"), 35.5, 89.3, uriList, "street",
                "city", "district", "ST", "12345");
        Request expected = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Optional<Request> request = agency.createSaleRequest(ownerEmail, new PropertyType("land"), "sale", 2345.0,
                35.5, av, "street", "city", "district", "ST", "12345", false, false, 0, null,
                0, 0, employee, 89.3, uriList);

        assertNotNull(request);
        assertTrue(request.isPresent());
        assertEquals(request.get(), expected);
    }

    @Test
    void ensureAddEmployeeWorks() {
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);

        Employee employee1 = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        assertTrue(agency.addEmployee(employee1));
    }

    @Test
    void ensureAddDuplicatedEmployeeFails() {
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        Employee employee1 = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        assertTrue(agency.addEmployee(employee1));
        assertFalse(agency.addEmployee(employee1));
    }

    @Test
    void ensureGetIdWorks() {
        Integer id = 1234;
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(id, "Description", "agency@email.com", "345 567 3456", location);

        assertEquals(agency.getId(), id);
    }

    @Test
    void ensureGetAgentListWorks() {
        List<Employee> expected = new ArrayList<>();
        Employee employee1 = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        Employee employee2 = new Employee(1234, "Annie Smith", "C04567089",
                "927-65-4091", "employee1@this.app", "agent", "555-555-5555",
                "New York", "Manhattan", "NY", "10001", "Broadway St");
        Employee employee3 = new Employee(1234, "Maria Silva", "C12339878",
                "134-23-2555", "employee2@this.app", "agent", "911-234-5567",
                "Porto", "Porto", "Porto", "43005", "Rua da Alegria, 123");
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency.addEmployee(employee1);
        agency.addEmployee(employee2);
        agency.addEmployee(employee3);

        assertEquals(agency.getAgentList(), expected);
    }

    @Test
    void ensureGetDescriptionWorks() {
        String expected = "Description";
        Agency agency = new Agency(expected);

        assertEquals(agency.getDescription(), expected);
    }

    @Test
    void ensurePublishAnnouncementWorks() {
        Location location = new Location("street", "city", "district", "ST", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement expected = new Announcement(employee, commissionType, 234.0, request);

        Optional<Announcement> announcement = agency.publishAnnouncement(employee, commissionType, 234.0, request);

        assertNotNull(announcement);
        assertTrue(announcement.isPresent());
        assertEquals(announcement.get().toString(), expected.toString());
    }
    @Test
    void ensureGetAnnouncementsWorks() {
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

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        assertEquals(agency.getAnnouncements().getList().toString(), expected.getList().toString());
        assertEquals(agency.getAnnouncements().getList().size(), expected.getList().size());
    }

    @Test
    void ensureGetAnnouncementsListWorks() {
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

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        assertEquals(agency.getAnnouncementsList().toString(), expected.toString());
        assertEquals(agency.getAnnouncementsList().size(), expected.size());
    }

    @Test
    void testClone() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);

        Agency clone = agency.clone();

        assertEquals(agency, clone);

    }

    @Test
    void testEqualsSameObject() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);

        assertEquals(agency, agency);
    }


    @Test
    void testEqualsDifferentClass() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);

        assertNotEquals("", agency);
    }

    @Test
    void testHashCodeSameObject() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);

        assertEquals(agency.hashCode(), agency.hashCode());
    }

    @Test
    void testHashCodeDifferentObjectsSameAttributes() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");


        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);
        Agency agency1 = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);

        assertEquals(agency.hashCode(), agency1.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);
        Agency agency1 = new Agency(4321, "agency 1", "agency1@this.app", "999 444 1234", location);
        assertNotEquals(agency1.hashCode(), agency.hashCode());
    }

    @Test
    void testEqualsNull() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);
        assertNotEquals(null, agency);
    }

    @Test
    void testEqualsDifferentObject() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);
        Agency agency1 = new Agency(4321, "agency 1", "agency1@this.app", "999 444 1234", location);
        assertNotEquals(agency1, agency);
    }

    @Test
    void testEqualsDifferentObjectSameAttributes() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);
        Agency agency1 = new Agency(1234, "agency 1", "agency@this.app", "999 444 5656", location);
        assertEquals(agency1, agency);
    }


}