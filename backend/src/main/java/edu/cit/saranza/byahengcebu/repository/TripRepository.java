package edu.cit.saranza.byahengcebu.repository;

import edu.cit.saranza.byahengcebu.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    // Existing
    List<Trip> findByStatus(String status);

    // Dashboard Statistics
    long countByStatus(String status);

    // Search
    List<Trip> findByDriverNameContainingIgnoreCaseOrVehiclePlateContainingIgnoreCaseOrRouteContainingIgnoreCase(
            String driverName,
            String vehiclePlate,
            String route
    );

}