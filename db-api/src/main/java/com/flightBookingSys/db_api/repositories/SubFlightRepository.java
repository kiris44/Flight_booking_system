package com.flightBookingSys.db_api.repositories;

import com.flightBookingSys.db_api.model.SubFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubFlightRepository extends JpaRepository<SubFlight, UUID> {
}
