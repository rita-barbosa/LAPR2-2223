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
    void getList() {
    }

    @Test
    void ensureAddRequestWorks() {
        List<Request> requests = new ArrayList<>();
        RequestList requestList = new RequestList();
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();


        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "employee@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
        authenticationRepository.doLogin("employee@this.app", "01AGEnt");

        agency.addEmployee(employee);

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        String ownerEmail1 = "owner@email.com";
        List<String> uriList1 = new ArrayList<>();
        uriList1.add("https://www.example.com/images/photo456.jpg");
        uriList1.add("https://www.example.com/images/photo789.jpg");
        Property property1 = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList1, "street", "city", "district", "state", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2500.0), LocalDate.now(), employee);

        agency.addRequest(request);
        agency.addRequest(request1);
        agencyRepository.add(agency);

        requests.add(request);
        requests.add(request1);
        requestList.addRequest(request);
        requestList.addRequest(request1);
        assertEquals(requests, requestList.getList());
    }

    @Test
    void getRequestsByAgentEmail() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testEqualsSameObjects() {
        String commissionType = "Something";
        CommissionType c1 = new CommissionType(commissionType);
        assertEquals(c1, c1);
    }

    @Test
    void testEqualsDifferentObjects() {
        String commissionType = "Something";
        CommissionType c1 = new CommissionType(commissionType);
        assertNotEquals(c1, new Object());

    }

    @Test
    void testHashCode() {
        String commissionType1 = "Something";
        String commissionType2 = "Nothing";
        CommissionType c1 = new CommissionType(commissionType1);
        CommissionType c2 = new CommissionType(commissionType2);

        assertNotEquals(c1.hashCode(), c2.hashCode());
    }

    @Disabled
    @Test
    void getRequestById() {
    }

    @Disabled
    @Test
    void anyRequestHasId() {
    }
}