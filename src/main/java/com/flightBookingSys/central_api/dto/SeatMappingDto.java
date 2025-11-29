package com.flightBookingSys.central_api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeatMappingDto {
    String className;
    String range; // 1-20
    int basePrice;
    int windowPrice;
    int totalWindow;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
