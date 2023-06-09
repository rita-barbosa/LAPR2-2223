package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.CommissionTypeDto;

import static org.junit.jupiter.api.Assertions.*;

class CommissionTypeDtoTest {

    @Test
    void ensureGetCommissionTypeWorks() {
        String commissionType = "Something";
        CommissionTypeDto dto = new CommissionTypeDto("Something");

        assertEquals(commissionType, dto.getCommissionType());
    }

    @Test
    void testToString() {
        String commissionType = "Fix";
        CommissionTypeDto dto = new CommissionTypeDto(commissionType);

        String expected = "Commission Type: Fix\n";

        assertEquals(expected, dto.toString());
    }

    @Test
    void testEqualsDifferentObjects() {
        String commissionType1 = "Something";
        String commissionType2 = "Nothing";
        CommissionTypeDto dto1 = new CommissionTypeDto(commissionType1);
        CommissionTypeDto dto2 = new CommissionTypeDto(commissionType2);

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsSameObjects() {
        String commissionType1 = "Something";
        CommissionTypeDto dto1 = new CommissionTypeDto(commissionType1);


        assertEquals(dto1, dto1);
    }

    @Test
    void testHashCodeDifferentObject() {
        String commissionType1 = "Something";
        String commissionType2 = "Nothing";
        CommissionTypeDto dto1 = new CommissionTypeDto(commissionType1);
        CommissionTypeDto dto2 = new CommissionTypeDto(commissionType2);

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeSameObject() {
        String commissionType1 = "Something";
        CommissionTypeDto dto1 = new CommissionTypeDto(commissionType1);

        assertEquals(dto1.hashCode(), dto1.hashCode());
    }
}