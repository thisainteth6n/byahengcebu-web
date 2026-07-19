package edu.cit.saranza.byahengcebu.features.maintenance.controller;

import edu.cit.saranza.byahengcebu.features.maintenance.entity.Maintenance;
import edu.cit.saranza.byahengcebu.features.maintenance.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "http://localhost:5173")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(
            MaintenanceService maintenanceService
    ) {

        this.maintenanceService = maintenanceService;

    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {

        return ResponseEntity.ok(
                maintenanceService.getAllMaintenance()
        );

    }

    // ==========================
    // GET BY STATUS
    // ==========================

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Maintenance>> getByStatus(
            @PathVariable String status
    ) {

        return ResponseEntity.ok(
                maintenanceService.getByStatus(status)
        );

    }

    // ==========================
    // GET BY VEHICLE
    // ==========================

    @GetMapping("/vehicle/{plate}")
    public ResponseEntity<List<Maintenance>> getByVehicle(
            @PathVariable String plate
    ) {

        return ResponseEntity.ok(
                maintenanceService.getByVehicle(plate)
        );

    }

    // ==========================
    // DASHBOARD STATISTICS
    // ==========================

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatistics() {

        return ResponseEntity.ok(
                maintenanceService.getStatistics()
        );

    }

    // ==========================
    // SCHEDULE MAINTENANCE
    // ==========================

    @PostMapping
    public ResponseEntity<?> scheduleMaintenance(
            @RequestBody Maintenance maintenance
    ) {

        try {

            return ResponseEntity.ok(
                    maintenanceService.scheduleMaintenance(
                            maintenance
                    )
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // COMPLETE MAINTENANCE
    // ==========================

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeMaintenance(

            @PathVariable Long id,

            @RequestBody Maintenance maintenance

    ) {

        try {

            return ResponseEntity.ok(
                    maintenanceService.completeMaintenance(
                            id,
                            maintenance
                    )
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaintenance(
            @PathVariable Long id
    ) {

        try {

            maintenanceService.deleteMaintenance(id);

            return ResponseEntity.ok(
                    "Maintenance record deleted successfully."
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

}