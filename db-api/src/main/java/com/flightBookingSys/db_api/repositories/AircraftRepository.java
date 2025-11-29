package com.flightBookingSys.db_api.repositories;

import com.flightBookingSys.db_api.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, UUID> {
}
