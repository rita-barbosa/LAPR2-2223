package pt.ipp.isep.dei.esoft.project.domain;

public class OrderDto {
    private String orderDate;
    private double orderAmount;
    private String clientEmail;
    private int id;


    public OrderDto(String orderDate, double orderAmount, String clientEmail, int id) {
        this.orderDate = orderDate;
        this.orderAmount = orderAmount;
        this.clientEmail = clientEmail;
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Order %s | Amount offered : %s ", id, orderAmount);
    }

}
