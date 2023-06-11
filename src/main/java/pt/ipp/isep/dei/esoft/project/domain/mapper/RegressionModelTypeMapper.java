package pt.ipp.isep.dei.esoft.project.domain.mapper;

import pt.ipp.isep.dei.esoft.project.domain.RegressionModelType;
import pt.ipp.isep.dei.esoft.project.domain.dto.RegressionModelTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps RegressionModelType objects to RegressionModelTypeDto objects and vice-versa.
 */
public class RegressionModelTypeMapper {

    /**
     * Converts a list of RegressionModelType objects to a list of RegressionModelTypeDto objects.
     *
     * @param regressionModelTypeList the list of RegressionModelType objects
     * @return the list of RegressionModelTypeDto objects
     */
    public static List<RegressionModelTypeDto> toDto(List<RegressionModelType> regressionModelTypeList) {
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
    public static RegressionModelTypeDto toDto(RegressionModelType regressionModelType) {
        String designation = regressionModelType.getDesignation();

        return new RegressionModelTypeDto(designation);

    }

    /**
     * Converts a RegressionModelTypeDto object to a RegressionModelType object.
     *
     * @param regressionModelTypeDto the RegressionModelTypeDto object
     * @return the RegressionModelType object
     */
    public static RegressionModelType toModel(RegressionModelTypeDto regressionModelTypeDto) {
        String designation = regressionModelTypeDto.getDesignation();
        return new RegressionModelType(designation);
    }
}
