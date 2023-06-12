package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Request.
 */
public class Request implements Serializable {

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
    private Employee agent;
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
    private Email ownerEmail;

    /**
     * The Validation status.
     */
    private Boolean validationStatus;
    /**
     * The id iteration variable.
     */
    private static int counter = 0;

    /**
     * The justification message for declining the property announcement request.
     */
    private String justificationMessage = null;

    /**
     * The String boolean value.
     */
    private final String STRING_BOOLEAN_VALUE = "Y";

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
        this.validationStatus = false;

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
                   Double distanceCityCenter, List<String> uri){

        this.ownerEmail = new Email(ownerEmail);
        this.requestDate = LocalDate.now();
        this.business = new Business(businessTypeDesignation, amount);
        this.agent = agent;
        this.id = counter++;
        this.validationStatus = false;
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
     * @param id          the id
     */
    public Request(String ownerEmail, Property property, Business business, LocalDate requestDate, Employee agent, Integer id) {
        this.ownerEmail = new Email(ownerEmail);
        this.requestDate = requestDate;
        this.business = business;
        this.agent = agent;
        this.property = property;
        this.id = id;
        this.validationStatus = false;
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
    public Request(String ownerEmail, Property property, Business business, LocalDate requestDate, Employee agent) {
        this.ownerEmail = new Email(ownerEmail);
        this.requestDate = requestDate;
        this.business = business;
        this.agent = agent;
        this.property = property;
        this.id = counter++;
        this.validationStatus = false;
    }

    /**
     * Instantiates a new Request.
     *
     * @param businessTypeDesignation the business type designation
     * @param contractDuration        the contract duration
     * @param amount                  the amount
     * @param propertyTypeDesignation the property type designation
     * @param area                    the area
     * @param location                the location
     * @param distanceCityCenter      the distance city center
     * @param dateAnnounceRequest     the date announce request
     */
    public Request(String businessTypeDesignation, String contractDuration, Double amount, String propertyTypeDesignation,
                   Double area, String location, Double distanceCityCenter, String dateAnnounceRequest) {

        this.property = new Property(new PropertyType(propertyTypeDesignation), area, distanceCityCenter, location);
        if (contractDuration.equalsIgnoreCase("NA")) {
            this.business = new Business(businessTypeDesignation, amount);
        } else {
            this.business = new Lease(mapStringToInteger(contractDuration), businessTypeDesignation, amount);
        }
        this.ownerEmail = null;
        this.agent = null;
        int[] date = mapStringToLocalDate(dateAnnounceRequest);
        this.requestDate = LocalDate.of(date[2], date[1], date[0]);
        this.validationStatus = false;
    }


    /**
     * Instantiates a new Request.
     *
     * @param businessTypeDesignation the business type designation
     * @param contractDuration        the contract duration
     * @param amount                  the amount
     * @param propertyTypeDesignation the property type designation
     * @param area                    the area
     * @param location                the location
     * @param distanceCityCenter      the distance city center
     * @param numberBedrooms          the number bedrooms
     * @param numberBathrooms         the number bathrooms
     * @param numberParkingSpaces     the number parking spaces
     * @param centralHeating          the central heating
     * @param airConditioning         the air conditioning
     * @param dateAnnounceRequest     the date announce request
     */
    public Request(String businessTypeDesignation, String contractDuration, Double amount, String propertyTypeDesignation,
                   Double area, String location, Double distanceCityCenter, String numberBedrooms, String numberBathrooms,
                   String numberParkingSpaces, String centralHeating, String airConditioning, String dateAnnounceRequest) {

        this.property = new Residence(new PropertyType(propertyTypeDesignation), area, distanceCityCenter, location, mapStringToInteger(numberBedrooms), mapStringToInteger(numberBathrooms), mapStringToInteger(numberParkingSpaces), mapStringToBoolean(centralHeating), mapStringToBoolean(airConditioning));
        if (contractDuration.equalsIgnoreCase("NA")) {
            this.business = new Business(businessTypeDesignation, amount);
        } else {
            this.business = new Lease(mapStringToInteger(contractDuration), businessTypeDesignation, amount);
        }
        this.ownerEmail = null;
        this.agent = null;
        int[] date = mapStringToLocalDate(dateAnnounceRequest);
        this.requestDate = LocalDate.of(date[2], date[1], date[0]);
        this.validationStatus = false;
    }

    /**
     * Instantiates a new Request.
     *
     * @param businessTypeDesignation the business type designation
     * @param contractDuration        the contract duration
     * @param amount                  the amount
     * @param propertyTypeDesignation the property type designation
     * @param area                    the area
     * @param location                the location
     * @param distanceCityCenter      the distance city center
     * @param numberBedrooms          the number bedrooms
     * @param numberBathrooms         the number bathrooms
     * @param numberParkingSpaces     the number parking spaces
     * @param airConditioning         the air conditioning
     * @param centralHeating          the central heating
     * @param basement                the basement
     * @param inhabitableLoft         the inhabitable loft
     * @param sunExposure             the sun exposure
     * @param dateAnnounceRequest     the date announce request
     * @throws NumberFormatException the number format exception
     */
    public Request(String businessTypeDesignation, String contractDuration, Double amount, String propertyTypeDesignation,
                   Double area, String location, Double distanceCityCenter, String numberBedrooms, String numberBathrooms,
                   String numberParkingSpaces, String airConditioning, String centralHeating, String basement, String inhabitableLoft,
                   String sunExposure, String dateAnnounceRequest) throws NumberFormatException {
        this.property = new House(new PropertyType(propertyTypeDesignation), area, distanceCityCenter, location, mapStringToInteger(numberBedrooms), mapStringToInteger(numberBathrooms),
                mapStringToInteger(numberParkingSpaces), mapStringToBoolean(centralHeating), mapStringToBoolean(airConditioning), mapStringToBoolean(basement),
                mapStringToBoolean(inhabitableLoft), mapStringToSunExposure(sunExposure));
        if (contractDuration.equalsIgnoreCase("NA")) {
            this.business = new Business(businessTypeDesignation, amount);
        } else {
            this.business = new Lease(mapStringToInteger(contractDuration), businessTypeDesignation, amount);
        }
        this.ownerEmail = null;
        this.agent = null;
        int[] date = mapStringToLocalDate(dateAnnounceRequest);
        this.requestDate = LocalDate.of(date[2], date[1], date[0]);
        this.validationStatus = false;
    }

    /**
     * Map string to sun exposure enum.
     *
     * @param sunExposureChar the sun exposure char
     * @return the enum
     */
    private Enum<SunExposureTypes> mapStringToSunExposure(String sunExposureChar) {
        SunExposureTypes sunExposure = null;
        switch (sunExposureChar.toLowerCase()) {
            case "n":
                sunExposure = SunExposureTypes.NORTH;
                break;
            case "e":
                sunExposure = SunExposureTypes.EAST;
                break;
            case "w":
                sunExposure = SunExposureTypes.WEST;
                break;
            case "s":
                sunExposure = SunExposureTypes.SOUTH;
                break;
            default:
                System.out.println("ERROR: Invalid data in file.");
                System.exit(1);
                break;
        }
        return sunExposure;
    }

    /**
     * Map string to local date int [ ].
     *
     * @param value the value
     * @return the int [ ]
     */
    private int[] mapStringToLocalDate(String value) {
        String[] dateString = value.split("-");
        int[] date = new int[dateString.length];
        try {
            for (int i = 0; i < dateString.length; i++) {
                date[i] = Integer.parseInt(dateString[i]);
            }
        } catch (NumberFormatException e) {
            throw e;
        }
        return date;
    }

    /**
     * Map string to boolean boolean.
     *
     * @param value the value
     * @return the boolean
     */
    private boolean mapStringToBoolean(String value) {
        return value.equalsIgnoreCase(STRING_BOOLEAN_VALUE);
    }

    /**
     * Map string to integer integer.
     *
     * @param value the value
     * @return the integer
     */
    private Integer mapStringToInteger(String value) {
        Integer number = null;
        try {
            number = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw e;
        }
        return number;
    }

    /**
     * Gets property attributes.
     *
     * @return the property attributes
     */
    public String getPropertyAttributes() {
        return this.property.toString();
    }

    /**
     * Gets business attributes.
     *
     * @return the business attributes
     */
    public String getBusinessAttributes() {
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
        return this.property;
    }

    /**
     * Gets request date.
     *
     * @return the request date
     */
    public LocalDate getRequestDate() {
        return this.requestDate;
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
        return Objects.equals(requestDate, request.requestDate) && Objects.equals(agent, request.agent) && Objects.equals(business, request.business) && Objects.equals(property, request.property) && Objects.equals(ownerEmail, request.ownerEmail);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(requestDate, agent, business, property, ownerEmail);
    }

//    public String getId() {
//        return this.id.toString();
//    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Gets acceptance status.
     *
     * @return the acceptance status
     */
    public Boolean getValidationStatus(){
        return validationStatus;
    }

    /**
     * Sets validation status.
     *
     * @param validationStatus the validation status
     */
    public void setValidationStatus(Boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    /**
     * Has agent with email boolean.
     *
     * @param agentEmail the agent email
     * @return the boolean
     */
    public Boolean hasAgentWithEmail(String agentEmail) {
        return this.getAgentEmail().equals(agentEmail);
    }

    /**
     * Gets agent email.
     *
     * @return the agent email
     */
    public String getAgentEmail() {
        return this.agent.getEmailAddress().getEmail();
    }

    /**
     * Returns the owner email of the order.
     *
     * @return the owner email
     */
    public Email getOwnerEmail() {
        return this.ownerEmail;
    }


    /**
     * Has id boolean.
     *
     * @param requestId the request id
     * @return the boolean
     */
    public Boolean hasId(Integer requestId) {
        return this.getId().equals(requestId);
    }

    /**
     * Send email boolean.
     *
     * @return the boolean
     */
    public Boolean sendEmail() {
        String ownerEmail = getOwnerEmail().toString();
        EmailNotification email = new EmailNotification();
        return email.sendNotification(ownerEmail, "Property Announcement Request Acceptance\n\n", getNotificationMessage());
    }

    /**
     * This method returns the email message, that will be sent to the owner.
     */
    private String getNotificationMessage() {
        return String.format("The property announcement request submitted in " + getRequestDate().toString() + " has been analysed. " +
                "Your request was declined. \n\nHere is the justification message:\n" + defineJustificationMessage(justificationMessage));
    }

    /**
     * Define justification message string.
     *
     * @param message the message
     * @return the string
     */
    public String defineJustificationMessage(String message) {
        return this.justificationMessage = message;
    }

    /**
     * Sets responsible agent.
     *
     * @param agent the agent
     */
    public void setResponsibleAgent(Employee agent) {
        this.agent = agent;
    }

    /**
     * Sets owner email.
     *
     * @param ownerEmail the owner email
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void setOwnerEmail(String ownerEmail) throws IllegalArgumentException {
        this.ownerEmail = new Email(ownerEmail);
    }

    /**
     * This method checks if its a sale request of a residence (apartment or house).
     *
     * @return {@code true} if its sale request of a residence (apartment or house); {@code false} otherwise;
     */
    public boolean isSaleResidence() {
        return (this.business.getBusinessType().getDesignation().equalsIgnoreCase("sale")) && (this.property instanceof Residence);
    }

    /**
     * This method returns the value of the specified variable (attribute)
     *
     * @param variable - the variable/attribute of the desired value.
     * @return the specified variable value
     */
    public Double getVariableValueByDesignation(String variable) {
        return this.property.getVariable(variable);
    }

    /**
     * This method returns a list with the values of the variables for the regression model analysis.
     * Specifically, the values of the area, distance from a city center, number of bedrooms, bathrooms and parking spaces.
     *
     * @return the list with the variables values.
     */
    public List<Double> getVariablesValue() {
        List<Double> variablesValue = new ArrayList<>();

        variablesValue.add(this.property.getArea());
        variablesValue.add(this.property.getDistanceCityCenter());
        variablesValue.add(((Residence) this.property).getNumberBedroom().doubleValue());
        variablesValue.add(((Residence) this.property).getNumberBathroom().doubleValue());
        variablesValue.add(((Residence) this.property).getParkingSpace().doubleValue());

        return variablesValue;
    }
}
