package pt.ipp.isep.dei.esoft.project.domain;


import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinear implements RegressionModel {

    private long n;
    private double intercept;
    private double slope;
    private double rSquare;
    private double r;
    private double xAverage;
    private double yAverage;

    private double Sxx;
    private double Syy;
    private double Sxy;
    private double SE;
    private double SR;
    private double ST;
    private double MSE;
    private double MSR;
    private double fObs;
    private double fSnedecor;
    private long degreeOfFreedom;
    private double slopeStdErr;

    private double interceptStdErr;

    private final double HYPOTHESIS_TEST_VALUE = 0;
    private final double ALPHA = 0.05;
    private List<List<Double>> dealsData;
    private SimpleRegression regression;

    public SimpleLinear(List<List<Double>> data) {
        this.dealsData = data;
        this.regression = new SimpleRegression();
        addData();
        n = this.regression.getN();
        degreeOfFreedom = this.n - 2;
        intercept = this.regression.getIntercept();
        slope = this.regression.getSlope();
        rSquare = this.regression.getRSquare();
        r = this.regression.getR();
        calculateVariablesAverage();
        Sxx = this.regression.getXSumSquares();
        Syy = this.regression.getTotalSumSquares();
        Sxy = this.regression.getSumOfCrossProducts();
        SE = this.regression.getSumSquaredErrors();
        SR = this.regression.getRegressionSumSquares();
        ST = SE + SR;
        MSR = SR; // MSR = SR / 1 -> to simplify we automatically assigned SR to MSR
        MSE = SE / degreeOfFreedom;
        fObs = MSR / MSE;
        System.out.println(degreeOfFreedom);
        fSnedecor = fSnedecor(1-ALPHA, 1, (int) degreeOfFreedom);
        slopeStdErr = this.regression.getSlopeStdErr();
        interceptStdErr = this.regression.getInterceptStdErr();

    }

    @Override
    public List<Double> getForecastValues() {
        List<Double> forecastValues = new ArrayList<>();
        for (List<Double> values : dealsData) {
            Double forecast = regression.predict(values.get(1));
            forecastValues.add(forecast);
        }
        return forecastValues;
    }

    @Override
    public String getRegressionReport() {
        StringBuilder report = new StringBuilder();

        report.append(String.format("%n[-----Regression Model | Simple Linear-----]%n"));
        report.append(String.format("Equation : y = %.2f + (%.2fx)  %n", intercept, slope));
        report.append(String.format("Intercept : %.4f%n", intercept));
        report.append(String.format("Slope : %.4f%n", slope));
        report.append(String.format("%n[-----Additional Statistics-----]%n"));
        report.append(String.format("n : %d%n", n));
        report.append(String.format("Independent Variables Average (x): %.4f%n", xAverage));
        report.append(String.format("Dependent Variable Average (y) : %.4f%n", yAverage));
        report.append(String.format("R^2 : %.4f%n", rSquare));
        report.append(String.format("R : %.4f%n", r));
        report.append(String.format("Sxx: %.4f%n", Sxx));
        report.append(String.format("Syy: %.4f%n", Syy));
        report.append(String.format("Sxy: %.4f%n", Sxy));
        report.append(String.format("%n[-----Significance Model with Anova-----]%n")); //
        report.append(String.format("SE: %.4f%n", SE));
        report.append(String.format("SR: %.4f%n", SR));
        report.append(String.format("ST: %.4f%n", ST));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append(String.format("MSR: %.4f%n", MSR));
        report.append(String.format("MSE: %.4f%n", MSE));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append(String.format("F - Statistic: %.4f%n", fObs));
        report.append(String.format("F - Distribution: %.4f%n", fSnedecor));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append(String.format("Test: H0 : b = b0 %n      H1 : b != b0%n"));
        report.append(compareAnovaSigModel());
        report.append(String.format("%n[-----Confidence Intervals-----]%n"));
        report.append("## For slope:\n");
        report.append(getConfidenceIntervals(slopeStdErr, slope));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append("## For intercept:\n");
        report.append(getConfidenceIntervals(interceptStdErr, intercept));
        report.append(String.format("%n[-----Hypothesis Tests-----]%n"));
        report.append("## For slope:\n");
        report.append(slopeHypothesisTest());
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append("## For intercept:\n");
        report.append(interceptHypothesisTest());

        return report.toString();
    }

    private String slopeHypothesisTest() {
        StringBuilder stringBuilder = new StringBuilder();
        double tc = tStudent(1 - (ALPHA / 2), (int) degreeOfFreedom);
        double S = Math.sqrt(this.SE / degreeOfFreedom);
        double tObs = ((slope - HYPOTHESIS_TEST_VALUE) / (S / Math.sqrt(Sxx)));

        stringBuilder.append(String.format("s : %.4f %n", S));
        stringBuilder.append(String.format("Test: H0: b = 0\n      H1: b != 0\n"));
        stringBuilder.append(getHypothesisTestDecision(tObs, tc));
        return stringBuilder.toString();
    }

    private String interceptHypothesisTest() {
        StringBuilder stringBuilder = new StringBuilder();
        double tc = tStudent(1 - (ALPHA / 2), (int) degreeOfFreedom);
        double S = Math.sqrt(this.SE / degreeOfFreedom);
        double tObs = (intercept - HYPOTHESIS_TEST_VALUE) / (S * Math.sqrt((1.0 / n) + ((xAverage * xAverage) / Sxx)));

        stringBuilder.append(String.format("s : %.4f %n", S));
        stringBuilder.append(String.format("Test: H0: a = 0\n      H1: a != 0\n"));
        stringBuilder.append(getHypothesisTestDecision(tObs, tc));
        return stringBuilder.toString();
    }

    @Override
    public String compareAnovaSigModel() {
        StringBuilder stringBuilder = new StringBuilder();
        if (fObs > fSnedecor) {
            stringBuilder.append(String.format("Decision: [f0 = %.2f] > [f(%.2f; %d; %d) = %.2f]\n", fObs, ALPHA, 1, degreeOfFreedom, fSnedecor));
            stringBuilder.append(String.format("    > Reject H0: the regression model is significant.\n"));
        } else {
            stringBuilder.append(String.format("Decision: [f0 = %.2f] <= [f(%.2f; %d; %d) = %.2f]\n", fObs, ALPHA, 1, degreeOfFreedom, fSnedecor));
            stringBuilder.append(String.format("    > Accept H0: the regression model isn't significant, therefore, shouldn't be used.\n"));
        }
        return stringBuilder.toString();
    }


    @Override
    public void calculateVariablesAverage() {
        double xSum = 0.0;
        double ySum = 0.0;
        int numPoints = dealsData.size();

        for (List<Double> point : dealsData) {
            double x = point.get(1);
            double y = point.get(0);
            xSum += x;
            ySum += y;
        }

        this.xAverage = xSum / numPoints;
        this.yAverage = ySum / numPoints;
    }


    private String getConfidenceIntervals(double stdErr, double value) {
        StringBuilder s = new StringBuilder();

        double tValue = tStudent(1 - (ALPHA / 2), (int) degreeOfFreedom);
        double lower = value - tValue * stdErr;
        double upper = value + tValue * stdErr;

        s.append(String.format("Standard error : %.4f%n", stdErr));
        s.append(String.format("Lower Value : %.4f%n", lower));
        s.append(String.format("Upper Value: %.4f%n", upper));
        s.append(String.format("IC(95%%): ] %.2f; %.2f [%n", lower, upper));

        return s.toString();
    }

    @Override
    public void addData() {
        for (List<Double> point : dealsData) {
            double x = point.get(1);
            double y = point.get(0);
            this.regression.addData(x, y);
        }
    }
}

