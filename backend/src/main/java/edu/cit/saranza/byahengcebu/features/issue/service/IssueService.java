package edu.cit.saranza.byahengcebu.features.issue.service;

import edu.cit.saranza.byahengcebu.features.issue.entity.Issue;
import edu.cit.saranza.byahengcebu.features.issue.repository.IssueRepository;
import edu.cit.saranza.byahengcebu.features.vehicle.entity.Vehicle;
import edu.cit.saranza.byahengcebu.features.vehicle.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final VehicleRepository vehicleRepository;

    public IssueService(
            IssueRepository issueRepository,
            VehicleRepository vehicleRepository
    ) {
        this.issueRepository = issueRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // ==========================
    // GET ALL
    // ==========================

    public List<Issue> getAllIssues() {

        return issueRepository.findAll();

    }

    // ==========================
    // DRIVER ISSUES
    // ==========================

    public List<Issue> getDriverIssues(
            String driverName
    ) {

        return issueRepository.findByDriverName(driverName);

    }

    // ==========================
    // SUBMIT ISSUE
    // ==========================

    public Issue submitIssue(
            Issue issue
    ) {

        Vehicle vehicle = vehicleRepository
                .findByPlateNumber(issue.getVehiclePlate())
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found.")
                );

        issue.setReportedDate(LocalDateTime.now());

        issue.setStatus("PENDING");

        return issueRepository.save(issue);

    }

    // ==========================
    // UPDATE STATUS
    // ==========================

    public Issue updateStatus(

            Long id,

            String status

    ) {

        Issue issue = issueRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Issue not found.")
                );

        issue.setStatus(status);

        return issueRepository.save(issue);

    }

}