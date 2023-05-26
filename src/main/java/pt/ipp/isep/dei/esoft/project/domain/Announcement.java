package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an Announcement made by a agent, based on a request.
 */
public class Announcement {
    /**
     * The date of acceptance for the request
     */
    private LocalDate acceptanceDate;
    /**
     * The commission chosen by the agent associated with the announcement.
     */
    private Commission commission;
    /**
     * The request based on which the announcement is made.
     */
    private Request request;
    /**
     * The agent responsible for the announcement.
     */
    private Employee agent;

    /**
     * The identifier of an announcement.
     */
    private Integer id;
    /**
     * The OrderList that contains the orders made by clients.
     */
    private OrderList orders;

    private List<Visit> visitList;

    /**
     * The id iteration variable.
     */
    private static int counter = 0;

    /**
     * Constructs an Announcement object with the specified agent, commission type,
     * commission value, and request. Sets the acceptance date to the current date.
     *
     * @param agent           The agent responsible for the announcement
     * @param commissionType  The type of commission for the announcement
     * @param commissionValue The value of the commission for the announcement
     * @param request         The request based on which the announcement is made
     */
    public Announcement(Employee agent, CommissionType commissionType, Double commissionValue, Request request) {
        this.commission = new Commission(commissionType, commissionValue);
        this.agent = agent;
        this.request = request;
        this.acceptanceDate = LocalDate.now();
        this.id = counter++;
    }

    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, and request.
     *
     * @param agent      The agent responsible for the announcement
     * @param commission The commission for the announcement
     * @param request    The request based on which the announcement is made
     */
    public Announcement(Employee agent, Commission commission, Request request) {
        this.commission = commission;
        this.request = request;
        this.agent = agent;
        this.id = counter++;
    }


    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, request and acceptance date.
     *
     * @param agent          the agent
     * @param commission     the commission
     * @param request        the request
     * @param acceptanceDate the acceptance date
     */
    public Announcement(Employee agent, Commission commission, Request request, LocalDate acceptanceDate) {
        this.commission = commission;
        this.agent = agent;
        this.request = request;
        this.acceptanceDate = acceptanceDate;
        this.id = counter++;
    }

    /**
     * This method returns the characteristics of the commission.
     *
     * @return the request characteristics.
     */
    public String getCommissionAttributes() {
        return this.commission.toString();
    }

    /**
     * This method returns the characteristics of the request.
     *
     * @return the request characteristics.
     */
    public String getRequestAttributes() {
        return this.request.toString();
    }

    /**
     * This method returns the acceptance date of the announcement.
     *
     * @return The acceptance date of the announcement
     */
    public LocalDate getAcceptanceDate() {
        return acceptanceDate;
    }

    /**
     * This method returns the ID of the announcement.
     *
     * @return the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method returns a list of the orders made by the clients.
     *
     * @return list of orders
     */
    public List<Order> getListOfOrders() {
        return this.orders.getList();
    }

    /**
     * This method returns the announcement OrderList instance.
     *
     * @return orderList
     */
    public OrderList getOrderList() {
        return this.orders;
    }

    /**
     * This method returns the request based on which the announcement is made.
     *
     * @return The request based on which the announcement is made
     */
    public Request getRequest() {
        return request;
    }

    /**
     * This method returns a string with the agent email.
     *
     * @return agent email
     */
    public String getAgentEmail() {
        return this.agent.getEmailAddress().getEmail();
    }

    /**
     * This method checks if the agent responsible for the announcement has the specified email.
     *
     * @param agentEmail - the email to be verified
     * @return {@code true} if the agent responsible for the announcement has the specified email;{@code false} otherwise;
     */
    public Boolean hasAgentWithEmail(String agentEmail) {
        return this.getAgentEmail().equals(agentEmail);
    }

    /**
     * This method checks if the announcement has the specified id.
     *
     * @param announcementId
     * @return {@code true} if the announcement has the specified id;{@code false} otherwise;
     */
    public Boolean hasId(Integer announcementId) {
        return this.getId().equals(announcementId);
    }

    public Boolean defineOrderAcceptance(String answer, OrderDto orderDto, int announcementId) {
        int orderId = OrderMapper.getOrderId(orderDto);
        return orders.defineOrderAcceptance(answer, orderId);
    }


    /**
     * Compares this Announcement object to the specified object.
     *
     * @param o The object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Announcement)) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(acceptanceDate, that.acceptanceDate) && Objects.equals(commission, that.commission)
                && Objects.equals(request, that.request) && Objects.equals(agent, that.agent);
    }

    /**
     * Generates a hash code for this Announcement object.
     *
     * @return The hash code of the Announcement object
     */
    @Override
    public int hashCode() {
        return Objects.hash(acceptanceDate, commission, request, agent);
    }

    /**
     * Creates a new copy of this Announcement object.
     *
     * @return A new copy of this Announcement object
     */
    public Announcement clone() {
        return new Announcement(this.agent, this.commission, this.request, this.acceptanceDate);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return getRequest().toString() + String.format("Acceptance Date: %s\n", acceptanceDate);
    }

    /**
     * Get announcement info to display string. This method gets the corresponding request's property
     * and business information.
     *
     * @return the property and business information string
     */
    public String getAnnouncementInfoToDisplay() {
        return this.getRequest().toString();
    }

    /**
     * Create visit optional.
     *
     * @param visitDate       the visit date
     * @param startHour       the start hour
     * @param endHour         the end hour
     * @param userName        the user's name
     * @param userPhoneNumber the user's phone number
     * @return the visit
     */
    public Optional<Visit> createVisit(LocalDate visitDate, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        Optional<Visit> optionalValue = Optional.empty();

        Visit visit = new Visit(visitDate, startHour, endHour, userName, userPhoneNumber);

        if (addVisit(visit)) {
            optionalValue = Optional.of(visit);
        }
        return optionalValue;
    }

    /**
     * Add visit boolean.A
     *
     * @param visit the visit
     * @return the boolean
     */
    public Boolean addVisit(Visit visit) {
        if (validateVisit(visit)) {
            return false;
        } else {
            this.visitList.add(visit);
            return true;
        }
    }

    /**
     * Validate visit boolean.
     *
     * @param createdVisit the created visit
     * @return the boolean
     */
    private boolean validateVisit(Visit createdVisit) {
        for (Visit visit : this.visitList) {
            if (visit.equals(createdVisit))
                return true;
        }
        return false;
    }

}
