package pt.ipp.isep.dei.esoft.project.domain;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementTest {
    @Test
    void ensureAnnouncementIsCreatedSuccessfully() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");


        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
    }

    @Test
    void testEqualsSameObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);

        assertEquals(announcement, announcement);
    }

    @Test
    void testEqualsDifferentClass() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");

        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);

        assertNotEquals(announcement, new Object());
    }

    @Test
    void testEqualsNull() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        assertNotEquals(announcement, null);
    }

    @Test
    void testEqualsDifferentObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");

        Property property1 = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street1", "city1", "district1", "stat1", "12351");

        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("sale1", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);
        assertNotEquals(announcement1, announcement);

    }


    @Test
    void testHashCodeSameObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);

        assertEquals(announcement.hashCode(), announcement.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Property property1 = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street1", "city1", "district1", "stae1", "12341");

        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property1, new Business("sale1", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);
        assertNotEquals(announcement1.hashCode(), announcement.hashCode());

    }

    @Test
    void ensureCloneWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement clone = announcement.clone();

        assertEquals(announcement, clone);
    }

    @Test
    void ensureGetAcceptanceDateWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        LocalDate acceptanceDate = LocalDate.now();

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        assertEquals(acceptanceDate, announcement.getAcceptanceDate());
    }


    @Test
    void ensureGetCommissionAttributesWorks() {
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
        assertEquals(commission.toString(), announcement.getCommissionAttributes());
    }

    @Test
    void ensureGetRequestAttributesWorks() {
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
        assertEquals(request.toString(), announcement.getRequestAttributes());
    }

    @Test
    void ensureGetIdWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        LocalDate acceptanceDate = LocalDate.now();

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        assertEquals(0, announcement.getId());
    }

    @Test
    void ensureGetListOfOrdersWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        List<Order> orders = new ArrayList<>();
        Order o1 = new Order(23000.0, client1);
        orders.add(o1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        announcement.addOrder(o1);

        assertEquals(orders, announcement.getListOfOrders());
    }

    @Test
    void getOrderList() {
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

        Announcement announcement = new Announcement(employee, commission, request);
        announcement.addOrder(o1);

        assertEquals(orderList, announcement.getOrderList().get());
    }

    @Test
    void addOrder() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);

        assertTrue(announcement.addOrder(o1));
    }

    @Test
    void ensureHasAgentWithEmailWorks() {
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

        assertTrue(announcement.hasAgentWithEmail(employee.getEmployeeEmail()));
    }

    @Test
    void ensureHasIdWorks() {
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

        assertTrue(announcement.hasId(announcement.getId()));
    }

    @Test
    void ensureDefineOrderAcceptanceWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);
        int orderId = o1.getId();
        String acceptanceOrder = "accept";

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);
        announcement.addOrder(o1);

        assertTrue(announcement.defineOrderAcceptance(acceptanceOrder, orderId));

    }

    @Test
    void ensureCreateVisitWorks() {
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

        Visit expected = new Visit(12, 5, 2023, 12, 13, "Jake Moon",
                "555-775-5555");

        Optional<Visit> visit = announcement.createVisit(12, 5, 2023, 12, 13, "Jake Moon",
                "555-775-5555");

        assertNotNull(visit);
        assertTrue(visit.isPresent());
        assertTrue(visit.get().equals(expected));
    }

    @Test
    void ensureAddVisitWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        Visit visit = new Visit(12, 5, 2023, 12, 13, "Jake Moon",
                "555-775-5555");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request);

        assertTrue(announcement.addVisit(visit));
    }
}