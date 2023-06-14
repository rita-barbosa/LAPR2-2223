package pt.ipp.isep.dei.esoft.project.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Visit.
 */
public class Visit implements Serializable {

    private static final long serialVersionUID = -6233496203157861713L;
    /**
     * The Id.
     */
    private Integer id;
    /**
     * The id iteration variable.
     */
    private static int counter = 0;
    /**
     * The Start hour.
     */
    private Integer startHour;
    /**
     * The End hour.
     */
    private Integer endHour;
    /**
     * The Visit date.
     */
    private LocalDate visitDate;
    /**
     * The User name.
     */
    private String userName;
    /**
     * The User phone number.
     */
    private String userPhoneNumber;
    /**
     * The Acceptance status.
     */
    private Boolean acceptanceStatus;

    /**
     * Instantiates a new Visit.
     *
     * @param visitDay        the visit day
     * @param visitMonth      the visit month
     * @param visitYear       the visit year
     * @param startHour       the start hour
     * @param endHour         the end hour
     * @param userName        the user's name
     * @param userPhoneNumber the user's phone number
     */
    public Visit(Integer visitDay, Integer visitMonth, Integer visitYear, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        this.id = counter++;
        this.startHour = startHour;
        this.endHour = endHour;
        this.visitDate = LocalDate.of(visitYear, visitMonth, visitDay);
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.acceptanceStatus = false;
    }

    /**
     * Gets visit date.
     *
     * @return the visit date
     */
    public LocalDate getVisitDate() {
        return this.visitDate;
    }

    /**
     * Instantiates a new Visit.
     *
     * @param visitDate       the visit date
     * @param startHour       the start hour
     * @param endHour         the end hour
     * @param userName        the user name
     * @param userPhoneNumber the user phone number
     */
    public Visit(LocalDate visitDate, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        this.id = counter++;
        this.startHour = startHour;
        this.endHour = endHour;
        this.visitDate = visitDate;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.acceptanceStatus = false;
    }

    /**
     * Gets visit id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Gets start hour.
     *
     * @return the start hour
     */
    public Integer getStartHour() {
        return this.startHour;
    }

    /**
     * Gets end hour.
     *
     * @return the end hour
     */
    public Integer getEndHour() {
        return this.endHour;
    }

    /**
     * Send notification boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public Boolean sendNotification(String email) {
        EmailNotification e = new EmailNotification();
        return e.sendNotification(email, "Visit Request Acceptance\n", getNotificationMessage());
    }

    /**
     * This method returns the email message, that will be sent to the owner.
     *
     */
    private String getNotificationMessage() {
        return String.format("A client, " + this.userName + " (Phone Number: " + this.userPhoneNumber + "), submitted a new visit request for " +
                this.getVisitDate().toString() + " from " + this.startHour + ":00 to " + this.endHour + ":00.\n" +
                "Please check the visit request that was assigned to you, analyse it, and accept or reject it.\n");
    }


    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(startHour, visit.startHour) && Objects.equals(endHour, visit.endHour) && Objects.equals(visitDate, visit.visitDate) && Objects.equals(userName, visit.userName) && Objects.equals(userPhoneNumber, visit.userPhoneNumber) && Objects.equals(acceptanceStatus, visit.acceptanceStatus);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(startHour, endHour, visitDate, userName, userPhoneNumber, acceptanceStatus);
    }


    /**
     * Clone visit.
     *
     * @return the visit
     */
    public Visit clone() {
        Visit visit = new Visit(this.getVisitDate(), this.getStartHour(), this.getEndHour(), this.getUserName(), this.getUserPhoneNumber());
        visit.id = this.id;
        return visit;
    }

    /**
     * Returns a string representation of the Visit object.
     * The string contains the visit date, start hour, end hour,
     * username and user phone number.
     *
     * @return a string representation of the Visit object.
     */
    @Override
    public String toString(){
        return String.format("Visit Date:%s\nStart Hour:%s\nEnd Hour:%s\nUser Name:%s\nUser Phone Number:%s\n\n", visitDate, startHour, endHour, userName, userPhoneNumber);
    }


    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets user phone number.
     *
     * @return the user phone number
     */
    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    /**
     * Gets acceptance status.
     *
     * @return the acceptance status
     */
    public Boolean getAcceptanceStatus(){
        return acceptanceStatus;
    }

//    private void writeObject(ObjectOutputStream opst) throws IOException {
//        opst.writeObject(this.orders.getList());
//        opst.writeInt(this.id);
//        opst.writeObject(this.agent);
//        opst.writeObject(this.request);
//        opst.writeObject(this.acceptanceDate);
//        opst.writeObject(this.commission);
//        opst.writeDouble(this.saleAmount);
//        opst.writeObject(this.saleDate);
//        opst.writeObject(this.visitList);
//        opst.writeDouble(this.SALE_AMOUNT_BY_DEFAULT);
//        opst.writeObject(this.DATE_BY_DEFAULT);
//    }
//
//
//    private void readObject(ObjectInputStream ipst) throws IOException, ClassNotFoundException {
//        this.orders = new OrderList((List<Order>) ipst.readObject());
//        this.id =  ipst.readInt();
//        this.agent = (Employee) ipst.readObject();
//        this.request = (Request) ipst.readObject();
//        this.acceptanceDate = (LocalDate) ipst.readObject();
//        this.commission = (Commission) ipst.readObject();
//        this.saleAmount =  ipst.readDouble();
//        this.saleDate = (LocalDate) ipst.readObject();
//        this.visitList = (ArrayList<Visit>) ipst.readObject();
//        this.SALE_AMOUNT_BY_DEFAULT = ipst.readDouble();
//        this.DATE_BY_DEFAULT = (LocalDate) ipst.readObject();
//    }
}
