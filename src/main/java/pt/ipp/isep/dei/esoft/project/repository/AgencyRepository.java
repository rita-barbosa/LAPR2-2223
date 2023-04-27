package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyRepository {
    /**
     * The list with existent agencies.
     */
    private final List<Agency> agencies = new ArrayList<>();

    /**
     * This method returns an Agency that has an Agent with the given email address.
     *
     * @param emailAddress - the email address of an Agent
     * @return the agency where the agent(that has the given email address) works in
     */
    public Optional<Agency> getAgencyByEmployeeEmail(String emailAddress){
        Optional<Agency> returnAgency = Optional.empty();
            for (Agency agency: agencies) {
                if (agency.anyAgentHasEmail(emailAddress)){
                    returnAgency = Optional.of(agency);
                }
            }
        return returnAgency;
    }
    public Optional<Agency> getAgencyByEmployee(Employee employee){
        Optional<Agency> returnAgency = Optional.empty();
        for (Agency agency: agencies) {
            if (agency.employs(employee)){
                returnAgency = Optional.of(agency);
            }
        }
        return returnAgency;
    }
    /**
     * This method adds a new agency to the list of agencies.
     *
     * @param agency - the agency to be
     * @return the agency that was added to the list
     */
//    public Optional<Agency> add(Agency agency) {
//        Optional<Agency> newAgency = Optional.empty();
//        boolean success = false;
//        if (validateAgency(agency)) {
//            newAgency = Optional.of(agency.clone());
//            success = agencies.add(newAgency.get());
//        }
//        if (!success) {
//            newAgency = Optional.empty();
//        }
//        return newAgency;
//
//    }

    public List<Agency> getAgenciesList() {
        return List.copyOf(agencies);
    }


    /**
     * This method verifies if the agency received is already present in the list of agencies.
     *
     * @param agency - the agency to be validated
     * @return {@code true} if the list of agencies does not contain the received agency; {@code false} otherwise.
     */
    private boolean validateAgency(Agency agency) {
        return (!agencies.contains(agency));
    }

    /**
     * This method goes through all the agencies in the list and finds the one whose id is the same.
     * @param id - identifier of a specific agency
     * @return agency that has that id
     * @throws IllegalArgumentException
     */
    public Agency getAgencyByID(int id) {
        Agency agencyWithId = null;
        for (Agency agency : agencies) {
            if (agency.getId() == id) {
                agencyWithId = agency;
            }
        }
        if (agencyWithId == null) {
            throw new IllegalArgumentException("Agency requested with id -" + id + "- does not exist.");
        }
        return agencyWithId;
    }
}
