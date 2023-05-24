package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class AnnouncementDto {
    private int id;
    private String requestAttributes;

    private String commissionAttributes;
    private String acceptanceDate;
    private List<OrderDto> listOrdersDto;

    public AnnouncementDto(int id, String requestAttributes, String commissionAttributes, String acceptanceDate, List<OrderDto> listOrdersDto) {
        this.id = id;
        this.requestAttributes = requestAttributes;
        this.commissionAttributes = commissionAttributes;
        this.acceptanceDate = acceptanceDate;
        this.listOrdersDto = listOrdersDto;
    }
    public int getAnnouncementId() {
        return id;
    }

    public String getRequestAttributes() {
        return requestAttributes;
    }

    public String getCommissionAttributes() {
        return commissionAttributes;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public List<OrderDto> getListOrdersDto() {
        return listOrdersDto;
    }
}
