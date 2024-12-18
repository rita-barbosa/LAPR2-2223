package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Publish announcement controller.
 */
public class PublishAnnouncementController {
    /**
     * The Commission type repository.
     */
    private CommissionTypeRepository commissionTypeRepository;
    /**
     * The Authentication repository.
     */
    private AuthenticationRepository authenticationRepository;
    /**
     * The Property type repository.
     */
    private PropertyTypeRepository propertyTypeRepository;

    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository;


    /**
     * Instantiates a new Publish Announcement Controller.
     */
    public PublishAnnouncementController() {
        getCommissionTypeRepository();
        getPropertyTypeRepository();
        getAuthenticationRepository();

    }
    /**
     * Instantiates a new Publish Announcement Controller.
     *
     * @param commissionTypeRepository the commission type repository
     * @param authenticationRepository the authentication repository
     * @param propertyTypeRepository   the property type repository
     * @param agencyRepository         the agency repository
     */
    public PublishAnnouncementController(CommissionTypeRepository commissionTypeRepository,
                                         AuthenticationRepository authenticationRepository,
                                         PropertyTypeRepository propertyTypeRepository,
                                         AgencyRepository agencyRepository) {
        this.commissionTypeRepository = commissionTypeRepository;
        this.authenticationRepository = authenticationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.agencyRepository = agencyRepository;
    }

    /**
     * Instantiates a new Publish Announcement Controller.
     *
     * @param agencyRepository         the agency repository
     * @param propertyTypeRepository   the property type repository
     * @param authenticationRepository the authentication repository
     */
    public PublishAnnouncementController(AgencyRepository agencyRepository, PropertyTypeRepository propertyTypeRepository, AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.agencyRepository = agencyRepository;
    }

    /**
     * Instantiates a new Publish Announcement Controller.
     *
     * @param agencyRepository         the agency repository
     * @param commissionTypeRepository the commission type repository
     * @param authenticationRepository the authentication repository
     */
    public PublishAnnouncementController(AgencyRepository agencyRepository, CommissionTypeRepository commissionTypeRepository, AuthenticationRepository authenticationRepository) {
        this.commissionTypeRepository = commissionTypeRepository;
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
    }


    /**
     * Gets property type repository.
     *
     * @return the property type repository
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    /**
     * Gets commission type repository.
     *
     * @return the commission type repository
     */
    private CommissionTypeRepository getCommissionTypeRepository() {
        if (commissionTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            commissionTypeRepository = repositories.getCommissionTypeRepository();
        }
        return commissionTypeRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Gets a commission type list.
     *
     * @return the commission type list
     */
    public List<CommissionType> getCommissionTypeList() {
        CommissionTypeRepository commissionTypeRepository = getCommissionTypeRepository();
        return commissionTypeRepository.getCommissionTypeList();
    }

    /**
     * Gets a property type list.
     *
     * @return the property type list
     */
    public List<PropertyType> getPropertyTypeList() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }

    /**
     * Gets a property type by its designation.
     *
     * @param designation the designation
     * @return the property type by designation
     */
    private PropertyType getPropertyTypeByDesignation(String designation) {
        return getPropertyTypeRepository().getPropertyTypeByDesignation(designation).get();
    }

    /**
     * Gets a commission type by its designation.
     *
     * @param designation the designation
     * @return the commission type by designation
     */
    private CommissionType getCommissionTypeByDesignation(String designation) {
        return getCommissionTypeRepository().getCommissionTypeByDesignation(designation).get();
    }

    /**
     * This method is responsible for publishing the announcement made by the agent.
     *
     * @param announcementDto - a dto with all the necessary information.
     * @return the optional
     */
    public Boolean publishAnnouncement(AnnouncementDto announcementDto) throws IllegalArgumentException {
        Boolean success = false;
        String email = getEmailFromSession();
        Optional<Agency> agency = getAgencyByEmail(email);


        PropertyType propertyType = getPropertyTypeByDesignation(announcementDto.getPropertyTypeDesignation());

        Optional<Request> newRequest;
        Optional<Announcement> newAnnouncement;

        CommissionType commissionType = getCommissionTypeByDesignation(announcementDto.getCommissionTypeDesignation());

        if (agency.isPresent()) {
            Employee agent = getAgentByEmail(email, agency.get());
            try {
                newRequest = agency.get().createSaleRequest(announcementDto,propertyType,agent);
                if (newRequest.isPresent()) {
                    newAnnouncement = agency.get().publishAnnouncement(agent, commissionType, announcementDto.getCommissionValue(), newRequest.get());
                    if (newAnnouncement.isPresent()) {
                        success = true;
                    }
                }
            } catch (IllegalArgumentException e) {
                throw e;
            }

        }
        return success;
    }


    /**
     * Gets agency by email.
     *
     * @param email the user email
     * @return the agency by email
     */
    private Optional<Agency> getAgencyByEmail(String email) {
        return getAgencyRepository().getAgencyByEmployeeEmail(email);
    }

    /**
     * Gets email from session.
     *
     * @return the user email.
     */
    private String getEmailFromSession() {
        return getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * Gets agency repository.
     *
     * @return the agency repository.
     */
    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    /**
     * Gets agent by email.
     *
     * @param email  the user email.
     * @param agency the agency.
     * @return the agent by email.
     */
    private Employee getAgentByEmail(String email, Agency agency) {
        return agency.getAgentByEmail(email);
    }
}
