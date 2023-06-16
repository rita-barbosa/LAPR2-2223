package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.CommissionTypeDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.RequestDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.CommissionTypeMapper;
import pt.ipp.isep.dei.esoft.project.domain.mapper.RequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.CommissionTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type List requests controller.
 */
public class AcceptRequestsController {

    /**
     * The Authentication repository.
     */
    private AuthenticationRepository authenticationRepository;

    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository;

    /**
     * The Commission type repository.
     */
    private CommissionTypeRepository commissionTypeRepository;

    /**
     * Instantiates a new List requests controller.
     */
    public AcceptRequestsController() {
        getAuthenticationRepository();
        getAgencyRepository();
        getCommissionTypeRepository();

    }

    /**
     * Instantiates a new List requests controller.
     *
     * @param authenticationRepository the authentication repository
     * @param agencyRepository         the agency repository
     * @param commissionTypeRepository the commission type repository
     */
    public AcceptRequestsController(AuthenticationRepository authenticationRepository, AgencyRepository agencyRepository, CommissionTypeRepository commissionTypeRepository) {
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
        this.commissionTypeRepository = commissionTypeRepository;
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
     * Gets commission type repository.
     *
     * @return the commission type repository
     */
    private CommissionTypeRepository getCommissionTypeRepository() {
        if (commissionTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            commissionTypeRepository = repositories.getCommissionTypeRepository();
        }
        return commissionTypeRepository;
    }

    /**
     * Get requests list optional.
     *
     * @return the optional
     */
    public Optional<List<RequestDto>> getRequestsList() {
        Optional<List<RequestDto>> newListRequestsDto = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Request>> requestsList = getRequestsListByAgentEmail(agentEmail);
        if (requestsList.isPresent()) {
            newListRequestsDto = RequestMapper.toDto(requestsList.get());
        }
        return newListRequestsDto;
    }


    /**
     * Gets requests list by agent email.
     *
     * @param email the email
     * @return the requests list by agent email
     */
    private Optional<List<Request>> getRequestsListByAgentEmail(String email) {
        Optional<List<Request>> newList = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByEmployeeEmail(email);
        if (newAgency.isPresent()) {
            newList = Optional.of(newAgency.get().getRequestsByAgentEmail(email));
        }
        return newList;
    }

    /**
     * Gets email from session.
     *
     * @return the email from session
     */
    private String getEmailFromSession() {
        return getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
    }

    /**
     * Gets agency by email.
     *
     * @param email the email
     * @return the agency by email
     */
    private Optional<Agency> getAgencyByEmail(String email) {
        return getAgencyRepository().getAgencyByEmployeeEmail(email);
    }

    /**
     * Gets commission type list.
     *
     * @return the commission type list
     */
    public Optional<List<CommissionType>> getCommissionTypeList() {
        CommissionTypeRepository commissionTypeRepository = getCommissionTypeRepository();
        return Optional.of(commissionTypeRepository.getCommissionTypeList());
    }

    /**
     * Get commission type list dto optional.
     *
     * @return the optional
     */
    public Optional<List<CommissionTypeDto>> getCommissionTypeListDto(){
        Optional<List<CommissionType>> commissionTypeList = getCommissionTypeList();
        return toDto(commissionTypeList.get());
    }

    /**
     * To dto optional.
     *
     * @param commissionTypeList the commission type list
     * @return the optional
     */
    public Optional<List<CommissionTypeDto>> toDto(List<CommissionType> commissionTypeList) {
        List<CommissionTypeDto> listDto = new ArrayList<>();
        for (CommissionType commissionType : commissionTypeList) {
            listDto.add(CommissionTypeMapper.toDto(commissionType));
        }
        return Optional.of(listDto);
    }

    /**
     * Gets commission type by designation.
     *
     * @param designation the designation
     * @return the commission type by designation
     */
    private CommissionType getCommissionTypeByDesignation(String designation) {
        return getCommissionTypeRepository().getCommissionTypeByDesignation(designation).get();
    }

    /**
     * Gets agent by email.
     *
     * @param email  the email
     * @param agency the agency
     * @return the agent by email
     */
    private Employee getAgentByEmail(String email, Agency agency) {
        return agency.getAgentByEmail(email);
    }

    /**
     * Send email.
     *
     * @param request the request
     */
    public void sendEmail(Request request) {
        request.sendEmail();
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
     * Get request by id dto optional .
     *
     * @param requestIdDto the request id dto
     * @return the optional
     */
    public Optional<Request> getRequestByDtoId(RequestDto requestIdDto) {

        Integer requestId = RequestMapper.getRequestIdFromDto(requestIdDto);
        Optional<Request> newRequest;
        newRequest = getRequestFromDto(requestId);

        return newRequest;
    }

    /**
     * Gets request from dto.
     *
     * @param requestId the request id
     * @return the request from dto
     */
    private Optional<Request> getRequestFromDto(Integer requestId) {
        Optional<Request> newRequest = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByRequestId(requestId);
        if (newAgency.isPresent()) {
            newRequest = newAgency.get().getRequestById(requestId);
        }
        return newRequest;
    }

    /**
     * Publish announcement optional.
     *
     * @param commissionTypeDesignation the commission type designation
     * @param commissionValue           the commission value
     * @param request                   the request
     * @return the optional
     */
    public Boolean publishAnnouncement(String commissionTypeDesignation, Double commissionValue, Optional<Request> request) {
        Boolean success = false;
        String email = getEmailFromSession();
        Optional<Agency> agency = getAgencyByEmail(email);

        Optional<Announcement> newAnnouncement = Optional.empty();

        CommissionType commissionType = getCommissionTypeByDesignation(commissionTypeDesignation);
        if (agency.isPresent()) {
            Employee agent = getAgentByEmail(email, agency.get());
            if (request.isPresent()) {

                newAnnouncement = agency.get().publishAnnouncement(agent, commissionType, commissionValue, request.get());
                if (newAnnouncement.isPresent()) {
                    request.get().setValidationStatus(true);
                    success = true;
                }
            }
        }
        return success;
    }

    /**
     * Define justification message.
     *
     * @param message the message
     * @param request the request
     */
    public void defineJustificationMessage(String message, Request request) {
        request.defineJustificationMessage(message);
    }

}
