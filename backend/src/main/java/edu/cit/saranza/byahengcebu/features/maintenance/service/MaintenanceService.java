package edu.cit.saranza.byahengcebu.features.maintenance.service;

import edu.cit.saranza.byahengcebu.features.maintenance.entity.Maintenance;
import edu.cit.saranza.byahengcebu.features.maintenance.repository.MaintenanceRepository;
import edu.cit.saranza.byahengcebu.features.vehicle.entity.Vehicle;
import edu.cit.saranza.byahengcebu.features.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceService(
            MaintenanceRepository maintenanceRepository,
            VehicleRepository vehicleRepository
    ) {

        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;

    }

    // ==========================
    // GET ALL
    // ==========================

    public List<Maintenance> getAllMaintenance() {

        return maintenanceRepository.findAll();

    }

    // ==========================
    // GET BY STATUS
    // ==========================

    public List<Maintenance> getByStatus(String status) {

        return maintenanceRepository.findByStatus(
                status.toUpperCase()
        );

    }

    // ==========================
    // GET BY VEHICLE
    // ==========================

    public List<Maintenance> getByVehicle(String plate) {

        return maintenanceRepository.findByVehiclePlate(
                plate
        );

    }

    // ==========================
    // CREATE MAINTENANCE
    // ==========================

    public Maintenance scheduleMaintenance(
            Maintenance maintenance
    ) {

        validateMaintenance(maintenance);

        Vehicle vehicle = vehicleRepository
                .findByPlateNumber(
                        maintenance.getVehiclePlate()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vehicle not found."
                        )
                );

        vehicle.setStatus("MAINTENANCE");

        vehicleRepository.save(vehicle);

        maintenance.setId(null);

        maintenance.setStatus("SCHEDULED");

        maintenance.setCompletedDate(null);

        return maintenanceRepository.save(
                maintenance
        );

    }

    // ==========================
    // COMPLETE MAINTENANCE
    // ==========================

    public Maintenance completeMaintenance(
            Long id,
            Maintenance updated
    ) {

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Maintenance record not found."
                                )
                        );

        Vehicle vehicle = vehicleRepository
                .findByPlateNumber(
                        maintenance.getVehiclePlate()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vehicle not found."
                        )
                );

        vehicle.setStatus("ACTIVE");

        vehicleRepository.save(vehicle);

        maintenance.setCompletedDate(
                LocalDate.now()
        );

        maintenance.setRemarks(
                updated.getRemarks()
        );

        maintenance.setStatus("COMPLETED");

        return maintenanceRepository.save(
                maintenance
        );

    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteMaintenance(Long id) {

        Maintenance maintenance =
                maintenanceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Maintenance record not found."
                                )
                        );

        if (!maintenance.getStatus().equals("COMPLETED")) {

            throw new RuntimeException(
                    "Only completed maintenance records can be deleted."
            );

        }

        maintenanceRepository.delete(
                maintenance
        );

    }

    // ==========================
    // DASHBOARD
    // ==========================

    public Map<String, Long> getStatistics() {

        Map<String, Long> stats =
                new HashMap<>();

        stats.put(
                "total",
                maintenanceRepository.count()
        );

        stats.put(
                "scheduled",
                maintenanceRepository.countByStatus(
                        "SCHEDULED"
                )
        );

        stats.put(
                "completed",
                maintenanceRepository.countByStatus(
                        "COMPLETED"
                )
        );

        return stats;

    }

    // ==========================
    // VALIDATION
    // ==========================

    private void validateMaintenance(
            Maintenance maintenance
    ) {

        if (maintenance.getVehiclePlate() == null ||
                maintenance.getVehiclePlate().isBlank()) {

            throw new RuntimeException(
                    "Vehicle is required."
            );

        }

        if (maintenance.getMaintenanceType() == null ||
                maintenance.getMaintenanceType().isBlank()) {

            throw new RuntimeException(
                    "Maintenance type is required."
            );

        }

        if (maintenance.getScheduledDate() == null) {

            throw new RuntimeException(
                    "Scheduled date is required."
            );

        }

        if (maintenance.getTechnician() == null ||
                maintenance.getTechnician().isBlank()) {

            throw new RuntimeException(
                    "Technician is required."
            );

        }

    }

}