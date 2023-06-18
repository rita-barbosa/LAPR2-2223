package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Multilinear.
 */
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
    private double alpha;
    private List<List<Double>> dealsData;
    private OLSMultipleLinearRegression regression;
    private Integer confidenceLevel;
    private List<Integer> values;
    private double predictionValue;

    private RealMatrix xTXX;

    /**
     * Instantiates a new Multilinear.
     *
     * @param data            the data
     * @param confidenceLevel the confidence level
     * @param values          the values
     */
    public Multilinear(List<List<Double>> data, Integer confidenceLevel, List<Integer> values) {
        this.alpha = (100 - confidenceLevel) / 100.0;
        this.confidenceLevel = confidenceLevel;
        this.dealsData = data;
        this.values = values;
        this.regression = new OLSMultipleLinearRegression();
        addData();
        this.n = data.size();
        calculateVariablesAverage();
        this.beta = regression.estimateRegressionParameters();
        this.k = beta.length - 1;
        this.SE = regression.calculateResidualSumOfSquares();
        this.ST = regression.calculateTotalSumOfSquares();
        this.SR = this.ST - this.SE;
        this.rSquare = regression.calculateRSquared();   // this.SR / this.ST;
        this.rSquareAdjusted = regression.calculateAdjustedRSquared();  // regression.calculateAdjustedRSquared();
        this.MQR = this.SR / this.k;
        this.MQE = this.SE / (this.n - (this.k + 1));
        this.fObs = MQR / MQE;
        this.fSnedecor = fSnedecor(1 - alpha, (int) this.k, (int) (this.n - (this.k + 1)));
        this.parametersStdErr = regression.estimateRegressionParametersStandardErrors();
        calculatePredictionValue(this.values);
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
            double tC = tStudent(1 - (alpha / 2), (int) (this.n - (this.k + 1)));

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
        report.append(String.format("F-Statistic: %.4f%n", fObs));
        report.append(String.format("F-Distribution: %.4f%n", fSnedecor));
        report.append(compareAnovaSigModel());
        report.append(String.format("%n[-----Confidence Intervals-----]%n"));
        report.append(getConfidenceIntervals());
        report.append(getConfidenceIntervalsForEstimated());
        report.append(String.format("%n[-----Hypothesis Tests-----]%n"));
        report.append(getHypothesisTests());

        return report.toString();
    }

    /**
     * This method calculates the confidence intervals
     *
     * @return a string with the confidence intervals information
     */
    private String getConfidenceIntervals() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < this.parametersStdErr.length; i++) {
            double lower = this.beta[i] - (tStudent(1 - (alpha / 2), (int) (this.n - (this.k + 1))) * this.parametersStdErr[i]);
            double upper = this.beta[i] + (tStudent(1 - (alpha / 2), (int) (this.n - (this.k + 1))) * this.parametersStdErr[i]);

            s.append(String.format("#%s | Parameter: %.4f%n", i, this.beta[i]));
            s.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
            s.append(String.format("Standard Error : %.4f%n", this.parametersStdErr[i]));
            s.append(String.format("Lower Values: %.4f%n", lower));
            s.append(String.format("Upper Values: %.4f%n", upper));
            s.append(String.format("IC(%d%%): ] %.2f; %.2f [%n%n",this.confidenceLevel, lower, upper));
        }
        return s.toString();
    }

    private String getConfidenceIntervalsForEstimated() {
        StringBuilder s = new StringBuilder();
        double tc = tStudent(1 - (alpha / 2), (int) (this.n - (this.k - 1)));

        Double lower = this.predictionValue - (Math.sqrt(this.MQE * ((1 + xTXX.getEntry(0, 0))) * tc));
        Double upper = this.predictionValue + (Math.sqrt(this.MQE * ((1 + xTXX.getEntry(0, 0))) * tc));

        s.append(String.format("Estimated Value: %.4f%n", this.predictionValue));
        s.append("-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-\n");
        s.append(String.format("Lower Values: %.4f%n", lower));
        s.append(String.format("Upper Values: %.4f%n", upper));
        s.append(String.format("IC(%d%%): ] %.2f; %.2f [%n%n",this.confidenceLevel, lower, upper));
        return s.toString();
    }

    /**
     * Calculate prediction value.
     *
     * @param values the values
     */
    public void calculatePredictionValue(List<Integer> values) {
        double[][] matrix = matrixX();

        RealMatrix xMatrix = MatrixUtils.createRealMatrix(matrix);

        RealMatrix xMatrixT = MatrixUtils.createRealMatrix(transpose(matrixX()));

        RealMatrix multiplicationXxT = (xMatrixT.multiply(xMatrix));

        RealMatrix inverse = new LUDecomposition(multiplicationXxT).getSolver().getInverse();
        double[][] pams = {{1, values.get(0), values.get(4), values.get(1), values.get(2), values.get(3)}};

        RealMatrix xO = MatrixUtils.createRealMatrix(pams);
        RealMatrix xOT = MatrixUtils.createRealMatrix(transpose(xO.getData()));

        this.xTXX = xO.multiply(inverse).multiply(xOT);

        this.predictionValue = predict((double) values.get(0), (double) values.get(4), (double) values.get(1), (double) values.get(2), (double) values.get(3));
    }



    /**
     * This method adjuts the matrix of X for calculation
     *
     * @return - double[][]
     */
    private double[][] matrixX() {
        int length = matrixX.length;
        double[][] matrixAux = new double[length][matrixX[0].length + 1];
        // X matrix
        for (int i = 0; i < length; i++) {
            matrixAux[i][0] = 1;
            matrixAux[i][1] = this.matrixX[i][0];
            matrixAux[i][2] = this.matrixX[i][1];
            matrixAux[i][3] = this.matrixX[i][2];
            matrixAux[i][4] = this.matrixX[i][3];
            matrixAux[i][5] = this.matrixX[i][4];
        }
        return matrixAux;
    }


    /**
     * This method transposes the matrix
     *
     * @param matrix
     * @return - double[][]
     */
    private double[][] transpose(double[][] matrix) {
        double[][] matrixTransposed = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixTransposed[i][j] = matrix[j][i];
            }
        }
        return matrixTransposed;

    }


    @Override
    public String compareAnovaSigModel() {
        StringBuilder stringBuilder = new StringBuilder();
        if (fObs > fSnedecor) {
            stringBuilder.append(String.format("Decision: [f0 = %.2f] > [f(%.2f,%d,%d) = %.2f]\n", this.fObs, alpha, this.n, (this.n - (this.k + 1)), this.fSnedecor));
            stringBuilder.append(String.format("    > Reject H0: the regression model is significant.\n"));
        } else {
            stringBuilder.append(String.format("Decision: [f0 = %.2f] <= [f(%.2f,%d,%d) = %.2f]\n", this.fObs, alpha, this.n, (this.n - (this.k + 1)), this.fSnedecor));
            stringBuilder.append(String.format("    > Accept H0: the regression model isn't significant, therefore, shouldn't be used.\n"));
        }
        return stringBuilder.toString();
    }
}
