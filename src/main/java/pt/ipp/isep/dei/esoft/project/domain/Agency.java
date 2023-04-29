package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * The Agency class represents a real estate agency that contains information about employees, announcements, and requests.
 */
public class Agency {
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
    private String emailAddress;
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
    List<Announcement> announcements;

    /**
     * Represents the list of requests associated with the agency.
     */
    List<Request> requests;

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
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.employees = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    /**
     * Constructs a new Agency object with the specified id.
     *
     * @param id -  the unique identifier of the agency.
     */
    public Agency(Integer id) {
        this.id = id;
        this.employees = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public Agency() {
        this.employees = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.requests = new ArrayList<>();
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
     * This method returns the agent that has the received email address.
     *
     * @param emailAddress - the email address that belongs to an agent.
     * @return the agent that has the received email; if there isn't an agent with that email then a null value will be returned.
     */
    public Employee getAgentByEmail(String emailAddress) {
        Employee returnEmployee = null;
        for (Employee employee : employees) {
            if (anyAgentHasEmail(emailAddress)) {
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    /**
     * This method checks if the agency has any Employee with the specified email.
     *
     * @param email - the employee email address.
     * @return {@code true} if the belongs to the agency; {@code false} otherwise;
     */


    /**
     * This method adds an announcement to the existent list of announcements.
     *
     * @param announcement - announcement intended to be added.
     * @return {@code true} if the announcement was successfully added; {@code false} otherwise;
     */
    private Boolean addAnnouncement(Announcement announcement) {
        Boolean success = false;
        if (validateAnnouncement(announcement)) {
            success = announcements.add(announcement.clone());
        }
        return success;
    }

    /**
     * This method creates a new Request instance, and adds it to the list of requests already existent.
     *
     * @param ownerEmail
     * @param propertyType
     * @param businessTypeDesignation
     * @param amount
     * @param area
     * @param contractDuration
     * @param availableEquipmentDescription
     * @param streetName
     * @param city
     * @param district
     * @param state
     * @param zipCode
     * @param basement
     * @param inhabitableLoft
     * @param parkingSpace
     * @param sunExposure
     * @param numberBedroom
     * @param numberBathroom
     * @param agent
     * @param distanceCityCenter
     * @param uri
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
     * @param ownerEmail
     * @param propertyType
     * @param businessType
     * @param amount
     * @param area
     * @param availableEquipment
     * @param streetName
     * @param city
     * @param district
     * @param state
     * @param zipCode
     * @param basement
     * @param inhabitableLoft
     * @param parkingSpace
     * @param sunExposure
     * @param numberBedroom
     * @param numberBathroom
     * @param agent
     * @param distanceCityCenter
     * @param photograph
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
     * This method adds a request to the existent list of announcements.
     *
     * @param request - request intended to be added.
     * @return {@code true} if the request was successfully added; {@code false} otherwise;
     */
    private Boolean addRequest(Request request) {
        Boolean success = false;
        if (validateRequest(request)) {
            success = requests.add(request.clone());
        }
        return success;
    }

    /**
     * This method checks if the list of announcements already contains the announcement received.
     *
     * @param announcement - announcement intended to be checked.
     * @return {@code true} if the announcement isn't in the list of announcements; {@code false} otherwise;
     */
    private Boolean validateAnnouncement(Announcement announcement) {
        return announcements != null && !(announcements.contains(announcement));
    }

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
     * This method checks if the list of requests already contains the request received.
     *
     * @param request - request intended to be checked.
     * @return {@code true} if the request isn't in the list of requests; {@code false} otherwise;
     */
    private Boolean validateRequest(Request request) {
        return requests != null & !(requests.contains(request));
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
    public Optional<Announcement> publishAnnouncement(Employee agent, CommissionType commissionType, Double commissionValue, Request request) {

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
        if (!(announcements.isEmpty())) {
            for (Announcement in :
                    this.announcements) {
                clone.announcements.add(in.clone());
            }
        }
        if (!(requests.isEmpty())) {
            for (Request in :
                    this.requests) {
                clone.requests.add(in.clone());
            }
        }
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agency)) {
            return false;
        }
        Agency that = (Agency) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(location, that.location) &&
                Objects.equals(employees, that.employees) &&
                Objects.equals(announcements, that.announcements) &&
                Objects.equals(requests, that.requests);
    }

    /**
     * Generates a hash code for this Agency object.
     *
     * @return The hash code of the Agency object.
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
        return this.announcements;
    }

    public List<Announcement> announcementHasBusinessType(List<Announcement> announcementList, String businessType) {
        for (Announcement announcement : announcementList) {
            if (!(announcement.getRequest().getBusiness().getBusinessType().equals(businessType))) {
                announcementList.remove(announcement);
            }
        }
        return announcementList;
    }

    public List<Announcement> announcementHasPropertyType(List<Announcement> announcementList, String propertyType) {
        for (Announcement announcement : announcementList) {
            if (!(announcement.getRequest().getProperty().getPropertyType().equals(propertyType))) {
                announcementList.remove(announcement);
            }
        }
        return announcementList;
    }

    public List<Announcement> announcementHasNumberBedrooms(List<Announcement> announcementList, Integer numberBedrooms) {
//        for (Announcement announcement : announcementList) {
//            if (!(announcement.getRequest().getProperty().getPropertyType().getNumberBedrooms().equals(numberBedrooms))){
//                announcementList.remove(announcement);
//            }
//        }
//        return announcementList;
        return announcementList;
    }

    public List<Announcement> sortAnnouncementsByAscendingPrice(List<Announcement> announcementList) {
//        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
//        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
//            public int compare(Announcement a1, Announcement a2){
//                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
//                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();
//
//                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
//            }
//        };
//
//        Collections.sort(clonedAnnouncementList);
//        return clonedAnnouncementList;
        return announcementList;
    }

    public List<Announcement> sortAnnouncementsByDescendingPrice(List<Announcement> announcementList) {
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);

        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2) {
                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
            }
        };

        Collections.sort(clonedAnnouncementList, Collections.reverseOrder(acceptanceDate));
        return clonedAnnouncementList;

    }

    public List<Announcement> sortAnnouncementsByAscendingCity(List<Announcement> announcementList) {
//        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
//        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
//            public int compare(Announcement a1, Announcement a2){
//                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
//                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();
//
//                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
//            }
//        };
//
//        Collections.sort(clonedAnnouncementList);
//        return clonedAnnouncementList;
        return announcementList;
    }

    public List<Announcement> sortAnnouncementsByDescendingCity(List<Announcement> announcementList) {
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2) {
                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
            }
        };

        Collections.sort(clonedAnnouncementList, Collections.reverseOrder(acceptanceDate));
        return clonedAnnouncementList;
    }

    public List<Announcement> sortAnnouncementsByAscendingState(List<Announcement> announcementList) {
//        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
//        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
//            public int compare(Announcement a1, Announcement a2){
//                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
//                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();
//
//                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
//            }
//        };
//
//        Collections.sort(clonedAnnouncementList);
//        return clonedAnnouncementList;
        return announcementList;
    }

    public List<Announcement> sortAnnouncementsByDesscendingState(List<Announcement> announcementList) {
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2) {
                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
            }
        };

        Collections.sort(clonedAnnouncementList, Collections.reverseOrder(acceptanceDate));
        return clonedAnnouncementList;
    }


}
