package edu.cit.saranza.byahengcebu.service;

import edu.cit.saranza.byahengcebu.entity.Trip;
import edu.cit.saranza.byahengcebu.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // Get all trips
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // Get ongoing trips
    public List<Trip> getOngoingTrips() {
        return tripRepository.findByStatus("ONGOING");
    }

    // Start a trip
    public Trip startTrip(Trip trip) {

        trip.setStatus("ONGOING");
        trip.setStartTime(LocalDateTime.now());

        return tripRepository.save(trip);
    }

    // End a trip
    public Trip endTrip(Long id, Trip updatedTrip) {

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        trip.setEndOdometer(updatedTrip.getEndOdometer());
        trip.setEndTime(LocalDateTime.now());
        trip.setStatus("COMPLETED");

        return tripRepository.save(trip);
    }

}