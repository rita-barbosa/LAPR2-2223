package pt.ipp.isep.dei.esoft.project.repository;

/**
 * The type Repositories.
 */
public class Repositories {

    /**
     * The constant instance.
     */
    private static final Repositories instance = new Repositories();
    /**
     * The Authentication repository.
     */
//    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
//    OrganizationRepository organizationRepository = new OrganizationRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**
     * The Commission type repository.
     */
    CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
    /**
     * The Property type repository.
     */
    PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
    /**
     * The Business type repository.
     */
    BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
    /**
     * The Agency repository.
     */
    AgencyRepository agencyRepository = new AgencyRepository();
    /**
     * The Person repository.
     */
    PersonRepository personRepository = new PersonRepository();
    /**
     * The Criteria repository.
     */
    CriteriaRepository criteriaRepository = new CriteriaRepository();


    /**
     * Instantiates a new Repositories.
     */
    private Repositories() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
//    public TaskCategoryRepository getTaskCategoryRepository() {
//        return taskCategoryRepository;
//    }
//    public OrganizationRepository getOrganizationRepository() {
//        return organizationRepository;
//    }
    public static Repositories getInstance() {
        return instance;
    }

    /**
     * Get commission type repository.
     *
     * @return the commission type repository
     */
    public CommissionTypeRepository getCommissionTypeRepository(){
        return commissionTypeRepository;
    }

    /**
     * Get property type repository.
     *
     * @return the property type repository
     */
    public PropertyTypeRepository getPropertyTypeRepository(){
        return propertyTypeRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Get business type repository.
     *
     * @return the business type repository
     */
    public BusinessTypeRepository getBusinessTypeRepository(){
        return businessTypeRepository;
    }

    /**
     * Get agency repository.
     *
     * @return the agency repository
     */
    public AgencyRepository getAgencyRepository(){
        return agencyRepository;
    }

    /**
     * Get person repository.
     *
     * @return the person repository
     */
    public PersonRepository getPersonRepository(){
        return personRepository;
    }

    /**
     * Get criteria repository.
     *
     * @return the criteria repository
     */
    public CriteriaRepository getCriteriaRepository(){
        return criteriaRepository;
    }

}
