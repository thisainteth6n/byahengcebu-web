package edu.cit.saranza.byahengcebu.features.remittance.controller;

import edu.cit.saranza.byahengcebu.features.remittance.entity.Remittance;
import edu.cit.saranza.byahengcebu.features.remittance.service.RemittanceService;
import edu.cit.saranza.byahengcebu.features.trip.entity.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/remittances")
@CrossOrigin(origins = "http://localhost:5173")
public class RemittanceController {

    private final RemittanceService remittanceService;

    public RemittanceController(
            RemittanceService remittanceService
    ) {
        this.remittanceService = remittanceService;
    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public ResponseEntity<List<Remittance>> getAllRemittances() {

        return ResponseEntity.ok(
                remittanceService.getAllRemittances()
        );

    }

    // ==========================
    // DRIVER REMITTANCES
    // ==========================

    @GetMapping("/driver/{driverName}")
    public ResponseEntity<List<Remittance>> getDriverRemittances(
            @PathVariable String driverName
    ) {

        return ResponseEntity.ok(
                remittanceService.getDriverRemittances(driverName)
        );

    }

    // ==========================
// ELIGIBLE TRIPS
// ==========================

    @GetMapping("/driver/{driverName}/eligible")
    public ResponseEntity<List<Trip>> getEligibleTrips(
            @PathVariable String driverName
    ) {

        return ResponseEntity.ok(

                remittanceService.getEligibleTrips(
                        driverName
                )

        );

    }

    // ==========================
    // SUBMIT
    // ==========================

    @PostMapping
    public ResponseEntity<?> submitRemittance(
            @RequestBody Remittance remittance
    ) {

        try {

            return ResponseEntity.ok(
                    remittanceService.submitRemittance(remittance)
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // VERIFY
    // ==========================

    @PutMapping("/verify/{id}")
    public ResponseEntity<?> verifyRemittance(
            @PathVariable Long id
    ) {

        try {

            return ResponseEntity.ok(
                    remittanceService.verifyRemittance(id)
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRemittance(
            @PathVariable Long id
    ) {

        try {

            remittanceService.deleteRemittance(id);

            return ResponseEntity.ok(
                    "Remittance deleted successfully."
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // STATISTICS
    // ==========================

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatistics() {

        return ResponseEntity.ok(
                remittanceService.getStatistics()
        );

    }

}