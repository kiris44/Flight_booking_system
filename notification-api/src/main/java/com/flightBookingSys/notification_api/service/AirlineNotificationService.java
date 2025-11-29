package com.flightBookingSys.notification_api.service;

import com.flightBookingSys.notification_api.dto.AirlineRejectDto;
import com.flightBookingSys.notification_api.models.Airline;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class AirlineNotificationService {

    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public AirlineNotificationService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void airlineRequestAcceptNotification(Airline airline){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("adminName", airline.getAdmin().getName());
        context.setVariable("airlineName", airline.getAirlineName());
        context.setVariable("companyName", airline.getCompanyName());
        context.setVariable("website", airline.getWebsite());
        context.setVariable("employees", airline.getEmployees());
        context.setVariable("totalFlights", airline.getTotalFlights());
        String htmlContent = templateEngine.process("accept-airline-registration", context);
        try {
            mimeMessageHelper.setTo(airline.getAdmin().getEmail());
            mimeMessageHelper.setSubject("AIRLINE REGISTRATION REQUEST ACCEPTED!!");
            mimeMessageHelper.setText(htmlContent, true);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        javaMailSender.send(mimeMessage);
    }

    public void notifyAirlineAdminRejectNotification(AirlineRejectDto airlineRejectDto){
        Context context = new Context();
        context.setVariable("adminName", airlineRejectDto.getAirlineAdminName());
        context.setVariable("rejectionReason", airlineRejectDto.getRejectReason());
        String htmlContent = templateEngine.process("reject-airline-dto", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(airlineRejectDto.getAirlineAdminEmail());
            mimeMessageHelper.setSubject("AIRLINE REGISTRATION REQUEST REJECTED!!");
            mimeMessageHelper.setText(htmlContent, true);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        javaMailSender.send(mimeMessage);
    }
}
