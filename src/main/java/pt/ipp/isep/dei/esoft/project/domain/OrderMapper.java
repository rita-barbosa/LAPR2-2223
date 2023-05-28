package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The OrderMapper class is responsible for mapping Order objects to OrderDto objects.
 */
public class OrderMapper {

    /**
     * Converts a list of Order objects to a list of OrderDto objects.
     *
     * @param listOrders The list of Order objects to be converted.
     * @return The list of OrderDto objects.
     */
    public static List<OrderDto> toDto(List<Order> listOrders) {
        List<OrderDto> listOrdersDto = new ArrayList<>();
        for (Order order : listOrders) {
            OrderDto dto = toDto(order);
            listOrdersDto.add(dto);
        }
        return listOrdersDto;
    }

    /**
     * Converts an Order object to an OrderDto object.
     *
     * @param order The Order object to be converted.
     * @return The converted OrderDto object.
     */
    public static OrderDto toDto(Order order) {
        double orderAmount = order.getOrderAmount();
        String clientEmail = order.getClientEmail().toString();
        String orderDate = order.getOrderDate().toString();
        int orderId = order.getId();

        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, orderId);

        return dto;
    }
}
