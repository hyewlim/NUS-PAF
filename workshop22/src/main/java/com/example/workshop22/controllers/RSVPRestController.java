package com.example.workshop22.controllers;

import com.example.workshop22.model.RSVP;
import com.example.workshop22.repositories.RSVPRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class RSVPRestController {

    @Autowired
    RSVPRepository rsvpRepository;

    @GetMapping ("/rsvps")
    public ResponseEntity<String> getRSVPS() {

        //Query the database for rsvps
        List<RSVP> rsvps = rsvpRepository.getAllRSVPs();

        //Build the result
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (RSVP rsvp: rsvps)
            arrayBuilder.add(rsvp.toJson());
        JsonArray result = arrayBuilder.build();

        return ResponseEntity.ok(result.toString());
    }

    @GetMapping("/rsvp")
    public ResponseEntity<?> getRSVP(@RequestParam String name) {

        List<RSVP> rsvps = rsvpRepository.getRSVP(name);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (RSVP rsvp: rsvps)
            arrayBuilder.add(rsvp.toJson());
        JsonArray result = arrayBuilder.build();

        System.out.println(">>>>>>>>>>>>>>" + result.toString());

        if (rsvps.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("error: your rsvp under name %s is not found".formatted(name));
        }

        return ResponseEntity.ok(result.toString());
    }

    @PostMapping(value = "/rsvp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postRSVP(@RequestBody String json) {

        JsonObject rsvpObject;

        RSVP rsvp = RSVP.create(json);


        if (rsvpRepository.insertRSVP(rsvp)) {

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                arrayBuilder.add(rsvp.toJson());
            JsonArray result = arrayBuilder.build();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());
        } else {

            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("nope!");
        }


    }

    @PutMapping (value="/rsvp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRSVP(@RequestBody String json) {

        JsonObject rsvpObject;
        RSVP rsvp = RSVP.create(json);

        if (rsvpRepository.updateRSVP(rsvp)) {

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            arrayBuilder.add(rsvp.toJson());
            JsonArray result = arrayBuilder.build();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());
        } else {

            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("nope!");
        }

    }

    @GetMapping (value="/rsvps/count")
    public ResponseEntity<Integer> getCount() {
        int count = rsvpRepository.getCount();

        return ResponseEntity.ok(count);
    }
}
