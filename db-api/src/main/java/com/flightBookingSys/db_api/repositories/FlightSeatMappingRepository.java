package com.flightBookingSys.db_api.repositories;

import com.flightBookingSys.db_api.model.FlightSeatMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightSeatMappingRepository extends JpaRepository<FlightSeatMapping, UUID> {
}
