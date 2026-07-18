package edu.cit.saranza.byahengcebu.features.trip.repository;

import edu.cit.saranza.byahengcebu.features.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    // Existing

    List<Trip> findByStatus(String status);

    long countByStatus(String status);

    List<Trip> findByDriverNameContainingIgnoreCaseOrVehiclePlateContainingIgnoreCaseOrRouteContainingIgnoreCase(
            String driverName,
            String vehiclePlate,
            String route
    );

    // ==========================
    // BUSINESS RULES
    // ==========================

    boolean existsByDriverNameAndStatus(
            String driverName,
            String status
    );

    boolean existsByVehiclePlateAndStatus(
            String vehiclePlate,
            String status
    );

    List<Trip> findByDriverName(String driverName);

}