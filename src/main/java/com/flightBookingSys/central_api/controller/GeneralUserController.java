package com.flightBookingSys.central_api.controller;

import com.flightBookingSys.central_api.dto.LoginDto;
import com.flightBookingSys.central_api.exceptions.InvalidCredentials;
import com.flightBookingSys.central_api.service.FlightService;
import com.flightBookingSys.central_api.service.UserService;
import com.flightBookingSys.central_api.utility.AuthUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/central/user")
@Slf4j
public class GeneralUserController {

    AuthUtility authUtility;
    UserService userService;
    FlightService flightService;

    @Autowired
    public GeneralUserController(AuthUtility authUtility, UserService userService, FlightService flightService){
        this.userService = userService;
        this.authUtility = authUtility;
        this.flightService = flightService;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        try {
            String token = userService.isValidCredential(loginDto.getEmail(), loginDto.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (InvalidCredentials e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);   // 401 code
        }
    }

    @GetMapping("/search")
    public Object searchFlight(@RequestParam String sourceAirport,
                               @RequestParam String destinationAirport,

                               @RequestParam String dateTime){
        // Flight service
        log.info(sourceAirport + " " + destinationAirport + " " + dateTime);
        sourceAirport.replace('+', ' ');
        destinationAirport.replace('+', ' ');
        dateTime.replace('+', ' ');
        log.info(sourceAirport + " " + destinationAirport + " " + dateTime);
        return flightService.searchFlight(sourceAirport, destinationAirport, dateTime);
    }
}
