package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class AcceptOrdersController {
    private AgencyRepository agencyRepository;
    private AuthenticationRepository authenticationRepository;

    public AcceptOrdersController() {
        getAuthenticationRepository();
        getAgencyRepository();
    }

    public AcceptOrdersController(AgencyRepository agencyRepository, AuthenticationRepository authenticationRepository) {
        this.agencyRepository = agencyRepository;
        this.authenticationRepository = authenticationRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    public Optional<List<AnnouncementDto>> getAnnouncementsList() {
        Optional<List<AnnouncementDto>> newListAnnouncementsDto = Optional.empty();
        String agentEmail = getAgentEmail();
        Optional<List<Announcement>> listAnnouncements = getAnnouncementListByAgentEmail(agentEmail);
        if (listAnnouncements.isPresent()) {
            newListAnnouncementsDto = AnnouncementMapper.toDto(listAnnouncements.get());
        }
        return newListAnnouncementsDto;
    }

    public Boolean defineOrderAcceptance(String acceptanceAnswer, int announcementId, int orderId) {
        Optional<Announcement> newAnnouncement;
        Boolean success = false;
        newAnnouncement = getAnnouncementFromDto(announcementId);
        if (newAnnouncement.isPresent()) {
            success = newAnnouncement.get().defineOrderAcceptance(acceptanceAnswer, orderId);
        }
        return success;
    }

    private String getAgentEmail() {
        String email = getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        return email;
    }

    private Optional<List<Announcement>> getAnnouncementListByAgentEmail(String email) {
        Optional<List<Announcement>> newList = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByEmployeeEmail(email);
        if (newAgency.isPresent()) {
            newList = Optional.of(newAgency.get().getAnnouncementsByAgentEmail(email));
        }
        return newList;
    }

    private Optional<Announcement> getAnnouncementFromDto(int announcementId) {
        Optional<Announcement> newAnnouncement = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByAnnouncementId(announcementId);
        if (newAgency.isPresent()) {
            newAnnouncement = newAgency.get().getAnnouncementById(announcementId);
        }
        return newAnnouncement;
    }
}
