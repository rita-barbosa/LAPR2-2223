package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.Objects;

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
     * Constructs an Announcement object with the specified agent, commission type,
     * commission value, and request. Sets the acceptance date to the current date.
     *
     * @param agent           The agent responsible for the announcement
     * @param commissionType  The type of commission for the announcement
     * @param commissionValue The value of commission for the announcement
     * @param request         The request based on which the announcement is made
     */
    public Announcement(Employee agent, CommissionType commissionType, Double commissionValue, Request request) {
        this.commission = new Commission(commissionType, commissionValue);
        this.agent = agent;
        this.request = request;
        this.acceptanceDate = LocalDate.now();
    }

    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, and request.
     *
     * @param agent The agent responsible for the announcement
     * @param commission     The commission for the announcement
     * @param request      The request based on which the announcement is made
     */
    public Announcement(Employee agent, Commission commission, Request request) {
        this.commission = commission;
        this.request = request;
        this.agent = agent;
    }

    public Announcement(Employee agent, Commission commission, Request request, LocalDate acceptanceDate) {
        this.commission = commission;
        this.agent = agent;
        this.request = request;
        this.acceptanceDate = acceptanceDate;
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
     * This method returns the request based on which the announcement is made.
     *
     * @return The request based on which the announcement is made
     */
    public Request getRequest() {
        return request;
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

    public String toString() {
        return getRequest().toString() + String.format("Acceptance Date: %s\n", acceptanceDate);
    }


}
