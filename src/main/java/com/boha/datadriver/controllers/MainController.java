package com.boha.datadriver.controllers;

import com.boha.datadriver.models.City;
import com.boha.datadriver.services.CityService;
import com.boha.datadriver.util.E;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getSimpleName());

    public MainController(CityService cityService) {
        this.cityService = cityService;
    }


    private final CityService cityService;
    @GetMapping("/saveCities")
    private ResponseEntity<Object> saveCities() {
        try {
            List<City> cities = cityService.addCitiesToFirestore();
            LOGGER.info(E.BLUE_HEART+E.BLUE_HEART+E.CHECK+
                    " MainController Returning " + cities.size() + " cities");
            return ResponseEntity.ok(cities);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @GetMapping("/getCities")
    private ResponseEntity<Object> getCities() {
        try {
            List<City> cities = cityService.getCitiesFromFirestore();
            LOGGER.info(E.BLUE_HEART+E.BLUE_HEART+E.CHECK+
                    " Firestore Returning " + cities.size() + " cities " + E.CHECK);
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
