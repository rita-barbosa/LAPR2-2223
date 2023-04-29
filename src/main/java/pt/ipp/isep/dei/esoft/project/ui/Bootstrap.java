package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.*;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    public void run() {
        addUserRoles();
        addUsers();
        addAgencies();
        addCommissionTypes();
        addPropertyTypes();
        addBusinessTypes();
    }

    private void addUserRoles() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT,
                AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_NETWORK_MANAGER, AuthenticationController.ROLE_NETWORK_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER, AuthenticationController.ROLE_STORE_MANAGER);
    }

    public void addEmployees(Agency agency) {
        Employee employee = new Employee("employee@this.app", "agent");
        Employee employee1 = new Employee("employee1@this.app", "agent");
        Employee employee2 = new Employee("employee2@this.app", "agent");
        agency.addEmployee(employee);
        agency.addEmployee(employee1);
        agency.addEmployee(employee2);
    }

    private void addAgencies() {
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        Agency agency1 = new Agency(2526);
        addEmployees(agency1);
        agencyRepository.add(agency1);

        Agency agency2 = new Agency(2526);
        agencyRepository.add(agency2);

        Agency agency3 = new Agency(2627);
        agencyRepository.add(agency3);
    }

    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserWithRole("Administrator", "admin@this.app", "01ADMin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Agent", "employee@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client 1", "client1@this.app", "01CLIent",
                AuthenticationController.ROLE_CLIENT);

    }

    private void addCommissionTypes() {
        CommissionTypeRepository commissionTypeRepository = Repositories.getInstance().getCommissionTypeRepository();

        commissionTypeRepository.add(new CommissionType("Fixed"));
        commissionTypeRepository.add(new CommissionType("Percentual"));
    }

    private void addPropertyTypes() {
        PropertyTypeRepository propertyTypeRepository = Repositories.getInstance().getPropertyTypeRepository();

        propertyTypeRepository.add(new PropertyType("Land"));
        propertyTypeRepository.add(new PropertyType("Apartment"));
        propertyTypeRepository.add(new PropertyType("House"));
    }

    private void addBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = Repositories.getInstance().getBusinessTypeRepository();

        businessTypeRepository.add(new BusinessType("Sale"));
        businessTypeRepository.add(new BusinessType("Lease"));
    }

}
