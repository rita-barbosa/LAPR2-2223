package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * The Create request controller.
 */
public class CreateRequestController {
    /**
     * The User session.
     */
    private UserSession userSession;
    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository = null;
    /**
     * The Property type repository.
     */
    private PropertyTypeRepository propertyTypeRepository = null;
    /**
     * The Business type repository.
     */
    private BusinessTypeRepository businessTypeRepository = null;

    /**
     * Instantiates a new Create request controller.
     */
    public CreateRequestController() {
        getBusinessTypeRepository();
        getPropertyTypeRepository();
        getAgencyRepository();
    }

    /**
     * Instantiates a new Create request controller.
     *
     * @param agencyRepository       the agency repository
     * @param propertyTypeRepository the property type repository
     * @param businessTypeRepository the business type repository
     */
    public CreateRequestController(AgencyRepository agencyRepository,
                                   PropertyTypeRepository propertyTypeRepository,
                                   BusinessTypeRepository businessTypeRepository) {
        this.agencyRepository = agencyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
    }

    /**
     * Gets agency repository.
     *
     * @return the agency repository
     */
    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
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
     * Gets business type repository.
     *
     * @return the business type repository
     */
    private BusinessTypeRepository getBusinessTypeRepository() {
        if (businessTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            businessTypeRepository = repositories.getBusinessTypeRepository();
        }
        return businessTypeRepository;
    }

    /**
     * Create request (optional).
     *
     * @param propertyTypeDesignation       the property type designation of a property
     * @param businessTypeDesignation       the business type designation of a request
     * @param amount                        the amount (price or rent of a property)
     * @param area                          the area of a property
     * @param contractDuration              the contract duration of a property
     * @param availableEquipmentDescription the available equipment description list of a property
     * @param streetName                    the street name of a property's location
     * @param city                          the city of a property's location
     * @param district                      the district of a property's location
     * @param state                         the state of a property's location
     * @param zipCode                       the zip code of a property's location
     * @param basement                      the basement of a property's existance
     * @param inhabitableLoft               the inhabitable loft of a property's existance
     * @param parkingSpace                  the parking space number of a property
     * @param sunExposure                   the sun exposure direction of a property
     * @param numberBedroom                 the number of bedrooms of a property
     * @param numberBathroom                the number of bathrooms of a property
     * @param agent                         the agent in charge of a property's request
     * @param distanceCityCenter            the distance from city center of a property
     * @param photograph                    the photograph uri list of a property
     * @param agency                        the agency responsible for a property's request
     * @return the (optional) request
     */
    public Optional<Request> createRequest(String propertyTypeDesignation, String businessTypeDesignation, Double amount,
                                           Double area, Integer contractDuration, List<String> availableEquipmentDescription,
                                           String streetName, String city, String district, String state, String zipCode,
                                           Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                                           Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter,
                                           List<String> photograph, Agency agency) {

        String ownerEmail = getOwnerEmail();

        PropertyType propertyType = getPropertyTypeByDesignation(propertyTypeDesignation);


        Optional<Request> newRequest = Optional.empty();

        Optional<Agency> newAgency = getAgencyByID(agency.getId());

        if (newAgency.isPresent()){
            newRequest = newAgency.get()
                    .createRequest(ownerEmail, propertyType, businessTypeDesignation, amount, area, contractDuration,
                            availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft,
                            parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, photograph);
            return newRequest;
        }
         return newRequest;
    }

    /**
     * Gets agency by id.
     *
     * @param id the agency's id
     * @return the agency with given id
     */
    private Optional<Agency> getAgencyByID(Integer id) {
        return getAgencyRepository().getAgencyByID(id);
    }

    /**
     * Gets property type by designation.
     *
     * @param propertyTypeDesignation the property type designation
     * @return the property type with given designation
     */
    public PropertyType getPropertyTypeByDesignation(String propertyTypeDesignation) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        PropertyType propertyTypeByDesignation =
                getPropertyTypeRepository().getPropertyTypeByDesignation(propertyTypeDesignation);
        return propertyTypeByDesignation;

    }

    /**
     * Gets business types list.
     *
     * @return the business types list
     */
    public List<BusinessType> getBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return businessTypeRepository.getBusinessTypeList();
    }

    /**
     * Gets property types list.
     *
     * @return the property types list
     */
    public List<PropertyType> getPropertyTypes() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }

    /**
     * Gets agencies list.
     *
     * @return the agencies list
     */
    public List<Agency> getAgenciesList() {
        AgencyRepository agencyRepository = getAgencyRepository();
        return agencyRepository.getAgenciesList();
    }

    /**
     * Gets the user (owner) email.
     *
     * @return the owner email
     */
    private String getOwnerEmail() {
        return ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    /**
     * Get agents list of given agency.
     *
     * @param agency the agency
     * @return the agents list
     */
    public List<Employee> getAgents(Agency agency){
        return agency.getAgentList();
    }
}
