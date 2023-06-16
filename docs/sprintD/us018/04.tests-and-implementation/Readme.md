# US 018 - To analyze sale deals of houses and apartments

# 5. Construction (Implementation)


## Class AnalyzeDealsController

```java
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

  
  
```

```java
  private List<List<Double>> getDataForAnalysis(RegressionModelType regressionModelType, String variable) {
        Optional<List<List<Double>>> newList;
        newList = getAgencyRepository().getAgenciesDataList(regressionModelType, variable);
        return newList.orElse(null);
    }
```

```java  
private StatisticDto getRegressionModelStatistics(RegressionModelType regressionModelType, List<List<Double>> dealsDataList) throws ReflectiveOperationException {
        Statistic s = new Statistic(dealsDataList, regressionModelType);
        return StatisticMapper.toDto(s);
        }

```

# 6. Integration and Demo 

> None.

# 7. Observations

> None.





