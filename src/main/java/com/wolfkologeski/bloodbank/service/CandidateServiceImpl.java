package com.wolfkologeski.bloodbank.service;

import com.wolfkologeski.bloodbank.model.Entity.CandidateEntity;
import com.wolfkologeski.bloodbank.repository.CandidateRepository;
import com.wolfkologeski.bloodbank.utils.AgeSumOverCandidatesCount;
import com.wolfkologeski.bloodbank.utils.ImcSumOverCandidatesCount;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import static org.slf4j.LoggerFactory.getLogger;
import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {

    private static Logger LOG = getLogger(CandidateServiceImpl.class);

    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public void save(List<CandidateEntity> candidates) {
        for (CandidateEntity candidate: candidates) {
            candidateRepository.save(candidate);
        }
    }

    @Override
    public Iterable<CandidateEntity> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Map<String, Integer> getCandidatesByState() {
        LOG.info("Getting information from repository");
        List<CandidateEntity> candidatesList = candidateRepository.findAll();
        Map<String, Integer> candidatesMap = new HashMap<>();

        LOG.info("Processing candidates quantity");
        for (CandidateEntity candidate: candidatesList) {

            if(candidatesMap.get(candidate.getEstado()) == null) {
                candidatesMap.put(candidate.getEstado(), 0);
            }
            // increments the amount of candidates by updating the key's value
            candidatesMap.put(candidate.getEstado(), candidatesMap.get(candidate.getEstado()) + 1);
        }
        return candidatesMap;
    }

    @Override
    public Map<String, Double> getAverageImcByAgeInterval() {
        LOG.info("Getting information from repository");
        List<CandidateEntity> candidatesList = candidateRepository.findAll();
        Map<Integer, ImcSumOverCandidatesCount> ageIntervalMap = new HashMap<>();

        ageIntervalMap.put(0,  new ImcSumOverCandidatesCount());
        ageIntervalMap.put(11, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(21, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(31, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(41, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(51, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(61, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(71, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(81, new ImcSumOverCandidatesCount());
        ageIntervalMap.put(91, new ImcSumOverCandidatesCount());

        Double imc;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birthDate;
        int age;
        LOG.info("Processing obese average imc information");
        // increments the candidate's count while add the IMC value to the total IMC sum
        for (CandidateEntity candidate: candidatesList) {
            birthDate = LocalDate.parse(candidate.getData_nasc(), formatter);
            age = Period.between(birthDate, LocalDate.now()).getYears();

            imc = candidate.getPeso() / (Math.pow(candidate.getAltura(), 2));

            if (age < 11) { // 0 to 10 years old
                ageIntervalMap.get(0).addImc(imc);
                ageIntervalMap.get(0).incrementCandidateCount();
            }
            else if (age < 21) { // 11 to 20 years old
                ageIntervalMap.get(11).addImc(imc);
                ageIntervalMap.get(11).incrementCandidateCount();
            }
            else if (age < 31) { // 21 to 30 years old
                ageIntervalMap.get(21).addImc(imc);
                ageIntervalMap.get(21).incrementCandidateCount();
            }
            else if (age < 41) { // 31 to 40 years old
                ageIntervalMap.get(31).addImc(imc);
                ageIntervalMap.get(31).incrementCandidateCount();
            }
            else if (age < 51) { // 41 to 50 years old
                ageIntervalMap.get(41).addImc(imc);
                ageIntervalMap.get(41).incrementCandidateCount();
            }
            else if (age < 61) { // 51 to 60 years old
                ageIntervalMap.get(51).addImc(imc);
                ageIntervalMap.get(51).incrementCandidateCount();
            }
            else if (age < 71) { // 61 to 70 years old
                ageIntervalMap.get(61).addImc(imc);
                ageIntervalMap.get(61).incrementCandidateCount();
            }
            else if (age < 81) { // 71 to 80 years old
                ageIntervalMap.get(71).addImc(imc);
                ageIntervalMap.get(71).incrementCandidateCount();
            }
            else if (age < 91) { // 81 to 90 years old
                ageIntervalMap.get(81).addImc(imc);
                ageIntervalMap.get(81).incrementCandidateCount();
            }
            else { // 91 to 100 years old
                ageIntervalMap.get(91).addImc(imc);
                ageIntervalMap.get(91).incrementCandidateCount();
            }
        }
        Map<String, Double> averageImc = new HashMap<>();

        averageImc.put("0 a 10 anos",   ageIntervalMap.get(0).getAverageImc());
        averageImc.put("11 a 20 anos",  ageIntervalMap.get(11).getAverageImc());
        averageImc.put("21 a 30 anos",  ageIntervalMap.get(21).getAverageImc());
        averageImc.put("31 a 40 anos",  ageIntervalMap.get(31).getAverageImc());
        averageImc.put("41 a 50 anos",  ageIntervalMap.get(41).getAverageImc());
        averageImc.put("51 a 60 anos",  ageIntervalMap.get(51).getAverageImc());
        averageImc.put("61 a 70 anos",  ageIntervalMap.get(61).getAverageImc());
        averageImc.put("71 a 80 anos",  ageIntervalMap.get(71).getAverageImc());
        averageImc.put("81 a 90 anos",  ageIntervalMap.get(81).getAverageImc());
        averageImc.put("91 a 100 anos", ageIntervalMap.get(91).getAverageImc());

        return averageImc;
    }

    @Override
    public Map<Character,Double> getObesePercentageBySex() {
        LOG.info("Getting information from repository");
        List<CandidateEntity> candidatesList = candidateRepository.findAll();
        Map<Character,Double> obesePercentage = new HashMap<>();
        int menCount = 0;
        int womenCount = 0;
        int obeseMenCount = 0;
        int obeseWomenCount = 0;
        LOG.info("Processing obese percentage information");
        for (CandidateEntity candidate: candidatesList) {

            if (candidate.getSexo().equals("Masculino")) {
                menCount++;
            } else {
                womenCount++;
            }
            Double imc = candidate.getPeso() / (Math.pow(candidate.getAltura(), 2));
            if(imc > 30) {
                if(candidate.getSexo().equals("Masculino")) {
                    obeseMenCount++;
                } else {
                    obeseWomenCount++;
                }
            }
        }
        obesePercentage.put('M', Precision.round(obeseMenCount * 100.0 / menCount, 2));
        obesePercentage.put('F', Precision.round(obeseWomenCount * 100.0 / womenCount, 2));
        return obesePercentage;
    }

    @Override
    public Map<String, Integer> getAverageAgeByBloodType() {
        LOG.info("Getting information from repository");
        List<CandidateEntity> candidatesList = candidateRepository.findAll();
        Map<String, AgeSumOverCandidatesCount> bloodTypeMap = new HashMap<>();

        bloodTypeMap.put("A+",  new AgeSumOverCandidatesCount());
        bloodTypeMap.put("A-",  new AgeSumOverCandidatesCount());
        bloodTypeMap.put("B+",  new AgeSumOverCandidatesCount());
        bloodTypeMap.put("B-",  new AgeSumOverCandidatesCount());
        bloodTypeMap.put("AB+", new AgeSumOverCandidatesCount());
        bloodTypeMap.put("AB-", new AgeSumOverCandidatesCount());
        bloodTypeMap.put("O+",  new AgeSumOverCandidatesCount());
        bloodTypeMap.put("O-",  new AgeSumOverCandidatesCount());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birthDate;
        int age;
        LOG.info("Processing obese average imc information");
        // increments the candidate's count while add the age value to the total age sum
        for (CandidateEntity candidate: candidatesList) {
            birthDate = LocalDate.parse(candidate.getData_nasc(), formatter);
            age = Period.between(birthDate, LocalDate.now()).getYears();

            if(candidate.getTipo_sanguineo().equals("A+")) {
                bloodTypeMap.get("A+").addAge(age);
                bloodTypeMap.get("A+").incrementCandidateCount();
            }
            else if (candidate.getTipo_sanguineo().equals("A-")) {
                bloodTypeMap.get("A-").addAge(age);
                bloodTypeMap.get("A-").incrementCandidateCount();
            }
            else if (candidate.getTipo_sanguineo().equals("B+")) {
                bloodTypeMap.get("B+").addAge(age);
                bloodTypeMap.get("B+").incrementCandidateCount();
            }
            else if (candidate.getTipo_sanguineo().equals("B-")) {
                bloodTypeMap.get("B-").addAge(age);
                bloodTypeMap.get("B-").incrementCandidateCount();
            }
            else if (candidate.getTipo_sanguineo().equals("AB+")) {
                bloodTypeMap.get("AB+").addAge(age);
                bloodTypeMap.get("AB+").incrementCandidateCount();
            }
            else if (candidate.getTipo_sanguineo().equals("AB-")) {
                bloodTypeMap.get("AB-").addAge(age);
                bloodTypeMap.get("AB-").incrementCandidateCount();
            }
            else if (candidate.getTipo_sanguineo().equals("O+")) {
                bloodTypeMap.get("O+").addAge(age);
                bloodTypeMap.get("O+").incrementCandidateCount();
            }
            else {
                bloodTypeMap.get("O-").addAge(age);
                bloodTypeMap.get("O-").incrementCandidateCount();
            }
        }

        Map<String, Integer> averageAge = new HashMap<>();

        averageAge.put("A+",  bloodTypeMap.get("A+").getAverageAge());
        averageAge.put("A-",  bloodTypeMap.get("A-").getAverageAge());
        averageAge.put("B+",  bloodTypeMap.get("B+").getAverageAge());
        averageAge.put("B-",  bloodTypeMap.get("B-").getAverageAge());
        averageAge.put("AB+", bloodTypeMap.get("AB+").getAverageAge());
        averageAge.put("AB-", bloodTypeMap.get("AB-").getAverageAge());
        averageAge.put("O+",  bloodTypeMap.get("O+").getAverageAge());
        averageAge.put("O-",  bloodTypeMap.get("O-").getAverageAge());

        return averageAge;
    }

    public Map<String, Integer> getDonatorsByBloodType() {
        LOG.info("Getting information from repository");
        List<CandidateEntity> candidatesList = candidateRepository.findAll();
        Map<String, Integer> donators = new HashMap<>();
        donators.put("A+",  0);
        donators.put("A-",  0);
        donators.put("B+",  0);
        donators.put("B-",  0);
        donators.put("AB+", 0);
        donators.put("AB-", 0);
        donators.put("O+",  0);
        donators.put("O-",  0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birthDate;
        int age;
        LOG.info("Processing donators information");
        // increments the amount of candidates by updating the key's value if the candidate is a possible donator
        for (CandidateEntity candidate : candidatesList) {
            birthDate = LocalDate.parse(candidate.getData_nasc(), formatter);
            age = Period.between(birthDate, LocalDate.now()).getYears();

            // check if candidate is a possible donator
            if (age >= 16 && age <= 69 && candidate.getPeso() >= 50) {

                if(candidate.getTipo_sanguineo().equals("A+")) {
                    donators.put("A+", donators.get("A+") + 1);
                }
                else if (candidate.getTipo_sanguineo().equals("A-")) {
                    donators.put("A-", donators.get("A-") + 1);
                }
                else if (candidate.getTipo_sanguineo().equals("B+")) {
                    donators.put("B+", donators.get("B+") + 1);
                }
                else if (candidate.getTipo_sanguineo().equals("B-")) {
                    donators.put("B-", donators.get("B-") + 1);
                }
                else if (candidate.getTipo_sanguineo().equals("AB+")) {
                    donators.put("AB+", donators.get("AB+") + 1);
                }
                else if (candidate.getTipo_sanguineo().equals("AB-")) {
                    donators.put("AB-", donators.get("AB-") + 1);
                }
                else if (candidate.getTipo_sanguineo().equals("O+")) {
                    donators.put("O+", donators.get("O+") + 1);
                }
                else {
                    donators.put("O-", donators.get("O-") + 1);
                }
            }
        }
        return donators;
    }
}