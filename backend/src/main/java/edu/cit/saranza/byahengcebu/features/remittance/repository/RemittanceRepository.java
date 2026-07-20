package edu.cit.saranza.byahengcebu.features.remittance.repository;

import edu.cit.saranza.byahengcebu.features.remittance.entity.Remittance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemittanceRepository extends JpaRepository<Remittance, Long> {

    // ==========================
    // DRIVER
    // ==========================

    List<Remittance> findByDriverName(String driverName);

    // ==========================
    // STATUS
    // ==========================

    List<Remittance> findByStatus(String status);

    long countByStatus(String status);

    // ==========================
    // BUSINESS RULES
    // ==========================

    boolean existsByTripId(Long tripId);

}