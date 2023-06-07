package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps RegressionModelType objects to RegressionModelTypeDto objects.
 */
public class RegressionModelTypeMapper {

    /**
     * Converts a list of RegressionModelType objects to a list of RegressionModelTypeDto objects.
     *
     * @param regressionModelTypeList the list of RegressionModelType objects
     * @return the list of RegressionModelTypeDto objects
     */
    public List<RegressionModelTypeDto> toDto(List<RegressionModelType> regressionModelTypeList) {
        List<RegressionModelTypeDto> regressionModelTypeDtoList = new ArrayList<>();

        for (RegressionModelType rmt : regressionModelTypeList) {
            regressionModelTypeDtoList.add(toDto(rmt));
        }

        return regressionModelTypeDtoList;
    }

    /**
     * Converts a RegressionModelType object to a RegressionModelTypeDto object.
     *
     * @param regressionModelType the RegressionModelType object
     * @return the RegressionModelTypeDto object
     */
    public RegressionModelTypeDto toDto(RegressionModelType regressionModelType) {
        String designation = regressionModelType.getDesignation();

        return new RegressionModelTypeDto(designation);

    }
}
