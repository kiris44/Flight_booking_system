package com.flightBookingSys.central_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Airline {
    UUID id;
    private  String website;
    private  String airlineName;
    private  String companyName;
    int employees;
    int totalFlights;
    AppUser admin;
    String status;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}
