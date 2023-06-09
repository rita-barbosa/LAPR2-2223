package pt.ipp.isep.dei.esoft.project.domain.dto;

/**
 * Represents a Regression Model Type DTO (Data Transfer Object).
 * This class holds the designation of a regression model type.
 */
public class RegressionModelTypeDto {

    /**
     * The designation.
     */
    private String designation;


    /**
     * Constructs a new RegressionModelTypeDto object with the given designation.
     *
     * @param designation the designation of the regression model type
     */
    public RegressionModelTypeDto(String designation) {
        this.designation = designation;
    }

    /**
     * Retrieves the designation of the regression model type.
     *
     * @return the designation of the regression model type
     */
    public String getDesignation() {
        return designation;
    }
}
