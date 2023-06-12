package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.ListRequestsController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CommissionTypeRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestListTest {

    @Test
    void ensureGetListWorks() {
        List<Request> requests = new ArrayList<>();
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        String ownerEmail1 = "owner@email.com";
        List<String> uriList1 = new ArrayList<>();
        uriList1.add("https://www.example.com/images/photo456.jpg");
        uriList1.add("https://www.example.com/images/photo789.jpg");
        Property property1 = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList1, "street", "city", "district", "ST", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2500.0), LocalDate.now(), employee);

        requests.add(request);
        requests.add(request1);
        requestList.addRequest(request);
        requestList.addRequest(request1);
        assertEquals(requests, requestList.getList());
    }

    @Test
    void ensureAddRequestWorks() {
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        assertTrue(requestList.addRequest(request));
    }

    @Test
    void ensureGetRequestsByAgentEmailWorks() {
        List<Request> requests = new ArrayList<>();
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        requests.add(request);
        requestList.addRequest(request);

        assertEquals(requests, requestList.getRequestsByAgentEmail(employee.getEmployeeEmail()));
    }

    @Test
    void testClone() {
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        requestList.addRequest(request);

        RequestList clone = requestList.clone();

        assertEquals(clone, requestList);

    }

    @Test
    void testEqualsSameObjects() {
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        requestList.addRequest(request);

        assertEquals(requestList, requestList);
    }

    @Test
    void testEqualsDifferentObjects() {
        RequestList requestList = new RequestList();
        RequestList requestList1 = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        String ownerEmail1 = "owner1@email.com";
        List<String> uriList1 = new ArrayList<>();
        uriList1.add("https://www.example.com/images/photo456.jpg");
        uriList1.add("https://www.example.com/images/photo789.jpg");
        Property property1 = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList1, "street", "city", "district", "ST", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2500.0), LocalDate.now(), employee);

        requestList.addRequest(request);
        requestList1.addRequest(request1);

        assertNotEquals(requestList, requestList1);
    }

    @Test
    void testHashCode() {
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        requestList.addRequest(request);

        assertEquals(requestList.hashCode(), requestList.hashCode());
    }

    @Test
    void ensureGetRequestByIdWorks() {
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee, 0);

        requestList.addRequest(request);

        assertEquals(request, requestList.getRequestById(0).get());
    }

    @Test
    void anyRequestHasId() {
        RequestList requestList = new RequestList();

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee, 0);

        requestList.addRequest(request);

        assertTrue(requestList.anyRequestHasId(0));
    }
}