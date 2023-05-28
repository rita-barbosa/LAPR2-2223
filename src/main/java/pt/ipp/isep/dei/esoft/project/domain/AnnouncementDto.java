package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**
 * The type Announcement dto.
 */
public class AnnouncementDto {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Request attributes.
     */
    private String requestAttributes;

    /**
     * The Commission attributes.
     */
    private String commissionAttributes;
    /**
     * The Acceptance date.
     */
    private String acceptanceDate;
    /**
     * The List orders dto.
     */
    private List<OrderDto> listOrdersDto;

    /**
     * Instantiates a new Announcement dto.
     *
     * @param id                   the id
     * @param requestAttributes    the request attributes
     * @param commissionAttributes the commission attributes
     * @param acceptanceDate       the acceptance date
     * @param listOrdersDto        the list orders dto
     */
    public AnnouncementDto(int id, String requestAttributes, String commissionAttributes, String acceptanceDate, List<OrderDto> listOrdersDto) {
        this.id = id;
        this.requestAttributes = requestAttributes;
        this.commissionAttributes = commissionAttributes;
        this.acceptanceDate = acceptanceDate;
        this.listOrdersDto = listOrdersDto;
    }

    /**
     * Instantiates a new Announcement dto.
     *
     * @param id                the id
     * @param requestAttributes the request attributes
     */
    public AnnouncementDto(int id, String requestAttributes) {
        this.id = id;
        this.requestAttributes = requestAttributes;
    }

    /**
     * Gets announcement id.
     *
     * @return the announcement id
     */

    public int getAnnouncementId() {
        return id;
    }

    /**
     * Gets request attributes.
     *
     * @return the request attributes
     */
    public String getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * Gets commission attributes.
     *
     * @return the commission attributes
     */
    public String getCommissionAttributes() {
        return commissionAttributes;
    }

    /**
     * Gets acceptance date.
     *
     * @return the acceptance date
     */
    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    /**
     * Gets list orders dto.
     *
     * @return the list orders dto
     */
    public List<OrderDto> getListOrdersDto() {
        return listOrdersDto;
    }

    /**
     * Returns a string representation of the AnnouncementDto object.
     * The string contains the announcement acceptance date, request attributes, commission attributes,
     * and a list of orders.
     *
     * @return a string representation of the AnnouncementDto object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Announcement Acceptance Date: %s\n%s\n%s\nOrders:\n", acceptanceDate, requestAttributes, commissionAttributes));
        for (OrderDto orderDto : listOrdersDto) {
            sb.append(String.format("%s\n", orderDto.toString()));
        }
        return sb.toString();
    }

    /**
     * This method checks if two instances are equal.
     *
     * @param o - object to be compared to
     * @return {@code true} if objects are equal;{@code false}otherwise;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementDto that = (AnnouncementDto) o;
        return id == that.id && Objects.equals(requestAttributes, that.requestAttributes) && Objects.equals(commissionAttributes, that.commissionAttributes) && Objects.equals(acceptanceDate, that.acceptanceDate) && Objects.equals(listOrdersDto, that.listOrdersDto);
    }

    /**
     * Returns a hash code value for the object.
     * If two objects are equal, according to the equals(Object) method, then calling the hashCode method on each
     * of the two objects must produce the same integer result.
     *
     * @return the hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);
    }
}
