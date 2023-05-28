package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AcceptOrdersControllerIT {

    @Test
    void ensureGetAnnouncementsListWorks() {
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
        CommissionType commissionType = new CommissionType("fixed");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);
        Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
        a1.addOrder(o1);

        agency.addAnnouncement(a1);
        agencyRepository.add(agency);

        AcceptOrdersController controller = new AcceptOrdersController(agencyRepository, authenticationRepository);

        Optional<List<AnnouncementDto>> listAnnouncementsDto = controller.getAnnouncementsList();

        assertTrue(listAnnouncementsDto.isPresent());
        assertEquals(1, listAnnouncementsDto.get().size());

    }

    @Test
    void ensureDefineOrderAcceptanceWorks() {
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
        CommissionType commissionType = new CommissionType("fixed");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);
        Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
        a1.addOrder(o1);

        agency.addAnnouncement(a1);
        agencyRepository.add(agency);

        AcceptOrdersController controller = new AcceptOrdersController(agencyRepository, authenticationRepository);

        int announceId = a1.getId();
        int orderId = o1.getId();
        String acceptanceAnswer = "accept";

        Boolean success = controller.defineOrderAcceptance(acceptanceAnswer, announceId, orderId);
        assertTrue(success);
    }
}