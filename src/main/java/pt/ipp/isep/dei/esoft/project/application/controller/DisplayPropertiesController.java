package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Display properties controller.
 */
public class DisplayPropertiesController {

    /**
     * The Announcement list.
     */
    private List<Announcement> announcementList;
    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository = null;
    /**
     * The Criteria repository.
     */
    private CriteriaRepository criteriaRepository = null;

    /**
     * The Property type repository.
     */
    private PropertyTypeRepository propertyTypeRepository = null;
    /**
     * The Business type repository.
     */
    private BusinessTypeRepository businessTypeRepository = null;

    /**
     * Instantiates a new Display properties controller.
     */
    public DisplayPropertiesController() {
        getAgencyRepository();
        getAgenciesList();
        announcementList = getAnnouncementsList();
        //List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
        sortAnnouncementsByMostRecentAdded();
        getCriteriaRepository();
    }

    /**
     * Gets the agency repository.
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
     * Gets the agencies list.
     *
     * @return the agencies list
     */
    public List<Agency> getAgenciesList() {
        AgencyRepository agencyRepository = getAgencyRepository();
        return agencyRepository.getAgenciesList();
    }

    /**
     * Get the announcements list from all the agencies.
     *
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsList() {
        List<Announcement> announcementList = new ArrayList<>();
        List<Agency> agencies = getAgenciesList();
        for (Agency agency : agencies) {
            announcementList.addAll(agency.getAnnouncementsList());
        }
        return announcementList;
    }

    /**
     * Sort announcements by most recent added date.
     *
     * @return the announcement list sorted
     */
    public List<Announcement> sortAnnouncementsByMostRecentAdded() {
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);

        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2) {
                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
            }
        };
        clonedAnnouncementList.sort(Collections.reverseOrder(acceptanceDate));
        //Collections.sort(clonedAnnouncementList, Collections.reverseOrder(acceptanceDate));
        return clonedAnnouncementList;

    }

    /**
     * Gets criteria repository.
     *
     * @return the criteria repository
     */
    public CriteriaRepository getCriteriaRepository() {
        if (criteriaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            criteriaRepository = repositories.getCriteriaRepository();
        }
        return criteriaRepository;
    }


    /**
     * Get announcement list by business type.
     *
     * @param announcementList the announcement list
     * @param businessType     the business type
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByBusinessType(List<Announcement> announcementList, String businessType) {
        Agency agency = new Agency();
        announcementList = agency.announcementHasBusinessType(announcementList, businessType);

        return announcementList;
    }

    /**
     * Get announcements list by property type.
     *
     * @param announcementList the announcement list
     * @param propertyType     the property type
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByPropertyType(List<Announcement> announcementList, String propertyType) {
        Agency agency = new Agency();
        announcementList = agency.announcementHasPropertyType(announcementList, propertyType);

        return announcementList;
    }

    /**
     * Get announcements list by number bedrooms.
     *
     * @param announcementList the announcement list
     * @param numberBedrooms   the number bedrooms
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByNumberBedrooms(List<Announcement> announcementList, Integer numberBedrooms) {
        Agency agency = new Agency();
        announcementList = agency.announcementHasNumberBedrooms(announcementList, numberBedrooms);

        return announcementList;
    }

    /**
     * Get announcements list by price.
     *
     * @param announcementList the announcement list
     * @param priceSorting     the price sorting order
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByPrice(List<Announcement> announcementList, String priceSorting) {
        if (priceSorting.equals("Ascending")) {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByAscendingPrice(announcementList);
            return announcementList;
        } else {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByDescendingPrice(announcementList);
            return announcementList;
        }
    }

    /**
     * Get announcements list by city.
     *
     * @param announcementList the announcement list
     * @param citySorting      the city sorting order
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByCity(List<Announcement> announcementList, String citySorting) {
        if (citySorting.equals("Ascending")) {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByAscendingCity(announcementList);
            return announcementList;
        } else {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByDescendingCity(announcementList);
            return announcementList;
        }
    }

    /**
     * Get announcements list by state.
     *
     * @param announcementList the announcement list
     * @param stateSorting     the state sorting order
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByState(List<Announcement> announcementList, String stateSorting) {
        if (stateSorting.equals("Ascending")) {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByAscendingState(announcementList);
            return announcementList;

        } else {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByDescendingState(announcementList);
            return announcementList;
        }
    }

    /**
     * Gets the business type repository.
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
     * Gets the list of business types.
     *
     * @return the business types
     */
    public List<BusinessType> getBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return businessTypeRepository.getBusinessTypeList();
    }

    /**
     * Gets the property type repository.
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
     * Gets the list of property types.
     *
     * @return the property types
     */
    public List<PropertyType> getPropertyTypes() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }
}
