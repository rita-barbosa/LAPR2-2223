package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Residence.
 */
public class Residence extends Property {

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
     * Fill available equipment list.
     *
     * @param availableEquipmentDescription the available equipment description
     */
    private void fillAvailableEquipmentList(List<String> availableEquipmentDescription) {
       if (availableEquipmentDescription != null){
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
            // A clone of the equipment is added to the list of equipments, to avoid side effects and outside manipulation.
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
        StringBuilder residence = new StringBuilder(super.toString() + String.format("Number of Bedrooms: %d \nNumber of Bathrooms: %d \nParking Space: %d \nAvailableEquipment:\n", numberBedroom, numberBathroom, parkingSpace));
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
