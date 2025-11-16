package com.flightBookingSys.central_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUser {
    UUID id;
    String name;
    String email;
    String password;
    boolean isVerified;
    long contactNumber;
    String userType;
    String status;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}
