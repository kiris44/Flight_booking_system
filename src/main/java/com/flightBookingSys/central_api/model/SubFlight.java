package com.flightBookingSys.central_api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubFlight {
    //id	flightid	starting 	ending	layover	boarding time	departure time	arrival time
    UUID id;
    Flight flight;
    int priority;
    String sourceAirport;
    String destinationAirport;
    LocalDateTime boardingTime;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime; // Where this subflight will land;
    int boardingMinutes;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
