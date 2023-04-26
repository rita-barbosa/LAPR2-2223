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

    private boolean validateAnnouncement(Announcement announcement) {
        return !(announcements.contains(announcement));
    }

    public Integer getId() {
        return id;
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
