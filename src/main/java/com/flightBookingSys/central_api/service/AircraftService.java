package com.flightBookingSys.central_api.service;

import com.flightBookingSys.central_api.connector.DbApiConnector;
import com.flightBookingSys.central_api.dto.AircraftRegistrationDto;
import com.flightBookingSys.central_api.enums.UserType;
import com.flightBookingSys.central_api.exceptions.UnAuthorizedException;
import com.flightBookingSys.central_api.model.Aircraft;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import com.flightBookingSys.central_api.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AircraftService {

    UserService userService;
    AirlineService airlineService;
    Mapper mapper;
    DbApiConnector dbApiConnector;

    @Autowired
    public AircraftService(UserService userService,
                           AirlineService airlineService,
                           Mapper mapper,
                           DbApiConnector dbApiConnector) {
        this.userService = userService;
        this.airlineService = airlineService;
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
    }

    public Aircraft registerAircraft(AircraftRegistrationDto aircraftRegistrationDto,
                                     String authorization) {
        // 1. Decrypt authorization and bring airline details
        String token = authorization.substring(7);
        AppUser airlineAdmin = userService.getUserFromToken(token);
        if (!airlineAdmin.getUserType().equals(UserType.AIRLINE_ADMIN.toString())) {
            throw new UnAuthorizedException("User is not allowed to register aircraft");
        }
        // With the help of airlineAdmin we need to get airline object
        Airline airline = airlineService.getAirlineByAdminId(airlineAdmin.getId());
        // Mapping logic to map aircraftdto to aircraft
        Aircraft aircraft = mapper.mapAircraftDtoToAircraft(aircraftRegistrationDto, airline);
        // Now we need to call save aircraft method.
        return saveAircraft(aircraft);
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        // We need to call dbApiConnector
        return dbApiConnector.callSaveAircraftEndpoint(aircraft);
    }

    public Aircraft getAirCraftrById(UUID id){
        return dbApiConnector.callGetAircraftById(id);
    }
}