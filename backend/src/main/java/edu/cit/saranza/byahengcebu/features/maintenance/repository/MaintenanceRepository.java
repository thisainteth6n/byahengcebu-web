package edu.cit.saranza.byahengcebu.features.maintenance.repository;

import edu.cit.saranza.byahengcebu.features.maintenance.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    // ==========================
    // SEARCH BY STATUS
    // ==========================

    List<Maintenance> findByStatus(String status);

    // ==========================
    // SEARCH BY VEHICLE
    // ==========================

    List<Maintenance> findByVehiclePlate(String vehiclePlate);

    // ==========================
    // DASHBOARD
    // ==========================

    long countByStatus(String status);

}