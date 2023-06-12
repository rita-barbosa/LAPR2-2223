package pt.ipp.isep.dei.esoft.project.domain.dto;

import java.util.Objects;

/**
 * Represents a Data Transfer Object (DTO) for an order.
 * This class encapsulates the order information and provides getter methods to access the data.
 */
public class OrderDto {
    /**
     * The order date.
     */
    private String orderDate;
    /**
     * The Order amount.
     */
    private double orderAmount;
    /**
     * The Client email.
     */
    private String clientEmail;
    /**
     * The Id.
     */
    private int id;

    /**
     * Constructs an OrderDto object with the specified order information.
     *
     * @param orderDate    the date of the order
     * @param orderAmount  the amount of the order
     * @param clientEmail  the email of the client
     * @param id           the ID of the order
     */
    public OrderDto(String orderDate, double orderAmount, String clientEmail, int id) {
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
        this.clientEmail = clientEmail;
        this.id = id;
    }
    /**
     * Returns the date of the order.
     *
     * @return the order date
     */
    public String getOrderDate() {
        return orderDate;
    }


    /**
     * Returns the amount of the order.
     *
     * @return the order amount
     */
    public double getOrderAmount() {
        return orderAmount;
    }

    /**
     * Returns the email of the client.
     *
     * @return the client email
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * Returns the ID of the order.
     *
     * @return the order ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of the OrderDto object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("Order | Amount offered : %s ", orderAmount);
    }

    /**
     * Checks if this OrderDto object is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Double.compare(orderDto.orderAmount, orderAmount) == 0 && id == orderDto.id && Objects.equals(orderDate, orderDto.orderDate) && Objects.equals(clientEmail, orderDto.clientEmail);
    }

    /**
     * Returns the hash code value for the OrderDto object.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderDate, orderAmount, clientEmail, id);
    }
}
