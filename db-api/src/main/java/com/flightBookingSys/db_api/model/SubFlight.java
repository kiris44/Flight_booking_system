package com.flightBookingSys.db_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "SubFlight")
@Getter
@Setter
@ToString
public class SubFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;
    
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @JsonBackReference("flight-subflights")
    Flight flight;
    
    @Column(name = "priority", nullable = false)
    int priority;
    
    @Column(name = "source_airport", nullable = false)
    String sourceAirport;
    
    @Column(name = "destination_airport", nullable = false)
    String destinationAirport;
    
    @Column(name = "boarding_minutes", nullable = false)
    int boardingMinutes;
    
    @Column(name = "boarding_time", nullable = false)
    LocalDateTime boardingTime;
    
    @Column(name = "departure_time", nullable = false)
    LocalDateTime departureTime;
    
    @Column(name = "arrival_time", nullable = false)
    LocalDateTime arrivalTime;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
