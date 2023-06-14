package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.VisitMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public Optional<List<VisitDto>> getVisitRequestsList(LocalDate beginDate, LocalDate endDate) throws IOException {
        Optional <List<VisitDto>> newVisitsDtoList = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Visit>> visitList = getVisitRequestsListByAgentEmail(agentEmail, beginDate, endDate);
        if (visitList.isPresent()){
            newVisitsDtoList = VisitMapper.toDto(visitList.get());
        }

        try(InputStream file = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(file);
            String sortClass = properties.getProperty("sortingAlgorithm");

            if (sortClass != null){
                Class<?> sortAlgorithmClass = Class.forName(sortClass);
                Constructor<?> constructor = sortAlgorithmClass.getConstructor(List.class);
                SortAlgorithm sortingAlgorithm = (SortAlgorithm) constructor.newInstance(newVisitsDtoList.get());
                newVisitsDtoList = Optional.of(sortingAlgorithm.sort(newVisitsDtoList.get()));
            }

        }  catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                  InvocationTargetException e){
            System.out.println("\n\nTHE PATH CHOSEN IS NOT CORRECT!! \nPLEASE CORRECT IT AND RUN THE APPLICATION AGAIN!!");
            newVisitsDtoList = Optional.empty();
        }

        return newVisitsDtoList;


//            if (getAlgorithm().equals("sortingAlgorithm1")){
//                SortingAlgorithm1 sortingAlgorithm1 = new SortingAlgorithm1(newVisitsDtoList.get());
//                sortingAlgorithm1.sort(ORDER, newVisitsDtoList.get());
//                return Optional.of(sortingAlgorithm1.getSortDtoList());
////                SortingAlgorithm1 sortingAlgorithm1 = new SortingAlgorithm1();
////                sortedDtoList = sortingAlgorithm1.sort(ORDER, newVisitsDtoList.get());
////                return Optional.of(sortedDtoList);
//
//            } else {
//                SortingAlgorithm2 sortingAlgorithm2 = new SortingAlgorithm2(newVisitsDtoList.get());
//                sortingAlgorithm2.sort(ORDER, newVisitsDtoList.get());
//                return Optional.of(sortingAlgorithm2.getSortDtoList());
//
////                SortingAlgorithm2 sortingAlgorithm2 = new SortingAlgorithm2();
////                sortedDtoList = sortingAlgorithm2.sort(ORDER, newVisitsDtoList.get());
////                return Optional.of(sortedDtoList);
//            }


//        List<VisitDto> sortedDtoList = getAlgorithm().sort(ORDER, newVisitsDtoList.get());
//        return Optional.of(sortedDtoList);
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

//    public SortAlgorithm getAlgorithm() throws IOException {
//        File configFile = new File("config.properties");
//        InputStream inputStream = new FileInputStream(configFile);
//        Properties properties = new Properties();
//        properties.load(inputStream);
//        String key = properties.getProperty("sortingAlgorithm");
//        Class< ? > oClass = null;
//
//        try {
//            oClass = Class.forName(key);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        SortAlgorithm sortingAlgorithm = null;
//        try {
//            sortingAlgorithm = (SortAlgorithm) oClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return sortingAlgorithm;
//    }

//    /**
//     * Gets algorithm.
//     *
//     * @return the algorithm
//     * @throws IOException the io exception
//     */
//    public String getAlgorithm() throws IOException{
//        File configFile = new File("config.properties");
//        InputStream inputStream = new FileInputStream(configFile);
//        Properties properties = new Properties();
//        properties.load(inputStream);
//        return properties.getProperty("sortingAlgorithm");
//    }

}
