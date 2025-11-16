package com.flightBookingSys.central_api.connector;

import com.flightBookingSys.central_api.dto.AllUserDto;
import com.flightBookingSys.central_api.enums.AirlineStatus;
import com.flightBookingSys.central_api.model.Airline;
import com.flightBookingSys.central_api.model.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class DbApiConnector {

    RestTemplate restTemplate;

    @Autowired
    public DbApiConnector(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${db.api.url}")
    String dbApiBaseUrl;

    public AppUser callCreateUserEndpoint(AppUser user){
        String url = dbApiBaseUrl + "/user/create";
        RequestEntity request = RequestEntity.post(url).body(user);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        return response.getBody();
    }

    public Airline callCreateAirlineEndpoint(Airline airline){
        log.info("calling create airline endpoint with ");
        String url = dbApiBaseUrl + "/airline/create";
        RequestEntity request = RequestEntity.post(url).body(airline);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.POST, request, Airline.class);
        return response.getBody();
    }

    public List<AppUser> callGetAllSystemAdmins(String userType){
        log.info("getAllSystemAdmins got called with userType: " + userType);
        String url = dbApiBaseUrl + "/user/get/" + userType;
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<AllUserDto> resp = restTemplate.exchange(url, HttpMethod.GET, request, AllUserDto.class);
        log.info("responseEntity returned inside dbapiconnector : " + resp);
        log.info("returning statement value inside dbapiconnector : " + resp.getBody().getAppUsers());
        return resp.getBody().getAppUsers();
    }

    public Airline callGetAirlineByIdEndpoint(UUID airlineId){
        String url = dbApiBaseUrl + "/airline/" + airlineId.toString();
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.GET, request, Airline.class);
        return resp.getBody();
    }

    public Airline callUpdateAirlineEndpoint(Airline airline){
        String url = dbApiBaseUrl + "/airline/update";
        RequestEntity request = RequestEntity.put(url).body(airline);
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.PUT, request, Airline.class);
        return response.getBody();
    }

    public AppUser callUpdateUserEndpoint(AppUser user){
        String url = dbApiBaseUrl + "/user/update";
        RequestEntity request = RequestEntity.put(url).body(user);
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.PUT, request, AppUser.class);
        return  response.getBody();
    }

    public AppUser callGetUserByEmailEndpoint(String email){
        String url = dbApiBaseUrl + "/user//email/" + email;
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.GET, request, AppUser.class);
        return response.getBody();
    }
}
