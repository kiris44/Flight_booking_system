package com.flightBookingSys.db_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Booking")
@Getter
@Setter
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;
    
    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonBackReference("flight-bookings")
    Flight flight;
    
    @ManyToMany
    @JoinTable(
        name = "booking_subflight",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "subflight_id")
    )
    List<SubFlight> subFlights;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-bookings")
    AppUser bookedBy;
    
    @Column(name = "passenger_name", nullable = false)
    String passengerName;
    
    @Column(name = "total_amount", nullable = false)
    int totalAmount;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
