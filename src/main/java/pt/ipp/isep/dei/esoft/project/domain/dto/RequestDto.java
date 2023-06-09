package pt.ipp.isep.dei.esoft.project.domain.dto;


import java.util.Objects;

/**
 * The type Request dto.
 */
public class RequestDto {

    /**
     * The Property attributes.
     */
    private String propertyAttributes;

    /**
     * The Business attributes.
     */
    private String businessAttributes;

    /**
     * The constant id.
     */
    private Integer id;

    /**
     * The Request date.
     */
    private String requestDate;

    /**
     * Instantiates a new Request dto.
     *
     * @param propertyAttributes the property attributes
     * @param businessAttributes the business attributes
     * @param id                 the id
     * @param requestDate        the request date
     */
    public RequestDto(String propertyAttributes, String businessAttributes, Integer id, String requestDate){
        this.propertyAttributes = propertyAttributes;
        this.businessAttributes = businessAttributes;
        this.id = id;
        this.requestDate = requestDate;
    }

    /**
     * Get property attributes string.
     *
     * @return the string
     */
    public String getPropertyAttributes(){
        return propertyAttributes;
    }

    /**
     * Get business attributes string.
     *
     * @return the string
     */
    public String getBusinessAttributes(){
        return businessAttributes;
    }

    /**
     * Get request date string.
     *
     * @return the string
     */
    public String getRequestDate(){
        return requestDate;
    }

    /**
     * Get request id integer.
     *
     * @return the integer
     */
    public Integer getRequestId(){
        return id;
    }

    /**
     * Returns a string representation of the RequestDto object.
     * The string contains the request Id, property attributes, business attributes,
     * and the request date.
     *
     * @return a string representation of the RequestDto object.
     */
    @Override
    public String toString() {
        return String.format("Request ID: %s \n%s%sRequest Date: %s\n", getRequestId(), propertyAttributes, businessAttributes, requestDate);
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
        RequestDto that = (RequestDto) o;
        return id == that.id && Objects.equals(propertyAttributes, that.propertyAttributes) && Objects.equals(businessAttributes, that.businessAttributes) && Objects.equals(requestDate, that.requestDate);
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
        return Objects.hash(id, propertyAttributes, businessAttributes, requestDate);
    }

}
