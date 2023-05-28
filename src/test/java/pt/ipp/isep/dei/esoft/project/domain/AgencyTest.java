package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgencyTest {

    @Test
    void ensureAnyAgentHasEmailWorks() {
        Employee employee = new Employee("employee@thisapp.com", "agent");

        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency1.addEmployee(employee);

        assertTrue(agency1.anyAgentHasEmail("employee@thisapp.com"));
    }

    @Test
    void ensureGetAgentByEmailWorks() {
        Employee employee1 = new Employee("employee@thisapp.com", "agent");
        Employee employee2 = new Employee("employee1@thisapp.com", "agent");
        Employee employee3 = new Employee("employee2@thisapp.com", "agent");

        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency1.addEmployee(employee1);
        agency1.addEmployee(employee2);
        agency1.addEmployee(employee3);

        assertEquals(agency1.getAgentByEmail("employee@thisapp.com"), employee1);
    }

    //Test is failing due to this.property being null
    @Test
    void ensureCreateRequestWorks() {
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency1.addEmployee(employee);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        Property property = new Property(new PropertyType("land"), 35.5, 89.3, uriList, "street",
                "city", "district", "state", "12345");

        Request expected = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Optional<Request> request = agency1.createRequest(ownerEmail, new PropertyType("land"), "sale", 2345.0,
                35.5, 0, av, "street", "city", "district", "state", "12345", null,
                null, 0, null, 0, 0, employee, 89.3, uriList);

        assertNotNull(request);
        assertTrue(request.isPresent());
        assertEquals(request.get(), expected);
    }

    //Test is failing due to this.property being null
    @Test
    void ensureCreateSaleRequestWorks() {
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        agency.addEmployee(employee);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        Property property = new Property(new PropertyType("land"), 35.5, 89.3, uriList, "street",
                "city", "district", "state", "12345");
        Request expected = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Optional<Request> request = agency.createSaleRequest(ownerEmail, new PropertyType("land"), "sale", 2345.0,
                35.5, av, "street", "city", "district", "state", "12345", false, false, 0, null,
                0, 0, employee, 89.3, uriList);

        assertNotNull(request);
        assertTrue(request.isPresent());
        assertEquals(request.get(), expected);
    }

    @Test
    void ensureAddEmployeeWorks() {
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);

        Employee employee1 = new Employee("employee@thisapp.com", "agent");
        assertTrue(agency.addEmployee(employee1));
    }

    @Test
    void ensureAddDuplicatedEmployeeFails() {
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        Employee employee1 = new Employee("employee@thisapp.com", "agent");

        assertTrue(agency.addEmployee(employee1));
        assertFalse(agency.addEmployee(employee1));
    }

    @Test
    void ensureGetIdWorks() {
        Integer id = 1234;
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency = new Agency(id, "Description", "agency@email.com", "345 567 3456", location);

        assertEquals(agency.getId(), id);
    }

    @Test
    void ensureGetAgentListWorks() {
        List<Employee> expected = new ArrayList<>();
        Employee employee1 = new Employee("employee@thisapp.com", "agent");
        Employee employee2 = new Employee("employee1@thisapp.com", "agent");
        Employee employee3 = new Employee("employee2@thisapp.com", "agent");
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        Location location = new Location("street", "city", "district", "state", "12345");
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
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement expected = new Announcement(employee, commissionType, 234.0, request);

        Optional<Announcement> announcement = agency.publishAnnouncement(employee, commissionType, 234.0, request);

        assertNotNull(announcement);
        assertTrue(announcement.isPresent());
        assertEquals(announcement.get(), expected);
    }

    @Test
    void ensureGetAnnouncementsListWorks() {
        Location location = new Location("street", "city", "district", "state", "12345");
        Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (89.3),
                uriList, "street 1", "city 1", "district 1", "st1", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        assertEquals(agency.getAnnouncementsList(), expected);

    }

    @Test
    void testClone() {

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

    @Test
    void ensureAnnouncementHasBusinessTypeWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (100.0), (70.3),
                uriList, "street 1", "city 1", "district 1", "st1", "12346");
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 300.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        assertEquals(expected, agency.announcementHasBusinessType(agency.announcements.getList(), "Lease"));
    }

    @Test
    void ensureAnnouncementHasPropertyTypeWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        assertEquals(expected, agency.announcementHasPropertyType(agency.announcements.getList(), "House"));

    }

    @Test
    void ensureAnnouncementHasNumberBedroomsWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 2345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request));

        assertEquals(expected, agency.announcementHasNumberBedrooms(agency.announcements.getList(), 1));
    }

    @Test
    void ensureSortAnnouncementsByAscendingPriceWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request));
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        assertEquals(expected, agency.sortAnnouncementsByAscendingPrice(agency.announcements.getList()));
    }

    @Test
    void ensureSortAnnouncementsByDescendingPriceWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));
        expected.add(new Announcement(employee, commissionType, 234.0, request));

        assertEquals(expected, agency.sortAnnouncementsByDescendingPrice(agency.announcements.getList()));
    }

    @Test
    void ensureSortAnnouncementsByAscendingCityWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "Amsterdam", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "Freamunde", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));
        expected.add(new Announcement(employee, commissionType, 234.0, request));

        assertEquals(expected, agency.sortAnnouncementsByAscendingCity(agency.announcements.getList()));

    }

    @Test
    void ensureSortAnnouncementsByDescendingCityWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "Amsterdam", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "Freamunde", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request));
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        assertEquals(expected, agency.sortAnnouncementsByDescendingCity(agency.announcements.getList()));
    }

    @Test
    void ensureSortAnnouncementsByAscendingStateWork() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "Amsterdam", "district 3", "AK", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "Freamunde", "district 4", "NY", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));
        expected.add(new Announcement(employee, commissionType, 234.0, request));

        assertEquals(expected, agency.sortAnnouncementsByAscendingState(agency.announcements.getList()));
    }

    @Test
    void ensureSortAnnouncementsByDescendingStateWork() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "Amsterdam", "district 3", "AK", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "Freamunde", "district 4", "NY", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));


        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request));
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        assertEquals(expected, agency.sortAnnouncementsByDescendingState(agency.announcements.getList()));
    }
}