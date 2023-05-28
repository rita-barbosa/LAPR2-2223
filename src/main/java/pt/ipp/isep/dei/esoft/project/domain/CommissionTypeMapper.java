package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public static Optional<List<CommissionTypeDto>> toDto(List<CommissionType> commissionTypes){
        List<CommissionTypeDto> listCommissionTypesDto = new ArrayList<>();
        for (CommissionType commissionType : commissionTypes) {
            CommissionTypeDto dto = toDto(commissionType);
            listCommissionTypesDto.add(dto);
        }
        return Optional.of(listCommissionTypesDto);
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
