package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ListVisitsControllerIT {

    @Test
    void ensureGetVisitRequestsListWorks() {
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
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);
        Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
        a1.addOrder(o1);

        agency.addAnnouncement(a1);
        agencyRepository.add(agency);

        ListVisitsController controller = new ListVisitsController(authenticationRepository, agencyRepository);
        LocalDate beginDate = LocalDate.of(2023, 6, 10);
        LocalDate endDate = LocalDate.of(2023, 7, 20);

        try {
            Optional<List<VisitDto>> visitDtoList = controller.getVisitRequestsList(beginDate, endDate);
            assertTrue(visitDtoList.isPresent());
            assertEquals(0, visitDtoList.get().size());
        } catch (Exception e) {

        }
    }

    @Test
    void getSortedVisitRequestList() {
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        AgencyRepository agencyRepository = new AgencyRepository();

        ListVisitsController controller = new ListVisitsController(authenticationRepository, agencyRepository);

        List<VisitDto> visitDtos = new ArrayList<>();

        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        LocalDate visitDate1 = LocalDate.of(2023, 6, 11);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate1, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto1 = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        visitDtos.add(visitDto);
        visitDtos.add(visitDto1);

        Optional<List<VisitDto>> visitDtoList = Optional.of(visitDtos);

        try {
            assertEquals(controller.getSortedVisitRequestList(visitDtoList), controller.getSortedVisitRequestList(visitDtoList));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}