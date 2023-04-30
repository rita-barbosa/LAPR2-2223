package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CriteriaRepositoryTest {

    @Test
    void ensureGetCriteriaListWorks() {
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        String cr1 = "Criteria";
        criteriaRepository.add(cr1);
        int expectedSize = 1;

        int size = criteriaRepository.getCriteriaList().size();
        assertEquals(expectedSize, size);
        assertEquals(cr1, criteriaRepository.getCriteriaList().get(size - 1));

    }

    @Test
    void ensureAddDuplicatedCriteriaFails() {
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        String cr1 = "Criteria";

        criteriaRepository.add(cr1);
        Optional<String> result = criteriaRepository.add(cr1);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureAddCriteriaWorks(){
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        String cr1 = "Criteria";

        Optional<String> returnCr1 = criteriaRepository.add(cr1);
        assertEquals(cr1, returnCr1.get());
    }
}