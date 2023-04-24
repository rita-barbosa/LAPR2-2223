package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Agency {
    private Integer id;
    private String description;
    private String emailAddress;
    private String phoneNumber;
    List<Agent> agents;

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

}
