package pt.ipp.isep.dei.esoft.project.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Property.
 */
public class Property {

    /**
     * The Property type.
     */
    private final PropertyType propertyType;
    /**
     * The Area.
     */
    private final Double area;
    /**
     * The Distance city center.
     */
    private final Double distanceCityCenter;
    /**
     * The Location.
     */
    private final Location location;

    /**
     * The Photograph.
     */
    private final List<Photograph> photograph;

    /**
     * Instantiates a new Property.
     *
     * @param propertyType       the property type
     * @param area               the area
     * @param distanceCityCenter the distance city center
     * @param uri                the uri
     * @param streetName         the street name
     * @param city               the city
     * @param district           the district
     * @param state              the state
     * @param zipCode            the zip code
     */
    public Property(PropertyType propertyType, Double area, Double distanceCityCenter, List<String> uri, String streetName,
                    String city, String district, String state, String zipCode) {
        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.photograph = new ArrayList<>();
        fillPhotographList(uri);
        this.location = new Location(streetName, city, district, state, zipCode);
    }


    /**
     * Fill photograph list.
     *
     * @param uriList the uri list
     */
    private void fillPhotographList(List<String> uriList) {
        for (String uri : uriList) {
            URI actualUri = URI.create(uri);
            Photograph photo = new Photograph(actualUri);
            addPhotograph(photo);
        }
    }

    /**
     * Add photograph.
     *
     * @param photo the photo
     */
    private void addPhotograph(Photograph photo) {
        if (validate(photo)) {
            // A clone of the photograph is added to the list of photographs, to avoid side effects and outside manipulation.
            this.photograph.add(photo.clone());
        }
    }

    /**
     * Validate boolean.
     *
     * @param photo the photo
     * @return the boolean
     */
    private boolean validate(Photograph photo) {
        return PhotographDoNotContain(photo);
    }

    /**
     * Photograph do not contain boolean.
     *
     * @param photo the photo
     * @return the boolean
     */
    private boolean PhotographDoNotContain(Photograph photo) {
        return !this.photograph.contains(photo);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString(){
        StringBuilder request = new StringBuilder(String.format("Property Type: %s\nArea: %.2f m²\nDistance from city center: %.2f m\nLocation: %s\nPhotographs:\n", propertyType, area, distanceCityCenter, location.toString()));
        for (Photograph photo : photograph) {
            request.append(String.format("%s\n", photo));
        }
        return request.toString();
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
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
        Property property = (Property) o;
        return Objects.equals(propertyType, property.propertyType) && Objects.equals(area, property.area) && Objects.equals(distanceCityCenter, property.distanceCityCenter) && Objects.equals(location, property.location) && Objects.equals(photograph, property.photograph);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(propertyType, area, distanceCityCenter, location, photograph);
    }
}
