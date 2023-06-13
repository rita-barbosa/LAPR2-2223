package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Multilinear implements RegressionModel {
    private long n;
    private long k;
    private double yAverage;
    private double[] xAverage;
    private double[] matrixY;
    private double[][] matrixX;
    private double[] beta;
    private double SE;
    private double SR;
    private double ST;
    private double rSquare;
    private double rSquareAdjusted;
    private double MQE;
    private double MQR;
    private double fObs;
    private double fSnedecor;
    private double[] parametersStdErr;
    private final double ALPHA = 0.05;
    private List<List<Double>> dealsData;
    private OLSMultipleLinearRegression regression;

    public Multilinear(List<List<Double>> data) {
        this.dealsData = data;
        this.regression = new OLSMultipleLinearRegression();
        addData();
        this.n = data.size();
        calculateVariablesAverage();
        this.beta = regression.estimateRegressionParameters();
        this.k = beta.length;
        this.SE = regression.calculateResidualSumOfSquares();
        this.ST = regression.calculateTotalSumOfSquares();
        this.SR = this.ST - this.SE;
        this.rSquare = regression.calculateRSquared();   // this.SR / this.ST;
        this.rSquareAdjusted = calculateAdjustedRSquared();  // regression.calculateAdjustedRSquared();
        this.MQR = this.SR / this.k;
        this.MQE = this.SE / (this.n - (this.k + 1));
        this.fObs = MQR / MQE;
        this.fSnedecor = fSnedecor(ALPHA, (int) this.k, (int) (this.n - (this.k + 1)));
        this.parametersStdErr = regression.estimateRegressionParametersStandardErrors();
    }

    private double calculateAdjustedRSquared() {
        return (1 - ((double) (this.n - 1) / (this.n - (this.k + 1))) * (1 - this.rSquare));
    }

    @Override
    public void addData() {
        this.matrixY = new double[dealsData.size()];
        this.matrixX = new double[dealsData.size()][dealsData.get(0).size() - 1];

        for (int i = 0; i < dealsData.size(); i++) {
            List<Double> row = dealsData.get(i);
            matrixY[i] = row.get(0);

            for (int j = 1; j < row.size(); j++) {
                matrixX[i][j - 1] = row.get(j);
            }
        }
        this.regression.newSampleData(matrixY, matrixX);
    }

    @Override
    public List<Double> getForecastValues() {
        List<Double> forecastValues = new ArrayList<>();

        for (List<Double> values : dealsData) {

            Double forecast = predict(values.get(1), values.get(2), values.get(3), values.get(4), values.get(5));
            forecastValues.add(forecast);
        }
        return forecastValues;
    }

    private Double predict(Double x1, Double x2, Double x3, Double x4, Double x5) {
        return beta[0] + (beta[1] * x1) + (beta[2] * x2) + (beta[3] * x3) + (beta[4] * x4) + (beta[5] * x5);
    }

    @Override
    public void calculateVariablesAverage() {
        this.xAverage = new double[5];
        double[] xSum = new double[5];
        double ySum = 0.0;

        for (List<Double> point : dealsData) {
            for (int i = 0; i < xSum.length; i++) {
                xSum[i] += point.get(i + 1);
            }
            ySum += point.get(0);
        }

        for (int i = 0; i < xSum.length; i++) {
            this.xAverage[i] = xSum[i] / this.n;
        }
        this.yAverage = ySum / this.n;
    }

    private String getHypothesisTests() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < this.parametersStdErr.length; i++) {
            double tObserved = (this.beta[i] / this.parametersStdErr[i]);
            double tC = tStudent(1 - (ALPHA / 2), (int) (this.n - (this.k + 1)));

            s.append(String.format("%n#%s | Parameter: %.4f%n", i, this.beta[i]));
            s.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
            s.append(String.format("Test: H0: Bj = 0\n      H1: Bj != 0\n"));
            s.append(getHypothesisTestDecision(tObserved, tC));
        }
        return s.toString();
    }

    @Override
    public String getRegressionReport() {
        StringBuilder report = new StringBuilder();

        report.append(String.format("%n[-----Regression Model | Multilinear-----]%n"));
        report.append(String.format("Equation: y = %.2f + (%.2fX\u2081) + (%.2fX\u2082) + (%.2fX\u2083) + (%.2fX\u2084) + (%.2fX\u2085)\n", beta[0], beta[1], beta[2], beta[3], beta[4], beta[5]));
        report.append(String.format("%n[-----Additional Statistics-----]%n"));
        report.append(String.format("n : %d%n", n));
        report.append(String.format("R Squared: %.4f\n", rSquare));
        report.append(String.format("R Squared Adjusted: %.4f\n", rSquareAdjusted));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append(String.format("# Independent Variables Average %n"));
        report.append(String.format("             .X\u2081 Average : %.2f%n", xAverage[0]));
        report.append(String.format("             .X\u2082 Average : %.2f%n", xAverage[1]));
        report.append(String.format("             .X\u2083 Average : %.2f%n", xAverage[2]));
        report.append(String.format("             .X\u2084 Average : %.2f%n", xAverage[3]));
        report.append(String.format("             .X\u2085 Average : %.2f%n", xAverage[4]));
        report.append(String.format("# Dependent Variable Average %n"));
        report.append(String.format("             .Y Average : %.2f%n", xAverage[0]));
        report.append(String.format("%n[-----Significance Model with Anova-----]%n"));
        report.append(String.format("SQT: %.4f\n", ST));
        report.append(String.format("SQR: %.4f\n", SR));
        report.append(String.format("SQE: %.4f\n", SE));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append(String.format("MQR: %.4f%n", MQR));
        report.append(String.format("MQE: %.4f%n", MQE));
        report.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        report.append(String.format("F (observed): %.4f%n", fObs));
        report.append(String.format("F Snedecor: %.4f%n", fSnedecor));
        report.append(compareAnovaSigModel());
        report.append(String.format("%n[-----Confidence Intervals-----]%n"));
        report.append(getConfidenceIntervals());
        report.append(String.format("%n[-----Hypothesis Tests-----]%n"));
        report.append(getHypothesisTests());

        return report.toString();
    }

    private String getConfidenceIntervals() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < this.parametersStdErr.length; i++) {
            double lower = this.beta[i] - (tStudent(1 - (ALPHA / 2), (int) (this.n - (this.k + 1))) * this.parametersStdErr[i]);
            double upper = this.beta[i] + (tStudent(1 - (ALPHA / 2), (int) (this.n - (this.k + 1))) * this.parametersStdErr[i]);

            s.append(String.format("#%s | Parameter: %.4f%n", i, this.beta[i]));
            s.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
            s.append(String.format("Standard Error : %.4f%n", this.parametersStdErr[i]));
            s.append(String.format("Lower : %.4f%n", lower));
            s.append(String.format("Upper : %.4f%n", upper));
            s.append(String.format("IC(95%%): ] %.2f; %.2f [%n%n", lower, upper));
        }
        return s.toString();
    }

    @Override
    public String compareAnovaSigModel() {
        StringBuilder stringBuilder = new StringBuilder();
        if (fObs > fSnedecor) {
            stringBuilder.append(String.format("Decision: [f0 = %.2f] > [f(%.2f,%d,%d) = %.2f]\n", this.fObs, ALPHA, this.n, (this.n - (this.k + 1)), this.fSnedecor));
            stringBuilder.append(String.format("    > Reject H0: the regression model is significant.\n"));
        } else {
            stringBuilder.append(String.format("Decision: [f0 = %.2f] <= [f(%.2f,%d,%d) = %.2f]\n", this.fObs, ALPHA, this.n, (this.n - (this.k + 1)), this.fSnedecor));
            stringBuilder.append(String.format("    > Accept H0: the regression model isn't significant, therefore, shouldn't be used.\n"));
        }
        return stringBuilder.toString();
    }
}
