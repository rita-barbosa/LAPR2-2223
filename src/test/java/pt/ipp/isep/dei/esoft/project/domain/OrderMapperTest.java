package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    @Test
    void ensureToDtoWorks() {
        List<Order> orders = new ArrayList<>();
        double amount = 245000;
        Email clientEmail = new Email("client@email.com");
        Order o1 = new Order(amount, clientEmail);
        String acceptanceAnswer = "reject";
        o1.setAcceptanceAnswer(acceptanceAnswer);

        double amount1 = 3245000;
        Email clientEmail1 = new Email("client1@email.com");
        Order o2 = new Order(amount1, clientEmail1);
        String acceptanceAnswer1 = "accept";
        o2.setAcceptanceAnswer(acceptanceAnswer1);

        orders.add(o1);
        orders.add(o2);

        List<OrderDto> ordersDto = new ArrayList<>();
        OrderDto dto1 = new OrderDto(LocalDate.now().toString(), amount, clientEmail.getEmail(), o1.getId());
        OrderDto dto2 = new OrderDto(LocalDate.now().toString(), amount1, clientEmail1.getEmail(), o2.getId());
        ordersDto.add(dto1);
        ordersDto.add(dto2);

        assertEquals(ordersDto, OrderMapper.toDto(orders));

    }

}