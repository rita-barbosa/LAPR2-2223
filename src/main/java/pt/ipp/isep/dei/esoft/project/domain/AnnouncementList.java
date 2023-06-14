package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * A List with the announcements of an agency.
 */
public class AnnouncementList implements Serializable {

    /**
     * The list with the announcements.
     */
    private List<Announcement> announcements;

    public AnnouncementList() {
        this.announcements = new ArrayList<>();
    }

    public AnnouncementList(List<Announcement> announcement) {
        this.announcements = announcement;
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
            announcement.getRequest().setValidationStatus(true);
            if (!announcement.isDeal()) {
                success = announcement.sendSMS();
            } else {
                success = true;
            }
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
            if (!a.isDeal() && a.hasAgentWithEmail(agentEmail)) {
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

    public List<Visit> getVisitRequestsByAgentEmail(String agentEmail, LocalDate beginDate, LocalDate endDate) {
        List<Visit> visitsList = new ArrayList<>();

        for (Announcement announcement : announcements) {
            System.out.println("Announcement: ENTERED LIST");
            if (announcement.hasAgentWithEmail(agentEmail)) { //falha aqui
                if (!announcement.isDeal()) {
                    System.out.println("Announcement: IS DEAL");
                    List<Visit> copyVisitList = announcement.getVisitList();
                    if (copyVisitList.isEmpty()) {
                        System.out.println("ANNOUNCEMETN:  NO VISITS");

                    }
                    for (Visit visit : copyVisitList) {
                        System.out.println("Announcement: " + visit.toString());
                        LocalDate visitDate = visit.getVisitDate();
                        if ((visitDate.isEqual(beginDate) || visitDate.isAfter(beginDate)) && (visitDate.isEqual(endDate) || visitDate.isBefore(endDate))) {
                            visitsList.add(visit.clone());
                        }
                    }
                }
            }
        }
        return visitsList;
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
     * Sort announcements by most recent sale date list.
     *
     * @return the list
     */
    public List<Announcement> sortAnnouncementsByMostRecentSaleDate() {
        this.announcements.sort(Comparator.comparing(Announcement::getSaleDate));
        Collections.reverse(this.announcements);
        return this.announcements;
    }

    /**
     * This method sorts the list of announcements by oldest sale date.
     *
     * @param listAnnouncements
     */
    private void sortAnnouncementsByOldestSaleDate(List<Announcement> listAnnouncements) {
        Collections.sort(listAnnouncements, saleDate);
    }

    Comparator<Announcement> saleDate = new Comparator<Announcement>() {
        public int compare(Announcement a1, Announcement a2) {
            LocalDate a1SaleDate = a1.getSaleDate();
            LocalDate a2SaleDate = a2.getSaleDate();
            return a1SaleDate.compareTo(a2SaleDate);
        }
    };


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

    /**
     * Returns all the deals data of an agency necessary for the regression model.
     *
     * @param regressionModelType - the type of regression model
     * @param variable            - the variable of the wanted data if the regression model type is simple linear.
     * @return a list with all the deals data of an agency.
     */
    public List<List<Double>> getAnnouncementsData(RegressionModelType regressionModelType, String variable) {
        List<List<Double>> dataList = new ArrayList<>();

        for (Announcement announcement : announcements) {
            if (announcement.validForAnalysis()) {
                dataList.add(announcement.getDataForRegression(regressionModelType, variable));
            }
        }
        return dataList;
    }

    /**
     * Gets deals list.
     *
     * @return the deals list
     */
    public List<Announcement> getDealsList() {
        List<Announcement> dealsList = new ArrayList<>();
        for (Announcement announcement : announcements) {
            if (announcement.isDeal()) {
                dealsList.add(announcement);
            }
        }
        return dealsList;
    }

    /**
     * Gets non deals list.
     *
     * @return the non deals list
     */
    public List<Announcement> getNonDealsList() {
        List<Announcement> dealsList = new ArrayList<>();
        for (Announcement announcement : announcements) {
            if (!announcement.isDeal()) {
                dealsList.add(announcement);
            }
        }
        return dealsList;
    }

    /**
     * Checks if an announcement has a business type.
     *
     * @param announcementList the announcement list
     * @param businessType     the business type
     * @return the list
     */
    public List<Announcement> announcementHasBusinessType(List<Announcement> announcementList, String businessType) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        for (Announcement announcement : announcementList) {
            if (!(announcement.getRequest().getBusiness().getBusinessType().getDesignation().equalsIgnoreCase((businessType)))) {
                copyList.remove(announcement);
            }
        }
        return copyList;
    }

    /**
     * Checks if an announcement has a specified a property type.
     *
     * @param announcementList the announcement list
     * @param propertyType     the property type
     * @return the list
     */
    public List<Announcement> announcementHasPropertyType(List<Announcement> announcementList, String propertyType) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        for (Announcement announcement : announcementList) {
            if (!((announcement.getRequest().getProperty().getPropertyType().getDesignation()).equalsIgnoreCase(propertyType))) {
                copyList.remove(announcement);
            }
        }
        return copyList;
    }

    /**
     * Checks if  an announcement has a specified number of bedrooms.
     *
     * @param announcementList the announcement list
     * @param numberBedrooms   the number bedrooms
     * @return the list
     */
    public List<Announcement> announcementHasNumberBedrooms(List<Announcement> announcementList, Integer numberBedrooms) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        for (Announcement announcement : announcementList) {
            if (announcement.getRequest().getProperty() instanceof Residence) {
                Residence residence = (Residence) announcement.getRequest().getProperty();
                if (!(residence.getNumberBedroom().equals(numberBedrooms))) {
                    copyList.remove(announcement);
                }
            } else {
                copyList.remove(announcement);
            }
        }
        return copyList;
    }

    /**
     * Sort announcements list by ascending price.
     *
     * @param announcementList the announcement list.
     * @return the sorted list.
     */
    public List<Announcement> sortAnnouncementsByAscendingPrice(List<Announcement> announcementList) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        copyList.sort(sortPricesByAscendingOrder);
        return copyList;

    }

    /**
     * Sort announcements list by descending price.
     *
     * @param announcementList the announcement list.
     * @return the sorted list.
     */
    public List<Announcement> sortAnnouncementsByDescendingPrice(List<Announcement> announcementList) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        copyList.sort(Collections.reverseOrder(sortPricesByAscendingOrder));
        return copyList;

    }

    /**
     * Sort announcements list by city in ascending alphabetic order.
     *
     * @param announcementList the announcement list.
     * @return the sorted list.
     */
    public List<Announcement> sortAnnouncementsByAscendingCity(List<Announcement> announcementList) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        copyList.sort(sortCitiesByAlphabeticOrder);
        return copyList;
    }

    /**
     * Sort announcements list by city in descending alphabetic order.
     *
     * @param announcementList the announcement list.
     * @return the sorted list.
     */
    public List<Announcement> sortAnnouncementsByDescendingCity(List<Announcement> announcementList) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        copyList.sort(Collections.reverseOrder(sortCitiesByAlphabeticOrder));
        return copyList;
    }

    /**
     * Sort announcements list by state in ascending alphabetic order.
     *
     * @param announcementList the announcement list.
     * @return the sorted list.
     */
    public List<Announcement> sortAnnouncementsByAscendingState(List<Announcement> announcementList) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        copyList.sort(sortStatesByAlphabeticOrder);
        return copyList;

    }

    /**
     * Sort announcements list by state in descending alphabetic order.
     *
     * @param announcementList the announcement list.
     * @return the sorted list.
     */
    public List<Announcement> sortAnnouncementsByDescendingState(List<Announcement> announcementList) {
        List<Announcement> copyList = new ArrayList<>(announcementList);
        copyList.sort(Collections.reverseOrder(sortStatesByAlphabeticOrder));
        return copyList;
    }

    /**
     * Comparator that sorts prices by ascending order.
     */
    Comparator<Announcement> sortPricesByAscendingOrder = new Comparator<Announcement>() {
        public int compare(Announcement a1, Announcement a2) {
            Double value1 = a1.getRequest().getBusiness().getPrice();
            Double value2 = a2.getRequest().getBusiness().getPrice();

            return value1.compareTo(value2);
        }
    };

    /**
     * Comparator that sorts cities by ascending alphabetic order.
     */
    Comparator<Announcement> sortCitiesByAlphabeticOrder = new Comparator<Announcement>() {
        public int compare(Announcement a1, Announcement a2) {
            String value1 = a1.getRequest().getProperty().getLocation().getCity();
            String value2 = a2.getRequest().getProperty().getLocation().getCity();

            return value1.compareTo(value2);
        }
    };

    /**
     * Comparator that sorts cities by descending alphabetic order.
     */
    Comparator<Announcement> sortStatesByAlphabeticOrder = new Comparator<Announcement>() {
        public int compare(Announcement a1, Announcement a2) {
            String value1 = a1.getRequest().getProperty().getLocation().getState();
            String value2 = a2.getRequest().getProperty().getLocation().getState();

            return value1.compareTo(value2);
        }
    };

    /**
     * Gets deals number.
     *
     * @return the deals number
     */
    public int getDealsNumber() {
        int dealsNumber = 0;
        for (Announcement announcement : announcements) {
            if (announcement.isDeal()) {
                dealsNumber++;
            }
        }
        return dealsNumber;
    }


}
