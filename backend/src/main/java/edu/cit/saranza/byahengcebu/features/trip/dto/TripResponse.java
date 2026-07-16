package edu.cit.saranza.byahengcebu.features.trip.dto;

import java.time.LocalDateTime;

public class TripResponse {

    private Long id;
    private String driverName;
    private String vehiclePlate;
    private String route;
    private Integer startOdometer;
    private Integer endOdometer;
    private Integer passengerCount;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TripResponse() {
    }

    public TripResponse(
            Long id,
            String driverName,
            String vehiclePlate,
            String route,
            Integer startOdometer,
            Integer endOdometer,
            Integer passengerCount,
            String status,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.id = id;
        this.driverName = driverName;
        this.vehiclePlate = vehiclePlate;
        this.route = route;
        this.startOdometer = startOdometer;
        this.endOdometer = endOdometer;
        this.passengerCount = passengerCount;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getRoute() {
        return route;
    }

    public Integer getStartOdometer() {
        return startOdometer;
    }

    public Integer getEndOdometer() {
        return endOdometer;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

}