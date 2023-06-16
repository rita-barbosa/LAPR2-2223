package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementDtoTest {

    @Test
    void ensureGetAnnouncementIdWorks() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        int announcementId = 0;
        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);

        assertEquals(id, announcementDto.getAnnouncementId());

    }

    @Test
    void ensureGetRequestAttributesWorks() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);

        assertEquals("requestAttributes", announcementDto.getRequestAttributes());
    }

    @Test
    void ensureGetCommissionAttributesWorks() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);

        assertEquals("commissionAttributes", announcementDto.getCommissionAttributes());
    }

    @Test
    void ensureGetAcceptanceDateWorks() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);

        assertEquals("23-12-2004", announcementDto.getAcceptanceDate());
    }

    @Test
    void ensureGetListOrdersDtoWorks() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);

        assertEquals(orderDtoList, announcementDto.getListOrdersDto());
    }

    @Test
    void testToString() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);
        String expected = "Announcement Acceptance Date: 23-12-2004\n" +
                "requestAttributes\n" +
                "commissionAttributes\n" +
                "Orders:\n" +
                "#1 Order | Amount offered : 245555.0 \n";

        assertEquals(expected, announcementDto.toString());
    }

    @Test
    void testEqualsDifferentObjects() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);
        AnnouncementDto announcementDto1 = new AnnouncementDto(id+1, "requestAttributes1", "commissionAttributes1", "3-12-2004", orderDtoList);

        assertNotEquals(announcementDto1,announcementDto);
    }
    @Test
    void testEqualsSameObjects() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);
        assertEquals(announcementDto,announcementDto);
    }

    @Test
    void testHashCodeDifferentObject() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);
        AnnouncementDto announcementDto1 = new AnnouncementDto(id+1, "requestAttributes1", "commissionAttributes1", "3-12-2004", orderDtoList);

        assertNotEquals(announcementDto1.hashCode(),announcementDto.hashCode());

    }

    @Test
    void testHashCodeSameObject() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        String orderDate = "23-12-2004";
        String clientEmail = "client@this.app";
        double orderAmount = 245555.0;
        int id = 0;
        OrderDto dto = new OrderDto(orderDate, orderAmount, clientEmail, id);
        orderDtoList.add(dto);

        AnnouncementDto announcementDto = new AnnouncementDto(id, "requestAttributes", "commissionAttributes", "23-12-2004", orderDtoList);

        assertEquals(announcementDto.hashCode(),announcementDto.hashCode());

    }
}