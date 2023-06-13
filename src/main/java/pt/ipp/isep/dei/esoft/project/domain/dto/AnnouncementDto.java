package pt.ipp.isep.dei.esoft.project.domain.dto;

import java.util.List;
import java.util.Objects;

public class AnnouncementDto {
    private String saleDate;
    private Double saleAmount;
    private String requestDate;
    private String agencyDescription;
    private Integer agencyId;

    /**
     * The designation of the type of property being announced.
     */
    private String propertyTypeDesignation;

    /**
     * The designation of the commission type.
     */
    private String commissionTypeDesignation;

    /**
     * The commission value.
     */
    private double commissionValue;

    /**
     * The email address of the owner of the property.
     */
    private String ownerEmail;

    /**
     * The name of the street where the property is located.
     */
    private String streetName;

    /**
     * The name of the city where the property is located.
     */
    private String city;

    /**
     * The name of the district where the property is located.
     */
    private String district;

    /**
     * The name of the state where the property is located.
     */
    private String state;

    /**
     * The zip code of the property.
     */
    private String zipCode;

    /**
     * The area of the property.
     */
    private double area;

    /**
     * The distance from the city center.
     */
    private double distanceCityCenter;

    /**
     * The price of the property.
     */
    private double price;

    /**
     * The list of URIs to the photographs of the property.
     */
    private List<String> uriList;

    /**
     * The number of bedrooms in the property.
     */
    private int numberBedroom;

    /**
     * The number of parking spaces in the property.
     */
    private int numberParkingSpace;

    /**
     * The number of bathrooms in the property.
     */
    private int numberBathroom;

    /**
     * The list of available equipment descriptions.
     */
    private List<String> availableEquipmentDescriptionList;

    /**
     * Indicates whether the property has a basement.
     */
    private boolean existenceBasement;

    /**
     * Indicates whether the property has an inhabitable loft.
     */
    private boolean inhabitableLoft;

    /**
     * The sun exposure of the property.
     */
    private String sunExposure;


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
     * @param id                   the id
     * @param saleDate             the sale date
     * @param saleAmount           the sale amount
     * @param requestDate          the request date
     * @param acceptanceDate       the acceptance date
     * @param commissionAttributes the commission attributes
     * @param requestAttributes    the request attributes
     * @param agencyDescription    the agency description
     * @param agencyId             the agency id
     */
    public AnnouncementDto(Integer id, String saleDate, Double saleAmount, String requestDate, String acceptanceDate,
                           String commissionAttributes, String requestAttributes, String agencyDescription, Integer agencyId) {
        this.id = id;
        this.requestAttributes = requestAttributes;
        this.commissionAttributes = commissionAttributes;
        this.acceptanceDate = acceptanceDate;
        this.saleDate = saleDate;
        this.saleAmount = saleAmount;
        this.requestDate = requestDate;
        this.agencyDescription = agencyDescription;
        this.agencyId = agencyId;
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

    public AnnouncementDto() {

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
        int idx = 1;
        StringBuilder sb = new StringBuilder(String.format("Announcement Acceptance Date: %s\n%s\n%s\nOrders:\n", acceptanceDate, requestAttributes, commissionAttributes));
        for (OrderDto orderDto : listOrdersDto) {
            sb.append(String.format("#%s %s\n", idx, orderDto.toString()));
            idx++;
        }
        return sb.toString();
    }

    public String toDealString() {
        StringBuilder sb = new StringBuilder(String.format("Announcement ID: %d\n%sRequest Date: %s\n%s\nSale Date: %s\nSale Amount: $%.2f\n",
                id, requestAttributes, requestDate, commissionAttributes, saleDate, saleAmount));
        sb.append(String.format("Agency ID: %d\nAgency Name: %s\n", agencyId, agencyDescription));
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
        return Double.compare(that.commissionValue, commissionValue) == 0 && Double.compare(that.area, area) == 0 && Double.compare(that.distanceCityCenter, distanceCityCenter) == 0 && Double.compare(that.price, price) == 0 && numberBedroom == that.numberBedroom && numberParkingSpace == that.numberParkingSpace && numberBathroom == that.numberBathroom && existenceBasement == that.existenceBasement && inhabitableLoft == that.inhabitableLoft && id == that.id && Objects.equals(saleDate, that.saleDate) && Objects.equals(saleAmount, that.saleAmount) && Objects.equals(requestDate, that.requestDate) && Objects.equals(agencyDescription, that.agencyDescription) && Objects.equals(agencyId, that.agencyId) && Objects.equals(propertyTypeDesignation, that.propertyTypeDesignation) && Objects.equals(commissionTypeDesignation, that.commissionTypeDesignation) && Objects.equals(ownerEmail, that.ownerEmail) && Objects.equals(streetName, that.streetName) && Objects.equals(city, that.city) && Objects.equals(district, that.district) && Objects.equals(state, that.state) && Objects.equals(zipCode, that.zipCode) && Objects.equals(uriList, that.uriList) && Objects.equals(availableEquipmentDescriptionList, that.availableEquipmentDescriptionList) && Objects.equals(sunExposure, that.sunExposure) && Objects.equals(requestAttributes, that.requestAttributes) && Objects.equals(commissionAttributes, that.commissionAttributes) && Objects.equals(acceptanceDate, that.acceptanceDate) && Objects.equals(listOrdersDto, that.listOrdersDto);
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
        return Objects.hash(saleDate, saleAmount, requestDate, agencyDescription, agencyId, propertyTypeDesignation, commissionTypeDesignation, commissionValue, ownerEmail, streetName, city, district, state, zipCode, area, distanceCityCenter, price, uriList, numberBedroom, numberParkingSpace, numberBathroom, availableEquipmentDescriptionList, existenceBasement, inhabitableLoft, sunExposure, id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);
    }

    /**
     * Gets property type designation.
     *
     * @return the property type designation
     */
    public String getPropertyTypeDesignation() {
        return propertyTypeDesignation;
    }

    /**
     * Sets property type designation.
     *
     * @param propertyTypeDesignation the property type designation
     */
    public void setPropertyTypeDesignation(String propertyTypeDesignation) {
        this.propertyTypeDesignation = propertyTypeDesignation;
    }

    /**
     * Gets commission type designation.
     *
     * @return the commission type designation
     */
    public String getCommissionTypeDesignation() {
        return commissionTypeDesignation;
    }

    /**
     * Sets commission type designation.
     *
     * @param commissionTypeDesignation the commission type designation
     */
    public void setCommissionTypeDesignation(String commissionTypeDesignation) {
        this.commissionTypeDesignation = commissionTypeDesignation;
    }

    /**
     * Gets commission value.
     *
     * @return the commission value
     */
    public double getCommissionValue() {
        return commissionValue;
    }

    /**
     * Sets commission value.
     *
     * @param commissionValue the commission value
     */
    public void setCommissionValue(double commissionValue) {
        this.commissionValue = commissionValue;
    }

    /**
     * Gets owner email.
     *
     * @return the owner email
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Sets owner email.
     *
     * @param ownerEmail the owner email
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * Gets street name.
     *
     * @return the street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets street name.
     *
     * @param streetName the street name
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets district.
     *
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets district.
     *
     * @param district the district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Gets distance city center.
     *
     * @return the distance city center
     */
    public double getDistanceCityCenter() {
        return distanceCityCenter;
    }

    /**
     * Sets distance city center.
     *
     * @param distanceCityCenter the distance city center
     */
    public void setDistanceCityCenter(double distanceCityCenter) {
        this.distanceCityCenter = distanceCityCenter;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getAmount() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets uri list.
     *
     * @return the uri list
     */
    public List<String> getUriList() {
        return uriList;
    }

    /**
     * Sets uri list.
     *
     * @param uriList the uri list
     */
    public void setUriList(List<String> uriList) {
        this.uriList = uriList;
    }

    /**
     * Gets number bedroom.
     *
     * @return the number bedroom
     */
    public int getNumberBedroom() {
        return numberBedroom;
    }

    /**
     * Sets number bedroom.
     *
     * @param numberBedroom the number bedroom
     */
    public void setNumberBedroom(int numberBedroom) {
        this.numberBedroom = numberBedroom;
    }

    /**
     * Gets number parking space.
     *
     * @return the number parking space
     */
    public int getNumberParkingSpace() {
        return numberParkingSpace;
    }

    /**
     * Sets number parking space.
     *
     * @param numberParkingSpace the number parking space
     */
    public void setNumberParkingSpace(int numberParkingSpace) {
        this.numberParkingSpace = numberParkingSpace;
    }

    /**
     * Gets number bathroom.
     *
     * @return the number bathroom
     */
    public int getNumberBathroom() {
        return numberBathroom;
    }

    /**
     * Sets number bathroom.
     *
     * @param numberBathroom the number bathroom
     */
    public void setNumberBathroom(int numberBathroom) {
        this.numberBathroom = numberBathroom;
    }

    /**
     * Gets available equipment description list.
     *
     * @return the available equipment description list
     */
    public List<String> getAvailableEquipmentDescriptionList() {
        return availableEquipmentDescriptionList;
    }

    /**
     * Sets available equipment description list.
     *
     * @param availableEquipmentDescriptionList the available equipment description list
     */
    public void setAvailableEquipmentDescriptionList(List<String> availableEquipmentDescriptionList) {
        this.availableEquipmentDescriptionList = availableEquipmentDescriptionList;
    }

    /**
     * Is existence basement boolean.
     *
     * @return the boolean
     */
    public boolean isExistenceBasement() {
        return existenceBasement;
    }

    /**
     * Sets existence basement.
     *
     * @param existenceBasement the existence basement
     */
    public void setExistenceBasement(boolean existenceBasement) {
        this.existenceBasement = existenceBasement;
    }

    /**
     * Is inhabitable loft boolean.
     *
     * @return the boolean
     */
    public boolean isInhabitableLoft() {
        return inhabitableLoft;
    }

    /**
     * Sets inhabitable loft.
     *
     * @param inhabitableLoft the inhabitable loft
     */
    public void setInhabitableLoft(boolean inhabitableLoft) {
        this.inhabitableLoft = inhabitableLoft;
    }

    /**
     * Gets sun exposure.
     *
     * @return the sun exposure
     */
    public String getSunExposure() {
        return sunExposure;
    }

    /**
     * Sets sun exposure.
     *
     * @param sunExposure the sun exposure
     */
    public void setSunExposure(String sunExposure) {
        this.sunExposure = sunExposure;
    }
}
