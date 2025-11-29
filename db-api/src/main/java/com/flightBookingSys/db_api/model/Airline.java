package com.flightBookingSys.db_api.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Airline")
@Getter
@Setter
@ToString
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(name = "website",  unique = true)
    private  String website;

    @Column(name = "name")
    private  String airlineName;

    @Column(name = "companyName")
    private  String companyName;

    @Column(name = "employees")
    int employees;

    @Column(name = "totalFlights")
    int totalFlights;

    @OneToOne
    @JoinColumn(name = "admin_id")
    AppUser admin;
    
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("airline-flights")
    List<Flight> flights;

    @Column(name = "status", nullable = false)
    String status;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
