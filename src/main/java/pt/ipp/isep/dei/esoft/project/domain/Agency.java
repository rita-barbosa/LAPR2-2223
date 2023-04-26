package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Agency {
    private Integer id;
    private String description;
    private String emailAddress;
    private String phoneNumber;
    List<Agent> agents = new ArrayList<>();

    /**
     * Represents the list of announcements associated with the agency.
     */
    List<Announcement> announcements = new ArrayList<>();
    List<Request> requests = new ArrayList<>();


//    /**
//     * This method checks if the agency has an employee with the given email.
//     *
//     * @param email The email to be checked.
//     * @return True if the agency has an employee with the given email.
//     */
//    public boolean anyAgentHasEmail(String email) {
//        boolean result = false;
//        for (Agent agent : agents) {
//            if (agent.hasEmail(email)) {
//                result = true;
//            }
//        }
//        return result;
//    }

//    private Optional<Agent> getAgentByEmail(String emailAddress) {
//        Optional<Agent> returnAgent = Optional.empty();
//        for (Agent agent : agents) {
//            if (anyAgentHasEmail(emailAddress)) {
//                returnAgent = Optional.of(agent);
//            }
//        }
//        return returnAgent;
//    }

//    private boolean addAnnouncement(Announcement announcement) {
//        boolean success = false;
//        if (validateAnnouncement(announcement)) {
//            announcement =announcements.add(announcement.clone());
//        }
//        return success;
//    }

    public Optional<Request> createRequest(String ownerEmail, PropertyType propertyType, BusinessType businessType,
                                           double amount, double area, int contractDuration, Optional<ArrayList<AvailableEquipment>> availableEquipment,
                                           String streetName, String city, String district, String state, String zipCode,
                                           boolean basement, boolean inhabitableLoft, int parkingSpace, Optional<String> sunExposure,
                                           int numberBedroom, Optional<Integer> numberBathroom, Agent agent, double distanceCityCenter,
                                           ArrayList<Photograph> photograph) {

        //TODO: we could also check if the employee works for the organization before proceeding
        //checkIfEmployeeWorksForOrganization(employee);

        // When a Task is added, it should fail if the Task already exists in the list of Tasks.
        // In order to not return null if the operation fails, we use the Optional class.
        Optional<Request> optionalValue = Optional.empty();

        Request request = new Request(ownerEmail, propertyType, businessType, amount, area, contractDuration, availableEquipment,
                streetName, city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure,
                numberBedroom, numberBathroom, agent, distanceCityCenter, photograph);

        if (addRequest(request)) {
            optionalValue = Optional.of(request);
        }
        return optionalValue;
    }

    private boolean addRequest(Request request) {
        boolean success = false;
        if (validateRequest(request)) {
            success = requests.add(request.clone());
        }
        return success;
    }

    private boolean validateAnnouncement(Announcement announcement) {
        return !(announcements.contains(announcement));
    }

    private boolean validateRequest(Request request) {
        return !(requests.contains(request));
    }

    public Integer getId() {
        return id;
    }

    public List<Agent> getAgentList() {
        return this.agents;
    }

//    public Optional<Announcement> publishAnnouncement(Agent agent,CommissionType commissionType, Double commissionValue, Request request){
//
//        Optional<Announcement> optionalValue = Optional.empty();
//
//        Announcement announcement = new Announcement(agent,commissionType,commissionValue,request);
//
//        if(addAnnouncement(announcement)){
//            optionalValue= Optional.of(announcement);
//        }
//        return  optionalValue;
//    }


}
