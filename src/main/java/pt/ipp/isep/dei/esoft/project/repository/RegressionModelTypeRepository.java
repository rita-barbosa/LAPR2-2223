package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModelType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing RegressionModelType objects.
 */
public class RegressionModelTypeRepository implements Serializable {
    /**
     * The list of regression model types.
     */
    private List<RegressionModelType> regressionModelTypes = new ArrayList<>();


    /**
     * This method adds a new regression model type to the list of property types.
     *
     * @param regressionModelType - the regression model type to be added
     * @return the regression model type that was added to the list
     */
    public Optional<RegressionModelType> add(RegressionModelType regressionModelType) {
        Optional<RegressionModelType> newCommissionType = Optional.empty();
        boolean success = false;

        if (validateRegressionModelType(regressionModelType)) {
            newCommissionType = Optional.of(regressionModelType.clone());
            success = regressionModelTypes.add(newCommissionType.get());
        }
        if (!success) {
            newCommissionType = Optional.empty();
        }
        return newCommissionType;
    }

    /**
     * This method verifies if the regression model type received is already present in the list of regression model types.
     *
     * @param regressionModelType - the regression model type to be validated
     * @return {@code true} if the list of regression model types does not contain the received regression model type; {@code false} otherwise.
     */
    private boolean validateRegressionModelType(RegressionModelType regressionModelType) {
        return (!(regressionModelTypes.contains(regressionModelType)));
    }

    /**
     * Returns the list of regression model types.
     * @return the list of regression model types.
     */
    public List<RegressionModelType> getRegressionModelTypes() {
        return regressionModelTypes;
    }
}
