package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateRequestController {
    private UserSession userSession;
    private AgencyRepository agencyRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;
    private BusinessTypeRepository businessTypeRepository = null;


    //Repository instances are obtained from the Repositories class
    public CreateRequestController() {
        getBusinessTypeRepository();
        getPropertyTypeRepository();
        getAgencyRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateRequestController(AgencyRepository agencyRepository,
                                   PropertyTypeRepository propertyTypeRepository,
                                   BusinessTypeRepository businessTypeRepository) {
        this.agencyRepository = agencyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            //Get the BusinessTypeRepository
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            //Get the PropertyTypeRepository
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    private BusinessTypeRepository getBusinessTypeRepository() {
        if (businessTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            //Get the BusinessTypeRepository
            businessTypeRepository = repositories.getBusinessTypeRepository();
        }
        return businessTypeRepository;
    }

    // AUTHENTICATION REPOSITORY?????????

    public Optional<Request> createRequest(String propertyTypeDesignation, String businessTypeDesignation, Double amount,
                                           Double area, Integer contractDuration, ArrayList<AvailableEquipment> availableEquipment,
                                           String streetName, String city, String district, String state, String zipCode,
                                           Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                                           Integer numberBedroom, Integer numberBathroom, Agent agent, Double distanceCityCenter,
                                           ArrayList<Photograph> photograph, Agency agency) {

        String ownerEmail = getOwnerEmail();

        PropertyType propertyType = getPropertyTypeByDesignation(propertyTypeDesignation);
        BusinessType businessType = getBusinessTypeByDesignation(businessTypeDesignation);

        Optional<Request> newRequest = Optional.empty();

        Optional<Agency> newAgency = Optional.of(getAgencyRepository().getAgencyByID(agency.getId()));

        newRequest = newAgency.get()
                .createRequest(ownerEmail, propertyType, businessType, amount, area, contractDuration,
                        availableEquipment, streetName, city, district, state, zipCode, basement, inhabitableLoft,
                        parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, photograph);
        return newRequest;
    }


    private PropertyType getPropertyTypeByDesignation(String propertyTypeDesignation) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();

        //Get the PropertyType by its designation
        PropertyType propertyTypeByDesignation =
                getPropertyTypeRepository().getPropertyTypeByDesignation(propertyTypeDesignation);
        return propertyTypeByDesignation;

    }

    private BusinessType getBusinessTypeByDesignation(String businessTypeDesignation) {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();

        //Get the BusinessType by its designation
        BusinessType businessTypeByDesignation =
                getBusinessTypeRepository().getBusinessTypeByDesignation(businessTypeDesignation);
        return businessTypeByDesignation;
    }

    //returns the list of business types
    public List<BusinessType> getBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return businessTypeRepository.getBusinessTypeList();
    }

    //returns the list of property types
    public List<PropertyType> getPropertyTypes() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }

    //returns the list of agencies
    public List<Agency> getAgenciesList() {
        AgencyRepository agencyRepository = getAgencyRepository();
        return agencyRepository.getAgenciesList();
    }

    //returns the agency that has the given id
    private Agency getAgencyByID(Integer id) {
        AgencyRepository agencyRepository = getAgencyRepository();
        //Get the Agency by its id
        return agencyRepository.getAgencyByID(id);
    }

    //returns the Owner's email
    private String getOwnerEmail() {
        this.userSession = new UserSession(new pt.isep.lei.esoft.auth.UserSession());
        return userSession.getUserEmail();
    }

    //returns the list of agents
    public List<Agent> getAgents(Integer id){
        return getAgencyByID(id).getAgentList();
    }
}
