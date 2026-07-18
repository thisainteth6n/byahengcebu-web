import api from "../../../shared/services/api.js";

// ==========================
// VEHICLES
// ==========================

export const getVehicles = () => {
    return api.get("/vehicles");
};

export const getDriverVehicles = (email) => {
    return api.get(`/vehicles/driver/${email}`);
};

export const getAssignedVehicle = (email) => {
    return api.get(`/vehicles/assigned/${email}`);
};

export const getVehicle = (id) => {
    return api.get(`/vehicles/${id}`);
};

export const addVehicle = (vehicle) => {
    return api.post("/vehicles", vehicle);
};

export const updateVehicle = (id, vehicle) => {
    return api.put(`/vehicles/${id}`, vehicle);
};

export const deleteVehicle = (id) => {
    return api.delete(`/vehicles/${id}`);
};

// ==========================
// DASHBOARD
// ==========================

export const getStatistics = () => {
    return api.get("/vehicles/statistics");
};