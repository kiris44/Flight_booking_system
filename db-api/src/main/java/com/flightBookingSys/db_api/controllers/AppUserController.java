package com.flightBookingSys.db_api.controllers;

import com.flightBookingSys.db_api.dto.AllUserDto;
import com.flightBookingSys.db_api.model.AppUser;
import com.flightBookingSys.db_api.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {

    AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }


    @PostMapping("/create")
    public ResponseEntity saveUser(@RequestBody AppUser user){
        AppUser userResponse = appUserRepository.save(user);
        return new ResponseEntity(userResponse, HttpStatus.CREATED);       // 201 status code
    }

    //if faced error write query annotation in app user repo
    @GetMapping("/get/{userType}")
    public ResponseEntity getAllUsersByUserType(@PathVariable String userType){
        log.info("get system admins got called in db api with usertype : " + userType);
        List<AppUser> users = appUserRepository.findByUserType(userType);
        log.info("called the query from repository and got user list :" + users);
        AllUserDto allUserDto = new AllUserDto(users);
        log.info("value of all user dto which is returning is :" + allUserDto);
        return new ResponseEntity(allUserDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody AppUser appUser){
        appUserRepository.save(appUser);
        return new ResponseEntity(appUser, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        AppUser user =  appUserRepository.findUserByEmail(email);
        return new ResponseEntity(user, HttpStatus.OK);
    }

}

