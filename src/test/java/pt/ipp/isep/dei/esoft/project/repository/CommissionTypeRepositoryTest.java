package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.CommissionType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommissionTypeRepositoryTest {

    @Test
    void ensureGetCommissionTypeListWorks() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        CommissionType c1 = new CommissionType("Commission Type");
        commissionTypeRepository.add(c1);
        int expectedSize = 1;

        int size = commissionTypeRepository.getCommissionTypeList().size();
        assertEquals(expectedSize, size);
        assertEquals(c1, commissionTypeRepository.getCommissionTypeList().get(size - 1));
    }


    @Test
    void ensureGetCommissionTypeByDesignationWorks() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        CommissionType c1 = new CommissionType("Commission Type");
        commissionTypeRepository.add(c1);
        Optional<CommissionType> returnC1 = commissionTypeRepository.getCommissionTypeByDesignation("Commission Type");

        assertEquals(c1, returnC1.get());
    }

    @Test
    void getCommissionTypeByDesignationEmptyList() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        String commissionTypeDesignation = "Commission Type";

        assertThrows(IllegalArgumentException.class,
                () -> commissionTypeRepository.getCommissionTypeByDesignation(commissionTypeDesignation));
    }

    @Test
    void ensureAddCommissionTypeWorks() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        CommissionType c1 = new CommissionType("Commission Type");
        commissionTypeRepository.add(c1);
        Optional<CommissionType> returnC1 = commissionTypeRepository.getCommissionTypeByDesignation("Commission Type");

        assertEquals(c1, returnC1.get());
        assertNotSame(c1, returnC1);
    }

    @Test
    void ensureAddDuplicatedCommissionTypeFails() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        CommissionType c1 = new CommissionType("Commission Type");
        commissionTypeRepository.add(c1);
        Optional<CommissionType> result = commissionTypeRepository.add(c1);

        assertTrue(result.isEmpty());
    }


}