package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Visit;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type List visits controller.
 */
public class ListVisitsController {

    /**
     * The Authentication repository.
     */
    private AuthenticationRepository authenticationRepository;

    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository;

    /**
     * Instantiates a new List visits controller.
     */
    public ListVisitsController(){
        getAuthenticationRepository();
        getAgencyRepository();
    }

    /**
     * Instantiates a new List visits controller.
     *
     * @param authenticationRepository the authentication repository
     * @param agencyRepository         the agency repository
     */
    public ListVisitsController(AuthenticationRepository authenticationRepository, AgencyRepository agencyRepository){
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
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

    public Optional<List<VisitDto>> getVisitRequestsList(LocalDate beginDate, LocalDate endDate){
        Optional <List<VisitDto>> newVisitsDtoList = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Visit>> visitList = getVisitRequestsListByAgentEmail(agentEmail, beginDate, endDate);

        return newVisitsDtoList;
    }

    private Optional<List<Visit>> getVisitRequestsListByAgentEmail(String agentEmail, LocalDate beginDate, LocalDate endDate){
        Optional<List<Visit>> newList = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByEmployeeEmail(agentEmail);
        if (newAgency.isPresent()){
            newList = Optional.of(newAgency.get().getVisitRequestsByAgentEmail(agentEmail, beginDate, endDate));
        }

        return newList;
    }

    /**
     * Gets agent email.
     *
     * @return the agent email
     */
    private String getAgentEmail() {
        String agentEmail = getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return agentEmail;
    }



}
