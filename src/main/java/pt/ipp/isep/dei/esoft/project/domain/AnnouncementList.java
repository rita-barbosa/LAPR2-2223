package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * A List with the announcements of an agency.
 */
public class AnnouncementList {
    /**
     * The list with the announcements.
     */
    private List<Announcement> announcements;

    public List<Announcement> getAnnouncementsByAgentEmail(String agentEmail) {
        List<Announcement> listAnnouncements = new ArrayList<>();
        for (Announcement a : announcements) {
            if (a.hasAgentWithEmail(agentEmail)) {
                a.getOrderList().sortOrdersByHighestOrderAmount();
                listAnnouncements.add(a.clone());
            }
        }
        sortAnnouncementsByOldestAcceptanceDate(listAnnouncements);
        return listAnnouncements;
    }

    public Boolean anyAnnouncementHasId(Integer announcementId) {
        for (Announcement a : announcements) {
            if (a.hasId(announcementId)) {
                return true;
            }
        }
        return false;
    }

    public Optional<Announcement> getAnnouncementById(Integer announcementId) {
        for (Announcement a : announcements) {
            if (a.hasId(announcementId)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    private void sortAnnouncementsByOldestAcceptanceDate(List<Announcement> listAnnouncements) {
        Collections.sort(listAnnouncements, sortAnnouncementsByAcceptanceDate);
    }

    Comparator<Announcement> sortAnnouncementsByAcceptanceDate = new Comparator<Announcement>() {
        public int compare(Announcement a1, Announcement a2) {
            LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
            LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

            return a1AcceptanceDate.compareTo(a2AcceptanceDate);
        }
    };

}

