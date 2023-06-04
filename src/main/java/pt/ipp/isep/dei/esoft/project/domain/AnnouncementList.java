package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.NotImplementedException;

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

    public AnnouncementList() {
        this.announcements = new ArrayList<>();
    }

    /**
     * Returns the list of announcements for this agency.
     *
     * @return the list of announcements for this agency.
     */
    public List<Announcement> getList() {
        return announcements;
    }

    /**
     * This method adds an announcement to the existent list of announcements.
     *
     * @param announcement - announcement intended to be added.
     * @return {@code true} if the announcement was successfully added; {@code false} otherwise;
     */
    public Boolean addAnnouncement(Announcement announcement) {
        Boolean success = false;
        if (validateAnnouncement(announcement)) {
            announcements.add(announcement.clone());
            success = announcement.sendSMS(announcement);
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

    /**
     * This method returns the announcements that the agent with the specified email is responsible for.
     *
     * @param agentEmail - email of the agent
     * @return list of the agent's announcements.
     */
    public List<Announcement> getAnnouncementsByAgentEmail(String agentEmail) {
        List<Announcement> listAnnouncements = new ArrayList<>();

        for (Announcement a : announcements) {
            if (a.hasAgentWithEmail(agentEmail)) {
                Optional<OrderList> newOrderList = a.getOrderList();
                if (newOrderList.isPresent()) {
                    newOrderList.get().sortOrdersByHighestOrderAmount();
                }
                listAnnouncements.add(a.clone());
            }
        }
        sortAnnouncementsByOldestAcceptanceDate(listAnnouncements);
        return listAnnouncements;
    }

    /**
     * This method checks if any announcement in the list has the specified ID.
     *
     * @param announcementId - the id
     * @return {@code true} if there is any announcement with the specified ID;{@code false} otherwise;
     */
    public Boolean anyAnnouncementHasId(Integer announcementId) {
        for (Announcement a : announcements) {
            if (a.hasId(announcementId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the announcement that has the specified ID.
     *
     * @param announcementId - the id.
     * @return an announcement that has the received ID.
     */
    public Optional<Announcement> getAnnouncementById(Integer announcementId) {
        for (Announcement a : announcements) {
            if (a.hasId(announcementId)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    /**
     * This method sorts the list of announcements by oldest acceptance date.
     *
     * @param listAnnouncements
     */
    private void sortAnnouncementsByOldestAcceptanceDate(List<Announcement> listAnnouncements) {
        Collections.sort(listAnnouncements, acceptanceDate);
    }

    /**
     * This method sorts the list of announcements by most recent acceptance date.
     */
    public List<Announcement> sortAnnouncementsByMostRecentAcceptanceDate() {
        List<Announcement> sortedList = new ArrayList<>(this.announcements);
        sortedList.sort(Comparator.comparing(Announcement::getAcceptanceDate));
        Collections.reverse(sortedList);
        return sortedList;
    }

    /**
     * Compares the announcements acceptance date.
     */
    Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
        public int compare(Announcement a1, Announcement a2) {
            LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
            LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

            return a1AcceptanceDate.compareTo(a2AcceptanceDate);
        }
    };

    /**
     * This method creates a new copy of an announcementList object.
     *
     * @return a copy of the announcementList object.
     */
    public AnnouncementList clone() {
        AnnouncementList clone = new AnnouncementList();
        if (!(announcements.isEmpty())) {
            for (Announcement in :
                    this.announcements) {
                clone.announcements.add(in.clone());
            }
        }
        return clone;
    }

    /**
     * This method compares this AnnouncementList object to the specified object.
     *
     * @param o - the object to compare to.
     * @return {@code true} if the objects are equal,{@code false} otherwise;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementList that = (AnnouncementList) o;
        return Objects.equals(announcements, that.announcements);
    }

    /**
     * Generates a hash code for this AnnouncementList object.
     *
     * @return The hash code of the AnnouncementList object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(announcements);
    }

    /**
     * Sets announcements.
     *
     * @param announcements the announcements
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }
}
