package edu.cit.saranza.byahengcebu.features.vehicle.repository;

import edu.cit.saranza.byahengcebu.features.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    long countByStatus(String status);

    List<Vehicle> findByAssignedDriverEmail(String assignedDriverEmail);

    boolean existsByPlateNumber(String plateNumber);

    boolean existsByAssignedDriverEmail(String assignedDriverEmail);

    boolean existsByPlateNumberAndIdNot(
            String plateNumber,
            Long id
    );

    boolean existsByAssignedDriverEmailAndIdNot(
            String assignedDriverEmail,
            Long id
    );

    // ==========================
    // NEW
    // ==========================

    Optional<Vehicle> findByPlateNumber(String plateNumber);

}