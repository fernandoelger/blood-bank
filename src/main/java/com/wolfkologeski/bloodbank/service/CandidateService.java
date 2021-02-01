package com.wolfkologeski.bloodbank.service;

import com.wolfkologeski.bloodbank.model.Entity.CandidateEntity;

import java.util.List;
import java.util.Map;

public interface CandidateService {
    void save(List<CandidateEntity> candidates);
    Iterable<CandidateEntity> findAll();
    Map<String, Integer> getCandidatesByState();
    Map<String, Double> getAverageImcByAgeInterval();
    Map<Character,Double> getObesePercentageBySex();
    Map<String, Integer> getAverageAgeByBloodType();
    Map<String, Integer> getDonatorsByBloodType();
}
