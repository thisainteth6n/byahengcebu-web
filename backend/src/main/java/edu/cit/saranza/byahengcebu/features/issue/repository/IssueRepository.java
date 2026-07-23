package edu.cit.saranza.byahengcebu.features.issue.repository;

import edu.cit.saranza.byahengcebu.features.issue.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByDriverName(String driverName);

    List<Issue> findByStatus(String status);

}