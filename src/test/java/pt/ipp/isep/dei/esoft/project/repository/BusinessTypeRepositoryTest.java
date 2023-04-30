package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Business;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTypeRepositoryTest {

    @Test
    void ensureGetBusinessTypeListWorks() {
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        BusinessType bt1 = new BusinessType("Business Type");
        businessTypeRepository.add(bt1);
        int expectedSize = 1;

        int size = businessTypeRepository.getBusinessTypeList().size();
        assertEquals(expectedSize, size);
        assertEquals(bt1, businessTypeRepository.getBusinessTypeList().get(size - 1));
    }

    @Test
    void ensureGetBusinessTypeByDesignationEmptyList() {
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        String businessTypeDesignation = "Business Type";

        assertThrows(IllegalArgumentException.class,
                () -> businessTypeRepository.getBusinessTypeByDesignation(businessTypeDesignation));

    }

    @Test
    void ensureGetBusinessTypeByDesignation() {
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        BusinessType bt1 = new BusinessType("Business Type");

        businessTypeRepository.add(bt1);
        Optional<BusinessType> returnBt1 = businessTypeRepository.getBusinessTypeByDesignation("Business Type");

        assertEquals(bt1, returnBt1.get());
    }

    @Test
    void ensureAddDuplicatedBusinessTypeFails() {
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        BusinessType bt1 = new BusinessType("Business Type");

        businessTypeRepository.add(bt1);
        Optional<BusinessType> result = businessTypeRepository.add(bt1);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureAddBusinessTypeWorks() {
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        BusinessType bt1 = new BusinessType("Business Type");

        businessTypeRepository.add(bt1);
        Optional<BusinessType> returnBt1 = businessTypeRepository.getBusinessTypeByDesignation("Business Type");

        assertEquals(bt1, returnBt1.get());
        assertNotSame(bt1, returnBt1.get());
    }
}