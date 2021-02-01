package com.wolfkologeski.bloodbank.utils;
import org.apache.commons.math3.util.Precision;

// In order to populate a hash map, this class is used to calculate the average IMC by age interval
public class ImcSumOverCandidatesCount {

    private Double imcSum;
    private int candidateCount;

    public ImcSumOverCandidatesCount() {
        imcSum = 0.0;
        candidateCount = 0;
    }

    public void addImc(Double imc) {
        imcSum += imc;
    }

    public void incrementCandidateCount() {
        candidateCount++;
    }

    public Double getAverageImc() {

        if (imcSum == 0) {
            return 0.0;
        }
        return Precision.round(imcSum / candidateCount, 2);
    }
}
