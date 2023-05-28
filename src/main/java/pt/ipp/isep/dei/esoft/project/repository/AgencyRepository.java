package pt.ipp.isep.dei.esoft.project.repository;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A repository for storing and retrieving agencies.
 */
public class AgencyRepository {
    /**
     * The list with existent agencies.
     */
    private final List<Agency> agencies;

    public AgencyRepository() {
        this.agencies = new ArrayList<>();
    }

    /**
     * This method returns an Agency that has an Agent with the given email address.
     *
     * @param emailAddress - the email address of an Agent
     * @return the agency where the agent(that has the given email address) works in
     */
    public Optional<Agency> getAgencyByEmployeeEmail(String emailAddress) {
        Optional<Agency> returnAgency = Optional.empty();

        for (Agency agency : agencies) {
            if (agency.anyAgentHasEmail(emailAddress)) {
                returnAgency = Optional.of(agency);
            }
        }

        return returnAgency;
    }

    /**
     * This method returns an agency that has the specified id.
     *
     * @param id - the id of the agency we intend to get.
     * @return an Optional object of Agency, allowing the calling code to handle the possibility of null values without the need for explicit null checks.
     */
    public Optional<Agency> getAgencyByID(Integer id) {
        Optional<Agency> returnAgency = Optional.empty();
        for (Agency agency : agencies) {
            if (agency.getId() == id) {
                returnAgency = Optional.of(agency);
            }
        }
        return returnAgency;
    }

    public Optional<Agency> getAgencyByAnnouncementId(Integer announcementId) {
        Optional<Agency> newAgency = Optional.empty();
        for (Agency agency : agencies) {
            if (agency.anyAnnouncementHasId(announcementId)) {
                newAgency = Optional.of(agency);
            }
        }
        return newAgency;
    }

    public Optional<Agency> getAgencyByRequestId(Integer requestId) {
        Optional<Agency> newAgency = Optional.empty();
        for (Agency agency : agencies) {
            if (agency.anyAnnouncementHasId(requestId)) {
                newAgency = Optional.of(agency);
            }
        }
        return newAgency;
    }

    /**
     * This method adds a new agency to the list of agencies.
     *
     * @param agency - the agency to be
     * @return the agency that was added to the list
     */
    public Optional<Agency> add(Agency agency) {
        Optional<Agency> newAgency = Optional.empty();
        boolean success = false;
        if (validateAgency(agency)) {
            newAgency = Optional.of(agency.clone());
            success = agencies.add(newAgency.get());
        }
        if (!success) {
            newAgency = Optional.empty();
        }
        return newAgency;
    }

    /**
     * This method returns a copy of the list of agencies.
     *
     * @return a copy of the list of agencies.
     */
    public List<Agency> getAgenciesList() {
        return agencies;
    }

    /**
     * Get all announcements list list.
     *
     * @return the list
     */
    public List<Announcement> getAllAnnouncementsList() {
        throw new NotImplementedException();
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


    public Optional<Agency> registerAgency(LegacySystemDto dto) throws IllegalArgumentException {
        Optional<Agency> newAgency;
        int agencyId = dto.getAgencyID();
        if (!anyAgencyHasAgencyId(agencyId)) {
            newAgency = add(LegacySystemMapper.toModelAgency(dto));
        } else {
            newAgency = getAgencyByID(agencyId);
        }

        return newAgency;
    }

    private boolean anyAgencyHasAgencyId(int agencyId) {
        for (Agency agency : agencies) {
            if (agency.hasId(agencyId)) {
                return true;
            }
        }
        return false;
    }
}
