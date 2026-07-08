package edu.cit.saranza.byahengcebu.service;

import edu.cit.saranza.byahengcebu.entity.Vehicle;
import edu.cit.saranza.byahengcebu.repository.VehicleRepository;
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

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Add vehicle
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Update vehicle
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setPlateNumber(updatedVehicle.getPlateNumber());
        vehicle.setRoute(updatedVehicle.getRoute());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setStatus(updatedVehicle.getStatus());

        return vehicleRepository.save(vehicle);
    }

    // Delete vehicle
    public void deleteVehicle(Long id) {

        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found");
        }

        vehicleRepository.deleteById(id);
    }

    // Dashboard statistics
    public Map<String, Long> getVehicleStatistics() {

        Map<String, Long> statistics = new HashMap<>();

        statistics.put("total", vehicleRepository.count());

        statistics.put("active",
                vehicleRepository.countByStatus("ACTIVE"));

        statistics.put("maintenance",
                vehicleRepository.countByStatus("MAINTENANCE"));

        return statistics;
    }

}