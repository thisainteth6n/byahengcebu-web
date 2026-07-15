package edu.cit.saranza.byahengcebu.controller;

import edu.cit.saranza.byahengcebu.entity.Vehicle;
import edu.cit.saranza.byahengcebu.service.VehicleService;
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

    // ==========================================
    // GET ALL VEHICLES
    // ==========================================

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // ==========================================
    // GET VEHICLES ASSIGNED TO DRIVER
    // ==========================================

    @GetMapping("/driver/{email}")
    public List<Vehicle> getVehiclesByDriver(
            @PathVariable String email
    ) {
        return vehicleService.getVehiclesByDriver(email);
    }

    // ==========================================
    // DASHBOARD STATISTICS
    // ==========================================

    @GetMapping("/statistics")
    public Map<String, Long> getStatistics() {
        return vehicleService.getVehicleStatistics();
    }

    // ==========================================
    // GET VEHICLE BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(
            @PathVariable Long id
    ) {

        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    // ==========================================
    // ADD VEHICLE
    // ==========================================

    @PostMapping
    public ResponseEntity<?> addVehicle(
            @RequestBody Vehicle vehicle
    ) {

        try {

            Vehicle savedVehicle = vehicleService.addVehicle(vehicle);

            return ResponseEntity.ok(savedVehicle);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================================
    // UPDATE VEHICLE
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle
    ) {

        try {

            Vehicle updatedVehicle =
                    vehicleService.updateVehicle(id, vehicle);

            return ResponseEntity.ok(updatedVehicle);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================================
    // DELETE VEHICLE
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(
            @PathVariable Long id
    ) {

        try {

            vehicleService.deleteVehicle(id);

            return ResponseEntity.ok("Vehicle deleted successfully.");

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}