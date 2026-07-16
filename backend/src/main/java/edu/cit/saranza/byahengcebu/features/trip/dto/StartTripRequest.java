package edu.cit.saranza.byahengcebu.features.trip.dto;

public class StartTripRequest {

    private String driverName;
    private String vehiclePlate;
    private String route;
    private Integer startOdometer;
    private Integer passengerCount;

    public StartTripRequest() {
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(Integer startOdometer) {
        this.startOdometer = startOdometer;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

}