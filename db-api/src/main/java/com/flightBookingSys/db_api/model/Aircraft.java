package com.flightBookingSys.db_api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    UUID id;

    @Column(name = "manufacturer",nullable = false)
    String manufacturer;
    @Column(name = "modelNumber",nullable = false)
    int modelNumber;
    
    @Column(name = "modelName")
    String modelName;
    
    @Column(name = "totalFlights",nullable = false)
    int totalFlights;
    
    @Column(name = "buildDate",nullable = false)
    LocalDate buildDate;
    
    @Column(name = "capacity",nullable = false)
    int capacity;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    @JsonBackReference("airline-aircrafts")
    Airline airline;
    
    @OneToMany(mappedBy = "aircrafts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("aircraft-flights")
    List<Flight> flights;
}
