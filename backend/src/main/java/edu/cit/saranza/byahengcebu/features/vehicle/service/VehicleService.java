package edu.cit.saranza.byahengcebu.features.vehicle.service;

import edu.cit.saranza.byahengcebu.features.auth.entity.User;
import edu.cit.saranza.byahengcebu.features.auth.repository.UserRepository;
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
    private final UserRepository userRepository;

    public VehicleService(
            VehicleRepository vehicleRepository,
            UserRepository userRepository
    ) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    // ==========================
    // GET ALL VEHICLES
    // ==========================

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // ==========================
    // GET ASSIGNED VEHICLE
    // ==========================

    public Vehicle getAssignedVehicle(String email) {

        return vehicleRepository.findByAssignedDriverEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("No vehicle assigned.")
                );

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

        User driver = userRepository.findByEmail(vehicle.getAssignedDriverEmail())
                .orElseThrow(() ->
                        new RuntimeException("Assigned driver does not exist.")
                );

        if (!driver.getRole().equalsIgnoreCase("DRIVER")) {
            throw new RuntimeException("Selected user is not a DRIVER.");
        }

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
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found.")
                );

        validateVehicle(updatedVehicle);

        User driver = userRepository.findByEmail(updatedVehicle.getAssignedDriverEmail())
                .orElseThrow(() ->
                        new RuntimeException("Assigned driver does not exist.")
                );

        if (!driver.getRole().equalsIgnoreCase("DRIVER")) {
            throw new RuntimeException("Selected user is not a DRIVER.");
        }

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
    // DELETE VEHICLE
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

        statistics.put("active", vehicleRepository.countByStatus("ACTIVE"));

        statistics.put("maintenance", vehicleRepository.countByStatus("MAINTENANCE"));

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