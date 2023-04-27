package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Agency {
    private final Integer id;
    private final String description;
    private final String emailAddress;
    private final String phoneNumber;
    List<Employee> employees;

    private Location location;

    /**
     * Represents the list of announcements associated with the agency.
     */
    List<Announcement> announcements;
    List<Request> requests;

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
     * This method checks if the agency has an employee with the given email.
     *
     * @param email The email to be checked.
     * @return True if the agency has an employee with the given email.
     */
    public Boolean anyAgentHasEmail(String email) {
        Boolean result = false;
        for (Employee employee : employees) {
            if (employee.hasEmail(email)) {
                result = true;
            }
        }
        return result;
    }

    public Employee getAgentByEmail(String emailAddress) {
        Employee returnEmployee = null;
        for (Employee employee : employees) {
            if (anyAgentHasEmail(emailAddress)) {
                returnEmployee = employee;
            }
        }
        return returnEmployee;
    }

    private Boolean addAnnouncement(Announcement announcement) {
        Boolean success = false;
        if (validateAnnouncement(announcement)) {
            announcements.add(announcement.clone());
        }
        return success;
    }

    public Optional<Request> createRequest(String ownerEmail, PropertyType propertyType, BusinessType businessType,
                                           Double amount, Double area, Integer contractDuration, ArrayList<AvailableEquipment> availableEquipment,
                                           String streetName, String city, String district, String state, String zipCode,
                                           Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                                           Integer numberBedroom, Integer numberBathroom, Agent agent, Double distanceCityCenter,
                                           ArrayList<Photograph> photograph) {

        //TODO: we could also check if the employee works for the agency before proceeding
        //checkIfEmployeeWorksForAgency(employee);

        // When a Request is added, it should fail if the Request already exists in the list of Request.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Request> optionalValue = Optional.empty();

        Request request;

        if (businessType.toString().equals("Lease")) {
            request = new Request(ownerEmail, propertyType, businessType, amount, area, contractDuration, availableEquipment,
                    streetName, city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure,
                    numberBedroom, numberBathroom, agent, distanceCityCenter, photograph);
        } else {
            request = new Request(ownerEmail, propertyType, businessType, amount, area, availableEquipment, streetName,
                    city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom,
                    numberBathroom, agent, distanceCityCenter, photograph);
        }

        if (addRequest(request)) {
            optionalValue = Optional.of(request);
        }
        return optionalValue;
    }

    public Optional<Request> createSaleRequest(String ownerEmail, PropertyType propertyType, String businessType,
                                               Double amount, Double area, List<String> availableEquipment,
                                               String streetName, String city, String district, String state, String zipCode,
                                               Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                                               Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter,
                                               List<String> photograph) {

        Optional<Request> optionalValue = Optional.empty();
        Request request;
//        request = new Request(ownerEmail, propertyType, businessType, amount, area, availableEquipment, streetName,
//                city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom,
//                numberBathroom, agent, distanceCityCenter, photograph);
//
//        if (addRequest(request)) {
//            optionalValue = Optional.of(request);
//        }
        return optionalValue;
    }

    private Boolean addRequest(Request request) {
        Boolean success = false;
        if (validateRequest(request)) {
            success = requests.add(request.clone());
        }
        return success;
    }

    private Boolean validateAnnouncement(Announcement announcement) {
        return !(announcements.contains(announcement));
    }

    private Boolean validateRequest(Request request) {
        return !(requests.contains(request));
    }

    public Integer getId() {
        return id;
    }

//    public List<Employee> getAgentList() {
//        List<Employee> agents = new ArrayList<>();
//        for (Employee employee : employees) {
//            if (employee.isAgent()) {
//                agents.add(employee);
//            }
//        }
//        return agents;
//    }

    public String getDescription() {
        return description;
    }

    public Optional<Announcement> publishAnnouncement(Employee agent, CommissionType commissionType, Double commissionValue, Request request) {

        Optional<Announcement> optionalValue = Optional.empty();

        Announcement announcement = new Announcement(agent, commissionType, commissionValue, request);

        if (addAnnouncement(announcement)) {
            optionalValue = Optional.of(announcement);
        }
        return optionalValue;
    }


}
