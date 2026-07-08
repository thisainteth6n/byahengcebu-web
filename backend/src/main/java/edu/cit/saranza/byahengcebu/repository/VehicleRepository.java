package edu.cit.saranza.byahengcebu.repository;

import edu.cit.saranza.byahengcebu.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    long countByStatus(String status);

}