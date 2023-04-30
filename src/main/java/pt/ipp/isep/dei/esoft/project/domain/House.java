package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**
 * The type House.
 */
public class House extends Residence {
    /**
     * The Basement.
     */
    private final Boolean basement;
    /**
     * The Inhabitable loft.
     */
    private final Boolean inhabitableLoft;
    /**
     * The Sun exposure.
     */
    private final Enum<SunExposureTypes> sunExposure;

    /**
     * Instantiates a new House.
     *
     * @param propertyType                  the property type
     * @param availableEquipmentDescription the available equipment descriptions' list
     * @param area                          the area
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
     * @param distanceCityCenter            the distance city center
     * @param uri                           the photographs' uri list
     */
    public House(PropertyType propertyType, List<String> availableEquipmentDescription, Double area,
                 String streetName, String city, String district, String state, String zipCode,
                 Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
                 Integer numberBedroom, Integer numberBathroom, Double distanceCityCenter,
                 List<String> uri) {
        super(propertyType, area, availableEquipmentDescription, streetName, city, district, state, zipCode, parkingSpace,
                numberBedroom, numberBathroom, distanceCityCenter, uri);
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString(){
        return super.toString() + String.format("Basement: %b \nInhabitable Loft: %b \nSun Exposure: %s\n", basement, inhabitableLoft, sunExposure);
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
        House house = (House) o;
        return Objects.equals(basement, house.basement) && Objects.equals(inhabitableLoft, house.inhabitableLoft) && Objects.equals(sunExposure, house.sunExposure);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), basement, inhabitableLoft, sunExposure);
    }
}