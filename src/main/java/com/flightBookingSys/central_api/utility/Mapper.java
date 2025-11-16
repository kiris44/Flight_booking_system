package com.flightBookingSys.central_api.utility;

import com.flightBookingSys.central_api.dto.AirlineRegistrationDto;
import com.flightBookingSys.central_api.enums.AirlineStatus;
import com.flightBookingSys.central_api.enums.UserStatus;
import com.flightBookingSys.central_api.enums.UserType;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {

    public AppUser mapAirlineRegistrationDtoToAppUser(AirlineRegistrationDto airlineRegistrationDto){
        AppUser airlineAdmin = new AppUser();
        airlineAdmin.setName(airlineRegistrationDto.getAirlineName()+" Admin");
        airlineAdmin.setEmail(airlineRegistrationDto.getEmail());
        airlineAdmin.setPassword(airlineRegistrationDto.getPassword());
        airlineAdmin.setUserType(UserType.AIRLINE_ADMIN.toString());
        airlineAdmin.setContactNumber(airlineRegistrationDto.getContactNumber());
        airlineAdmin.setVerified(false);
        airlineAdmin.setStatus(UserStatus.ACTIVE.toString());
        airlineAdmin.setCreated_at(LocalDateTime.now());
        airlineAdmin.setUpdated_at(LocalDateTime.now());
        return  airlineAdmin;
    }

    public Airline mapAirlineRegistrationDtoToAirline(AirlineRegistrationDto airlineRegistrationDto,AppUser airlineAdmin){
        Airline airline = new Airline();
        airline.setAirlineName(airlineRegistrationDto.getAirlineName());
        airline.setStatus(AirlineStatus.INACTIVE.toString());
        airline.setAdmin(airlineAdmin);
        airline.setEmployees(airlineRegistrationDto.getEmployees());
        airline.setWebsite(airlineRegistrationDto.getWebsite());
        airline.setTotalFlights(airlineRegistrationDto.getTotalFlights());
        airline.setCompanyName(airlineRegistrationDto.getCompanyName());
        airline.setCreated_at(LocalDateTime.now());
        airline.setUpdated_at(LocalDateTime.now());
        return airline;
    }
}
