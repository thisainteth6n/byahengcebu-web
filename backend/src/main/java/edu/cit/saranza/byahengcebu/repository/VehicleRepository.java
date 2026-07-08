package edu.cit.saranza.byahengcebu.repository;

import edu.cit.saranza.byahengcebu.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}