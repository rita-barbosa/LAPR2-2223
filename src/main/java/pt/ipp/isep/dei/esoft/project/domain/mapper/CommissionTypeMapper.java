package pt.ipp.isep.dei.esoft.project.domain.mapper;

import pt.ipp.isep.dei.esoft.project.domain.CommissionType;
import pt.ipp.isep.dei.esoft.project.domain.dto.CommissionTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * The type CommissionType mapper.
 */
public class CommissionTypeMapper {

    /**
     * To dto optional.
     *
     * @param commissionTypes the commissionTypes
     * @return the optional
     */
    public static List<CommissionTypeDto> toDto(List<CommissionType> commissionTypes){
        List<CommissionTypeDto> listCommissionTypesDto = new ArrayList<>();
        for (CommissionType commissionType : commissionTypes) {
            CommissionTypeDto dto = toDto(commissionType);
            listCommissionTypesDto.add(dto);
        }
        return listCommissionTypesDto;
    }

    /**
     * To dto commissionType dto.
     *
     * @param commissionType the commission type
     * @return the commissionType dto
     */
    public static CommissionTypeDto toDto(CommissionType commissionType){
        String commissionTypeAttributes = commissionType.getDesignation();

        return new CommissionTypeDto(commissionTypeAttributes);
    }

}
