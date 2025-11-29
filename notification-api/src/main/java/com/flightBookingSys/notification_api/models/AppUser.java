package com.flightBookingSys.notification_api.models;

import lombok.*;

import java.time.LocalDateTime;
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
