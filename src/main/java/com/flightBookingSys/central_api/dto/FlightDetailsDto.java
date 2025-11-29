package com.flightBookingSys.central_api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class FlightDetailsDto {
    String sourceAirport; // Mumbai
    String destinationAirport; // New York
    String flightType; // International, Domestic, Emergency
    int totalTime;// TotalTime in minutes
    LocalDateTime boardingTime; // When you passengers need to sit in the aircraft
    int boardingMinutes;
    LocalDateTime departureTime; // When aircraft is going to takeoff; // IST TimeZone
    LocalDateTime arrivalTime; // When aircreaft is going to land // EST Timezone
    boolean isConnecting; // is this flight a connecting flight ? or not
    UUID airCraftID;
    List<SubFlightDto> subFlightDtos;
    List<SeatMappingDto> seatMappingDtos;
}
