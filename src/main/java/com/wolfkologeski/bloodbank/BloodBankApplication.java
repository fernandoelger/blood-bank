package com.wolfkologeski.bloodbank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfkologeski.bloodbank.model.Entity.CandidateEntity;
import com.wolfkologeski.bloodbank.service.CandidateService;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class BloodBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodBankApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner (CandidateService candidateService) {
//
//		Logger LOG = getLogger(BloodBankApplication.class);
//
//		return args -> {
//			// read candidates json and write into database
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<CandidateEntity>> typeReference = new TypeReference<List<CandidateEntity>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/candidates_data/data.json");
//			try {
//				LOG.error("Saving candidates information");
//				List candidates = mapper.readValue(inputStream, typeReference);
//				//System.out.println(candidates);
//				candidateService.save(candidates);
//			} catch (IOException ex) {
//				LOG.error("An error occurred while trying to save candidates information");
//				throw new IOException(ex.getMessage());
//			}
//
//			LOG.error("Candidates saved successfully");
//		};
//	}
}
