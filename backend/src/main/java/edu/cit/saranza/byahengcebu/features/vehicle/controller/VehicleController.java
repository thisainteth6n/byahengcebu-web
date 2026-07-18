package edu.cit.saranza.byahengcebu.features.vehicle.controller;

import edu.cit.saranza.byahengcebu.features.vehicle.entity.Vehicle;
import edu.cit.saranza.byahengcebu.features.vehicle.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // ==========================
    // GET ALL VEHICLES
    // ==========================

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // ==========================
    // DRIVER VEHICLES
    // ==========================

    @GetMapping("/driver/{email}")
    public List<Vehicle> getDriverVehicles(
            @PathVariable String email
    ) {
        return vehicleService.getVehiclesByDriver(email);
    }

    // ==========================
    // GET ASSIGNED VEHICLE
    // ==========================

    @GetMapping("/assigned/{email}")
    public ResponseEntity<?> getAssignedVehicle(
            @PathVariable String email
    ) {

        try {

            return ResponseEntity.ok(
                    vehicleService.getAssignedVehicle(email)
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(
                    e.getMessage()
            );

        }

    }

    // ==========================
    // DASHBOARD
    // ==========================

    @GetMapping("/statistics")
    public Map<String, Long> getStatistics() {
        return vehicleService.getVehicleStatistics();
    }

    // ==========================
    // GET VEHICLE
    // ==========================

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(
            @PathVariable Long id
    ) {

        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    // ==========================
    // ADD VEHICLE
    // ==========================

    @PostMapping
    public ResponseEntity<?> addVehicle(
            @RequestBody Vehicle vehicle
    ) {

        try {

            return ResponseEntity.ok(
                    vehicleService.addVehicle(vehicle)
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // UPDATE VEHICLE
    // ==========================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle
    ) {

        try {

            return ResponseEntity.ok(
                    vehicleService.updateVehicle(id, vehicle)
            );

        }

        catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // DELETE VEHICLE
    // ==========================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(
            @PathVariable Long id
    ) {

        try {

            vehicleService.deleteVehicle(id);

            return ResponseEntity.ok("Vehicle deleted successfully.");

        }

        catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}