package com.flightBookingSys.central_api.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirlineRegistrationDto {
    private  String website;
    private  String airlineName;
    private  String companyName;
    int employees;
    int totalFlights;
    String email;
    String password;
    long contactNumber;
}
