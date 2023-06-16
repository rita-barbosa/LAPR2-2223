package pt.ipp.isep.dei.esoft.project.domain.dto;

/**
 * The type Criteria dto.
 */
public class CriteriaDto {

    private String designation;

    /**
     * Instantiates a new Criteria dto.
     *
     * @param designation the designation
     */
    public CriteriaDto(String designation) {
        this.designation = designation;
    }

    /**
     * Gets designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    @Override
    public String toString() {
        return designation;
    }
}
