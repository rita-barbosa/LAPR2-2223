package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

import static java.awt.SystemColor.text;

/**
 * The purchase order made by the client.
 */
public class Order implements Notification {
    /**
     * The Id.
     */
    private Integer id = 0;
    /**
     * The amount of the order.
     */
    private Double orderAmount;
    /**
     * The date when the order was submitted.
     */
    private LocalDate orderDate;
    /**
     * The email of the client that submitted the order.
     */
    private Email clientEmail;
    /**
     * The acceptance decided by the agent of the order.
     */
    private String acceptanceAnswer;
    /**
     * The default value of the acceptanceAnswer.
     */
    public static final String ACCEPTANCE_ANSWER_BY_DEFAULT = "non existent";
    /**
     * The rejection answer.
     */
    public static final String REJECTION_ANSWER = "reject";
    /**
     * The id iteration variable.
     */
    private static int counter = 0;

    /**
     * Instantiates a new Order with the amount, date, and client email.
     *
     * @param orderAmount the order amount
     * @param clientEmail the client email
     */
    public Order(Double orderAmount, Email clientEmail) {
        this.orderAmount = orderAmount;
        this.orderDate = LocalDate.now();
        this.clientEmail = clientEmail;
        this.acceptanceAnswer = ACCEPTANCE_ANSWER_BY_DEFAULT;
        this.id = counter++;
    }

    /**
     * Instantiates a new Order with the id, amount, date, client email, and acceptance answer.
     *
     * @param id               the id
     * @param orderAmount      the order amount
     * @param orderDate        the order date
     * @param clientEmail      the client email
     * @param acceptanceAnswer the acceptance answer
     */
    public Order(Integer id, Double orderAmount, LocalDate orderDate, Email clientEmail, String acceptanceAnswer) {
        this.id = id;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
        this.clientEmail = clientEmail;
        this.acceptanceAnswer = acceptanceAnswer;
    }


    /**
     * Returns the amount of the order.
     *
     * @return the order amount
     */
    public Double getOrderAmount() {
        return orderAmount;
    }

    /**
     * Returns the date of the order.
     *
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Returns the client email of the order.
     *
     * @return the client email
     */
    public Email getClientEmail() {
        return clientEmail;
    }


    /**
     * Returns the id of the order.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the acceptance answer of the order.
     *
     * @return the acceptance answer
     */
    public String getAcceptanceAnswer() {
        return this.acceptanceAnswer;
    }

    /**
     * Checks if the order has the specified id.
     *
     * @param id the id to check
     * @return true if the order has the specified id, false otherwise
     */
    public Boolean hasId(Integer id) {
        return this.getId().equals(id);
    }

    /**
     * Sets the acceptance answer to the specified answer.
     *
     * @param acceptanceAnswer the acceptance answer to set
     * @return true if the notification is sent successfully, false otherwise
     */
    public Boolean setAcceptanceAnswer(String acceptanceAnswer) {
        this.acceptanceAnswer = acceptanceAnswer;
        return sendNotification(getClientEmail().toString());
    }

    /**
     * Sets the acceptance answer to the rejection value.
     *
     * @return true if the notification is sent successfully, false otherwise
     */
    public Boolean rejectOrder() {
        this.acceptanceAnswer = REJECTION_ANSWER;
        return sendNotification(getClientEmail().toString());
    }

    /**
     * Creates a file with the notification information.
     *
     * @param email the recipient's email address
     * @return true if the notification is sent successfully, false otherwise
     */
    @Override
    public Boolean sendNotification(String email) {
        String fileName = "Notifications/" + FILE_NAME + "Order" + getId() + "." + email + FILE_TYPE;
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            FileWriter text = new FileWriter(file);
            text.write(TEXT_TO + email + "\n");
            text.write(TEXT_TOPIC + "Order Acceptance\n\n");
            text.write("The purchase order submitted in " + getOrderDate().toString() + " has been analyzed. The final decision was: " + this.acceptanceAnswer);
            text.close();
            return true;
        } catch (IOException e) {
            System.out.println("ERROR: Failed to send notification.\n");
            return false;
        }
    }


    /**
     * Creates a copy of the order object.
     *
     * @return a new Order object that is a clone of this instance
     */
    public Order clone() {
        return new Order(this.getId(), this.getOrderAmount(), this.getOrderDate(), this.getClientEmail(), this.getAcceptanceAnswer());
    }

    /**
     * Checks if this order is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(orderAmount, order.orderAmount) && Objects.equals(orderDate, order.orderDate) && Objects.equals(clientEmail, order.clientEmail) && Objects.equals(acceptanceAnswer, order.acceptanceAnswer);
    }

    /**
     * Returns the hash code of the order.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, orderAmount, orderDate, clientEmail, acceptanceAnswer);
    }

}
