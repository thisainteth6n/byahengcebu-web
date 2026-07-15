package edu.cit.saranza.byahengcebu.controller;

import edu.cit.saranza.byahengcebu.entity.Trip;
import edu.cit.saranza.byahengcebu.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "http://localhost:5173")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // ==========================
    // GET ALL TRIPS
    // ==========================

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {

        return ResponseEntity.ok(
                tripService.getAllTrips()
        );

    }

    // ==========================
    // GET ONGOING TRIPS
    // ==========================

    @GetMapping("/ongoing")
    public ResponseEntity<List<Trip>> getOngoingTrips() {

        return ResponseEntity.ok(
                tripService.getOngoingTrips()
        );

    }

    // ==========================
    // SEARCH TRIPS
    // ==========================

    @GetMapping("/search")
    public ResponseEntity<List<Trip>> searchTrips(
            @RequestParam String keyword
    ) {

        return ResponseEntity.ok(
                tripService.searchTrips(keyword)
        );

    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Trip>> getTripsByStatus(
            @PathVariable String status
    ) {

        return ResponseEntity.ok(
                tripService.getTripsByStatus(status)
        );

    }

    // ==========================
    // DASHBOARD STATISTICS
    // ==========================

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatistics() {

        return ResponseEntity.ok(
                tripService.getTripStatistics()
        );

    }

    // ==========================
    // START TRIP
    // ==========================

    @PostMapping("/start")
    public ResponseEntity<?> startTrip(
            @RequestBody Trip trip
    ) {

        try {

            return ResponseEntity.ok(
                    tripService.startTrip(trip)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // END TRIP
    // ==========================

    @PutMapping("/end/{id}")
    public ResponseEntity<?> endTrip(
            @PathVariable Long id,
            @RequestBody Trip trip
    ) {

        try {

            return ResponseEntity.ok(
                    tripService.endTrip(id, trip)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // DELETE TRIP
    // ==========================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(
            @PathVariable Long id
    ) {

        try {

            tripService.deleteTrip(id);

            return ResponseEntity.ok(
                    "Trip deleted successfully."
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

}