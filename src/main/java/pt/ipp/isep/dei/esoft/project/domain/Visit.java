package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Visit implements Notification {

    private Integer id;
    private Integer startHour;
    private Integer endHour;
    private LocalDate visitDate;
    private String userName;
    private String userPhoneNumber;
    private Boolean acceptanceStatus;

    public Visit(Integer id, Integer startHour, Integer endHour, LocalDate visitDate, String userName, String userPhoneNumber) {
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.visitDate = visitDate;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getId() {
        return this.id.toString();
    }

    public String getStartHour() {
        return this.startHour.toString();
    }

    public String getEndHour() {
        return this.endHour.toString();
    }

    public String getVisitDate() {
        return this.visitDate.toString();
    }

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
