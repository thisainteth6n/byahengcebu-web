package edu.cit.saranza.byahengcebu.dto;

public class VehicleDTO {

    private Long id;
    private String plateNumber;
    private String route;
    private String model;
    private String status;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, String plateNumber, String route, String model, String status) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.route = route;
        this.model = model;
        this.status = status;
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
}