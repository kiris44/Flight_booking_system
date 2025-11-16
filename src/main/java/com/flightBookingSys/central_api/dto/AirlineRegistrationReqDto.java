package com.flightBookingSys.central_api.dto;

import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AirlineRegistrationReqDto {
    AppUser appAdmin;
    Airline airline;
}
