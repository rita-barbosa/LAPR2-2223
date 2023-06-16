package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void ensureRequestIsCreatedSuccessfully() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
    }

    @Test
    void testEqualsSameObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertEquals(request, request);
    }

    @Test
    void testEqualsDifferentClass() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertNotEquals(request, new Object());
    }

    @Test
    void testEqualsNull() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertNotEquals(request, null);
    }

    @Test
    void testEqualsDifferentObject() {
        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> uriList1 = new ArrayList<>();
        uriList.add("https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_2x3.jpg");
        Property property1 = new Property(new PropertyType("Apartment"), 12.6, 150.8, uriList1,
                "May Av.", "New York", "York", "AL", "58240");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2043.9), LocalDate.now(), employee1);

        assertNotEquals(request, request1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("Land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertEquals(request, request1);
    }

    @Test
    void testHashCodeSameObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertEquals(request.hashCode(), request.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Property property1 = new Property(new PropertyType("House"), (35.5), (89.3), uriList, "street1",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request request1 = new Request(ownerEmail, property1, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertNotEquals(request.hashCode(), request1.hashCode());
    }


    @Test
    void ensureCloneWorks() {
        Employee employee = new Employee("john.doe@this.company.org", "Agent");
        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        Request clone = request.clone();
        assertEquals(request, clone);
    }


    @Test
    void ensureGetPropertyAttributesWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertEquals(property.toString(), request.getPropertyAttributes());

    }

    @Test
    void ensureGetBusinessAttributesWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Business business = new Business("sale", 2345.0);
        Request request = new Request(ownerEmail, property, business, LocalDate.now(), employee);

        assertEquals(business.toString(), request.getBusinessAttributes());
    }

    @Test
    void testToString() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Business business = new Business("sale", 2345.0);
        Request request = new Request(ownerEmail, property, business, LocalDate.now(), employee);

        String expected = "Type of Business: sale\nPrice: $2345,00\nProperty Type: land\nArea: 35,50 m²\nDistance from city center: 89,30 miles\n" +
                "Location: street, city, district, ST, 12345\nPhotographs:\n    * https://www.example.com/images/photo.jpg\n";

        assertEquals(expected, request.toString());
    }

    @Test
    void ensureGetBusinessWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Business business = new Business("sale", 2345.0);
        Request request = new Request(ownerEmail, property, business, LocalDate.now(), employee);

        assertEquals(business, request.getBusiness());
    }

    @Test
    void ensureGetPropertyWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertEquals(property, request.getProperty());
    }

    @Test
    void getRequestDate() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        assertEquals(LocalDate.now(), request.getRequestDate());
    }

    @Test
    void getId() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        assertEquals(0, request.getId());

    }

    @Test
    void ensureHasAgentWithEmailWorks() {
        String agentEmail = "employee@this.app";
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(agentEmail, "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        assertEquals(true, request.hasAgentWithEmail(agentEmail));
    }

    @Test
    void ensureGetAgentEmailWorks() {
        String agentEmail = "employee@this.app";
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        assertEquals(agentEmail, request.getAgentEmail());
    }

    @Test
    void ensureGetOwnerEmailWorks() {
        String ownerEmail = "owner@email.com";
        Email email = new Email(ownerEmail);
        Employee employee = new Employee("employee@this.app", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        assertEquals(email, request.getOwnerEmail());
    }

    @Test
    void ensureSendNotificationWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        assertEquals(true, request.sendEmail());
    }

    @Test
    void ensureHasIdWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 200,false);

        assertEquals(true, request.hasId(200));
    }

    @Test
    void ensureSendEmailWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        assertEquals(true, request.sendEmail());
    }

    @Test
    void ensureDefineJustificationMessageWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0,false);

        String message = "This is the message";

        assertEquals(message, request.defineJustificationMessage(message));
    }

}