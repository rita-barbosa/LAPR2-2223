package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.*;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements Runnable {

    public void run() {
        addUserRoles();
        addUsers();
        addAgencies();
        addCommissionTypes();
        addPropertyTypes();
        addBusinessTypes();
        addCriteria();
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
        PersonRepository personRepository = new PersonRepository();
        Employee employee = new Employee(agency.getId(), "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623 456 7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        Employee employee1 = new Employee(agency.getId(), "Annie Smith", "C04567089",
                "927-65-4091", "employee1@this.app", "agent", "555 555 5555",
                "New York", "Manhattan", "NY", "10001", "Broadway St");
        Employee employee2 = new Employee(agency.getId(), "Maria Silva", "C12339878",
                "134-23-2555", "employee2@this.app", "agent", "911 234 5567",
                "Porto", "Porto", "Porto", "43005", "Rua da Alegria, 123");

        agency.addEmployee(employee);
        agency.addEmployee(employee1);
        agency.addEmployee(employee2);
    }

    private void addAgencies() {
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        //Integer id, String description, String emailAddress, String phoneNumber, Location location

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency4 = new Agency(1234, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);
        addEmployees(agency4);
        addAnnouncements(agency4);
        agencyRepository.add(agency4);

        Location location1 = new Location("Devil's Road", "Hell", "Earth", "ET", "16789");
        Agency agency5 = new Agency(6789, "Make It Home LLC.", "agency5@this.app", "286 776 8435", location1);
        addEmployees(agency5);
        agencyRepository.add(agency5);
    }

    private void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserWithRole("Administrator", "admin@this.app", "01ADMin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Agent", "employee@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Agent", "employee3@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Client 1", "client1@this.app", "01CLIen",
                AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Network", "teste@this.app", "01CLIen",
                AuthenticationController.ROLE_NETWORK_MANAGER);

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

    private void addCriteria() {
        CriteriaRepository criteriaRepository = Repositories.getInstance().getCriteriaRepository();

        criteriaRepository.add("Type of Business");
        criteriaRepository.add("Type of Property");
        criteriaRepository.add("Number of Bedrooms");
        criteriaRepository.add("Price");
        criteriaRepository.add("City");
        criteriaRepository.add("State");
    }

    private void addAnnouncements(Agency agency) {
        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        String ownerEmail2 = "owner2@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (100.0), (70.3),
                uriList, "street 1", "city 1", "district 1", "st1", "12346");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 300.0), LocalDate.now(), employee);

        Property property2 = new Property(new PropertyType("land"), (30.5), (456.3),
                uriList, "street 2", "city 2", "district 2", "st2", "12347");
        Request request2 = new Request(ownerEmail1, property2, new Business("sale", 2345.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request3 = new Request(ownerEmail2, property3, new Business("lease", 2345.0), LocalDate.now(), employee);


        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 25, 3, 15.2, uriList);
        Request request4 = new Request(ownerEmail2, property4, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property5 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 25, 3, 15.2, uriList);
        Request request5 = new Request(ownerEmail2, property5, new Business("sale", 2345.0), LocalDate.now(), employee);

        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request1));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request2));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request3));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request4));
        agency.addAnnouncement(new Announcement(employee, commissionType, 234.0, request5));

    }

}