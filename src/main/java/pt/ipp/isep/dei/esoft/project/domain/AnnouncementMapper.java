package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnnouncementMapper {
    public static Optional<List<AnnouncementDto>> toDto(List<Announcement> announcements) {
        List<AnnouncementDto> listAnnouncementsDto = new ArrayList<>();
        for (Announcement announce : announcements) {
            AnnouncementDto dto = toDto(announce);
            listAnnouncementsDto.add(dto);
        }
        return Optional.of(listAnnouncementsDto);
    }

    public static AnnouncementDto toDto(Announcement announcement) {
        String requestAttributes = announcement.getRequestAttributes();
        String commissionAttributes = announcement.getCommissionAttributes();
        int id = announcement.getId();
        String acceptanceDate = announcement.getAcceptanceDate().toString();
        List<Order> listOrders = announcement.getListOfOrders();

        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        return new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);
    }
}
