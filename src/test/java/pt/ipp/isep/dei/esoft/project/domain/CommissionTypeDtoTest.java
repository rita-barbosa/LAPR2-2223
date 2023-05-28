package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
    }

    @Disabled
    @Test
    void testEquals() {
    }

    @Disabled
    @Test
    void testHashCode() {
    }
}