package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Visit.
 */
public class Visit implements Notification {

    /**
     * The Id.
     */
    private Integer id = 0;
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
    @Override
    public Boolean sendNotification(String email) {
        String fileName = "Notifications/" + FILE_NAME + "Visit" + getId() + "." + email + FILE_TYPE;
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            PrintWriter text = new PrintWriter(file);
            text.write(TEXT_TO + email + "\n");
            text.write(TEXT_TOPIC + "Visit Request Acceptance\n");
            text.write("A client, " + this.userName + " (Phone Number: " + this.userPhoneNumber + "), submitted a new visit request for " +
                    this.getVisitDate().toString() + " from " + this.startHour + ":00 to " + this.endHour + ":00.\n");
            text.write("Please check the visit request that was assigned to you, analyse it, and accept or reject it.\n");
            text.close();
            return true;
        } catch (IOException e) {
            System.out.println("ERROR: Failed to send notification.");
            return false;
        }
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
        return Objects.equals(id, visit.id) && Objects.equals(startHour, visit.startHour) && Objects.equals(endHour, visit.endHour) && Objects.equals(visitDate, visit.visitDate) && Objects.equals(userName, visit.userName) && Objects.equals(userPhoneNumber, visit.userPhoneNumber) && Objects.equals(acceptanceStatus, visit.acceptanceStatus);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, startHour, endHour, visitDate, userName, userPhoneNumber, acceptanceStatus);
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
}
