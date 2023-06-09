package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.CommissionTypeDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.CommissionTypeMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommissionTypeMapperTest {

    @Test
    void ensureToDtoWorks() {
        List<CommissionType> commissionTypes = new ArrayList<>();
        String commType1 = "Something";
        CommissionType c1 = new CommissionType(commType1);

        String commType2 = "Nothing";
        CommissionType c2 = new CommissionType(commType2);

        commissionTypes.add(c1);
        commissionTypes.add(c2);

        List<CommissionTypeDto> commissionTypeDtos = new ArrayList<>();
        CommissionTypeDto dto1 = new CommissionTypeDto(c1.getDesignation());
        CommissionTypeDto dto2 = new CommissionTypeDto(c2.getDesignation());
        commissionTypeDtos.add(dto1);
        commissionTypeDtos.add(dto2);

        assertEquals(commissionTypeDtos, CommissionTypeMapper.toDto(commissionTypes));
    }
}