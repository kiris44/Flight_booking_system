package com.flightBookingSys.central_api.controller;

import com.flightBookingSys.central_api.dto.AircraftRegistrationDto;
import com.flightBookingSys.central_api.dto.AirlineRegistrationDto;
import com.flightBookingSys.central_api.dto.FlightDetailsDto;
import com.flightBookingSys.central_api.model.Aircraft;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.Flight;
import com.flightBookingSys.central_api.service.AircraftService;
import com.flightBookingSys.central_api.service.AirlineService;
import com.flightBookingSys.central_api.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/airline")
@Slf4j                                                 //  usd to take logs
public class AirlineController {

    AirlineService airlineService;
    AircraftService aircraftService;
    FlightService flightService;

    @Autowired
    public AirlineController(AirlineService airlineService, AircraftService aircraftService, FlightService flightService){
        this.aircraftService = aircraftService;
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirlineRegistrationDto airlineRegistrationDto) {
        log.info("Airline registration method got called with requestbody:" + airlineRegistrationDto.toString());
        log.info("Calling airline service registerAirline method");
        Airline airline = airlineService.registerAirline(airlineRegistrationDto);
        return new ResponseEntity(airline, HttpStatus.CREATED);
    }

    @GetMapping("/request/accept/{airlineId}")
    public void acceptAirlineRequest(@PathVariable UUID airlineId){
        log.info("calling accepAirline from airline controller of central api, airline Id =" + airlineId.toString());
        airlineService.acceptAirlineRequest(airlineId);
    }

    @GetMapping("/request/reject/{airlineId}")
    public void rejectAirlineRequest(@PathVariable UUID airlineId){
        log.info("rejecting airline with Id =" + airlineId.toString());
        airlineService.rejectAirlineRequest(airlineId);
    }

    @PostMapping("/aircraft/register")
    public Aircraft registerAircraft(@RequestBody AircraftRegistrationDto aircraftRegistrationDto,
                                     @RequestHeader String Authorization){
        // We need to call the service
        return aircraftService.registerAircraft(aircraftRegistrationDto, Authorization);
    }

    @PostMapping("/flight/create")
    public Flight createFlight(@RequestBody FlightDetailsDto flightDetailsDto,
                               @RequestHeader String Authorization
    ){
        return flightService.createFlight(flightDetailsDto, Authorization);
    }
}
