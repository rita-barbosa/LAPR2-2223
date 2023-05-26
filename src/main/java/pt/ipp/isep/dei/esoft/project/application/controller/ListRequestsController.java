package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.RequestDto;
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

    public List<RequestDto> getRequestsList(){
        throw new NotImplementedException();
    }

}
