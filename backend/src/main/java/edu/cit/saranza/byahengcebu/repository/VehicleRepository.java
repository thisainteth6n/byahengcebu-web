package edu.cit.saranza.byahengcebu.repository;

import edu.cit.saranza.byahengcebu.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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

}