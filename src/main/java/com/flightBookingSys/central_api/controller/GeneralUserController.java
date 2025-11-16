package com.flightBookingSys.central_api.controller;

import com.flightBookingSys.central_api.dto.LoginDto;
import com.flightBookingSys.central_api.exceptions.InvalidCredentials;
import com.flightBookingSys.central_api.service.UserService;
import com.flightBookingSys.central_api.utility.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/user")
public class GeneralUserController {

    AuthUtility authUtility;
    UserService userService;

    @Autowired
    public GeneralUserController(AuthUtility authUtility, UserService userService){
        this.userService = userService;
        this.authUtility = authUtility;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        try {
            String token = userService.isValidCredential(loginDto.getEmail(), loginDto.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (InvalidCredentials e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);   // 401 code
        }
    }
}
