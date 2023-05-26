package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommissionTypeMapper {

    public static Optional<List<CommissionTypeDto>> toDto(List<CommissionType> commissionTypes){
        throw new NotImplementedException();
//        List<RequestDto> listCommissionTypesDto = new ArrayList<>();
//        for (CommissionType commissionType : commissionTypes) {
//            CommissionTypeDto dto = toDto(commissionType);
//            listCommissionTypesDto.add(dto);
//        }
//        return Optional.of(listCommissionTypesDto);
    }

    public static CommissionTypeDto toDto(CommissionType commissionType){
        throw new NotImplementedException();
//        String commissionTypeAttributes = commissionType.getDesignation();
//
//        return new CommissionType(commissionTypeAttributes);
    }

}
