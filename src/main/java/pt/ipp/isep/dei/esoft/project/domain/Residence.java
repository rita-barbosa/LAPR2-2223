package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Residence extends Property {

    private final Integer numberBedroom;
    private final Integer numberBathroom;
    private final Integer parkingSpace;
    private final List<AvailableEquipment> availableEquipment;

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

    private void fillAvailableEquipmentList(List<String> availableEquipmentDescription) {
        for (String description : availableEquipmentDescription) {
            AvailableEquipment equipment = new AvailableEquipment(description);
            addAvailableEquipment(equipment);
        }
    }

    private void addAvailableEquipment(AvailableEquipment equipment) {
        if (validate(equipment)) {
            // A clone of the equipment is added to the list of equipments, to avoid side effects and outside manipulation.
            this.availableEquipment.add(equipment.clone());
        }
    }

    private boolean validate(AvailableEquipment equipment) {
        return availableEquipmetsDoNotContain(equipment);
    }

    private boolean availableEquipmetsDoNotContain(AvailableEquipment equipment) {
        return !this.availableEquipment.contains(equipment);
    }

    public List<AvailableEquipment> getAvailableEquipment() {
        return availableEquipment;
    }

    public Integer getNumberBedroom() {
        return numberBedroom;
    }

    public Integer getNumberBathroom() {
        return numberBathroom;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public String toString(){ //fix
        return "a";
    }
}
