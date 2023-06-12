package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The type Residence.
 */
public class Residence extends Property implements Serializable {


    /**
     * The Number bedroom.
     */
    private Integer numberBedroom;
    /**
     * The Number bathroom.
     */
    private Integer numberBathroom;
    /**
     * The Parking space.
     */
    private Integer parkingSpace;
    /**
     * The Available equipment.
     */
    private List<AvailableEquipment> availableEquipment;

    /**
     * Instantiates a new Residence.
     *
     * @param propertyType                  the property type
     * @param area                          the area
     * @param availableEquipmentDescription the available equipment description
     * @param streetName                    the street name
     * @param city                          the city
     * @param district                      the district
     * @param state                         the state
     * @param zipCode                       the zip code
     * @param parkingSpace                  the parking space
     * @param numberBedroom                 the number bedroom
     * @param numberBathroom                the number bathroom
     * @param distanceCityCenter            the distance city center
     * @param uri                           the uri
     */
    public Residence(PropertyType propertyType, Double area, List<String> availableEquipmentDescription,
                     String streetName, String city, String district, String state, String zipCode, Integer parkingSpace,
                     Integer numberBedroom, Integer numberBathroom, Double distanceCityCenter, List<String> uri) {

        super(propertyType, area, distanceCityCenter, uri, streetName, city, district, state, zipCode);
        this.parkingSpace = parkingSpace;
        this.numberBathroom = numberBathroom;
        this.numberBedroom = numberBedroom;
        this.availableEquipment = new ArrayList<>();
        fillAvailableEquipmentList(availableEquipmentDescription);
    }

    /**
     * Instantiates a new Residence.
     *
     * @param propertyType        the property type
     * @param area                the area
     * @param distanceCityCenter  the distance city center
     * @param location            the location
     * @param numberBedrooms      the number bedrooms
     * @param numberBathrooms     the number bathrooms
     * @param numberParkingSpaces the number parking spaces
     * @param centralHeating      the central heating
     * @param airConditioning     the air conditioning
     */
    public Residence(PropertyType propertyType, Double area, Double distanceCityCenter, String location,
                     Integer numberBedrooms, Integer numberBathrooms, Integer numberParkingSpaces, boolean centralHeating, boolean airConditioning) {
        super(propertyType, area, distanceCityCenter, location);
        this.numberBedroom = numberBedrooms;
        this.numberBathroom = numberBathrooms;
        this.parkingSpace = numberParkingSpaces;
        this.availableEquipment = new ArrayList<>();
        List<String> availableEquipments = new ArrayList<>();
        if (centralHeating) {
            availableEquipments.add(AvailableEquipment.CENTRAL_HEATING);
            fillAvailableEquipmentList(availableEquipments);
        }
        if (airConditioning) {
            availableEquipments.add(AvailableEquipment.AIR_CONDITIONING);
            fillAvailableEquipmentList(availableEquipments);
        }
    }


    /**
     * Fill available equipment list.
     *
     * @param availableEquipmentDescription the available equipment description
     */
    private void fillAvailableEquipmentList(List<String> availableEquipmentDescription) {
        if (availableEquipmentDescription != null) {
            for (String description : availableEquipmentDescription) {
                AvailableEquipment equipment = new AvailableEquipment(description);
                addAvailableEquipment(equipment);
            }
        }
    }

    /**
     * Add available equipment.
     *
     * @param equipment the equipment
     */
    private void addAvailableEquipment(AvailableEquipment equipment) {
        if (validate(equipment)) {
            this.availableEquipment.add(equipment.clone());
        }
    }

    /**
     * Validate boolean.
     *
     * @param equipment the equipment
     * @return the boolean
     */
    private boolean validate(AvailableEquipment equipment) {
        return availableEquipmetsDoNotContain(equipment);
    }

    /**
     * Available equipmets do not contain boolean.
     *
     * @param equipment the equipment
     * @return the boolean
     */
    private boolean availableEquipmetsDoNotContain(AvailableEquipment equipment) {
        return !this.availableEquipment.contains(equipment);
    }

    /**
     * Gets available equipment.
     *
     * @return the available equipment
     */
    public List<AvailableEquipment> getAvailableEquipment() {
        return availableEquipment;
    }

    /**
     * Gets number bedroom.
     *
     * @return the number bedroom
     */
    public Integer getNumberBedroom() {
        return numberBedroom;
    }

    /**
     * Gets number bathroom.
     *
     * @return the number bathroom
     */
    public Integer getNumberBathroom() {
        if (numberBathroom == null) {
            return 0;
        }
        return numberBathroom;
    }

    /**
     * Gets parking space.
     *
     * @return the parking space
     */
    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public Double getVariable(String variable) {
        super.getVariable(variable);
        switch (variable.toLowerCase()) {
            case "area":
                return this.getArea();
            case "distance of city center":
                return this.getDistanceCityCenter();
            case "number of bedrooms":
                return this.getNumberBedroom().doubleValue();
            case "number of bathrooms":
                return this.getNumberBathroom().doubleValue();
            case "number of parking spaces":
                return this.getParkingSpace().doubleValue();
            default:
                return -1d;
        }
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        if (numberBathroom == null) {
            numberBathroom = 0;
        }
        StringBuilder residence = new StringBuilder(super.toString() + String.format("Number of Bedrooms: %d \nNumber of Bathrooms: %d \nParking Spaces: %d \nAvailable Equipments:\n", numberBedroom, numberBathroom, parkingSpace));
        for (AvailableEquipment equipment : availableEquipment) {
            residence.append(String.format("%s\n", equipment));
        }
        return residence.toString();
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
        if (!super.equals(o)) return false;
        Residence residence = (Residence) o;
        return Objects.equals(numberBedroom, residence.numberBedroom) && Objects.equals(numberBathroom, residence.numberBathroom) && Objects.equals(parkingSpace, residence.parkingSpace) && Objects.equals(availableEquipment, residence.availableEquipment);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberBedroom, numberBathroom, parkingSpace, availableEquipment);
    }
}
