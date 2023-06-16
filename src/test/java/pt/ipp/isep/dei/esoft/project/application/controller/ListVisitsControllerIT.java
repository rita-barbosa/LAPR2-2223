package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Visit;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListVisitsControllerIT {

    @Test
    void ensureGetVisitRequestsListWorks() {
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        AgencyRepository agencyRepository = new AgencyRepository();

        ListVisitsController controller = new ListVisitsController(authenticationRepository, agencyRepository);

        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit visit1 = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");

        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        visits.add(visit1);

        assertEquals(visits, visits);
    }

    @Test
    void getSortedVisitRequestList() {
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        AgencyRepository agencyRepository = new AgencyRepository();

        ListVisitsController controller = new ListVisitsController(authenticationRepository, agencyRepository);

        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");
        Visit visit1 = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");

        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        visits.add(visit1);

        assertEquals(visits, visits);
    }
}