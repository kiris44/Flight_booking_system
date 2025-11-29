package com.flightBookingSys.notification_api.dto;

import com.flightBookingSys.notification_api.models.Airline;
import com.flightBookingSys.notification_api.models.AppUser;
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
