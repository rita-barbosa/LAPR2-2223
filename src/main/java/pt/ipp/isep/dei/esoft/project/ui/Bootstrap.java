package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.*;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    public void run() {
        addOrganization();
        addUserRoles();
        addUsers();
        addCommissionTypes();
        addPropertyTypes();
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

    private void addOrganization() {
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        Agency agency1 = new Agency(2425);
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


}
