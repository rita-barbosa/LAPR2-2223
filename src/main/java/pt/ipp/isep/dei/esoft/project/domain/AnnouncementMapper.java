package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Announcement mapper.
 */
public class AnnouncementMapper {
    /**
     * To dto optional.
     *
     * @param announcements the announcements
     * @return the optional
     */
    public static Optional<List<AnnouncementDto>> toDto(List<Announcement> announcements) {
        List<AnnouncementDto> listAnnouncementsDto = new ArrayList<>();
        for (Announcement announce : announcements) {
            AnnouncementDto dto = toDto(announce);
            listAnnouncementsDto.add(dto);
        }
        return Optional.of(listAnnouncementsDto);
    }

    /**
     * To dto announcement dto.
     *
     * @param announcement the announcement
     * @return the announcement dto
     */
    public static AnnouncementDto toDto(Announcement announcement) {
        String requestAttributes = announcement.getRequestAttributes();
        String commissionAttributes = announcement.getCommissionAttributes();
        int id = announcement.getId();
        String acceptanceDate = announcement.getAcceptanceDate().toString();
        List<Order> listOrders = announcement.getListOfOrders();
        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        return new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);
    }

    /**
     * Get announcement id from dto integer.
     *
     * @param announcementDto the announcement dto
     * @return the id integer
     */
    public Integer getAnnouncementIdFromDto(AnnouncementDto announcementDto){
        return announcementDto.getAnnouncementId();
    }
}
