package edu.cit.saranza.byahengcebu.features.issue.controller;

import edu.cit.saranza.byahengcebu.features.issue.entity.Issue;
import edu.cit.saranza.byahengcebu.features.issue.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "http://localhost:5173")
public class IssueController {

    private final IssueService issueService;

    public IssueController(
            IssueService issueService
    ) {

        this.issueService = issueService;

    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {

        return ResponseEntity.ok(

                issueService.getAllIssues()

        );

    }

    // ==========================
    // DRIVER ISSUES
    // ==========================

    @GetMapping("/driver/{driverName}")
    public ResponseEntity<List<Issue>> getDriverIssues(

            @PathVariable String driverName

    ) {

        return ResponseEntity.ok(

                issueService.getDriverIssues(driverName)

        );

    }

    // ==========================
    // SUBMIT
    // ==========================

    @PostMapping
    public ResponseEntity<Issue> submitIssue(

            @RequestBody Issue issue

    ) {

        return ResponseEntity.ok(

                issueService.submitIssue(issue)

        );

    }

    // ==========================
    // UPDATE STATUS
    // ==========================

    @PutMapping("/{id}")

    public ResponseEntity<Issue> updateStatus(

            @PathVariable Long id,

            @RequestParam String status

    ) {

        return ResponseEntity.ok(

                issueService.updateStatus(id, status)

        );

    }

}