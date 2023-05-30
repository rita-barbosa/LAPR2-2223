package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CommissionTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListRequestsControllerIT {

    @Test
    void ensureGetRequestsListWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();

        CommissionType c1 = new CommissionType("Fixed");
        commissionTypeRepository.add(c1);

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

        agency.addRequest(request);
        agencyRepository.add(agency);

        ListRequestsController controller = new ListRequestsController(authenticationRepository, agencyRepository, commissionTypeRepository);

        Optional<List<RequestDto>> listRequestsDto = controller.getRequestsList();

        assertTrue(listRequestsDto.isPresent());
        assertEquals(1, listRequestsDto.get().size());
    }


    @Test
    void ensureGetCommissionTypeListWorks() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        CommissionType commissionType1 = new CommissionType("Commission Type Description One");
        commissionTypeRepository.add(commissionType1);

        CommissionType commissionType2 = new CommissionType("Commission Type Description Two");
        commissionTypeRepository.add(commissionType2);

        ArrayList<CommissionType> expected = new ArrayList<CommissionType>();
        expected.add(commissionType1);
        expected.add(commissionType2);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "employee@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        Agency agency = new Agency(1234);
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("agent", "agent1@this.app","01AGEnt" ,
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.doLogin("agent1@this.app", "01AGEnt");

        ListRequestsController controller = new ListRequestsController(authenticationRepository, agencyRepository, commissionTypeRepository);

        List<CommissionType> commissionTypeList = controller.getCommissionTypeList();

        assertArrayEquals(expected.toArray(), commissionTypeList.toArray());
    }

    @Test
    void ensureGetRequestByIdDtoWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "state", "12345");
        Business business = new Business("sale", 2345.0);
        Request request = new Request(ownerEmail, property, business, LocalDate.now(), employee);

        RequestDto dto = new RequestDto(property.toString(), business.toString(), 0, LocalDate.now().toString());

//        Optional<Request> newRequest = new Optional<Request>(2345.0);
    }

    @Disabled
    @Test
    void ensurePublishAnnouncementWorks() {
    }

    @Disabled
    @Test
    void ensureDefineJustificationMessageWorks() {
    }
}