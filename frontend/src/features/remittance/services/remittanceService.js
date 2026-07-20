import api from "../../../shared/services/api";

// ==========================
// GET ALL REMITTANCES
// ==========================

export const getRemittances = () =>
    api.get("/remittances");

// ==========================
// DRIVER REMITTANCES
// ==========================

export const getDriverRemittances = (driverName) =>
    api.get(`/remittances/driver/${driverName}`);

// ==========================
// ELIGIBLE TRIPS
// ==========================

export const getEligibleTrips = (driverName) =>
    api.get(`/remittances/driver/${driverName}/eligible`);

// ==========================
// SUBMIT REMITTANCE
// ==========================

export const submitRemittance = (remittance) =>
    api.post("/remittances", remittance);

// ==========================
// VERIFY
// ==========================

export const verifyRemittance = (id) =>
    api.put(`/remittances/verify/${id}`);

// ==========================
// DELETE
// ==========================

export const deleteRemittance = (id) =>
    api.delete(`/remittances/${id}`);

// ==========================
// STATISTICS
// ==========================

export const getRemittanceStatistics = () =>
    api.get("/remittances/statistics");