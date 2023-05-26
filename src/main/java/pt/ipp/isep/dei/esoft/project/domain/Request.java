package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.NotImplementedException;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * The type Request.
 */
public class Request implements Notification {

    /**
     * The Id.
     */
    private Integer id;
    /**
     * The Request date.
     */
    private final LocalDate requestDate;
    /**
     * The Agent.
     */
    private final Employee agent;
    /**
     * The Business.
     */
    private final Business business;
    /**
     * The Property.
     */
    private Property property;
    /**
     * The Owner email.
     */
    private final Email ownerEmail;
    /**
     * The id iteration variable.
     */
    private static int counter = 0;


    /**
     * Instantiates a new Request.
     *
     * @param ownerEmail                    the owner email
     * @param propertyType                  the property type
     * @param businessTypeDesignation       the business type designation
     * @param amount                        the amount
     * @param area                          the area
     * @param contractDuration              the contract duration
     * @param availableEquipmentDescription the available equipment description
     * @param streetName                    the street name
     * @param city                          the city
     * @param district                      the district
     * @param state                         the state
     * @param zipCode                       the zip code
     * @param basement                      the basement
     * @param inhabitableLoft               the inhabitable loft
     * @param parkingSpace                  the parking space
     * @param sunExposure                   the sun exposure
     * @param numberBedroom                 the number bedroom
     * @param numberBathroom                the number bathroom
     * @param agent                         the agent
     * @param distanceCityCenter            the distance city center
     * @param uri                           the uri
     */
    public Request(String ownerEmail, PropertyType propertyType, String businessTypeDesignation, Double amount,
                   Double area, Integer contractDuration, List<String> availableEquipmentDescription,
                   String streetName, String city, String district, String state, String zipCode, Boolean basement,
                   Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                   Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter,
                   List<String> uri) {

        this.ownerEmail = new Email(ownerEmail);
        this.requestDate = LocalDate.now();
        this.business = new Lease(contractDuration, businessTypeDesignation, amount);

        this.agent = agent;
        this.id = counter++;

        switch (propertyType.toString().toLowerCase()) {
            case "land":
                this.property = new Property(propertyType, area, distanceCityCenter, uri, streetName, city,
                        district, state, zipCode);
                break;
            case "apartment":
                this.property = new Residence(propertyType, area, availableEquipmentDescription, streetName, city, district, state,
                        zipCode, parkingSpace, numberBedroom, numberBathroom, distanceCityCenter, uri);
                break;
            case "house":
                this.property = new House(propertyType, availableEquipmentDescription, area, streetName, city, district, state,
                        zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom,
                        distanceCityCenter, uri);
                break;
            default:
                break;
        }
    }

    /**
     * Instantiates a new Request.
     *
     * @param ownerEmail              the owner email
     * @param propertyType            the property type
     * @param businessTypeDesignation the business type designation
     * @param amount                  the amount
     * @param area                    the area
     * @param availableEquipment      the available equipment
     * @param streetName              the street name
     * @param city                    the city
     * @param district                the district
     * @param state                   the state
     * @param zipCode                 the zip code
     * @param basement                the basement
     * @param inhabitableLoft         the inhabitable loft
     * @param parkingSpace            the parking space
     * @param sunExposure             the sun exposure
     * @param numberBedroom           the number bedroom
     * @param numberBathroom          the number bathroom
     * @param agent                   the agent
     * @param distanceCityCenter      the distance city center
     * @param uri                     the uri
     */
    public Request(String ownerEmail, PropertyType propertyType, String businessTypeDesignation, Double amount, Double area,
                   List<String> availableEquipment, String streetName, String city, String district,
                   String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace,
                   Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Employee agent,
                   Double distanceCityCenter, List<String> uri) {
        this.ownerEmail = new Email(ownerEmail);
        this.requestDate = LocalDate.now();
        this.business = new Business(businessTypeDesignation, amount);
        this.agent = agent;
        this.id = counter++;

        switch (propertyType.toString().toLowerCase()) {
            case "land":
                this.property = new Property(propertyType, area, distanceCityCenter, uri, streetName, city,
                        district, state, zipCode);
                break;
            case "apartment":
                this.property = new Residence(propertyType, area, availableEquipment, streetName, city, district, state,
                        zipCode, parkingSpace, numberBedroom, numberBathroom, distanceCityCenter, uri);
                break;
            case "house":
                this.property = new House(propertyType, availableEquipment, area, streetName, city, district, state,
                        zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom,
                        distanceCityCenter, uri);
                break;
            default:
                break;
        }
    }

    /**
     * Instantiates a new Request.
     *
     * @param ownerEmail  the owner email
     * @param property    the property
     * @param business    the business
     * @param requestDate the request date
     * @param agent       the agent
     */
    public Request(String ownerEmail, Property property, Business business, LocalDate requestDate, Employee agent, Integer id) {
        this.ownerEmail = new Email(ownerEmail);
        this.requestDate = requestDate;
        this.business = business;
        this.agent = agent;
        this.property = property;
        this.id = id;
    }

    public String getPropertyAttributes(){
        return this.property.toString();
    }

    public String getBusinessAttributes(){
        return this.business.toString();
    }



    /**
     * Clone request.
     *
     * @return the request
     */
    public Request clone() {
        return new Request(this.ownerEmail.toString(), this.property, this.business, this.requestDate, this.agent, this.id);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return business.toString() + property.toString();
    }

    /**
     * Gets business.
     *
     * @return the business
     */
    public Business getBusiness() {
        return this.business;
    }

    /**
     * Gets property.
     *
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    public LocalDate getRequestDate(){
        return requestDate;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(requestDate, request.requestDate) && Objects.equals(agent, request.agent) && Objects.equals(business, request.business) && Objects.equals(property, request.property) && Objects.equals(ownerEmail, request.ownerEmail) && Objects.equals(id, request.id);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(requestDate, agent, business, property, ownerEmail, id);
    }

//    public String getId() {
//        return this.id.toString();
//    }

    public Integer getId() {
        return id;
    }

    public LocalDate getAcceptanceDate() {
        return requestDate;
    }

    public Boolean hasAgentWithEmail(String agentEmail) {
        return this.getAgentEmail().equals(agentEmail);
    }

    public String getAgentEmail() {
        return this.agent.getEmailAddress().getEmail();
    }

    @Override
    public Boolean sendNotification(String email) {
        File file = new File(FILE_NAME + getId() + "." + email + FILE_TYPE);
        try {
            PrintWriter text = new PrintWriter(file);
            text.write(TEXT_TO + email+"\n");
            text.write(TEXT_TOPIC + "Property Announcement Request Acceptance\n");
            text.write("The property announcement request submitted in " + getRequestDate().toString() + " has been analysed. Your request was declined. Here is the justification message:\n " );

            text.close();
            return true;
        } catch (IOException e) {
            System.out.println("ERROR: Failed to send notification.");
            return false;
        }
    }

    public Boolean hasId(Integer requestId) {
        return this.getId().equals(requestId);
    }

    public void sendEmail(String ownerEmail, String message){
        throw new NotImplementedException();
    }

}
