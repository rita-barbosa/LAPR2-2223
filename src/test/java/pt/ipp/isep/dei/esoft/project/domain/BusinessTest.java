package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {

    @Test
    void ensureGetBusinessTypeWorks() {
        Business b1 = new Business("Business Type", 2.0);

        assertEquals(b1.getBusinessType(), new BusinessType("Business Type"));
    }

    @Test
    void ensureGetPriceWorks() {
        Business b1 = new Business("Business Type", 2.0);

        assertEquals(b1.getPrice(), 2.0);
    }

    @Test
    void ensureToStringWorksForLease() {
        Business b1 = new Business("Lease", 2.0);
        String expected = "Type of Business: Lease\nRent: $2,00\n";

        assertEquals(expected, b1.toString());
    }

    @Test
    void ensureToStringWorksForSale() {
        Business b1 = new Business("Sale", 2.0);
        String expected = "Type of Business: Sale\nPrice: $2,00\n";

        assertEquals(expected, b1.toString());
    }
}