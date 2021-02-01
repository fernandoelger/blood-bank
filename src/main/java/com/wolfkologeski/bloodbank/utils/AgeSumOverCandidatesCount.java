package com.wolfkologeski.bloodbank.utils;

// In order to populate a hash map, this class is used to calculate the average age by blood type
public class AgeSumOverCandidatesCount {

    private int ageSum;
    private int candidateCount;

    public AgeSumOverCandidatesCount() {
        ageSum = 0;
        candidateCount = 0;
    }

    public void addAge(int age) {
        ageSum += age;
    }

    public void incrementCandidateCount() {
        candidateCount++;
    }

    public int getAverageAge() {

        if (ageSum == 0) {
            return 0;
        }
        return ageSum / candidateCount;
    }
}
