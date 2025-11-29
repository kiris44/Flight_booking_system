package com.flightBookingSys.db_api.dto;

import com.flightBookingSys.db_api.model.AppUser;
import jakarta.persistence.Table;
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
