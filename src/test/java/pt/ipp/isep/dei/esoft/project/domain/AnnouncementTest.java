package pt.ipp.isep.dei.esoft.project.domain;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementTest {
    @Test
    void ensureAnnouncementIsCreatedSuccessfully() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
    void testEqualsSameObjectSameDescription() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), 35.5, 89.3,
                uriList, "street", "city", "district", "AK", "12334");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request);

        assertEquals(announcement1, announcement);
    }

    @Test
    void testHashCodeSameObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
        Employee employee = new Employee("employee@this.app.com", "Agent");
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
    void getAcceptanceDate() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        LocalDate acceptanceDate = LocalDate.now();

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commission, request, acceptanceDate);
        assertEquals(acceptanceDate, announcement.getAcceptanceDate());
    }


}