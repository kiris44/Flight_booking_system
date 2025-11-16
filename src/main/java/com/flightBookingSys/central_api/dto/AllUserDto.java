package com.flightBookingSys.central_api.dto;

import com.flightBookingSys.central_api.model.AppUser;
import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllUserDto {
    List<AppUser> appUsers;
}
