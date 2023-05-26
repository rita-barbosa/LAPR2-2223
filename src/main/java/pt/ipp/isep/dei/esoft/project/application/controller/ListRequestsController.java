package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CommissionTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class ListRequestsController {

    private AuthenticationRepository authenticationRepository;

    private AgencyRepository agencyRepository;

    private CommissionTypeRepository commissionTypeRepository;

    public ListRequestsController(){
        getAuthenticationRepository();
        getAgencyRepository();
        getCommissionTypeRepository();

    }

    public ListRequestsController(AuthenticationRepository authenticationRepository, AgencyRepository agencyRepository, CommissionTypeRepository commissionTypeRepository){
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
        this.commissionTypeRepository = commissionTypeRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private CommissionTypeRepository getCommissionTypeRepository() {
        if (commissionTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            commissionTypeRepository = repositories.getCommissionTypeRepository();
        }
        return commissionTypeRepository;
    }

    public Optional<List<RequestDto>> getRequestsList(){
        Optional<List<RequestDto>> newListRequestsDto = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Request>> requestsList = getRequestsListByAgentEmail(agentEmail);
        if (requestsList.isPresent()) {
            newListRequestsDto = RequestMapper.toDto(requestsList.get());
        }
        return newListRequestsDto;
    }

    private Optional<List<Request>> getRequestsListByAgentEmail(String email) {
        Optional<List<Request>> newList = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByEmployeeEmail(email);
        if (newAgency.isPresent()) {
            newList = Optional.of(newAgency.get().getRequestsByAgentEmail(email));
        }
        return newList;
    }

//    public Integer getRequestByIdDto(){
//
//    }

    private String getEmailFromSession() {
        return getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
    }

    private Optional<Agency> getAgencyByEmail(String email) {
        return getAgencyRepository().getAgencyByEmployeeEmail(email);
    }

    public List<CommissionType> getCommissionTypeList() {
        CommissionTypeRepository commissionTypeRepository = getCommissionTypeRepository();
        return commissionTypeRepository.getCommissionTypeList();
    }

    private CommissionType getCommissionTypeByDesignation(String designation) {
        return getCommissionTypeRepository().getCommissionTypeByDesignation(designation).get();
    }

    private Employee getAgentByEmail(String email, Agency agency) {
        return agency.getAgentByEmail(email);
    }

    public void sendEmail(String ownerEmail, String message){
        throw new NotImplementedException();
    }

    private String getAgentEmail() {
        String email = getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return email;
    }

    public Request getRequestByIdDto(Integer requestIdDto){
        Integer id = RequestMapper.getRequestIdFromDto(requestIdDto);
        Request newRequest;
        newRequest = getRequestByIdDto(id);

        return newRequest;
    }

    private Optional<Request> getRequestFromDto(Integer requestId) {
        Optional<Request> newRequest = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByAnnouncementId(requestId);
        if (newAgency.isPresent()) {
            newRequest = newAgency.get().getRequestById(requestId);
        }
        return newRequest;
    }

    public Optional<Announcement> publishAnnouncement(String commissionTypeDesignation, Double commissionValue, Request request, String ownerEmail) {
        throw new NotImplementedException();

    }

    public String getOwnerEmail(){
        throw new NotImplementedException();
    }



}
