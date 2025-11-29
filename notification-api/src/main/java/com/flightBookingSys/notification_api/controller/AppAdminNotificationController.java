package com.flightBookingSys.notification_api.controller;

import com.flightBookingSys.notification_api.dto.AirlineRegistrationReqDto;
import com.flightBookingSys.notification_api.service.AppAdminNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/notify/appadmin")
public class AppAdminNotificationController {

    AppAdminNotificationService appAdminNotificationService;

    @Autowired
    public AppAdminNotificationController(AppAdminNotificationService appAdminNotificationService){
        this.appAdminNotificationService = appAdminNotificationService;
    }


    @PutMapping("/airline-registration")
    public ResponseEntity<Map<String, String>> airlineRegistrationRequestNotification(
            @RequestBody AirlineRegistrationReqDto airlineRegistrationReqDto) {
        log.info("Received airline registration notification request");
        try {
            appAdminNotificationService.sendAirlineRegistrationRequestNotification(airlineRegistrationReqDto);
            return ResponseEntity.ok(Map.of("status", "Notification sent successfully"));
        } catch (Exception e) {
            log.error("Error processing notification request: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to process notification: " + e.getMessage()));
        }
    }


}
