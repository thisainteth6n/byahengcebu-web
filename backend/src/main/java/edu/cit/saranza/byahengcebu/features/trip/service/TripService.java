package edu.cit.saranza.byahengcebu.features.trip.service;

import edu.cit.saranza.byahengcebu.features.trip.entity.Trip;
import edu.cit.saranza.byahengcebu.features.trip.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // ==========================
    // GET ALL TRIPS
    // ==========================

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // ==========================
    // GET ONGOING TRIPS
    // ==========================

    public List<Trip> getOngoingTrips() {
        return tripRepository.findByStatus("ONGOING");
    }

    // ==========================
    // SEARCH TRIPS
    // ==========================

    public List<Trip> searchTrips(String keyword) {

        return tripRepository
                .findByDriverNameContainingIgnoreCaseOrVehiclePlateContainingIgnoreCaseOrRouteContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                );

    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    public List<Trip> getTripsByStatus(String status) {
        return tripRepository.findByStatus(status.toUpperCase());
    }

    // ==========================
    // START TRIP
    // ==========================

    public Trip startTrip(Trip trip) {

        validateTrip(trip);

        trip.setId(null);

        trip.setStatus("ONGOING");

        trip.setStartTime(LocalDateTime.now());

        trip.setEndTime(null);

        trip.setEndOdometer(null);

        return tripRepository.save(trip);

    }

    // ==========================
    // END TRIP
    // ==========================

    public Trip endTrip(Long id, Trip updatedTrip) {

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found."));

        if (!trip.getStatus().equals("ONGOING")) {
            throw new RuntimeException("Trip has already been completed.");
        }

        if (updatedTrip.getEndOdometer() == null) {
            throw new RuntimeException("End odometer is required.");
        }

        if (updatedTrip.getEndOdometer() <= trip.getStartOdometer()) {
            throw new RuntimeException(
                    "End odometer must be greater than start odometer."
            );
        }

        trip.setEndOdometer(updatedTrip.getEndOdometer());
        trip.setEndTime(LocalDateTime.now());
        trip.setStatus("COMPLETED");

        return tripRepository.save(trip);

    }

    // ==========================
    // DELETE TRIP
    // ==========================

    public void deleteTrip(Long id) {

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found."));

        if (!trip.getStatus().equals("COMPLETED")) {
            throw new RuntimeException(
                    "Only completed trips can be deleted."
            );
        }

        tripRepository.delete(trip);

    }

    // ==========================
    // DASHBOARD STATISTICS
    // ==========================

    public Map<String, Long> getTripStatistics() {

        Map<String, Long> statistics = new HashMap<>();

        statistics.put("total", tripRepository.count());

        statistics.put(
                "ongoing",
                tripRepository.countByStatus("ONGOING")
        );

        statistics.put(
                "completed",
                tripRepository.countByStatus("COMPLETED")
        );

        return statistics;

    }

    // ==========================
    // VALIDATION
    // ==========================

    private void validateTrip(Trip trip) {

        if (trip.getDriverName() == null ||
                trip.getDriverName().trim().isEmpty()) {

            throw new RuntimeException("Driver Name is required.");

        }

        if (trip.getVehiclePlate() == null ||
                trip.getVehiclePlate().trim().isEmpty()) {

            throw new RuntimeException("Vehicle Plate is required.");

        }

        if (trip.getRoute() == null ||
                trip.getRoute().trim().isEmpty()) {

            throw new RuntimeException("Route is required.");

        }

        if (trip.getPassengerCount() == null ||
                trip.getPassengerCount() < 0) {

            throw new RuntimeException("Passenger count is invalid.");

        }

        if (trip.getStartOdometer() == null ||
                trip.getStartOdometer() < 0) {

            throw new RuntimeException("Start odometer is invalid.");

        }

    }

}