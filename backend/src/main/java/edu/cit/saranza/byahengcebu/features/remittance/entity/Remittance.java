package edu.cit.saranza.byahengcebu.features.remittance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "remittances")
public class Remittance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private Long tripId;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private String vehiclePlate;

    @Column(nullable = false)
    private Double totalCollection;

    @Column(nullable = false)
    private Double expenses;

    @Column(nullable = false)
    private Double netCollection;

    private String remarks;

    @Column(nullable = false)
    private String status;

    private LocalDateTime submittedDate;

    public Remittance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public Double getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(Double totalCollection) {
        this.totalCollection = totalCollection;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getNetCollection() {
        return netCollection;
    }

    public void setNetCollection(Double netCollection) {
        this.netCollection = netCollection;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate) {
        this.submittedDate = submittedDate;
    }
}