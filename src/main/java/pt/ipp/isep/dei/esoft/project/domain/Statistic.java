package pt.ipp.isep.dei.esoft.project.domain;

import java.lang.reflect.Constructor;
import java.util.List;

public class Statistic {

    private List<List<Double>> dealsDataList;

    private String report;

    private RegressionModelType regressionModelType;

    private List<Double> forecastValues;


    public Statistic(List<List<Double>> dealsDataList, RegressionModelType regressionModelType) throws ReflectiveOperationException {
        this.dealsDataList = dealsDataList;
        this.regressionModelType = regressionModelType;
        String className = "pt.ipp.isep.dei.esoft.project.domain." + regressionModelType.getDesignation().replace(" ", "");
        try {
            Class<?> oClass = Class.forName(className);
            if (RegressionModel.class.isAssignableFrom(oClass)) {
                Constructor<?> constructor = oClass.getDeclaredConstructor(List.class);
                RegressionModel r = (RegressionModel) constructor.newInstance(dealsDataList);
                this.report = r.getRegressionReport();
                this.forecastValues = r.getForecastValues();
            }
        } catch (ReflectiveOperationException e) {
            throw new ReflectiveOperationException("ERROR: Invalid Regression Model Type (" + className + " is not available.)");
        }
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
