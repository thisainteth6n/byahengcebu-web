package edu.cit.saranza.byahengcebu.features.vehicle.dto;

public class VehicleResponse {

    private Long id;
    private String plateNumber;
    private String route;
    private String model;
    private String status;
    private String assignedDriverEmail;

    public VehicleResponse() {
    }

    public VehicleResponse(
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getRoute() {
        return route;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedDriverEmail() {
        return assignedDriverEmail;
    }

}