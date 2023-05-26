package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

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
     * Instantiates a new Schedule visit controller.
     */
    public ScheduleVisitController() {
        getAgencyRepository();
        getPersonRepository();
        getCriteriaRepository();
        getAuthenticationRepository();
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
                                   AuthenticationRepository authenticationRepository) {
        this.agencyRepository = agencyRepository;
        this.personRepository = personRepository;
        this.criteriaRepository = criteriaRepository;
        this.authenticationRepository = authenticationRepository;
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
    public List<AnnouncementDto> getAnnouncementListDto() {
        return toDto(getAllAnnouncementsList());
    }

    /**
     * Get all announcements list list.
     *
     * @return the list
     */
    public List<Announcement> getAllAnnouncementsList(){
        throw new NotImplementedException();
    }

    /**
     * Gets filtered list.
     *
     * @param criteria the criteria
     * @return the filtered list
     */
    public List<AnnouncementDto> getFilteredList(String criteria) {
        throw new NotImplementedException();
    }

    /**
     * To model announcement.
     *
     * @param announcementDto the announcement dto
     * @return the announcement
     */
    public Announcement toModel(AnnouncementDto announcementDto) {
        throw new NotImplementedException();
    }

    /**
     * To dto list.
     *
     * @param announcementList the announcement list
     * @return the list
     */
    public List<AnnouncementDto> toDto(List<Announcement> announcementList) {
        throw new NotImplementedException();
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
        Visit visit;
        if (newVisit.isPresent()) {
            visit = newVisit.get();
            String agentEmail = announcement.getAgentEmail();
            visit.sendNotification(agentEmail);
        }
        return newVisit.isPresent();
    }
}
