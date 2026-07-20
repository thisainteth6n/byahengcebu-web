package edu.cit.saranza.byahengcebu.features.remittance.service;

import edu.cit.saranza.byahengcebu.features.remittance.entity.Remittance;
import edu.cit.saranza.byahengcebu.features.remittance.repository.RemittanceRepository;
import edu.cit.saranza.byahengcebu.features.trip.entity.Trip;
import edu.cit.saranza.byahengcebu.features.trip.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RemittanceService {

    private final RemittanceRepository remittanceRepository;
    private final TripRepository tripRepository;

    public RemittanceService(
            RemittanceRepository remittanceRepository,
            TripRepository tripRepository
    ) {

        this.remittanceRepository = remittanceRepository;
        this.tripRepository = tripRepository;

    }

    // ==========================
    // GET ALL
    // ==========================

    public List<Remittance> getAllRemittances() {

        return remittanceRepository.findAll();

    }

    // ==========================
    // DRIVER REMITTANCES
    // ==========================

    public List<Remittance> getDriverRemittances(
            String driverName
    ) {

        return remittanceRepository.findByDriverName(driverName);

    }

    // ==========================
    // SUBMIT REMITTANCE
    // ==========================

    public Remittance submitRemittance(Remittance remittance) {

        Trip trip = tripRepository.findById(remittance.getTripId())
                .orElseThrow(() ->
                        new RuntimeException("Trip not found.")
                );

        if (!trip.getStatus().equals("COMPLETED")) {

            throw new RuntimeException(
                    "Only completed trips can be remitted."
            );

        }

        if (remittanceRepository.existsByTripId(
                remittance.getTripId()
        )) {

            throw new RuntimeException(
                    "This trip already has a remittance."
            );

        }

        remittance.setDriverName(
                trip.getDriverName()
        );

        remittance.setVehiclePlate(
                trip.getVehiclePlate()
        );

        remittance.setNetCollection(

                remittance.getTotalCollection()

                        - remittance.getExpenses()

        );

        remittance.setSubmittedDate(
                LocalDateTime.now()
        );

        remittance.setStatus("PENDING");

        return remittanceRepository.save(remittance);

    }

    // ==========================
    // VERIFY
    // ==========================

    public Remittance verifyRemittance(
            Long id
    ) {

        Remittance remittance = remittanceRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Remittance not found."
                        )
                );

        remittance.setStatus("VERIFIED");

        return remittanceRepository.save(remittance);

    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteRemittance(
            Long id
    ) {

        Remittance remittance = remittanceRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Remittance not found."
                        )
                );

        remittanceRepository.delete(remittance);

    }

    // ==========================
    // STATISTICS
    // ==========================

    // ==========================
// ELIGIBLE TRIPS
// ==========================

    public List<Trip> getEligibleTrips(
            String driverName
    ) {

        List<Trip> completedTrips = tripRepository
                .findAllByDriverNameAndStatus(
                        driverName,
                        "COMPLETED"
                );

        return completedTrips.stream()

                .filter(trip ->

                        !remittanceRepository.existsByTripId(
                                trip.getId()
                        )

                )

                .toList();

    }

    public Map<String, Long> getStatistics() {

        return Map.of(

                "total",
                remittanceRepository.count(),

                "pending",
                remittanceRepository.countByStatus(
                        "PENDING"
                ),

                "verified",
                remittanceRepository.countByStatus(
                        "VERIFIED"
                )

        );

    }

}