package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

import java.util.List;

public interface RegressionModel {


    String getRegressionReport();

    default String getHypothesisTestDecision(double tObs, double tc) {
        StringBuilder s = new StringBuilder();
        s.append(String.format("|t observed = %.2f|\n", tObs));
        s.append(String.format("|     tc = %.2f    |\n", tc));
        if (Math.abs(tObs) > tc) {
            s.append(String.format("Decision: t observed > tc\n    > Rejects H0\n"));
        } else {
            s.append(String.format("Decision: t observed <= tc\n    > Accepts H0\n"));
        }
        return s.toString();
    }

    default double tStudent(double alpha, int degreesOfFreedom) {
        TDistribution td = new TDistribution(degreesOfFreedom);
        return td.inverseCumulativeProbability(alpha);
    }


    default double fSnedecor(double alpha, int numeratorDegreeOfFreedom, int denominatorDegreeOfFreedom) {
        FDistribution fd = new FDistribution(numeratorDegreeOfFreedom, denominatorDegreeOfFreedom);
        return fd.inverseCumulativeProbability(alpha);
    }

    void addData();

    List<Double> getForecastValues();

    void calculateVariablesAverage();

    String compareAnovaSigModel();
}
