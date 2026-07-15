package edu.cit.saranza.byahengcebu.features.vehicle.dto;

public class VehicleRequest {

    private String plateNumber;
    private String route;
    private String model;
    private String status;
    private String assignedDriverEmail;

    public VehicleRequest() {
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