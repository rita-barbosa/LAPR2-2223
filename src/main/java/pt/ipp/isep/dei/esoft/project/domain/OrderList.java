package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

/**
 * A list of orders.
 */
public class OrderList {

    /**
     * The list of orders.
     */
    private List<Order> orders;

    /**
     * Constructs an empty OrderList.
     */
    public OrderList() {
        this.orders = new ArrayList<>();
    }

    /**
     * Adds an order to the list.
     *
     * @param order the order to add
     * @return true if the order is added successfully, false otherwise
     */
    public Boolean addOrder(Order order) {
        Boolean success = false;
        if (validateOrder(order)) {
            success = orders.add(order.clone());
        }
        return success;
    }

    /**
     * Validates an order before adding it to the list.
     *
     * @param order the order to validate
     * @return true if the order is valid, false otherwise
     */
    private boolean validateOrder(Order order) {
        return orders != null && !(orders.contains(order));
    }

    /**
     * Retrieves an order from the list by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return an Optional containing the order if found, or an empty Optional otherwise
     */
    private Optional<Order> getOrderById(Integer orderId) {
        Optional<Order> newOrder = Optional.empty();
        for (Order order : orders) {
            if (order.hasId(orderId)) {
                newOrder = Optional.of(order);
                return newOrder;
            }
        }
        return newOrder;
    }

    /**
     * Defines the acceptance of an order.
     *
     * @param answer  the acceptance answer
     * @param orderId the ID of the order
     * @return true if the acceptance is successfully defined, false otherwise
     */
    public boolean defineOrderAcceptance(String answer, Integer orderId) {
        Boolean success = false;
        Optional<Order> order = getOrderById(orderId);

        if (order.isPresent()) {
            success = order.get().setAcceptanceAnswer(answer);
            if (!answer.equalsIgnoreCase(Order.REJECTION_ANSWER)) {
                for (Order o : orders) {
                    if (o.getId().equals(orderId)) {
                        continue;
                    }
                    success = o.rejectOrder();
                }
            }
        }
        return success;
    }

    /**
     * This method returns the list of orders.
     * @return a list of orders.
     */
    public List<Order> getList() {
        return orders;
    }

    /**
     * Sorts the list of orders by highest order amount.
     */
    public void sortOrdersByHighestOrderAmount() {
            Collections.sort(orders, sortOrdersByHighestOrderAmount);
    }

    /**
     * Comparator that sorts orders by amount.
     */
    Comparator<Order> sortOrdersByHighestOrderAmount = new Comparator<Order>() {
        public int compare(Order o1, Order o2) {
            Double value1 = o1.getOrderAmount();
            Double value2 = o2.getOrderAmount();

            return value2.compareTo(value1);
        }
    };

    /**
     * Checks if this OrderList is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderList orderList = (OrderList) o;
        return Objects.equals(orders, orderList.orders);
    }

    /**
     * Returns the hash code of the OrderList.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }
}
