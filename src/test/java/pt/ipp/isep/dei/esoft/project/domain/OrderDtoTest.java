package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.OrderDto;

import static org.junit.jupiter.api.Assertions.*;

class OrderDtoTest {

    @Test
    void ensureGetOrderDateWorks() {
        String orderDate = "23-12-2004";
        OrderDto dto = new OrderDto(orderDate, 245555.0, "client@this.app", 0);

        assertEquals(orderDate, dto.getOrderDate());
    }

    @Test
    void ensureGetOrderAmountWorks() {
        String orderDate = "23-12-2004";
        double orderAmount = 245555.0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, "client@this.app", 0);

        assertEquals(orderAmount, dto.getOrderAmount());
    }

    @Test
    void ensureGetClientEmailWorks() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, 0);

        assertEquals(clientEmail, dto.getClientEmail());
    }

    @Test
    void ensureGetIdWorks() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);

        assertEquals(id, dto.getId());
    }

    @Test
    void testToString() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
    }

    @Test
    void testEqualsDifferentObjects() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);

        String orderDate1 = "3-12-2004";
        String clientEmail1 = "client1@this.app";
        double orderAmount1 = 2405555.0;
        int id1 = 1;
        OrderDto dto1 = new OrderDto(orderDate1, orderAmount1, clientEmail1, id1);

        assertNotEquals(dto, dto1);
    }

    @Test
    void testEqualsSameObjects() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);

        assertEquals(dto, dto);
    }

    @Test
    void testHashCodeDifferentObject() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);

        String orderDate1 = "3-12-2004";
        String clientEmail1 = "client1@this.app";
        double orderAmount1 = 2405555.0;
        int id1 = 1;
        OrderDto dto1 = new OrderDto(orderDate1, orderAmount1, clientEmail1, id1);

        assertNotEquals(dto.hashCode(), dto1.hashCode());


    }

    @Test
    void testHashCodeSameObject() {
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);

        assertEquals(dto.hashCode(), dto.hashCode());
    }
}