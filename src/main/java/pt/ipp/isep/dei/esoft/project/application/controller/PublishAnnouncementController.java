package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

public class PublishAnnouncementController {
    private CommissionTypeRepository commissionTypeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;

    private AgencyRepository agencyRepository = null;


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


    public Optional<Announcement> publishAnnouncement(Double commissionValue, String commissionTypeDesignation, String ownerEmail, String propertyTypeDesignation, String streetName, String city, String district, String state, String zipCode, Double area, Double distanceCityCenter, Double price, Integer numberBedroom, Integer numberParkingSpace, Boolean existenceBasement, Boolean inhabitableLoft, Integer numberBathroom, List<String> availableEquipmentDescriptionList, List<String> uriList, SunExposureTypes sunExposure) {

        String email = getEmployeeEmail();
        Optional<Agency> agency = getAgencyFromEmail(email);
        Employee agent = getAgentFromSession(email, agency);

        PropertyType propertyType = getPropertyTypeRepository().getPropertyTypeByDesignation(propertyTypeDesignation);

        Optional<Request> newRequest = Optional.empty();
        Optional<Announcement> newAnnouncement = Optional.empty();

        CommissionType commissionType = getCommissionTypeRepository().getCommissionTypeByDesignation(commissionTypeDesignation);

        if (agency.isPresent()) {
            newRequest = agency.get().createSaleRequest(ownerEmail, propertyType, "sale", price, area, availableEquipmentDescriptionList, streetName, city, district, state,
                    zipCode, existenceBasement, inhabitableLoft, numberParkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uriList);
            if (newRequest.isPresent()) {
                newAnnouncement = agency.get().publishAnnouncement(agent, commissionType, commissionValue, newRequest.get());
            }
        }
        return newAnnouncement;

    }


    private Optional<Agency> getAgencyFromEmail(String email) {
        return getAgencyRepository().getAgencyByEmployeeEmail(email);
    }

    private String getEmployeeEmail() {
        return ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private Employee getAgentFromSession(String email, Optional<Agency> agency) {
        if (agency.isPresent()) {
            return agency.get().getAgentByEmail(email);
        } else {
            throw new RuntimeException("Agency not found for email: " + email);
        }
    }
}
