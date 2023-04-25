package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class PublishAnnouncementController {
    private CommissionTypeRepository commissionTypeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;


    public PublishAnnouncementController() {
        getCommissionTypeRepository();
        getPropertyTypeRepository();
        getAuthenticationRepository();
    }
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }
    private CommissionTypeRepository getCommissionTypeRepository() {
        if (commissionTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            commissionTypeRepository = repositories.getCommissionTypeRepository();
        }
        return commissionTypeRepository;
    }
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
    public List<CommissionType> getCommissionTypeList() {
        CommissionTypeRepository commissionTypeRepository = getCommissionTypeRepository();
        return commissionTypeRepository.getCommissionTypeList();
    }
    public List<PropertyType> getPropertyTypeList(){
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypeList();
    }
}
