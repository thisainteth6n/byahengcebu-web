import api from "../../../shared/services/api";

// ==========================
// GET ALL MAINTENANCE
// ==========================

export const getMaintenance = () =>
    api.get("/maintenance");

// ==========================
// GET STATISTICS
// ==========================

export const getMaintenanceStatistics = () =>
    api.get("/maintenance/statistics");

// ==========================
// FILTER BY STATUS
// ==========================

export const getMaintenanceByStatus = (status) =>
    api.get(`/maintenance/status/${status}`);

// ==========================
// GET VEHICLE HISTORY
// ==========================

export const getVehicleMaintenance = (plate) =>
    api.get(`/maintenance/vehicle/${plate}`);

// ==========================
// SCHEDULE MAINTENANCE
// ==========================

export const scheduleMaintenance = (maintenance) =>
    api.post("/maintenance", maintenance);

// ==========================
// COMPLETE MAINTENANCE
// ==========================

export const completeMaintenance = (id, maintenance) =>
    api.put(
        `/maintenance/${id}/complete`,
        maintenance
    );

// ==========================
// DELETE
// ==========================

export const deleteMaintenance = (id) =>
    api.delete(`/maintenance/${id}`);