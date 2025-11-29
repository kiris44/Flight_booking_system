package com.flightBookingSys.db_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

//limited to non-connecting flights only
@Entity
@Table(name = "FlightSeatBooked")
@Getter
@Setter
@ToString
public class FlightSeatBooked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;
    
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    Flight flight;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    AppUser bookedBy;
    
    @Column(name = "passenger_name", nullable = false)
    String passengerName;
    
    @Column(name = "seat_number", nullable = false)
    int seatNumber;
    
    @Column(name = "above_18", nullable = false)
    boolean above18;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
