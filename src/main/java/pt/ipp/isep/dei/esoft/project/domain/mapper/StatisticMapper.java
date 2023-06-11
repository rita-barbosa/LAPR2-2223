package pt.ipp.isep.dei.esoft.project.domain.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Statistic;
import pt.ipp.isep.dei.esoft.project.domain.dto.StatisticDto;

import java.util.List;

public class StatisticMapper {


    public static StatisticDto toDto(Statistic statistic) {
        String report = statistic.getReport();
        List<Double> forecastValues = statistic.getForecastValues();
        List<List<Double>> dealsDataList = statistic.getDealsDataList();

        return new StatisticDto(report, forecastValues, dealsDataList);
    }
}
