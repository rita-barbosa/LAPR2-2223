package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class CreateRequestController {

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

    public Optional<Request> createRequest(String propertyTypeDesignation, String businessTypeDesignation, double amount,
                                           double area, int contractDuration, Optional<ArrayList<AvailableEquipment>> availableEquipment,
                                           String streetName, String city, String district, String state, String zipCode,
                                           boolean basement, boolean inhabitableLoft, boolean parkingSpace, Optional<String> sunExposure,
                                           int numberBedroom, Optional<Integer> numberBathroom, Agent agent, double distanceCityCenter,
                                           ArrayList<Photograph> photograph, Agency agency) {


        PropertyType propertyType = getPropertyTypeByDesignation(propertyTypeDesignation);
        BusinessType businessType = getBusinessTypeByDesignation(businessTypeDesignation);

        LocalDate requestDate = LocalDate.now();

        Property property;

        // CRIO UMA CLASS OWNER?????

        Optional<Request> newRequest = Optional.empty();

        // CHANGE METHOD OF COMPARISSON


        /*if (agency.isPresent()) {
            newRequest = agency.get()
                    .createRequest(reference, description, informalDescription, technicalDescription, duration, cost,
                            taskCategory, employee);
        }*/
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

    private Agency getAgencyByID(int id) {
        AgencyRepository agencyRepository = getAgencyRepository();
        //Get the Agency by its id
        Agency agencyById = agencyRepository.getAgencyByID(id);
        return agencyById;
    }


}
