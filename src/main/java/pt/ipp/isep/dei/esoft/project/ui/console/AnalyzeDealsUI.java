package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
import pt.ipp.isep.dei.esoft.project.domain.IndependentVariables;
import pt.ipp.isep.dei.esoft.project.domain.dto.RegressionModelTypeDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.StatisticDto;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnalyzeDealsUI implements Runnable {

    private RegressionModelTypeDto regression;
    private String variable;
    private AnalyzeDealsController controller;
    private StatisticDto statisticDto;


    public AnalyzeDealsUI() {
        this.controller = new AnalyzeDealsController();
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        Scanner s = new Scanner(System.in);

        List<RegressionModelTypeDto> list = controller.getRegressionModelTypeList();
        System.out.println("Select a regression model:");
        int idx = Utils.showAndSelectIndex(list, "Regression Models");
        regression = list.get(idx);
        if (regression.getDesignation().equalsIgnoreCase("simple linear")) {
            System.out.println("Select an independent variable:");
            int idx2 = Utils.showAndSelectIndex(Arrays.asList(IndependentVariables.values()), "Independent Variables");
            variable = Arrays.asList(IndependentVariables.values()).get(idx2).toString();
        }

        try {
            statisticDto = controller.getStatisticsAndForecastValues(regression, variable);
            if (statisticDto != null) {
                System.out.println(statisticDto.getReport());
            } else {
                System.out.println("ERROR: There are no sale deals.");
            }
        } catch (ReflectiveOperationException e) {
            System.out.println(e.getMessage());
        }

    }
}