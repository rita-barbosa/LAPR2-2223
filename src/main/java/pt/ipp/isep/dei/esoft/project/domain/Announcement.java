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
    private Request isBasedOn;
    /**
     * The agent responsible for the announcement.
     */
    private Employee responsibleFor;

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
        this.responsibleFor = agent;
        this.isBasedOn = request;
        this.acceptanceDate = LocalDate.now();
    }

    /**
     * Constructs an Announcement object with the specified responsible agent,
     * commission, and request.
     *
     * @param responsibleFor The agent responsible for the announcement
     * @param commission     The commission for the announcement
     * @param isBasedOn      The request based on which the announcement is made
     */
    public Announcement(Employee responsibleFor,Commission commission, Request isBasedOn) {
        this.commission = commission;
        this.isBasedOn = isBasedOn;
        this.responsibleFor = responsibleFor;
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
    public Request getRequest(){return isBasedOn;}

    /**
     * Compares this Announcement object to the specified object.
     *
     * @param o The object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(acceptanceDate, that.acceptanceDate) && Objects.equals(commission, that.commission)
                && Objects.equals(isBasedOn, that.isBasedOn) && Objects.equals(responsibleFor, that.responsibleFor);
    }

    /**
     * Generates a hash code for this Announcement object.
     *
     * @return The hash code of the Announcement object
     */
    @Override
    public int hashCode() {
        return Objects.hash(acceptanceDate, commission, isBasedOn, responsibleFor);
    }

    /**
     * Creates a new copy of this Announcement object.
     *
     * @return A new copy of this Announcement object
     */
    public Announcement clone(){
        return new Announcement(this.responsibleFor,this.commission,this.isBasedOn);
    }

    public String toString(){
        return getRequest().toString(); //fix
    }


}
