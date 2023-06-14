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
//        String className = "pt.ipp.isep.dei.esoft.project.domain." + regressionModelType.getDesignation().replace(" ", "");
//        try {
//            Class<?> oClass = Class.forName(className);
//            if (RegressionModel.class.isAssignableFrom(oClass)) {
//                Constructor<?> constructor = oClass.getDeclaredConstructor(List.class);
//                RegressionModel r = (RegressionModel) constructor.newInstance(dealsDataList);
//                this.report = r.getRegressionReport();
//                this.forecastValues = r.getForecastValues();
//            }
//        } catch (ReflectiveOperationException e) {
//            throw new ReflectiveOperationException("ERROR: Invalid Regression Model Type (" + className + " is not available.)");
//        }
        if (regressionModelType.isSimpleLinear()) {
            SimpleLinear s = new SimpleLinear(dealsDataList);
            this.report = s.getRegressionReport();
            this.forecastValues = s.getForecastValues();
        } else if (regressionModelType.isMultilinear()) {
            Multilinear m = new Multilinear(dealsDataList);
            this.report = m.getRegressionReport();
            this.forecastValues = m.getForecastValues();

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
