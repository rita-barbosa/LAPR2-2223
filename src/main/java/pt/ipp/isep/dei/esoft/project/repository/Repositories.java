package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
//    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
//    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
    PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
    BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
    AgencyRepository agencyRepository = new AgencyRepository();
    PersonRepository personRepository = new PersonRepository();
    CriteriaRepository criteriaRepository = new CriteriaRepository();


    private Repositories() {
    }
//    public TaskCategoryRepository getTaskCategoryRepository() {
//        return taskCategoryRepository;
//    }
//    public OrganizationRepository getOrganizationRepository() {
//        return organizationRepository;
//    }
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
    public BusinessTypeRepository getBusinessTypeRepository(){
        return businessTypeRepository;
    }
    public AgencyRepository getAgencyRepository(){
        return agencyRepository;
    }
    public PersonRepository getPersonRepository(){
        return personRepository;
    }
    public CriteriaRepository getCriteriaRepository(){
        return criteriaRepository;
    }


}
