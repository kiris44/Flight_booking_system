package com.flightBookingSys.central_api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FlightSeatMapping {
    UUID id;
    String className;
    String range; // 1-20
    int basePrice;
    int windowPrice;
    int totalWindow;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    // id	flightId	classname	range	baseprice	windowprice
    Flight flight;
}
