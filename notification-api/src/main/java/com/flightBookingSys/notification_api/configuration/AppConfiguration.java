package com.flightBookingSys.notification_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    @Bean                   // will be asking springboot to save the hashmap object inside IOC container
    public HashMap<Integer, Integer> generateHashmap(){
        return  new HashMap<>();
    }

    @Bean
    public JavaMailSender generateJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("flightbookKaro@gmail.com");
        javaMailSender.setPassword("jbdewiiql");
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return javaMailSender;
    }

    @Bean
    public TemplateEngine getThymeleafBean(){
        return new TemplateEngine();
    }
}
