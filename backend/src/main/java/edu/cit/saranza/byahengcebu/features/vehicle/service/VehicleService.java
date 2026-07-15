package edu.cit.saranza.byahengcebu.features.vehicle.service;

import edu.cit.saranza.byahengcebu.features.vehicle.entity.Vehicle;
import edu.cit.saranza.byahengcebu.features.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // ==========================
    // GET ALL VEHICLES
    // ==========================

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // ==========================
    // DRIVER VEHICLES
    // ==========================

    public List<Vehicle> getVehiclesByDriver(String email) {
        return vehicleRepository.findByAssignedDriverEmail(email);
    }

    // ==========================
    // GET VEHICLE
    // ==========================

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // ==========================
    // ADD VEHICLE
    // ==========================

    public Vehicle addVehicle(Vehicle vehicle) {

        validateVehicle(vehicle);

        if (vehicleRepository.existsByPlateNumber(vehicle.getPlateNumber())) {
            throw new RuntimeException("Plate number already exists.");
        }

        if (vehicleRepository.existsByAssignedDriverEmail(vehicle.getAssignedDriverEmail())) {
            throw new RuntimeException("Driver is already assigned to another vehicle.");
        }

        return vehicleRepository.save(vehicle);
    }

    // ==========================
    // UPDATE VEHICLE
    // ==========================

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found."));

        validateVehicle(updatedVehicle);

        if (vehicleRepository.existsByPlateNumberAndIdNot(
                updatedVehicle.getPlateNumber(),
                id
        )) {

            throw new RuntimeException("Plate number already exists.");

        }

        if (vehicleRepository.existsByAssignedDriverEmailAndIdNot(
                updatedVehicle.getAssignedDriverEmail(),
                id
        )) {

            throw new RuntimeException("Driver is already assigned to another vehicle.");

        }

        vehicle.setPlateNumber(updatedVehicle.getPlateNumber());
        vehicle.setRoute(updatedVehicle.getRoute());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setStatus(updatedVehicle.getStatus());
        vehicle.setAssignedDriverEmail(updatedVehicle.getAssignedDriverEmail());

        return vehicleRepository.save(vehicle);

    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteVehicle(Long id) {

        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found.");
        }

        vehicleRepository.deleteById(id);

    }

    // ==========================
    // DASHBOARD
    // ==========================

    public Map<String, Long> getVehicleStatistics() {

        Map<String, Long> statistics = new HashMap<>();

        statistics.put("total", vehicleRepository.count());

        statistics.put(
                "active",
                vehicleRepository.countByStatus("ACTIVE")
        );

        statistics.put(
                "maintenance",
                vehicleRepository.countByStatus("MAINTENANCE")
        );

        return statistics;

    }

    // ==========================
    // VALIDATION
    // ==========================

    private void validateVehicle(Vehicle vehicle) {

        if (vehicle.getPlateNumber() == null ||
                vehicle.getPlateNumber().trim().isEmpty()) {

            throw new RuntimeException("Plate Number is required.");

        }

        if (vehicle.getRoute() == null ||
                vehicle.getRoute().trim().isEmpty()) {

            throw new RuntimeException("Route is required.");

        }

        if (vehicle.getModel() == null ||
                vehicle.getModel().trim().isEmpty()) {

            throw new RuntimeException("Vehicle Model is required.");

        }

        if (vehicle.getStatus() == null ||
                vehicle.getStatus().trim().isEmpty()) {

            throw new RuntimeException("Status is required.");

        }

        if (vehicle.getAssignedDriverEmail() == null ||
                vehicle.getAssignedDriverEmail().trim().isEmpty()) {

            throw new RuntimeException("Assigned Driver Email is required.");

        }

    }

}