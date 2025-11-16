package com.flightBookingSys.central_api.service;

import com.flightBookingSys.central_api.connector.DbApiConnector;
import com.flightBookingSys.central_api.connector.GeminiConnector;
import com.flightBookingSys.central_api.dto.AirlineRegistrationDto;
import com.flightBookingSys.central_api.enums.AirlineStatus;
import com.flightBookingSys.central_api.enums.UserStatus;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import com.flightBookingSys.central_api.model.GeminiApiResponse;
import com.flightBookingSys.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AirlineService {

    Mapper mapper;
    DbApiConnector dbApiConnector;
    UserService userService;
    MailService mailService;
    GeminiConnector geminiConnector;

    @Autowired
    public AirlineService(GeminiConnector geminiConnector, Mapper mapper, DbApiConnector dbApiConnector, UserService userService, MailService mailService){
        this.mapper = mapper;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.mailService = mailService;
        this.geminiConnector = geminiConnector;
    }

    public Airline registerAirline(AirlineRegistrationDto airlineRegistrationDto){
        log.info("AirlineService register airline got called with:" + airlineRegistrationDto.toString());
        AppUser airlineAdmin = mapper.mapAirlineRegistrationDtoToAppUser(airlineRegistrationDto);
        log.info("Callinng dbApiConnector createUserEndpoint with:" + airlineAdmin.toString());
        airlineAdmin = dbApiConnector.callCreateUserEndpoint(airlineAdmin);

        Airline airline = mapper.mapAirlineRegistrationDtoToAirline(airlineRegistrationDto,airlineAdmin);
        airline = dbApiConnector.callCreateAirlineEndpoint(airline);

        List<AppUser> adminList = userService.getAllSystemAdmins();
        log.info("airline service system admins value in register airline : " + adminList);
        mailService.mailSystemAdminForAirlineRegistration(adminList,airline);
        return airline;
    }

    public Airline getAirlineById(UUID airlineId){
        return dbApiConnector.callGetAirlineByIdEndpoint(airlineId);
    }

    public Airline updateAirline(Airline airline){
        return dbApiConnector.callUpdateAirlineEndpoint(airline);
    }

    public Airline acceptAirlineRequest(UUID airlineId){
        Airline airline = this.getAirlineById(airlineId);
        airline.setStatus(AirlineStatus.ACTIVE.toString());
        airline.setUpdated_at(LocalDateTime.now());
        airline = updateAirline(airline);
        AppUser airlineAdmin = airline.getAdmin();
        airlineAdmin.setStatus(UserStatus.ACTIVE.toString());
        airlineAdmin.setUpdated_at(LocalDateTime.now());
        userService.callUserUpdate(airlineAdmin);

        mailService.notifyAcceptRequestToAirlineAdmin(airline);

        return airline;
    }

    public void rejectAirlineRequest(UUID airlineId){
        Airline airline = this.getAirlineById(airlineId);
        if(airline == null){
            throw new IllegalArgumentException("AirlineId is incorrect");
        }
        airline.setStatus(AirlineStatus.REJECTED.toString());
        airline.setUpdated_at(LocalDateTime.now());
        this.updateAirline(airline);
        String prompt = "Generate failure reasons for the airline details" + airline.toString();
        GeminiApiResponse geminiApiResponse = geminiConnector.callGeminiGenAIApiEndpoint(prompt);
        String res = geminiApiResponse.getCandidates().get(0).getContent().getParts().get(0).getText();

        mailService.notifyRejectRequestToAirlineAdmin(airline.getAdmin().getName(), airline.getAdmin().getEmail(), res);
    }
}
