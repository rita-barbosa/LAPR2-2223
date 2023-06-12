package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * The Agency class represents a real estate agency that contains information about employees, announcements, and requests.
 */
public class Agency implements Serializable {
    /**
     * The id of the agency;
     */
    private Integer id;
    /**
     * The description of the agency;
     */
    private String description;
    /**
     * The email address of the agency;
     */
    private Email emailAddress;
    /**
     * The phone number of the agency.
     */
    private String phoneNumber;
    /**
     * The business type designation of a lease
     */
    public static final String LEASE_BUSINESSTYPE = "Lease";

    /**
     * The location of the agency.
     */
    private Location location;

    /**
     * Represents the list of employees associated with the agency.
     */
    List<Employee> employees;

    /**
     * Represents the list of announcements associated with the agency.
     */
    AnnouncementList announcements;

    //List<Request> requests;

    /**
     * Represents the list of requests associated with the agency.
     */
    RequestList requests;


    /**
     * Gets announcements.
     *
     * @return the announcements
     */
    public AnnouncementList getAnnouncements() {
        return announcements;
    }

    /**
     * Constructs a new Agency object with the specified id, description, email address, phone number and location.
     *
     * @param id           the unique identifier of the agency.
     * @param description  the description of the agency.
     * @param emailAddress the email address of the agency.
     * @param phoneNumber  the phone number of the agency.
     * @param location     the location of the agency.
     */
    public Agency(Integer id, String description, String emailAddress, String phoneNumber, Location location) {
        this.id = id;
        this.description = description;
        this.emailAddress = new Email(emailAddress);
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.employees = new ArrayList<>();
        this.announcements = new AnnouncementList();
        this.requests = new RequestList();
    }

    /**
     * Constructs a new Agency object with the specified id, description, email address, phone number and location.
     *
     * @param id           the unique identifier of the agency.
     * @param description  the description of the agency.
     * @param emailAddress the email address of the agency.
     * @param phoneNumber  the phone number of the agency.
     * @param location     the location of the agency.
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Agency(Integer id, String description, String emailAddress, String phoneNumber, String location) throws IllegalArgumentException {
        this.id = id;
        this.description = description;
        this.emailAddress = new Email(emailAddress);
        this.phoneNumber = phoneNumber;
        this.location = new Location(location);
        this.employees = new ArrayList<>();
        this.announcements = new AnnouncementList();
        this.requests = new RequestList();
    }

    /**
     * Constructs a new Agency object with the specified id.
     *
     * @param id -  the unique identifier of the agency.
     */
    public Agency(Integer id) {
        this.id = id;
        this.employees = new ArrayList<>();
        this.announcements = new AnnouncementList();
        this.requests = new RequestList();
    }

    /**
     * Instantiates a new Agency.
     */
    public Agency() {
        this.employees = new ArrayList<>();
        this.announcements = new AnnouncementList();
        this.requests = new RequestList();
    }

    /**
     * Instantiates a new Agency.
     *
     * @param description the description
     */
    public Agency(String description) {
        this.description = description;
        this.employees = new ArrayList<>();
        this.announcements = new AnnouncementList();
        this.requests = new RequestList();
    }


    /**
     * This method checks if the agency has an agent with the given email.
     *
     * @param email - the email to be checked.
     * @return {@code true} if the agency has an agent with the given email; {@code false} otherwise;
     */
    public Boolean anyAgentHasEmail(String email) {
        Boolean result = false;
        for (Employee employee : employees) {
            if (employee.hasEmail(email) && employee.isAgent()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Any announcement has id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean anyAnnouncementHasId(Integer id) {
        return announcements.anyAnnouncementHasId(id);
    }

    /**
     * Any request has id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean anyRequestHasId(Integer id) {
        return requests.anyRequestHasId(id);
    }

    /**
     * This method returns the agent that has the received email address.
     *
     * @param emailAddress - the email address that belongs to an agent.
     * @return the agent that has the received email; if there isn't an agent with that email then a null value will be returned.
     */
    public Employee getAgentByEmail(String emailAddress) {
        for (Employee employee : employees) {
            if (anyAgentHasEmail(emailAddress)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Gets announcement by id.
     *
     * @param announcementId the announcement id
     * @return the announcement by id
     */
    public Optional<Announcement> getAnnouncementById(Integer announcementId) {
        return announcements.getAnnouncementById(announcementId);
    }

    /**
     * Gets request by id.
     *
     * @param requestId the request id
     * @return the request by id
     */
    public Optional<Request> getRequestById(Integer requestId) {
        return requests.getRequestById(requestId);
    }

    /**
     * This method adds an announcement to the existent list of announcements.
     *
     * @param announcement - announcement intended to be added.
     * @return {@code true} if the announcement was successfully added; {@code false} otherwise;
     */
    public Boolean addAnnouncement(Announcement announcement) {
        return announcements.addAnnouncement(announcement);
    }

    /**
     * This method creates a new Request instance, and adds it to the list of requests already existent.
     *
     * @param ownerEmail                    the owner email
     * @param propertyType                  the property type
     * @param businessTypeDesignation       the business type designation
     * @param amount                        the amount
     * @param area                          the area
     * @param contractDuration              the contract duration
     * @param availableEquipmentDescription the available equipment description
     * @param streetName                    the street name
     * @param city                          the city
     * @param district                      the district
     * @param state                         the state
     * @param zipCode                       the zip code
     * @param basement                      the basement
     * @param inhabitableLoft               the inhabitable loft
     * @param parkingSpace                  the parking space
     * @param sunExposure                   the sun exposure
     * @param numberBedroom                 the number bedroom
     * @param numberBathroom                the number bathroom
     * @param agent                         the agent
     * @param distanceCityCenter            the distance city center
     * @param uri                           the uri
     * @return an Optional object of Request, allowing the calling code to handle the possibility of null values without the need for explicit null checks.
     */
    public Optional<Request> createRequest(String ownerEmail, PropertyType propertyType, String businessTypeDesignation, Double amount, Double area, Integer contractDuration, List<String> availableEquipmentDescription, String streetName, String city, String district, String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter, List<String> uri) {

        // When a Request is added, it should fail if the Request already exists in the list of Request.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Request> optionalValue = Optional.empty();

        Request request;

        if (businessTypeDesignation.equalsIgnoreCase(LEASE_BUSINESSTYPE)) {
            request = new Request(ownerEmail, propertyType, businessTypeDesignation, amount, area, contractDuration, availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uri);
        } else {
            request = new Request(ownerEmail, propertyType, businessTypeDesignation, amount, area, availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uri);
        }

        if (addRequest(request)) {
            optionalValue = Optional.of(request);
        }
        return optionalValue;
    }


    /**
     * This method creates a new sale Request instance, and adds it to the list of requests already existent.
     *
     * @param ownerEmail         the owner email
     * @param propertyType       the property type
     * @param businessType       the business type
     * @param amount             the amount
     * @param area               the area
     * @param availableEquipment the available equipment
     * @param streetName         the street name
     * @param city               the city
     * @param district           the district
     * @param state              the state
     * @param zipCode            the zip code
     * @param basement           the basement
     * @param inhabitableLoft    the inhabitable loft
     * @param parkingSpace       the parking space
     * @param sunExposure        the sun exposure
     * @param numberBedroom      the number bedroom
     * @param numberBathroom     the number bathroom
     * @param agent              the agent
     * @param distanceCityCenter the distance city center
     * @param photograph         the photograph
     * @return an Optional object of Request, allowing the calling code to handle the possibility of null values without the need for explicit null checks.
     */
    public Optional<Request> createSaleRequest(String ownerEmail, PropertyType propertyType, String businessType, Double amount, Double area, List<String> availableEquipment, String streetName, String city, String district, String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter, List<String> photograph) {

        Optional<Request> optionalValue = Optional.empty();
        Request request;

            request = new Request(ownerEmail, propertyType, businessType, amount, area, availableEquipment, streetName,
                    city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom,
                    numberBathroom, agent, distanceCityCenter, photograph);

        if (addRequest(request)) {
            optionalValue = Optional.of(request);
        }
        return optionalValue;
    }


    /**
     * Add request boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public Boolean addRequest(Request request) {
        return requests.addRequest(request);
    }


    /**
     * Add employee boolean.
     *
     * @param employee the employee
     * @return the boolean
     */
    public Boolean addEmployee(Employee employee) {
        Boolean success = false;
        if (validateEmployee(employee)) {
            success = employees.add(employee.clone());
        }
        return success;
    }

    /**
     * This method checks if the list of requests already contains the employee received.
     *
     * @param employee - employee intended to be checked.
     * @return {@code true} if the employee isn't in the list of employees; {@code false} otherwise;
     */
    private Boolean validateEmployee(Employee employee) {
        return employees != null && !(employees.contains(employee));
    }


    /**
     * This method returns the agency id.
     *
     * @return the agency id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method returns the agents that are associated with this agency (that are in the list of employees).
     *
     * @return the list of agents (employees with role "Agent").
     */
    public List<Employee> getAgentList() {
        List<Employee> agents = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.isAgent()) {
                agents.add(employee);
            }
        }
        return agents;
    }

    /**
     * Gets announcements by agent email.
     *
     * @param email the email
     * @return the announcements by agent email
     */
    public List<Announcement> getAnnouncementsByAgentEmail(String email) {
        return announcements.getAnnouncementsByAgentEmail(email);
    }

    /**
     * Gets requests by agent email.
     *
     * @param email the email
     * @return the requests by agent email
     */
    public List<Request> getRequestsByAgentEmail(String email) {
        return requests.getRequestsByAgentEmail(email);
    }

    /**
     * Get visit requests by agent email list.
     *
     * @param agentEmail the agent email
     * @param beginDate  the begin date
     * @param endDate    the end date
     * @return the list
     */
    public List<Visit> getVisitRequestsByAgentEmail(String agentEmail, LocalDate beginDate, LocalDate endDate){
        return announcements.getVisitRequestsByAgentEmail(agentEmail, beginDate, endDate);
    }


    /**
     * This method returns the agency description.
     *
     * @return the agency description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method creates a new Announcement instance and adds it to the already existent list of announcements.
     *
     * @param agent           - the agent that created the request and is responsible for it.
     * @param commissionType  - the commission type that the agent chose.
     * @param commissionValue - the commission value that the agent chose.
     * @param request         - the request created by the agent.
     * @return an Optional object of Announcement, allowing the calling code to handle the possibility of null values without the need for explicit null checks.
     */
    public Optional<Announcement> publishAnnouncement(Employee agent, CommissionType commissionType, Double
            commissionValue, Request request) {

        Optional<Announcement> optionalValue = Optional.empty();

        Announcement announcement = new Announcement(agent, commissionType, commissionValue, request);

        if (addAnnouncement(announcement)) {
            optionalValue = Optional.of(announcement);
        }
        return optionalValue;
    }

    /**
     * Creates a new copy of this Agency object.
     *
     * @return A new copy of this Agency object.
     */
    public Agency clone() {
        Agency clone = new Agency(this.id);
        clone.description = (this.description);
        clone.phoneNumber = (this.phoneNumber);
        clone.emailAddress = (this.emailAddress);
        clone.location = (this.location);
        if (!(employees.isEmpty())) {
            for (Employee in : this.employees) {
                clone.employees.add(in.clone());
            }
        }
        clone.announcements = this.announcements;
        clone.requests = this.requests;
        return clone;

    }

    /**
     * Compares this Agency object to the specified object.
     *
     * @param o - the object to compare to.
     * @return {@code true} if the objects are equal,{@code false} otherwise;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(id, agency.id) && Objects.equals(description, agency.description) &&
                Objects.equals(emailAddress, agency.emailAddress) && Objects.equals(phoneNumber, agency.phoneNumber)
                && Objects.equals(location, agency.location) && Objects.equals(employees, agency.employees) &&
                Objects.equals(announcements, agency.announcements) && Objects.equals(requests, agency.requests);
    }

    /**
     * The agency hashCode
     *
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, description, emailAddress, phoneNumber, location, employees, announcements, requests);
    }

    /**
     * Returns the list of announcements for this agency.
     *
     * @return the list of announcements for this agency.
     */
    public List<Announcement> getAnnouncementsList() {
        return this.announcements.getList();
    }

    /**
     * Has id boolean.
     *
     * @param agencyId the agency id
     * @return the boolean
     */
    public boolean hasId(int agencyId) {
        return (this.id == agencyId);
    }

    /**
     * Create default agent employee.
     *
     * @return the employee
     */
    public Employee createDefaultAgent() {
        String role = "agent";
        return new Employee(role);
    }

    /**
     * Gets announcement list deal data.
     *
     * @param regressionModelType the regression model type
     * @param variable            the variable
     * @return the announcement list deal data
     */
    public List<List<Double>> getAnnouncementListDealData(RegressionModelType
                                                                  regressionModelType, String variable) {
        return announcements.getAnnouncementsData(regressionModelType, variable);

    }

    /**
     * Gets deals announcement list.
     *
     * @return the deals announcement list
     */
    public List<Announcement> getDealsAnnouncementList() {
        return announcements.getDealsList();
    }
}
