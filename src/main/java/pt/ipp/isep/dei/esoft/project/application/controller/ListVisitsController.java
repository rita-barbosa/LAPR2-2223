package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.VisitMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

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

    /**
     * Gets visit requests list.
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the visit requests list
     * @throws IOException the io exception
     */
    public Optional<List<VisitDto>> getVisitRequestsList(LocalDate beginDate, LocalDate endDate) throws Exception {
        Optional <List<VisitDto>> newVisitsDtoList = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Visit>> visitList = getVisitRequestsListByAgentEmail(agentEmail, beginDate, endDate);
        if (visitList.isPresent()){
            newVisitsDtoList = VisitMapper.toDto(visitList.get());
        }

        return getSortedVisitRequestList(newVisitsDtoList);
//        return getSortVisitRequestList(newVisitsDtoList);
    }

    /**
     * Get visit requests list by agent email optional.
     *
     * @param agentEmail the agent email
     * @param beginDate  the begin date
     * @param endDate    the end date
     * @return the optional
     */
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


    /**
     * Gets sorted visit request list.
     *
     * @param listVisitsDto the list visits dto
     * @return the sorted visit request list
     * @throws Exception the exception
     */
    public Optional<List<VisitDto>> getSortedVisitRequestList(Optional<List<VisitDto>> listVisitsDto) throws Exception {
        ApplicationSession appSession = ApplicationSession.getInstance();
        Properties properties = appSession.getProperties();
        String sortClass = properties.getProperty("sortingAlgorithm");

        try {
            if (sortClass != null) {
                Class<?> sortAlgorithmClass = Class.forName(sortClass);
                Constructor<?> constructor = sortAlgorithmClass.getConstructor(List.class);
                if(listVisitsDto.isPresent()) {
                    SortAlgorithm sortingAlgorithm = (SortAlgorithm) constructor.newInstance(listVisitsDto.get());
                    listVisitsDto = Optional.of(sortingAlgorithm.sort(listVisitsDto.get()));
                }
            }
        }catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e){
            throw new Exception();
        }

        return listVisitsDto;
    }


//    public Optional<List<VisitDto>> getSortVisitRequestList(Optional<List<VisitDto>> listVisitsDto) throws Exception{
//        try(InputStream file = new FileInputStream("config.properties")) {
//            Properties properties = new Properties();
//            properties.load(file);
//            String sortClass = properties.getProperty("sortingAlgorithm");
//
//            if (sortClass != null){
//                Class<?> sortAlgorithmClass = Class.forName(sortClass);
//                Constructor<?> constructor = sortAlgorithmClass.getConstructor(List.class);
//                SortAlgorithm sortingAlgorithm = (SortAlgorithm) constructor.newInstance(listVisitsDto.get());
//                listVisitsDto = Optional.of(sortingAlgorithm.sort(listVisitsDto.get()));
//            }
//
//        }  catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
//                  InvocationTargetException e){
//            throw new Exception();
//        }
//
////        listVisitsDto = Optional.of(getAlgorithm().sort(listVisitsDto.get()));
//        return listVisitsDto;
//    }
}
