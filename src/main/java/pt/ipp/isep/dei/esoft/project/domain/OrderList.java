package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

public class OrderList {
    private List<Order> orders;

    public OrderList() {
        this.orders = new ArrayList<>();
    }

    public Boolean addOrder(Order order) {
        Boolean success = false;
        if (validateOrder(order)) {
            success = orders.add(order.clone());
        }
        return success;
    }

    private boolean validateOrder(Order order) {
        return orders != null && !(orders.contains(order));
    }

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
     * Comparator that sorts orders by the highest amount.
     */
    Comparator<Order> sortOrdersByHighestOrderAmount = new Comparator<Order>() {
        public int compare(Order o1, Order o2) {
            Double value1 = o1.getOrderAmount();
            Double value2 = o2.getOrderAmount();

            return value2.compareTo(value1);
        }
    };
}
