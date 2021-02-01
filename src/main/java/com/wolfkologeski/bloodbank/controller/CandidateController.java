package com.wolfkologeski.bloodbank.controller;

import com.wolfkologeski.bloodbank.service.CandidateService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private static Logger LOG = getLogger(CandidateController.class);

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllCandidates() {
        try {
            LOG.info("Getting all candidates information");
            return ResponseEntity.ok(candidateService.findAll());
        } catch (ResponseStatusException response) {
            LOG.error("An error occurred while trying to get all candidates information");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(response.getStatus()).body(response.getMessage());
        }
    }

    @GetMapping("/candidatesByState")
    public ResponseEntity<?> getCandidatesByState() {
        try {
            LOG.info("Getting candidates by state information");
            JSONArray jsonArray = new JSONArray();
            Iterator it = candidateService.getCandidatesByState().entrySet().iterator();
            // pass hash map information to Json format
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("state", pair.getKey());
                jsonObj.put("candidates", pair.getValue());
                jsonArray.put(jsonObj);
                it.remove();
            }
            return ResponseEntity.ok(jsonArray.toString());
        } catch (ResponseStatusException response) {
            LOG.error("An error occurred while trying to get candidates by state information");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(response.getStatus()).body(response.getMessage());

        } catch (JSONException response) {
            LOG.error("An error occurred while trying to create json response");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(402).body(response.getMessage());
        }
    }

    @GetMapping("/averageImcByAgeInterval")
    public ResponseEntity<?> getAverageImcByAgeInterval() {
        try {
            LOG.info("Getting average imc by age interval information");
            JSONArray jsonArray = new JSONArray();
            Iterator it = candidateService.getAverageImcByAgeInterval().entrySet().iterator();
            // pass hash map information to Json format
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("age", pair.getKey());
                jsonObj.put("imcAverage", pair.getValue());
                jsonArray.put(jsonObj);
                it.remove();
            }

            JSONArray sortedJsonArray = new JSONArray();

            // sort Json alphabetically
            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonValues.add(jsonArray.getJSONObject(i));
            }
            Collections.sort( jsonValues, new Comparator<JSONObject>() {
                private static final String KEY_NAME = "age";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();
                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    }catch (JSONException response) {
                        LOG.error("An error occurred while trying to create json response");
                        LOG.error("Error [{}]", response.getMessage());
                    }

                    return valA.compareTo(valB);
                }
            });

            // populate sorted Json
            for (int i = 0; i < jsonArray.length(); i++) {
                sortedJsonArray.put(jsonValues.get(i));
            }

            return ResponseEntity.ok(sortedJsonArray.toString());
        } catch (ResponseStatusException response) {
            LOG.error("An error occurred while trying to get average imc by age interval information");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(response.getStatus()).body(response.getMessage());

        } catch (JSONException response) {
            LOG.error("An error occurred while trying to create json response");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(402).body(response.getMessage());
        }
    }

    @GetMapping("/obesePercentageBySex")
    public ResponseEntity<?> getObesePercentageBySex() {
        try {
            LOG.info("Getting obese percentage by sex information");
            JSONArray jsonArray = new JSONArray();
            Iterator it = candidateService.getObesePercentageBySex().entrySet().iterator();
            // pass hash map information to Json format
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("sex", pair.getKey());
                jsonObj.put("obesePercentage", pair.getValue());
                jsonArray.put(jsonObj);
                it.remove();
            }
            return ResponseEntity.ok(jsonArray.toString());
        } catch (ResponseStatusException response) {
            LOG.error("An error occurred while trying to get obese percentage by sex information");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(response.getStatus()).body(response.getMessage());

        } catch (JSONException response) {
            LOG.error("An error occurred while trying to create json response");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(402).body(response.getMessage());
        }
    }

    @GetMapping("/averageAgeByBloodType")
    public ResponseEntity<?> getAverageAgeByBloodType() {
        try {
            LOG.info("Getting average age by blood type information");
            JSONArray jsonArray = new JSONArray();
            Iterator it = candidateService.getAverageAgeByBloodType().entrySet().iterator();
            // pass hash map information to Json format
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("bloodType", pair.getKey());
                jsonObj.put("averageAge", pair.getValue());
                jsonArray.put(jsonObj);
                it.remove();
            }

            return ResponseEntity.ok(jsonArray.toString());
        } catch (ResponseStatusException response) {
            LOG.error("An error occurred while trying to get average age by blood type information");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(response.getStatus()).body(response.getMessage());

        } catch (JSONException response) {
            LOG.error("An error occurred while trying to create json response");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(402).body(response.getMessage());
        }
    }

    @GetMapping("/donatorsByBloodType")
    public ResponseEntity<?> getDonatorsByBloodType() {
        try {
            LOG.info("Getting donators by blood type information");
            JSONArray jsonArray = new JSONArray();
            Iterator it = candidateService.getDonatorsByBloodType().entrySet().iterator();
            // pass hash map information to Json format
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("bloodType", pair.getKey());
                jsonObj.put("donators", pair.getValue());
                jsonArray.put(jsonObj);
                it.remove();
            }
            return ResponseEntity.ok(jsonArray.toString());
        } catch (ResponseStatusException response) {
            LOG.error("An error occurred while trying to get donators by blood type information");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(response.getStatus()).body(response.getMessage());

        } catch (JSONException response) {
            LOG.error("An error occurred while trying to create json response");
            LOG.error("Error [{}]", response.getMessage());
            return ResponseEntity.status(402).body(response.getMessage());
        }
    }
}
