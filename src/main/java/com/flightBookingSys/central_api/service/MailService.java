package com.flightBookingSys.central_api.service;

import com.flightBookingSys.central_api.connector.NotificationApiConnector;
import com.flightBookingSys.central_api.dto.AirlineRegistrationReqDto;
import com.flightBookingSys.central_api.dto.AirlineRejectDto;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MailService {

    NotificationApiConnector notificationApiConnector;

    @Autowired
    public MailService(NotificationApiConnector notificationApiConnector){
        this.notificationApiConnector = notificationApiConnector;
    }

    public void mailSystemAdminForAirlineRegistration(List<AppUser> systemAdmins, Airline airline){
        log.info("in mail service central-api airline is : " + airline);
        log.info("and list of admins/users is : " + systemAdmins);
        for(AppUser systemAdmin : systemAdmins){
            AirlineRegistrationReqDto airlineRegistrationReqDto = new AirlineRegistrationReqDto();
            airlineRegistrationReqDto.setAirline(airline);
            airlineRegistrationReqDto.setAppAdmin(systemAdmin);
            try {
                notificationApiConnector.notifySystemAdminForAirlineRegistration(airlineRegistrationReqDto);
            } catch(Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public void notifyAcceptRequestToAirlineAdmin(Airline airline){
        try {
            notificationApiConnector.notifyAcceptRequestToAirlineAdmin(airline);
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }

    public void notifyRejectRequestToAirlineAdmin(String name, String email, String responseAI){
        AirlineRejectDto airlineRejectDto = new AirlineRejectDto();
        airlineRejectDto.setAirlineAdminEmail(email);
        airlineRejectDto.setRejectReason(responseAI);
        airlineRejectDto.setAirlineAdminName(name);
        try {
            notificationApiConnector.notifyRejectRequestToAirlineAdmin(airlineRejectDto);
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
