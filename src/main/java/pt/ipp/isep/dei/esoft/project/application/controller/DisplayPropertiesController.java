package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.*;

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
        getPropertyTypeRepository();
        getBusinessTypeRepository();
        announcementList = getAnnouncementsList();
        getCriteriaRepository();
    }

    /**
     * Instantiates a new Display properties controller.
     *
     * @param agencyRepository       the agency repository
     * @param propertyTypeRepository the property type repository
     * @param businessTypeRepository the business type repository
     * @param criteriaRepository     the criteria repository
     */
    public DisplayPropertiesController(AgencyRepository agencyRepository,
                                       PropertyTypeRepository propertyTypeRepository,
                                       BusinessTypeRepository businessTypeRepository,
                                       CriteriaRepository criteriaRepository) {
        this.agencyRepository = agencyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.criteriaRepository = criteriaRepository;
        this.announcementList = getAnnouncementsList();

    }

    /**
     * Instantiates a new Display properties controller.
     *
     * @param criteriaRepository the criteria repository
     */
    public DisplayPropertiesController(CriteriaRepository criteriaRepository) {
        this.criteriaRepository = criteriaRepository;
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

//    /**
//     * Sort announcements by most recent added date.
//     *
//     * @return the announcement list sorted
//     */
//    public List<Announcement> sortAnnouncementsByMostRecentAdded() {
//        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
//
//        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
//            public int compare(Announcement a1, Announcement a2) {
//                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
//                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();
//
//                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
//            }
//        };
//        clonedAnnouncementList.sort(Collections.reverseOrder(acceptanceDate));
//        return clonedAnnouncementList;
//
//    }

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


//    /**
//     * Get announcement list by business type.
//     *
//     * @param businessType     the business type
//     * @return the announcement list
//     */
//    public List<Announcement> getAnnouncementsByBusinessType(String businessType, List<Announcement> announcementList) {
//        List<Announcement> announcements = new ArrayList<>();
//        List<Agency> agencies = getAgenciesList();
//        for (Agency agencyStore : agencies) {
//            announcements.addAll(agencyStore.announcementHasBusinessType(agencyStore.getAnnouncementsList(), businessType));
//        }
//        return announcements;
//    }
//
//    /**
//     * Get announcements list by property type.
//     *
//     * @param propertyType     the property type
//     * @return the announcement list
//     */
//    public List<Announcement> getAnnouncementsByPropertyType(String propertyType) {
//        List<Announcement> announcements = new ArrayList<>();
//        List<Agency> agencies = getAgenciesList();
//        for (Agency agencyStore : agencies) {
//            announcements.addAll(agencyStore.announcementHasPropertyType(agencyStore.getAnnouncementsList(), propertyType));
//        }
//        return announcements;
//    }
//
//    /**
//     * Get announcements list by number bedrooms.
//     *
//     * @param numberBedrooms   the number bedrooms
//     * @return the announcement list
//     */
//    public List<Announcement> getAnnouncementsByNumberBedrooms(Integer numberBedrooms) {
//        List<Announcement> announcements = new ArrayList<>();
//    //    Agency agency = new Agency();
//        List<Agency> agencies = getAgenciesList();
//        for (Agency agencyStore : agencies) {
//            announcements.addAll(agencyStore.announcementHasNumberBedrooms(agencyStore.getAnnouncementsList(), numberBedrooms));
//        }
//        //announcementList = agency.announcementHasNumberBedrooms(announcementList, numberBedrooms);
//
//      //  return announcementList;
//        return announcements;
//    }
//
//    /**
//     * Get announcements list by price.
//     *
//     * @param priceSorting     the price sorting order
//     * @return the announcement list
//     */
//    public List<Announcement> getAnnouncementsByPrice(String priceSorting) {
//        List<Announcement> announcements = new ArrayList<>();
//        List<Agency> agencies = getAgenciesList();
//        if (priceSorting.equals("Ascending")) {
////            Agency agency = new Agency();
////            announcementList = agency.sortAnnouncementsByAscendingPrice(announcementList);
////            return announcementList;
//            for (Agency agencyStore : agencies) {
//                announcements.addAll(agencyStore.sortAnnouncementsByAscendingPrice(agencyStore.getAnnouncementsList()));
//            }
//            return announcements;
//        } else {
////            Agency agency = new Agency();
////            announcementList = agency.sortAnnouncementsByDescendingPrice(announcementList);
////            return announcementList;
//            for (Agency agencyStore : agencies) {
//                announcements.addAll(agencyStore.sortAnnouncementsByDescendingPrice(agencyStore.getAnnouncementsList()));
//            }
//            return announcements;
//        }
//    }
//
//    /**
//     * Get announcements list by city.
//     *
//     * @param citySorting      the city sorting order
//     * @return the announcement list
//     */
//    public List<Announcement> getAnnouncementsByCity(String citySorting) {
//        List<Announcement> announcements = new ArrayList<>();
//        List<Agency> agencies = getAgenciesList();
//        if (citySorting.equals("Ascending")) {
////            Agency agency = new Agency();
////            announcementList = agency.sortAnnouncementsByAscendingCity(announcementList);
////            return announcementList;
//            for (Agency agencyStore : agencies) {
//                announcements.addAll(agencyStore.sortAnnouncementsByAscendingCity(agencyStore.getAnnouncementsList()));
//            }
//            return announcements;
//        } else {
////            Agency agency = new Agency();
////            announcementList = agency.sortAnnouncementsByDescendingCity(announcementList);
////            return announcementList;
//            for (Agency agencyStore : agencies) {
//                announcements.addAll(agencyStore.sortAnnouncementsByDescendingCity(agencyStore.getAnnouncementsList()));
//            }
//            return announcements;
//        }
//    }
//
//    /**
//     * Get announcements list by state.
//     *
//     * @param stateSorting     the state sorting order
//     * @return the announcement list
//     */
//    public List<Announcement> getAnnouncementsByState(String stateSorting) {
//        List<Announcement> announcements = new ArrayList<>();
//        List<Agency> agencies = getAgenciesList();
//        if (stateSorting.equals("Ascending")) {
////            Agency agency = new Agency();
////            announcementList = agency.sortAnnouncementsByAscendingState(announcementList);
////            return announcementList;
//            for (Agency agencyStore : agencies) {
//                announcements.addAll(agencyStore.sortAnnouncementsByAscendingState(agencyStore.getAnnouncementsList()));
//            }
//            return announcements;
//
//        } else {
////            Agency agency = new Agency();
////            announcementList = agency.sortAnnouncementsByDescendingState(announcementList);
////            return announcementList;
//            for (Agency agencyStore : agencies) {
//                announcements.addAll(agencyStore.sortAnnouncementsByDescendingState(agencyStore.getAnnouncementsList()));
//            }
//            return announcements;
//        }
//    }

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
    public List<BusinessType> getBusinessTypeList() {
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
    public List<PropertyType> getPropertyTypeList() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }

    /**
     * Get all announcements list.
     *
     * @return the list
     */
    public Optional<List<Announcement>> getAllAnnouncementsList() {
        Agency agency = new Agency();
        Optional<List<Announcement>> list = agencyRepository.getAllAnnouncementsList();
        if (list.isPresent() && list.get().size() > 0) {
            agency.getAnnouncements().setAnnouncements(list.get());
        }
        return Optional.of(agency.getAnnouncements().sortAnnouncementsByMostRecentAcceptanceDate());
    }


    /**
     * Gets announcements by business type.
     *
     * @param businessType     the business type
     * @param announcementList the announcement list
     * @return the announcements by business type
     */
    public List<Announcement> getAnnouncementsByBusinessType(String businessType, List<Announcement> announcementList) {
        Agency newAgency = new Agency();
        newAgency.getAnnouncements().setAnnouncements(announcementList);
        return newAgency.getAnnouncements().announcementHasBusinessType(announcementList, businessType);
    }

    /**
     * Get announcements list by property type.
     *
     * @param propertyType     the property type
     * @param announcementList the announcement list
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByPropertyType(String propertyType, List<Announcement> announcementList) {
        Agency newAgency = new Agency();
        newAgency.getAnnouncements().setAnnouncements(announcementList);
        return newAgency.getAnnouncements().announcementHasPropertyType(announcementList, propertyType);
    }

    /**
     * Get announcements list by number bedrooms.
     *
     * @param numberBedrooms   the number bedrooms
     * @param announcementList the announcement list
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByNumberBedrooms(Integer numberBedrooms, List<Announcement> announcementList) {
        Agency newAgency = new Agency();
        newAgency.getAnnouncements().setAnnouncements(announcementList);
        return newAgency.getAnnouncements().announcementHasNumberBedrooms(announcementList, numberBedrooms);
    }

    /**
     * Get announcements list by price.
     *
     * @param priceSorting     the price sorting order
     * @param announcementList the announcement list
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByPrice(String priceSorting, List<Announcement> announcementList) {
        Agency newAgency = new Agency();
        newAgency.getAnnouncements().setAnnouncements(announcementList);
        if (priceSorting.equals("Ascending")){
            return newAgency.getAnnouncements().sortAnnouncementsByAscendingPrice(announcementList);
        } else {
            return newAgency.getAnnouncements().sortAnnouncementsByDescendingPrice(announcementList);
        }
    }

    /**
     * Get announcements list by city.
     *
     * @param citySorting      the city sorting order
     * @param announcementList the announcement list
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByCity(String citySorting, List<Announcement> announcementList) {
        Agency newAgency = new Agency();
        newAgency.getAnnouncements().setAnnouncements(announcementList);
        if (citySorting.equals("Ascending")){
            return newAgency.getAnnouncements().sortAnnouncementsByAscendingCity(announcementList);
        } else {
            return newAgency.getAnnouncements().sortAnnouncementsByDescendingCity(announcementList);
        }
    }

    /**
     * Get announcements list by state.
     *
     * @param stateSorting     the state sorting order
     * @param announcementList the announcement list
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByState(String stateSorting, List<Announcement> announcementList) {
        Agency newAgency = new Agency();
        newAgency.getAnnouncements().setAnnouncements(announcementList);
        if (stateSorting.equals("Ascending")){
            return newAgency.getAnnouncements().sortAnnouncementsByAscendingState(announcementList);
        } else {
            return newAgency.getAnnouncements().sortAnnouncementsByDescendingState(announcementList);
        }
    }
}
