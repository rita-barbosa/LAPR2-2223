package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RepositoriesTest {
    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetAgencyRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAgencyRepository());
    }

    @Test
    void testGetCommissionTypeRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getCommissionTypeRepository());
    }

    @Test
    void testGetAuthenticationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAuthenticationRepository());
    }
    @Test
    void testGetBusinessTypeRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getBusinessTypeRepository());
    }


    @Test
    void testGetPropertyTypeRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getPropertyTypeRepository());
    }


    @Test
    void testGetPersonRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getPersonRepository());
    }

    @Test
    void testGetCriteriaRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getCriteriaRepository());
    }
}
