package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementMapperTest {

    @Test
    void toDto() {
        List<Announcement> announcements = new ArrayList<>();
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John Doe", "C12000078",
                "004-45-6989", "employee@this.app", "agent", "623-456-7890",
                "New York", "Manhattan", "NY", "10001", "Broadway");
        CommissionType commissionType = new CommissionType("Commission Type");
        Commission commission = new Commission(commissionType, 234.0);
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = LocalDate.now().toString();
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        Order order = new Order(orderAmount,new Email(clientEmail));

        OrderDto dtoOrder = new OrderDto(orderDate, orderAmount, clientEmail, order.getId());
        orderDtoList.add(dtoOrder);

        Announcement announcement = new Announcement(employee, commission, request);
        announcements.add(announcement);
        announcement.addOrder(order);

        List<AnnouncementDto> announcementDtoList = new ArrayList<>();
        AnnouncementDto dto = new AnnouncementDto(announcement.getId(), request.toString(), commission.toString(), LocalDate.now().toString(), orderDtoList);
        announcementDtoList.add(dto);

        assertTrue(AnnouncementMapper.toDto(announcements).isPresent());
        assertEquals(announcementDtoList, AnnouncementMapper.toDto(announcements).get());
    }

}