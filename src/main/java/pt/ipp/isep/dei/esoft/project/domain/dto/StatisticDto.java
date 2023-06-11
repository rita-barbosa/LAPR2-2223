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
}
