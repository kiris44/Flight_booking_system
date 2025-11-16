package com.flightBookingSys.central_api.configuration;

import com.flightBookingSys.central_api.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class AuthConfig {
    @Autowired
    AuthFilter authFilter;

    /**
     * In this SecurityFilterChain we are declaring what all endpoints will be not secured.
     * Other then that all the endpoints will be secured
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/api/v1/central/user/login",
                                        "/api/v1/central/airline/register"        // not secured endpoints

                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
