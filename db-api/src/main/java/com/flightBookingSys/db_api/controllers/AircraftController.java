package com.flightBookingSys.db_api.controllers;

import com.flightBookingSys.db_api.model.Aircraft;
import com.flightBookingSys.db_api.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/aircraft")
public class AircraftController {
    AircraftRepository aircraftRepository;

    @Autowired
    public AircraftController(AircraftRepository aircraftRepository){
        this.aircraftRepository = aircraftRepository;
    }

    @PostMapping("/save")
    public Aircraft saveAircraft(@RequestBody Aircraft aircraft){
        aircraftRepository.save(aircraft);
        return aircraft;
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable UUID id){
        return aircraftRepository.findById(id).orElse(null);
    }
}
