package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.*;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class PublishAnnouncementControllerIT {

    @Test
    void ensurePublishAnnouncementWorks() {
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        CommissionType c1 = new CommissionType("Fixed");
        commissionTypeRepository.add(c1);

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);
        ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "agent1@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);

        Agency agency = new Agency(1234);
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "1agent1@this.app","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.doLogin("agent1@this.app", "01AGEnt");

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();

        PublishAnnouncementController ctrl = new PublishAnnouncementController(commissionTypeRepository, authenticationRepository, propertyTypeRepository, agencyRepository);

        Boolean result = ctrl.publishAnnouncement(234.4, c1.getDesignation(), ownerEmail, propertyType.getDesignation(),
                "street", "city", "district", "AK", "12345", 234.4, 23.4, 456.6,
                null, null, null, null, null,
                null, uriList, null);
    }

    @Test
    void ensurePublishAnnouncementForNonExistingAgencyFails() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        CommissionType c1 = new CommissionType("Fixed");
        commissionTypeRepository.add(c1);

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);
        ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "2agent2@this.app", "02AGEnt",
                AuthenticationController.ROLE_AGENT);

        Agency agency = new Agency(1234);
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "1agent1@this.app","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.doLogin("2agent2@this.app", "02AGEnt");

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        PublishAnnouncementController controller = new PublishAnnouncementController(commissionTypeRepository, authenticationRepository, propertyTypeRepository, agencyRepository);

        Boolean result = controller.publishAnnouncement(234.4, c1.getDesignation(), ownerEmail, propertyType.getDesignation(),
                "street", "city", "district", "AK", "12345", 234.4, 23.4, 456.6,
                null, null, null, null, null,
                null, uriList, null);

        assertFalse(result);
    }

    @Test
    void ensureGetCommissionTypeListWorks() {
        Repositories repositories = Repositories.getInstance();
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

        Agency agency = new Agency(1234);
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "1agent1@this.app","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("agent", "agent1@this.app","01AGEnt" ,
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.doLogin("agent1@this.app", "01AGEnt");

        PublishAnnouncementController controller =
                new PublishAnnouncementController(agencyRepository, commissionTypeRepository, authenticationRepository);


        List<CommissionType> commissionTypeList = controller.getCommissionTypeList();

        assertArrayEquals(expected.toArray(), commissionTypeList.toArray());
    }

    @Test
    void ensureGetPropertyTypeListWorks() {

        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PropertyType propertyType1 = new PropertyType("Property Type Description One");
        propertyTypeRepository.add(propertyType1);

        PropertyType propertyType2 = new PropertyType("Property Type Description Two");
        propertyTypeRepository.add(propertyType2);

        ArrayList<PropertyType> expected = new ArrayList<PropertyType>();
        expected.add(propertyType1);
        expected.add(propertyType2);

        Agency agency = new Agency(1234);
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "1agent1@this.app","agent", "423-423-2345","city", "district", "AK", "12345", "street");
        agency.addEmployee(employee);
        agencyRepository.add(agency);


        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Agent", "agent1@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.doLogin("agent1@this.app", "01AGEnt");

        PublishAnnouncementController controller =
                new PublishAnnouncementController(agencyRepository, propertyTypeRepository, authenticationRepository);


        List<PropertyType> propertyTypeList = controller.getPropertyTypeList();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());
    }
}