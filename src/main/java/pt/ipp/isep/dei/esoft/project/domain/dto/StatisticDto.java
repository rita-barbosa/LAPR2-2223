package pt.ipp.isep.dei.esoft.project.domain.dto;

import pt.ipp.isep.dei.esoft.project.domain.RegressionModelType;

import java.util.List;

public class StatisticDto {
    private List<List<Double>> dealsDataList;

    private String report;

    private List<Double> forecastValues;

    public StatisticDto(String report, List<Double> forecastValues, List<List<Double>> dealsDataList) {
        this.forecastValues = forecastValues;
        this.dealsDataList = dealsDataList;
        this.report = report;
    }

    public List<List<Double>> getDealsDataList() {
        return dealsDataList;
    }

    public String getReport() {
        return report;
    }

    public List<Double> getForecastValues() {
        return forecastValues;
    }

    public String getValuesComparison() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < forecastValues.size(); i++) {
            s.append(String.format("-=-=-=--=-=-=-| %s |-=-=-=--=-=-=-\n", i));
            s.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
            s.append(String.format("Deal Sale Price: $ %.2f\n", dealsDataList.get(i).get(0)));
            s.append(String.format("Forecast value: $ %.2f \n", forecastValues.get(i)));
            s.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n\n");
        }
        return s.toString();
    }
}
