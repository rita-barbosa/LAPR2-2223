package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleVisitControllerIT {

    @Test
    void scheduleVisit() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Jake Moon", "client1@this.app", "01CLIen",
                AuthenticationController.ROLE_CLIENT);

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        List<String> roles = new ArrayList<>();
        roles.add("client");
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
        authenticationRepository.doLogin("client1@this.app", "01CLIen");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);

        assertTrue(controller.scheduleVisit(announcement, 12, 13, 25, 5, 2023));
    }

    @Test
    void getAllAnnouncementsList() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);


        Optional<List<Announcement>> list = controller.getAllAnnouncementsList();

        List<Announcement> expected = new ArrayList<>();

        if (list.isPresent() && list.get().size() > 0) {
            expected = list.get();
        }

        List<Announcement> actual = new ArrayList<>();

        for (Agency agency : agencyRepository.getAgenciesList()) {
            actual.addAll(agency.getAnnouncementsList());

        }

        assertEquals(expected, actual);
    }

    @Test
    void toDto() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

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

        List<AnnouncementDto> actual;

        actual = controller.toDto(agency.getAnnouncements().getList()).get();

        String requestAttributes = a1.getRequestAttributes();
        String commissionAttributes = a1.getCommissionAttributes();
        int id = a1.getId();
        String acceptanceDate = a1.getAcceptanceDate().toString();
        List<Order> listOrders = a1.getListOfOrders();
        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        AnnouncementDto expected = new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);

        assertEquals(expected, actual.get(0));
    }

    @Test
    void toModel() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

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

        List<AnnouncementDto> actualList;

        actualList = controller.toDto(agency.getAnnouncements().getList()).get();

        String requestAttributes = a1.getRequestAttributes();
        String commissionAttributes = a1.getCommissionAttributes();
        int id = a1.getId();
        String acceptanceDate = a1.getAcceptanceDate().toString();
        List<Order> listOrders = a1.getListOfOrders();
        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        AnnouncementDto a2 = new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);


        Announcement expected = controller.toModel(a2).get();

        Announcement actual = controller.toModel(actualList.get(0)).get();

        assertEquals(expected, actual);
    }

    @Test
    void ensureGetPropertyTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        List<PropertyType> expected = propertyTypeRepository.getPropertyTypeList();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<PropertyType> propertyTypeList = controller.getPropertyTypeList();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());
    }

    @Test
    void ensureGetBusinessTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        List<BusinessType> expected = businessTypeRepository.getBusinessTypeList();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<BusinessType> businessTypeList = controller.getBusinessTypeList();

        assertArrayEquals(expected.toArray(), businessTypeList.toArray());
    }


    @Test
    void ensureGetCriteriaListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();


        List<String> expected = criteriaRepository.getCriteriaList();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<String> criteria = controller.getCriteriaList();

        assertArrayEquals(expected.toArray(), criteria.toArray());
    }


    @Test
    void ensureGetAgenciesListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        List<Agency> expected = agencyRepository.getAgenciesList();

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        List<Agency> agencies = controller.getAgenciesList();

        assertArrayEquals(expected.toArray(), agencies.toArray());
    }

    @Test
    void getAnnouncementsByBusinessType() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByBusinessType("Sale");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            List<Announcement> announcements = agency.getAnnouncements().getList();
            for (Announcement announce : announcements) {
                if (announce.getRequest().getBusiness().getBusinessType().getDesignation().equalsIgnoreCase("Sale")) {
                    actual.add(announce);
                }
            }
        }

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByPropertyType() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByPropertyType("Land");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            List<Announcement> announcements = agency.getAnnouncements().getList();
            for (Announcement announce : announcements) {
                if (announce.getRequest().getProperty().getPropertyType().getDesignation().equalsIgnoreCase("Land")) {
                    actual.add(announce);
                }
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByNumberBedrooms() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByNumberBedrooms(2);

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            List<Announcement> announcements = agency.getAnnouncements().getList();


            for (Announcement announce : announcements) {
                if (announce.getRequest().getProperty() instanceof Residence) {
                    Residence residence = (Residence) announce.getRequest().getProperty();
                    if ((residence.getNumberBedroom().equals(2))) {
                        actual.add(announce);
                    }
                }
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByAscendingPrice() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByPrice("Ascending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByAscendingPrice(agency.getAnnouncementsList()));
        }

        Agency agency = new Agency();

        actual = agency.sortAnnouncementsByAscendingPrice(actual);

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByDescendingPrice() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByPrice("Descending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByAscendingPrice(agency.getAnnouncementsList()));
        }

        Agency agency = new Agency();

        actual = agency.sortAnnouncementsByDescendingPrice(actual);

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByAscendingAlphabeticalOrderCity() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByCity("Ascending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByAscendingCity(agency.getAnnouncementsList()));
        }

        Agency agency = new Agency();

        actual = agency.sortAnnouncementsByAscendingPrice(actual);

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByDescendingAlphabeticalOrderCity() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByPrice("Descending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByDescendingCity(agency.getAnnouncementsList()));
        }

        Agency agency = new Agency();

        actual = agency.sortAnnouncementsByDescendingState(actual);

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByAscendingAlphabeticalOrderState() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByState("Ascending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByAscendingState(agency.getAnnouncementsList()));
        }

        Agency agency = new Agency();

        actual = agency.sortAnnouncementsByAscendingPrice(actual);

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByDescendingAlphabeticalOrderState() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByState("Descending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByDescendingState(agency.getAnnouncementsList()));
        }

        Agency agency = new Agency();

        actual = agency.sortAnnouncementsByDescendingState(actual);

        assertEquals(expected, actual);
    }

}