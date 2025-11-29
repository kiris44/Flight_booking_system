package com.flightBookingSys.notification_api.service;

import com.flightBookingSys.notification_api.dto.AirlineRegistrationReqDto;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
@Service
@Slf4j
public class AppAdminNotificationService {

    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public AppAdminNotificationService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendAirlineRegistrationRequestNotification(AirlineRegistrationReqDto airlineRegistrationReqDto) {
        if (airlineRegistrationReqDto == null || 
            airlineRegistrationReqDto.getAirline() == null || 
            airlineRegistrationReqDto.getAppAdmin() == null) {
            log.error("Invalid request: Airline or Admin details are missing");
            throw new IllegalArgumentException("Airline and Admin details are required");
        }

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            
            // Process the template with the DTO
            String htmlContent = processTemplate(airlineRegistrationReqDto);
            
            mimeMessageHelper.setTo(airlineRegistrationReqDto.getAppAdmin().getEmail());
            mimeMessageHelper.setSubject(airlineRegistrationReqDto.getAirline().getAirlineName() + " - Registration Request");
            mimeMessageHelper.setText(htmlContent, true);
            
            javaMailSender.send(mimeMessage);
            log.info("Email notification sent successfully to: {}", airlineRegistrationReqDto.getAppAdmin().getEmail());
            
        } catch (Exception e) {
            log.error("Failed to send email notification: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send email notification", e);
        }
    }
    
    private String processTemplate(AirlineRegistrationReqDto dto) {
        try {
            Context context = new Context();
            
            // Set airline details as a map to avoid null references
            Map<String, Object> airlineDetails = new HashMap<>();
            airlineDetails.put("airlineName", dto.getAirline().getAirlineName() != null ? 
                dto.getAirline().getAirlineName() : "");
            airlineDetails.put("companyName", dto.getAirline().getCompanyName() != null ? 
                dto.getAirline().getCompanyName() : "");
            airlineDetails.put("website", dto.getAirline().getWebsite() != null ? 
                dto.getAirline().getWebsite() : "");
            context.setVariable("airline", airlineDetails);
            context.setVariable("appAdmin", dto.getAppAdmin());
            context.setVariable("acceptLink", "http//localhost:7071/api/v1/central/airline/request/accept/" + dto.getAirline().getId().toString());
            context.setVariable("rejectLink", "http//localhost:7071/api/v1/central/airline/request/reject/" + dto.getAirline().getId().toString());

            return templateEngine.process("airline-verification-email", context);
            
        } catch (Exception e) {
            log.error("Error processing email template: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process email template", e);
        }
    }


}
