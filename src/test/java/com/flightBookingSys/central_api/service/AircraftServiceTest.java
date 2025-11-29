package com.flightBookingSys.central_api.service;

import com.flightBookingSys.central_api.connector.DbApiConnector;
import com.flightBookingSys.central_api.dto.AircraftRegistrationDto;
import com.flightBookingSys.central_api.enums.UserType;
import com.flightBookingSys.central_api.exceptions.UnAuthorizedException;
import com.flightBookingSys.central_api.model.Aircraft;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import com.flightBookingSys.central_api.utility.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AircraftServiceTest {
    @Mock
    UserService userService;
    @Mock
    AirlineService airlineService;
    @Mock
    Mapper mapper;
    @Mock
    DbApiConnector dbApiConnector;

    @InjectMocks
    AircraftService aircraftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aircraftService = new AircraftService(userService, airlineService, mapper, dbApiConnector);
    }

    @Test
    void testGetAirCraftrById() {
        UUID id = UUID.randomUUID();
        Aircraft aircraft = new Aircraft();
        when(dbApiConnector.callGetAircraftById(id)).thenReturn(aircraft);
        Aircraft result = aircraftService.getAirCraftrById(id);
        assertEquals(aircraft, result);
        verify(dbApiConnector).callGetAircraftById(id);
    }

    @Test
    void testRegisterAircraft_Authorized() {
        AircraftRegistrationDto dto = new AircraftRegistrationDto();
        String token = "sometoken";
        String authorization = "Bearer "+token;
        AppUser airlineAdmin = new AppUser();
        airlineAdmin.setUserType(UserType.AIRLINE_ADMIN.toString());
        airlineAdmin.setId(UUID.randomUUID());
        Airline airline = new Airline();
        Aircraft aircraft = new Aircraft();
        Aircraft savedAircraft = new Aircraft();

        when(userService.getUserFromToken(token)).thenReturn(airlineAdmin);
        when(airlineService.getAirlineByAdminId(airlineAdmin.getId())).thenReturn(airline);
        when(mapper.mapAircraftDtoToAircraft(dto, airline)).thenReturn(aircraft);
        when(dbApiConnector.callSaveAircraftEndpoint(aircraft)).thenReturn(savedAircraft);

        Aircraft result = aircraftService.registerAircraft(dto, authorization);
        assertEquals(savedAircraft, result);
    }

    @Test
    void testRegisterAircraft_UnAuthorized() {
        AircraftRegistrationDto dto = new AircraftRegistrationDto();
        String token = "othertoken";
        String authorization = "Bearer "+token;
        AppUser notAdmin = new AppUser();
        notAdmin.setUserType(UserType.CUSTOMER.toString());
        when(userService.getUserFromToken(token)).thenReturn(notAdmin);
        Exception exception = assertThrows(UnAuthorizedException.class, () -> {
            aircraftService.registerAircraft(dto, authorization);
        });
        assertTrue(exception.getMessage().contains("User is not allowed to register aircraft"));
    }

    @Test
    void testSaveAircraft() {
        Aircraft aircraft = new Aircraft();
        Aircraft saved = new Aircraft();
        when(dbApiConnector.callSaveAircraftEndpoint(aircraft)).thenReturn(saved);
        Aircraft result = aircraftService.saveAircraft(aircraft);
        assertEquals(saved, result);
        verify(dbApiConnector).callSaveAircraftEndpoint(aircraft);
    }
}
