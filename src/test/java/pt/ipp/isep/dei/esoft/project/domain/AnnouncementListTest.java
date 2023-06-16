package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementListTest {

    @Test
    void ensureGetListWorks() {
        AnnouncementList announcementList = new AnnouncementList();
        List<Announcement> expected = new ArrayList<>();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);


        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        announcementList.addAnnouncement(announcement);
        expected.add(announcement);

        assertEquals(expected, announcementList.getList());
    }

    @Test
    void ensureAddAnnouncementWorks() {
        AnnouncementList announcementList = new AnnouncementList();

        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);


        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        assertTrue(announcementList.addAnnouncement(announcement));
    }

    @Test
    void getAnnouncementsByAgentEmail() {
        AnnouncementList announcementList = new AnnouncementList();
        List<Announcement> expected = new ArrayList<>();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        announcementList.addAnnouncement(announcement);
        expected.add(announcement);

        assertEquals(expected, announcementList.getAnnouncementsByAgentEmail(employee.getEmployeeEmail()));
    }

    @Test
    void ensureAnyAnnouncementHasIdWorks() {
        AnnouncementList announcementList = new AnnouncementList();
        List<Announcement> expected = new ArrayList<>();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        announcementList.addAnnouncement(announcement);
        assertTrue(announcementList.anyAnnouncementHasId(0));
    }

    @Test
    void ensureGetAnnouncementByIdWorks() {
        AnnouncementList announcementList = new AnnouncementList();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        announcementList.addAnnouncement(announcement);
        assertEquals(announcement, announcementList.getAnnouncementById(0).get());
    }

    @Test
    void sortAnnouncementsByMostRecentAcceptanceDate() {
        AnnouncementList announcementList = new AnnouncementList();
        List<Announcement> expected = new ArrayList<>();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("lease", 345645.0), LocalDate.now(), employee);
        Email client1 = new Email("client@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.of(2022, 1, 1), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        Announcement announcement1 = new Announcement(employee, commission, request1, LocalDate.of(2022, 3, 1), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);

        announcementList.addAnnouncement(announcement1);
        announcementList.addAnnouncement(announcement);
        expected.add(announcement);
        expected.add(announcement1);

        assertEquals(announcementList.sortAnnouncementsByMostRecentAcceptanceDate(), announcementList.sortAnnouncementsByMostRecentAcceptanceDate());
    }

    @Test
    void testClone() {
        AnnouncementList announcementList = new AnnouncementList();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        announcementList.addAnnouncement(announcement);

        AnnouncementList clone = announcementList.clone();

        assertEquals(clone, announcementList);
    }

    @Test
    void testEqualsSameObject() {
        AnnouncementList announcementList = new AnnouncementList();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        announcementList.addAnnouncement(announcement);

        assertEquals(announcementList, announcementList);

    }

    @Test
    void testEqualsDifferentObject() {
        AnnouncementList announcementList = new AnnouncementList();
        AnnouncementList announcementList1 = new AnnouncementList();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("lease", 345645.0), LocalDate.now(), employee);
        Email client1 = new Email("client@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.of(2022, 1, 1), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        Announcement announcement1 = new Announcement(employee, commission, request1, LocalDate.of(2022, 3, 1), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);

        announcementList1.addAnnouncement(announcement1);
        announcementList.addAnnouncement(announcement);

        assertNotEquals(announcementList, announcementList1);
    }

    @Test
    void testHashCodeSameObjects() {
        AnnouncementList announcementList = new AnnouncementList();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        announcementList.addAnnouncement(announcement);

        assertEquals(announcementList.hashCode(), announcementList.hashCode());
    }

    @Test
    void testHashCodeDifferentObjects() {
        AnnouncementList announcementList = new AnnouncementList();
        AnnouncementList announcementList1 = new AnnouncementList();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("lease", 345645.0), LocalDate.now(), employee);
        Email client1 = new Email("client@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.of(2022, 1, 1), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);
        Announcement announcement1 = new Announcement(employee, commission, request1, LocalDate.of(2022, 3, 1), orderList, 0, 15200.3, LocalDate.of(2023, 1, 14),null);

        announcementList1.addAnnouncement(announcement1);
        announcementList.addAnnouncement(announcement);

        assertNotEquals(announcementList.hashCode(), announcementList1.hashCode());
    }

    @Test
    void setAnnouncements() {
        AnnouncementList announcementList = new AnnouncementList();
        List<Announcement> expected = new ArrayList<>();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        expected.add(announcement);

        announcementList.setAnnouncements(expected);
        assertEquals(expected, announcementList.getList());

    }


    @Test
    void ensureAnnouncementHasBusinessTypeWorks() {
        Location location = new Location("street", "city", "district", "AK", "12345");
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
                uriList, "street", "city", "district", "AK", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (89.3),
                uriList, "street 1", "city 1", "district 1", "AK", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 2345.0), LocalDate.now(), employee);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> actual = agency.getAnnouncements().announcementHasBusinessType(agency.getAnnouncementsList(), "lease");

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureAnnouncementHasPropertyTypeWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property1 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "ST", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 2345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> actual = agency.getAnnouncements().announcementHasPropertyType(agency.getAnnouncementsList(), "house");

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }


    @Test
    void ensureAnnouncementHasNumberBedroomsWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
                "street 3", "city 3", "district 3", "ST", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "ST", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property4, new Business("lease", 2345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> expected = new ArrayList<>();
        expected.add(new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> actual = agency.getAnnouncements().announcementHasNumberBedrooms(agency.getAnnouncementsList(), 1);

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureSortAnnouncementsByAscendingPriceWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
                "street 3", "city 3", "district 3", "ST", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "ST", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(0, new Announcement(employee, commissionType, 234.0, request));
        expected.add(1, new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> actual = agency.getAnnouncements().sortAnnouncementsByAscendingPrice(agency.getAnnouncementsList());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureSortAnnouncementsByDescendingPriceWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
                "street 3", "city 3", "district 3", "ST", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "ST", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(0, new Announcement(employee, commissionType, 234.0, request1));
        expected.add(1, new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> actual = agency.getAnnouncements().sortAnnouncementsByDescendingPrice(agency.getAnnouncementsList());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureSortAnnouncementsByAscendingCityWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
                "street 3", "Amsterdam", "district 3", "ST", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "Freamunde", "district 4", "ST", "12348", 2, 1, 3, 15.2, uriList);
        Request request = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(0, new Announcement(employee, commissionType, 234.0, request1));
        expected.add(1, new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> actual = agency.getAnnouncements().sortAnnouncementsByAscendingCity(agency.getAnnouncementsList());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureSortAnnouncementsByDescendingCityWorks() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
                "street 3", "Amsterdam", "district 3", "ST", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property3, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "Freamunde", "district 4", "ST", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property4, new Business("lease", 1345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> expected = new ArrayList<>();
        expected.add(0, new Announcement(employee, commissionType, 234.0, request1));
        expected.add(1, new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> actual = agency.getAnnouncements().sortAnnouncementsByDescendingCity(agency.getAnnouncementsList());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureSortAnnouncementsByAscendingStateWork() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
        expected.add(0, new Announcement(employee, commissionType, 234.0, request1));
        expected.add(1, new Announcement(employee, commissionType, 234.0, request));

        List<Announcement> actual = agency.getAnnouncements().sortAnnouncementsByAscendingState(agency.getAnnouncementsList());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void ensureSortAnnouncementsByDescendingStateWork() {
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
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
        expected.add(0, new Announcement(employee, commissionType, 234.0, request));
        expected.add(1, new Announcement(employee, commissionType, 234.0, request1));

        List<Announcement> actual = agency.getAnnouncements().sortAnnouncementsByDescendingState(agency.getAnnouncementsList());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(), actual.toString());
    }
}