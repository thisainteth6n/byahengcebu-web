package edu.cit.saranza.byahengcebu.features.trip.service;

import edu.cit.saranza.byahengcebu.features.trip.entity.Trip;
import edu.cit.saranza.byahengcebu.features.trip.repository.TripRepository;
import org.springframework.stereotype.Service;
import edu.cit.saranza.byahengcebu.features.vehicle.entity.Vehicle;
import edu.cit.saranza.byahengcebu.features.vehicle.repository.VehicleRepository;
import edu.cit.saranza.byahengcebu.features.auth.entity.User;
import edu.cit.saranza.byahengcebu.features.auth.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public TripService(
            TripRepository tripRepository,
            VehicleRepository vehicleRepository,
            UserRepository userRepository
    ) {

        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;

    }

    // ==========================
    // GET ALL TRIPS
    // ==========================

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

// ==========================
// DRIVER TRIPS
// ==========================

    public List<Trip> getTripsByDriver(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found.")
                );

        return tripRepository.findByDriverName(
                user.getFullname()
        );

    }

    public Trip getDriverCurrentTrip(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found.")
                );

        return tripRepository.findByDriverNameAndStatus(
                user.getFullname(),
                "ONGOING"
        ).orElse(null);

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

    public Trip startTrip(String email, Trip trip) {

        User driver = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found.")
                );

        Vehicle vehicle = vehicleRepository
                .findByAssignedDriverEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No vehicle assigned to this driver."
                        )
                );

        if ("MAINTENANCE".equals(vehicle.getStatus())) {

            throw new RuntimeException(
                    "Vehicle is currently under maintenance."
            );

        }

        if (!vehicle.getStatus().equalsIgnoreCase("ACTIVE")) {

            throw new RuntimeException(
                    "Vehicle is not available for operation."
            );

        }

        if (tripRepository.existsByDriverNameAndStatus(
                driver.getFullname(),
                "ONGOING")) {

            throw new RuntimeException(
                    "You already have an ongoing trip."
            );

        }

        if (tripRepository.existsByVehiclePlateAndStatus(
                vehicle.getPlateNumber(),
                "ONGOING")) {

            throw new RuntimeException(
                    "This vehicle already has an ongoing trip."
            );

        }

        if (trip.getPassengerCount() == null ||
                trip.getPassengerCount() < 0) {

            throw new RuntimeException(
                    "Passenger count is invalid."
            );

        }

        if (trip.getStartOdometer() == null ||
                trip.getStartOdometer() < 0) {

            throw new RuntimeException(
                    "Start odometer is invalid."
            );

        }

        trip.setId(null);

        trip.setDriverName(driver.getFullname());

        trip.setVehiclePlate(vehicle.getPlateNumber());

        trip.setRoute(vehicle.getRoute());

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
                .orElseThrow(() ->
                        new RuntimeException("Trip not found.")
                );

        if (!trip.getStatus().equals("ONGOING")) {

            throw new RuntimeException(
                    "Trip has already been completed."
            );

        }

        if (updatedTrip.getEndOdometer() == null) {

            throw new RuntimeException(
                    "End odometer is required."
            );

        }

        if (updatedTrip.getEndOdometer()
                <= trip.getStartOdometer()) {

            throw new RuntimeException(
                    "End odometer must be greater than start odometer."
            );

        }

        trip.setEndOdometer(
                updatedTrip.getEndOdometer()
        );

        trip.setEndTime(LocalDateTime.now());

        trip.setStatus("COMPLETED");

        return tripRepository.save(trip);

    }

    // ==========================
    // DELETE TRIP
    // ==========================

    public void deleteTrip(Long id) {

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Trip not found.")
                );

        if (!trip.getStatus().equals("COMPLETED")) {

            throw new RuntimeException(
                    "Only completed trips can be deleted."
            );

        }

        tripRepository.delete(trip);

    }

    // ==========================
    // DASHBOARD
    // ==========================

    public Map<String, Long> getTripStatistics() {

        Map<String, Long> statistics =
                new HashMap<>();

        statistics.put(
                "total",
                tripRepository.count()
        );

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

            throw new RuntimeException(
                    "Driver Name is required."
            );

        }

        if (trip.getVehiclePlate() == null ||
                trip.getVehiclePlate().trim().isEmpty()) {

            throw new RuntimeException(
                    "Vehicle Plate is required."
            );

        }

        if (trip.getRoute() == null ||
                trip.getRoute().trim().isEmpty()) {

            throw new RuntimeException(
                    "Route is required."
            );

        }

        if (trip.getPassengerCount() == null ||
                trip.getPassengerCount() < 0) {

            throw new RuntimeException(
                    "Passenger count is invalid."
            );

        }

        if (trip.getStartOdometer() == null ||
                trip.getStartOdometer() < 0) {

            throw new RuntimeException(
                    "Start odometer is invalid."
            );

        }

    }

}