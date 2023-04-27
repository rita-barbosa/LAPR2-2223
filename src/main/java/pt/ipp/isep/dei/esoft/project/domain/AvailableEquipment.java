package pt.ipp.isep.dei.esoft.project.domain;

public class AvailableEquipment {
    private String description;

    public AvailableEquipment() {
    }

    public AvailableEquipment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public AvailableEquipment clone() {
        return new AvailableEquipment(this.description);
    }
}
