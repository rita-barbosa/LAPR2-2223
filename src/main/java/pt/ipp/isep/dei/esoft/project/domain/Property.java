package pt.ipp.isep.dei.esoft.project.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Property {

    private final PropertyType propertyType;
    private final Double area;
    private final Double distanceCityCenter;
    private final Location location;

    private final List<Photograph> photograph;

    public Property(PropertyType propertyType, Double area, Double distanceCityCenter, List<String> uri, String streetName,
                    String city, String district, String state, String zipCode) {
        this.propertyType = propertyType;
        this.area = area;
        this.distanceCityCenter = distanceCityCenter;
        this.photograph = new ArrayList<>();
        fillPhotographList(uri);
        this.location = new Location(streetName, city, district, state, zipCode);
    }


    private void fillPhotographList(List<String> uriList) {
        for (String uri : uriList) {
            URI actualUri = URI.create(uri);
            Photograph photo = new Photograph(actualUri);
            addPhotograph(photo);
        }
    }

    private void addPhotograph(Photograph photo) {
        if (validate(photo)) {
            // A clone of the photograph is added to the list of photographs, to avoid side effects and outside manipulation.
            this.photograph.add(photo.clone());
        }
    }

    private boolean validate(Photograph photo) {
        return PhotographDoNotContain(photo);
    }

    private boolean PhotographDoNotContain(Photograph photo) {
        return !this.photograph.contains(photo);
    }

    @Override
    public String toString(){
        StringBuilder request = new StringBuilder(String.format("Property Type: %s\nArea: %f m²\nDistance from city center: %f m\nLocation: %s\nPhotographs:\n", propertyType, area, distanceCityCenter, location.toString()));
        for (Photograph photo : photograph) {
            request.append(String.format("%s\n", photo));
        }
        return request.toString();
    }

}
