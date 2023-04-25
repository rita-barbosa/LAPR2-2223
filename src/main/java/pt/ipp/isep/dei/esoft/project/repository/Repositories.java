package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
    PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();

    private Repositories() {
    }
    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }
    public static Repositories getInstance() {
        return instance;
    }
    public CommissionTypeRepository getCommissionTypeRepository(){
        return commissionTypeRepository;
    }
    public PropertyTypeRepository getPropertyTypeRepository(){
        return propertyTypeRepository;
    }
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }


}
