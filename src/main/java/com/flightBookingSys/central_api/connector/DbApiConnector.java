package com.flightBookingSys.central_api.connector;

import com.flightBookingSys.central_api.dto.AllUserDto;
import com.flightBookingSys.central_api.enums.AirlineStatus;
import com.flightBookingSys.central_api.model.*;
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

    public Airline callGetAirlineByAdminIdEndpoint(UUID adminId){
        String url = dbApiBaseUrl + "/airline/get/admin/" +  adminId.toString();
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Airline> resp = restTemplate.exchange(url, HttpMethod.GET, request, Airline.class);
        return resp.getBody();
    }

    public Aircraft callSaveAircraftEndpoint(Aircraft aircraft){
        String url = dbApiBaseUrl + "/aircraft/save";
        RequestEntity request = RequestEntity.post(url).body(aircraft);
        ResponseEntity<Aircraft> resp = restTemplate.exchange(url, HttpMethod.POST, request, Aircraft.class);
        return resp.getBody();
    }

    public Aircraft callGetAircraftById(UUID aircraftId){
        String url = dbApiBaseUrl + "/aircraft/" + aircraftId.toString();
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Aircraft> resp = restTemplate.exchange(url, HttpMethod.GET, request, Aircraft.class);
        return resp.getBody();
    }

    public Flight callCreateFlightEndpoint(Flight flight){
        String url = dbApiBaseUrl + "/flight/create";
        RequestEntity request = RequestEntity.post(url).body(flight);
        ResponseEntity<Flight> response = restTemplate.exchange(url, HttpMethod.POST, request, Flight.class);
        return response.getBody();
    }

    public FlightSeatMapping callCreateFlightSeatMapping(FlightSeatMapping flightSeatMapping){
        String url = dbApiBaseUrl + "/seatmapping/create";
        RequestEntity request = RequestEntity.post(url).body(flightSeatMapping);
        ResponseEntity<FlightSeatMapping> response = restTemplate.exchange(url, HttpMethod.POST, request, FlightSeatMapping.class);
        return response.getBody();
    }

    public SubFlight callCreateSubFlightEndpoint(SubFlight subFlight){
        String url = dbApiBaseUrl + "/subflight/create";
        RequestEntity request = RequestEntity.post(url).body(subFlight);
        ResponseEntity<SubFlight> response = restTemplate.exchange(url, HttpMethod.POST, request, SubFlight.class);
        return response.getBody();
    }

    public Object callSearchFlightEndpoint(String sourceAirport,
                                           String destinationAirport,
                                           String dateTime){
        // db Api endpoint
        sourceAirport = sourceAirport.replace(' ', '+');
        destinationAirport = destinationAirport.replace(' ', '+');
        dateTime = dateTime.replace(' ', '+');
        String url = dbApiBaseUrl + "/flight/search?" + "sourceAirport="+sourceAirport+"&" + "destinationAirport=" + destinationAirport +"&" + "dateTime=" + dateTime;
        log.info(url);
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        return resp.getBody();
    }
}
