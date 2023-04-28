package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

public class PublishAnnouncementController {
    private CommissionTypeRepository commissionTypeRepository;
    private AuthenticationRepository authenticationRepository;
    private PropertyTypeRepository propertyTypeRepository;

    private AgencyRepository agencyRepository;


    public PublishAnnouncementController() {
        getCommissionTypeRepository();
        getPropertyTypeRepository();
        getAuthenticationRepository();
    }

    public PublishAnnouncementController(CommissionTypeRepository commissionTypeRepository,
                                         AuthenticationRepository authenticationRepository,
                                         PropertyTypeRepository propertyTypeRepository,
                                         AgencyRepository agencyRepository) {
        this.commissionTypeRepository = commissionTypeRepository;
        this.authenticationRepository = authenticationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.agencyRepository = agencyRepository;
    }

    public PublishAnnouncementController(AgencyRepository agencyRepository, PropertyTypeRepository propertyTypeRepository, AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.agencyRepository = agencyRepository;
    }

    public PublishAnnouncementController(AgencyRepository agencyRepository, CommissionTypeRepository commissionTypeRepository, AuthenticationRepository authenticationRepository) {
        this.commissionTypeRepository = commissionTypeRepository;
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
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

    private PropertyType getPropertyTypeByDesignation(String designation) {
        return getPropertyTypeRepository().getPropertyTypeByDesignation(designation);
    }

    private CommissionType getCommissionTypeByDesignation(String designation) {
        return getCommissionTypeRepository().getCommissionTypeByDesignation(designation);
    }

    public Optional<Announcement> publishAnnouncement(Double commissionValue, String commissionTypeDesignation, String ownerEmail, String propertyTypeDesignation, String streetName, String city, String district, String state, String zipCode, Double area, Double distanceCityCenter, Double price, Integer numberBedroom, Integer numberParkingSpace, Boolean existenceBasement, Boolean inhabitableLoft, Integer numberBathroom, List<String> availableEquipmentDescriptionList, List<String> uriList, SunExposureTypes sunExposure) {

        String email = getEmailFromSession();
        Optional<Agency> agency = getAgencyByEmail(email);
        Employee agent = getAgentByEmail(email, agency);

        PropertyType propertyType = getPropertyTypeByDesignation(propertyTypeDesignation);

        Optional<Request> newRequest;
        Optional<Announcement> newAnnouncement = Optional.empty();

        CommissionType commissionType = getCommissionTypeByDesignation(commissionTypeDesignation);

        if (agency.isPresent()) {
            newRequest = agency.get().createSaleRequest(ownerEmail, propertyType, "sale", price, area, availableEquipmentDescriptionList, streetName, city, district, state,
                    zipCode, existenceBasement, inhabitableLoft, numberParkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uriList);
            if (newRequest.isPresent()) {
                newAnnouncement = agency.get().publishAnnouncement(agent, commissionType, commissionValue, newRequest.get());
            }
        }
        return newAnnouncement;

    }


    private Optional<Agency> getAgencyByEmail(String email) {
        return getAgencyRepository().getAgencyByEmployeeEmail(email);
    }

    private String getEmailFromSession() {
        return ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private Employee getAgentByEmail(String email, Optional<Agency> agency) {
        if (agency.isPresent()) {
            return agency.get().getAgentByEmail(email);
        } else {
            throw new RuntimeException("Agency not found for email: " + email);
        }
    }
}
