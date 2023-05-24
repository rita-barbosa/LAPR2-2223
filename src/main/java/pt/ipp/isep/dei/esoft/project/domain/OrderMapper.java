package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static List<OrderDto> toDto(List<Order> listOrders) {
        List<OrderDto> listOrdersDto = new ArrayList<>();
        for (Order order : listOrders) {
            OrderDto dto = toDto(order);
            listOrdersDto.add(dto);
        }
        return listOrdersDto;
    }

    public static OrderDto toDto(Order order) {
        double orderAmount = order.getOrderAmount();
        String clientEmail = order.getClientEmail().toString();
        String orderDate = order.getOrderDate().toString();
        int orderId = order.getId();

        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, orderId);

        return dto;
    }

    public static int getOrderId(OrderDto dto) {
        return dto.getId();
    }
}
