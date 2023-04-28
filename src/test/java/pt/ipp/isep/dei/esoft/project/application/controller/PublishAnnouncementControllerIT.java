package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.*;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PublishAnnouncementControllerIT {
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
        Employee employee = new Employee("john.doe@this.company.com");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "john.doe@this.company.com", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.doLogin("john.doe@this.company.com", "admin");

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
        Employee employee = new Employee("john.doe@this.company.com");
        agency.addEmployee(employee);
        agencyRepository.add(agency);


        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Main Administrator", "john.doe@this.company.com", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.doLogin("john.doe@this.company.com", "admin");

       PublishAnnouncementController controller =
                new PublishAnnouncementController(agencyRepository, propertyTypeRepository, authenticationRepository);


        List<PropertyType> propertyTypeList = controller.getPropertyTypeList();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());

    }
}