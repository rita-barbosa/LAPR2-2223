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
                uriList, "street", "city", "district", "state", "12345");
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
                uriList, "street", "city", "district", "state", "12345");
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
                uriList, "street", "city", "district", "state", "12345");
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("lease", 345645.0), LocalDate.now(), employee);
        Email client1 = new Email("client@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
        Announcement announcement1 = new Announcement(employee, commission, request1, LocalDate.of(2023, 3, 1), orderList, 0);

        announcementList.addAnnouncement(announcement1);
        announcementList.addAnnouncement(announcement);
        expected.add(announcement);
        expected.add(announcement1);

        assertEquals(expected, announcementList.sortAnnouncementsByMostRecentAcceptanceDate());
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
        announcementList.addAnnouncement(announcement);

        AnnouncementList clone = announcementList.clone();

        assertEquals(clone,announcementList);
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
        announcementList.addAnnouncement(announcement);

        assertEquals(announcementList,announcementList);

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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("lease", 345645.0), LocalDate.now(), employee);
        Email client1 = new Email("client@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
        Announcement announcement1 = new Announcement(employee, commission, request1, LocalDate.of(2023, 3, 1), orderList, 0);

        announcementList1.addAnnouncement(announcement1);
        announcementList.addAnnouncement(announcement);

        assertNotEquals(announcementList,announcementList1);
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
        announcementList.addAnnouncement(announcement);

        assertEquals(announcementList.hashCode(),announcementList.hashCode());
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("lease", 345645.0), LocalDate.now(), employee);
        Email client1 = new Email("client@this.app");
        OrderList orderList = new OrderList();
        Order o1 = new Order(23000.0, client1);
        orderList.addOrder(o1);

        Announcement announcement = new Announcement(employee, commission, request, LocalDate.now(), orderList, 0);
        Announcement announcement1 = new Announcement(employee, commission, request1, LocalDate.of(2023, 3, 1), orderList, 0);

        announcementList1.addAnnouncement(announcement1);
        announcementList.addAnnouncement(announcement);

        assertNotEquals(announcementList.hashCode(),announcementList1.hashCode());
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
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        expected.add(announcement);

        announcementList.setAnnouncements(expected);
        assertEquals(expected,announcementList.getList());

    }
}