package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.RegressionModelType;
import pt.ipp.isep.dei.esoft.project.domain.Statistic;
import pt.ipp.isep.dei.esoft.project.domain.dto.RegressionModelTypeDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.StatisticDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.RegressionModelTypeMapper;
import pt.ipp.isep.dei.esoft.project.domain.mapper.StatisticMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.RegressionModelTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class AnalyzeDealsController {

    private RegressionModelTypeRepository regressionModelTypeRepository;
    private AgencyRepository agencyRepository;

    public AnalyzeDealsController() {
    }

    public AnalyzeDealsController(RegressionModelTypeRepository regressionModelTypeRepository, AgencyRepository agencyRepository) {
        this.regressionModelTypeRepository = regressionModelTypeRepository;
        this.agencyRepository = agencyRepository;
    }

    private RegressionModelTypeRepository getRegressionModelTypeRepository() {
        if (regressionModelTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            regressionModelTypeRepository = repositories.getRegressionModelTypeRepository();
        }
        return regressionModelTypeRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    public List<RegressionModelTypeDto> getRegressionModelTypeList() {
        List<RegressionModelType> regressionModelTypeList = getRegressionModelTypeRepository().getRegressionModelTypes();
        return RegressionModelTypeMapper.toDto(regressionModelTypeList);
    }

    public StatisticDto getStatisticsAndForecastValues(RegressionModelTypeDto regressionModelTypeDto, String variable) throws ReflectiveOperationException {
        RegressionModelType regressionModelType = RegressionModelTypeMapper.toModel(regressionModelTypeDto);
        List<List<Double>> dealsDataList = getDataForAnalysis(regressionModelType, variable);
        if (!dealsDataList.isEmpty()) {
            try {
                return getRegressionModelStatistics(regressionModelType, dealsDataList);
            } catch (ReflectiveOperationException e) {
                throw e;
            }
        } else {
            return null;
        }
    }

    private List<List<Double>> getDataForAnalysis(RegressionModelType regressionModelType, String variable) {
        Optional<List<List<Double>>> newList;
        newList = getAgencyRepository().getAgenciesDataList(regressionModelType, variable);
        return newList.orElse(null);
    }

    private StatisticDto getRegressionModelStatistics(RegressionModelType regressionModelType, List<List<Double>> dealsDataList) throws ReflectiveOperationException {
        Statistic s = new Statistic(dealsDataList, regressionModelType);
        return StatisticMapper.toDto(s);
    }
}
