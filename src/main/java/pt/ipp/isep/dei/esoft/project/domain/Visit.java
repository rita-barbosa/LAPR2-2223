package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * The type Visit.
 */
public class Visit implements Notification {

    /**
     * The Id.
     */
    private Integer id;
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
     * @param id              the id
     * @param startHour       the start hour
     * @param endHour         the end hour
     * @param visitDate       the visit date
     * @param userName        the user name
     * @param userPhoneNumber the user phone number
     */
    public Visit(Integer id, Integer startHour, Integer endHour, LocalDate visitDate, String userName, String userPhoneNumber) {
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.visitDate = visitDate;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return this.id.toString();
    }

    /**
     * Gets start hour.
     *
     * @return the start hour
     */
    public String getStartHour() {
        return this.startHour.toString();
    }

    /**
     * Gets end hour.
     *
     * @return the end hour
     */
    public String getEndHour() {
        return this.endHour.toString();
    }

    /**
     * Gets visit date.
     *
     * @return the visit date
     */
    public String getVisitDate() {
        return this.visitDate.toString();
    }

    /**
     * Send notification boolean.
     *
     * @param email the email
     * @return the boolean
     */
    @Override
    public Boolean sendNotification(String email) {
        File file = new File(FILE_NAME + getId() + "." + email + FILE_TYPE);
        try {
            PrintWriter text = new PrintWriter(file);
            text.write(TEXT_TO + email+"\n");
            text.write(TEXT_TOPIC + "Visit Request Acceptance\n");
            text.write("A client, " + this.userName + " (" + this.userPhoneNumber + "), submitted a new visit request for " +
                    this.visitDate + ", beginning at " + this.startHour + " and ending at " + this.endHour + ".\n");
            text.write("Please check the visit request that was assigned to you, analyse it, and accept or reject it.\n");
            text.close();
            return true;
        } catch (IOException e) {
            System.out.println("ERROR: Failed to send notification.");
            return false;
        }
    }
}
