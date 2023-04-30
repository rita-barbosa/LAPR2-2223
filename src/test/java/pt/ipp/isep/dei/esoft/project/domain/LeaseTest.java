package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaseTest {

    @Test
    void ensureGetContractDurationWorks() {
        Lease lease = new Lease(12, "lease", 200.0);

        assertEquals(lease.getContractDuration(),12);
    }

    @Test
    void ensureSetContractDurationWorks() {
        Lease lease = new Lease(12, "lease", 200.0);
        lease.setContractDuration(15);

        assertEquals(lease.getContractDuration(),15);
    }

    @Test
    void ensureToStringWorks() {
        Lease lease = new Lease(12, "lease", 200.0);
        String expected = "Type of Business: lease\n" +
                "Rent: $200,00\n" +
                "Contract Duration: 12\n";

        assertEquals(expected, lease.toString());
    }
}