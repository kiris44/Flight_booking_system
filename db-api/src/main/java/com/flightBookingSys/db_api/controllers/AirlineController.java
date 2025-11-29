package com.flightBookingSys.db_api.controllers;

import com.flightBookingSys.db_api.model.Airline;
import com.flightBookingSys.db_api.model.AppUser;
import com.flightBookingSys.db_api.repositories.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/airline")
@Slf4j
public class AirlineController {

    AirlineRepository airlineRepository;

    @Autowired
    public AirlineController(AirlineRepository airlineRepository){
        this.airlineRepository = airlineRepository;
    }

    @PostMapping("create")
    public ResponseEntity createAirline(@RequestBody Airline airline){
        Airline savedAirline = airlineRepository.save(airline);
        log.info("create airline got called with value: " + savedAirline);
        return new ResponseEntity(savedAirline, HttpStatus.CREATED); 
    }

    @GetMapping("/{airlineId}")
    public ResponseEntity getAirlineById(@PathVariable UUID airlineId){
        Airline airline = airlineRepository.findById(airlineId).orElse(null);
        return new ResponseEntity<>(airline,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateAirline(@RequestBody Airline airline){
        airlineRepository.save(airline);
        return new ResponseEntity(airline, HttpStatus.OK);
    }

    @GetMapping("/get/admin/{adminId}")
    public ResponseEntity getAirlineAdminId(@PathVariable UUID adminId){
        Airline airline = airlineRepository.getAirlineByAdminId(adminId);
        return new ResponseEntity(airline, HttpStatus.OK);
    }
}
