package edu.cit.saranza.byahengcebu.controller;

import edu.cit.saranza.byahengcebu.entity.Trip;
import edu.cit.saranza.byahengcebu.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "http://localhost:5173")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // GET ALL TRIPS
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    // GET ONGOING TRIPS
    @GetMapping("/ongoing")
    public List<Trip> getOngoingTrips() {
        return tripService.getOngoingTrips();
    }

    // START TRIP
    @PostMapping("/start")
    public Trip startTrip(@RequestBody Trip trip) {
        return tripService.startTrip(trip);
    }

    // END TRIP
    @PutMapping("/end/{id}")
    public ResponseEntity<Trip> endTrip(
            @PathVariable Long id,
            @RequestBody Trip trip
    ) {

        try {
            return ResponseEntity.ok(tripService.endTrip(id, trip));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

}