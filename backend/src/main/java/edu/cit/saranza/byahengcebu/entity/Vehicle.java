package edu.cit.saranza.byahengcebu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String plateNumber;

    @Column(nullable = false)
    private String route;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String status;

    // NEW
    @Column(name = "assigned_driver_email")
    private String assignedDriverEmail;

    public Vehicle() {
    }

    public Vehicle(
            Long id,
            String plateNumber,
            String route,
            String model,
            String status,
            String assignedDriverEmail
    ) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.route = route;
        this.model = model;
        this.status = status;
        this.assignedDriverEmail = assignedDriverEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedDriverEmail() {
        return assignedDriverEmail;
    }

    public void setAssignedDriverEmail(String assignedDriverEmail) {
        this.assignedDriverEmail = assignedDriverEmail;
    }

}