package com.flightBookingSys.db_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Flights")
@Getter
@Setter
@ToString
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;
    
    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    @JsonBackReference("airline-flights")
    Airline airline;
    
    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    @JsonBackReference("aircraft-flights")
    Aircraft aircrafts;
    
    @Column(name = "source_airport", nullable = false)
    String sourceAirport;
    
    @Column(name = "destination_airport", nullable = false)
    String destinationAirport;
    
    @Column(name = "total_time", nullable = false)
    int totalTime;
    
    @Column(name = "flight_type", nullable = false)
    String flightType;
    
    @Column(name = "boarding_time", nullable = false)
    LocalDateTime boardingTime;
    
    @Column(name = "departure_time", nullable = false)
    LocalDateTime departureTime;
    
    @Column(name = "arrival_time", nullable = false)
    LocalDateTime arrivalTime;
    
    @Column(name = "is_connecting", nullable = false)
    boolean isConnecting;
    
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("flight-subflights")
    List<SubFlight> subFlights;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
