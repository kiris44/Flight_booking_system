package com.flightBookingSys.db_api.repositories;

import com.flightBookingSys.db_api.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline, UUID> {
    @Query(name = "select * from airlines where admin_id=:adminId", nativeQuery = true)
    public Airline getAirlineByAdminId(UUID adminId);
}
