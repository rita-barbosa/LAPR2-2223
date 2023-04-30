package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateRequestControllerIT {


    @Test
    void ensureCreateRequestWorks() {

        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);
        ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        BusinessType businessType = new BusinessType("Sale");
        businessTypeRepository.add(businessType);
        ArrayList<BusinessType> bussType = new ArrayList<>();
        bussType.add(businessType);

        Agency agency = new Agency("RE/MAX");
        agencyRepository.add(agency);
        Employee employee = new Employee("employee@this.app", "agent");
        agency.addEmployee(employee);

        //String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("John", "client1@this.app", "01CLIent",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client1@this.app", "01CLIent");

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        //Act
        Optional<Request> result = controller.createRequest("land", "sale", 2345.0,
                35.5, 0, av, "street", "city", "district", "state", "12345", null,
                null, null, null, null, null, employee, 89.3, uriList, agency);
    }


    @Test
    void ensureGetPropertyTypeListWorks() {

        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PropertyType propertyType = new PropertyType("House");
        propertyTypeRepository.add(propertyType);

        PropertyType propertyType1 = new PropertyType("Land");
        propertyTypeRepository.add(propertyType1);

        ArrayList<PropertyType> expected = new ArrayList<>();
        expected.add(propertyType);
        expected.add(propertyType1);

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<PropertyType> propertyTypeList = controller.getPropertyTypes();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());
    }

    @Test
    void ensureGetBusinessTypeListWorks() {

        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();


        BusinessType businessType = new BusinessType("Lease");
        businessTypeRepository.add(businessType);

        BusinessType businessType1 = new BusinessType("Sale");
        businessTypeRepository.add(businessType1);

        ArrayList<BusinessType> expected = new ArrayList<>();
        expected.add(businessType);
        expected.add(businessType1);

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<BusinessType> businessTypeList = controller.getBusinessTypes();

        assertArrayEquals(expected.toArray(), businessTypeList.toArray());
    }

    @Test
    void ensureGetAgenciesListWorks() {

        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();


        Agency agency = new Agency(1234);
        Agency agency1 = new Agency(6789);

        agencyRepository.add(agency);
        agencyRepository.add(agency1);

        ArrayList<Agency> expected = new ArrayList<>();
        expected.add(agency);
        expected.add(agency1);

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<Agency> agencies = controller.getAgenciesList();

        assertArrayEquals(expected.toArray(), agencies.toArray());
    }

    @Test
    void ensureGetAgentsListWorks() {
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        Agency agency = new Agency(6789);
        agencyRepository.add(agency);


        Employee employee = new Employee("employee@this.app", "agent");
        Employee employee1 = new Employee("employee1@this.app", "agent");

        agency.addEmployee(employee);
        agency.addEmployee(employee1);

        ArrayList<Employee> expected = new ArrayList<>();
        expected.add(employee);
        expected.add(employee1);

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<Employee> agents = controller.getAgents(agency);

        assertArrayEquals(expected.toArray(), agents.toArray());
    }

    @Test
    void ensureCreateRequestForNonExistingAgencyFails() {
        //Arrange
        //Get Repositories
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);
        ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        BusinessType businessType = new BusinessType("Sale");
        businessTypeRepository.add(businessType);
        ArrayList<BusinessType> bussType = new ArrayList<>();
        bussType.add(businessType);

        Agency agency = new Agency("RE/MAX");
        agencyRepository.add(agency);
        Employee employee = new Employee("employee@this.app", "agent");
        agency.addEmployee(employee);

        //String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("John", "client1@this.app", "01CLIent",
                AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client1@this.app", "01CLIent");

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        //Act
        Optional<Request> result = controller.createRequest("land", "sale", 2345.0,
                35.5, 0, av, "street", "city", "district", "state", "12345", null,
                null, null, null, null, null, employee, 89.3, uriList, agency);

        assertTrue(result.isEmpty());
    }


}