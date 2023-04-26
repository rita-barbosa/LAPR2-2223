package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class PublishAnnouncementController {
    private CommissionTypeRepository commissionTypeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;

    private AgencyRepository agencyRepository = null;
    private final BusinessType businessType = new BusinessType("Sale");

    public PublishAnnouncementController() {
        getCommissionTypeRepository();
        getPropertyTypeRepository();
        getAuthenticationRepository();
    }

    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    private CommissionTypeRepository getCommissionTypeRepository() {
        if (commissionTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            commissionTypeRepository = repositories.getCommissionTypeRepository();
        }
        return commissionTypeRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public List<CommissionType> getCommissionTypeList() {
        CommissionTypeRepository commissionTypeRepository = getCommissionTypeRepository();
        return commissionTypeRepository.getCommissionTypeList();
    }

    public List<PropertyType> getPropertyTypeList() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }


//    public Optional<Announcement> publishAnnoucement(Double commissionValue, String commissionTypeDesignation, String ownerEmail, String propertyTypeDesignation, String streetName, String city, String district, String state, String zipCode, Double area, Double distanceCityCenter, Double price, Integer numberBedroom, Integer numberParkingSpace, Boolean existenceBasement, Boolean inhabitableLoft, Integer numberBathroom, List<String> availableEquipmentDescriptionList, List<String> uriList, SunExposureTypes sunExposure) {
//
//        Employee employee = getAgentFromSession();
//        Optional<Agency> agency = getAgencyRepository().getAgencyByEmployee(employee);
//
//        PropertyType propertyType = getPropertyTypeRepository().getPropertyTypeByDesignation(propertyTypeDesignation);
//        Optional<Request> newRequest = Optional.empty();
//
//        CommissionType commissionType = getCommissionTypeRepository().getCommissionTypeByDesignation(commissionTypeDesignation);
//
//        if (agency.isPresent()) {
//           newRequest = agency.get().createRequest();
//        }
//    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private Employee getAgentFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
    }
}
