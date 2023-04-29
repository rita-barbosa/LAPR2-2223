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

        Employee employee3 = new Employee(1234, "John Doe", "C12345678",
                "123-45-6789", "employee3@this.app", "agent", "623 456 7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");

        Employee employee4 = new Employee(1234, "Annie Smith", "C23456789",
                "987-65-4321", "employee4@this.app", "agent", "555 555 5555",
                "New York", "Manhattan", "NY", "10001", "Broadway St");

        Employee employee5 = new Employee(6789, "Maria Silva", "C12345678",
                "123-45-6789", "employee5@this.app", "agent", "911 234 5567",
                "Porto", "Porto", "Porto", "43005", "Rua da Alegria, 123");
        agency.addEmployee(employee);
        agency.addEmployee(employee1);
        agency.addEmployee(employee2);
        agency.addEmployee(employee3);
        agency.addEmployee(employee4);
        agency.addEmployee(employee5);
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

        //Integer id, String description, String emailAddress, String phoneNumber, Location location

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency4 = new Agency(1234, "Idealista", "agency4@this.app", "999 444 5656", location);
        addEmployees(agency4);
        agencyRepository.add(agency4);

        Location location1 = new Location("Devil's Road", "Hell", "Earth", "ET", "16789");
        Agency agency5 = new Agency(6789, "RE/MAX", "agency5@this.app", "286 776 8435", location1);
        addEmployees(agency5);
        agencyRepository.add(agency5);

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