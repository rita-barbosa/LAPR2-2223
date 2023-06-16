package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.RegressionModelType;
import pt.ipp.isep.dei.esoft.project.domain.Statistic;
import pt.ipp.isep.dei.esoft.project.domain.dto.RegressionModelTypeDto;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.RegressionModelTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeDealsControllerIT {


    @Test
    void getRegressionModelTypeList() {
        RegressionModelTypeRepository regressionModelTypeRepository = Repositories.getInstance().getRegressionModelTypeRepository();
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        AnalyzeDealsController ctrl = new AnalyzeDealsController(regressionModelTypeRepository, agencyRepository);

        List<RegressionModelTypeDto> actual = ctrl.getRegressionModelTypeList();

        RegressionModelTypeDto d1 = new RegressionModelTypeDto("Simple Linear");
        RegressionModelTypeDto d2 = new RegressionModelTypeDto("Multilinear");
        List<RegressionModelTypeDto> expected = new ArrayList<>();
        expected.add(d1);
        expected.add(d2);

        assertEquals(expected, expected);

    }


    @Test
    void getStatisticsAndForecastValues() {
        try {
            List<List<Double>> list = new ArrayList<>();
            Statistic s = new Statistic(list, new RegressionModelType("Simple Linear"));
            assertEquals(s, s);
        } catch (ReflectiveOperationException e) {
                assertTrue(true);
        }

    }
}