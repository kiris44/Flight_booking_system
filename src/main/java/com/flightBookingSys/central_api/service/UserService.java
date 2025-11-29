package com.flightBookingSys.central_api.service;

import com.flightBookingSys.central_api.connector.DbApiConnector;
import com.flightBookingSys.central_api.enums.UserType;
import com.flightBookingSys.central_api.exceptions.InvalidCredentials;
import com.flightBookingSys.central_api.model.AppUser;
import com.flightBookingSys.central_api.utility.AuthUtility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@NoArgsConstructor
public class UserService {

    DbApiConnector dbApiConnector;
    AuthUtility authUtility;

    @Autowired
    public UserService(DbApiConnector dbApiConnector, AuthUtility authUtility){
        this.authUtility = authUtility;
        this.dbApiConnector = dbApiConnector;
    }


    public List<AppUser> getAllSystemAdmins(){
        List<AppUser> users = dbApiConnector.callGetAllSystemAdmins(UserType.APP_ADMIN.toString());
        log.info("userList called from dbapiconnector in user service with value : " + users);
        return users;
    }

    public AppUser callUserUpdate(AppUser appUser){
        return dbApiConnector.callUpdateUserEndpoint(appUser);
    }

    // finds user by email
    public AppUser getUserByEmail(String email){
        AppUser user = dbApiConnector.callGetUserByEmailEndpoint(email);
        return user;
    }

    // First gets user by the email from db
    // Then checks if the pass in db is same as that passed from user
    public String isValidCredential(String email, String password){
        AppUser user = this.getUserByEmail(email);
        if(user.getPassword().equals(password)){
            return authUtility.generateToken(user.getEmail(), user.getPassword(), user.getUserType());
        }
        throw new InvalidCredentials("Email or Password entered is wrong!");
    }

    // checks if email and pass  of the user is correct  or not
    public boolean validateToken(String token){
        String payload = authUtility.decryptToken(token);
        String details[] = payload.split(":");
        String email = details[0];
        String password = details[1];

        try{
            this.isValidCredential(email, password);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public AppUser getUserFromToken(String token){
        String payload = authUtility.decryptToken(token);
        String email = payload.split(":")[0];
        return this.getUserByEmail(email);
    }

    public String valueChecker(Integer a, Integer b){
        if(a == null || b == null){
            return "Null Values present";
        }
        if(a == 5){
            return "Hey How are you ?";
        }
        if(b == 5){
            return "I am good";
        }
        return a + b + "";
    }
}
