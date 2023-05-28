package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Schedule visit controller.
 */
public class ScheduleVisitController {

    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository = null;
    /**
     * The Person repository.
     */
    private PersonRepository personRepository = null;
    /**
     * The Criteria repository.
     */
    private CriteriaRepository criteriaRepository = null;
    /**
     * The Authentication repository.
     */
    private AuthenticationRepository authenticationRepository = null;

    /**
     * The Property type repository.
     */
    private PropertyTypeRepository propertyTypeRepository = null;
    /**
     * The Business type repository.
     */
    private BusinessTypeRepository businessTypeRepository = null;

    /**
     * Instantiates a new Schedule visit controller.
     */
    public ScheduleVisitController() {
        getAgencyRepository();
        getPersonRepository();
        getCriteriaRepository();
        getAuthenticationRepository();
        getBusinessTypeRepository();
        getPropertyTypeRepository();
    }

    /**
     * Instantiates a new Schedule visit controller.
     *
     * @param agencyRepository         the agency repository
     * @param personRepository         the person repository
     * @param criteriaRepository       the criteria repository
     * @param authenticationRepository the authentication repository
     */
    public ScheduleVisitController(AgencyRepository agencyRepository,
                                   PersonRepository personRepository,
                                   CriteriaRepository criteriaRepository,
                                   AuthenticationRepository authenticationRepository,
                                   PropertyTypeRepository propertyTypeRepository,
                                   BusinessTypeRepository businessTypeRepository) {
        this.agencyRepository = agencyRepository;
        this.personRepository = personRepository;
        this.criteriaRepository = criteriaRepository;
        this.authenticationRepository = authenticationRepository;
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
     * Gets person repository.
     *
     * @return the person repository
     */
    private PersonRepository getPersonRepository() {
        if (personRepository == null) {
            Repositories repositories = Repositories.getInstance();
            personRepository = repositories.getPersonRepository();
        }
        return personRepository;
    }

    /**
     * Gets criteria repository.
     *
     * @return the criteria repository
     */
    private CriteriaRepository getCriteriaRepository() {
        if (criteriaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            criteriaRepository = repositories.getCriteriaRepository();
        }
        return criteriaRepository;
    }

    /**
     * Gets criteria list.
     *
     * @return the criteria list
     */
    public List<String> getCriteriaList() {
        CriteriaRepository criteriaRepository = getCriteriaRepository();
        return criteriaRepository.getCriteriaList();
    }


    /**
     * Gets user person.
     *
     * @return the user person
     */
    public Optional<Person> getUserPerson() {
        String userEmail = authenticationRepository.getCurrentUserSession().getUserId().toString();
        return personRepository.getPersonByEmail(userEmail);
    }


    /**
     * Gets announcement list dto.
     *
     * @return the announcement list dto
     */
    public Optional<List<AnnouncementDto>> getAnnouncementListDto(List<Announcement> list) {
        return toDto(list);
    }

    /**
     * Get all announcements list.
     *
     * @return the list
     */
    public Optional<List<Announcement>> getAllAnnouncementsList() {
        Agency agency = new Agency();
        List<Announcement> list = agencyRepository.getAllAnnouncementsList();
        agency.getAnnouncements().setAnnouncements(list);
        return Optional.of(agency.getAnnouncements().sortAnnouncementsByMostRecentAcceptanceDate());
    }

    /**
     * To model announcement.
     *
     * @param announcementDto the announcement dto
     * @return the announcement
     */
    public Optional<Announcement> toModel(AnnouncementDto announcementDto) {
        Optional<Announcement> newAnnouncement = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByAnnouncementId(announcementDto.getAnnouncementId());
        if (newAgency.isPresent()) {
            newAnnouncement = newAgency.get().getAnnouncementById(announcementDto.getAnnouncementId());
        }
        return newAnnouncement;
    }

    /**
     * To dto list.
     *
     * @param announcementList the announcement list
     * @return the list with announcement dtos
     */
    public Optional<List<AnnouncementDto>> toDto(List<Announcement> announcementList) {
        List<AnnouncementDto> listDto = new ArrayList<>();
        for (Announcement announcement : announcementList) {
            listDto.add(AnnouncementMapper.toDto(announcement));
        }
        return Optional.of(listDto);
    }

    /**
     * Schedule visit boolean.
     *
     * @param announcement the announcement
     * @param startHour    the start hour
     * @param endHour      the end hour
     * @param visitDay     the visit day
     * @param visitMonth   the visit month
     * @param visitYear    the visit year
     * @return the boolean
     */
    public Boolean scheduleVisit(Announcement announcement, Integer startHour, Integer endHour,
                                 Integer visitDay, Integer visitMonth, Integer visitYear) {
        Optional<Person> user = getUserPerson();
        String userName = "";
        String userPhoneNumber = "";

        if (user.isPresent()) {
            userName = user.get().getName();
            userPhoneNumber = user.get().getPhoneNumber();
        }

        Optional<Visit> newVisit = announcement.createVisit(visitDay, visitMonth, visitYear, startHour, endHour, userName, userPhoneNumber);

        if (newVisit.isPresent()) {
            String agentEmail = announcement.getAgentEmail();
            newVisit.get().sendNotification(agentEmail);
        }
        return newVisit.isPresent();
    }

    /**
     * Get announcement list by business type.
     *
     * @param businessType the business type
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByBusinessType(String businessType) {
        List<Announcement> listToDisplay = new ArrayList<>();
        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            listToDisplay.addAll(agency.announcementHasBusinessType(agency.getAnnouncementsList(), businessType));
        }
        return listToDisplay;
    }

    /**
     * Get announcements list by property type.
     *
     * @param propertyType the property type
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByPropertyType(String propertyType) {
        List<Announcement> listToDisplay = new ArrayList<>();
        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            listToDisplay.addAll(agency.announcementHasPropertyType(agency.getAnnouncementsList(), propertyType));
        }
        return listToDisplay;
    }

    /**
     * Get announcements list by number bedrooms.
     *
     * @param numberBedrooms the number bedrooms
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByNumberBedrooms(Integer numberBedrooms) {
        List<Announcement> listToDisplay = new ArrayList<>();
        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            listToDisplay.addAll(agency.announcementHasNumberBedrooms(agency.getAnnouncementsList(), numberBedrooms));
        }
        return listToDisplay;
    }

    /**
     * Get announcements list by price.
     *
     * @param priceSorting the price sorting order
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByPrice(String priceSorting) {
        List<Announcement> listToDisplay = new ArrayList<>();
        List<Agency> agencies = agencyRepository.getAgenciesList();

        if (priceSorting.equals("Ascending")) {
            for (Agency agency : agencies) {
                listToDisplay.addAll(agency.sortAnnouncementsByAscendingPrice(agency.getAnnouncementsList()));
            }
        } else {
            for (Agency agency : agencies) {
                listToDisplay.addAll(agency.sortAnnouncementsByDescendingPrice(agency.getAnnouncementsList()));
            }
        }
        return listToDisplay;
    }

    /**
     * Get announcements list by city.
     *
     * @param citySorting the city sorting order
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByCity(String citySorting) {
        List<Announcement> listToDisplay = new ArrayList<>();
        List<Agency> agencies = agencyRepository.getAgenciesList();

        if (citySorting.equals("Ascending")) {
            for (Agency agency : agencies) {
                listToDisplay.addAll(agency.sortAnnouncementsByAscendingCity(agency.getAnnouncementsList()));
            }
        } else {
            for (Agency agency : agencies) {
                listToDisplay.addAll(agency.sortAnnouncementsByDescendingCity(agency.getAnnouncementsList()));
            }
        }
        return listToDisplay;
    }

    /**
     * Get announcements list by state.
     *
     * @param stateSorting the state sorting order
     * @return the announcement list
     */
    public List<Announcement> getAnnouncementsByState(String stateSorting) {
        List<Announcement> listToDisplay = new ArrayList<>();
        List<Agency> agencies = agencyRepository.getAgenciesList();

        if (stateSorting.equals("Ascending")) {
            for (Agency agency : agencies) {
                listToDisplay.addAll(agency.sortAnnouncementsByAscendingState(agency.getAnnouncementsList()));
            }
        } else {
            for (Agency agency : agencies) {
                listToDisplay.addAll(agency.sortAnnouncementsByDescendingState(agency.getAnnouncementsList()));
            }
        }
        return listToDisplay;
    }
}
