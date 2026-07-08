import api from "./api";

export const getVehicles = () =>
    api.get("/vehicles");

export const getVehicle = (id) =>
    api.get(`/vehicles/${id}`);

export const addVehicle = (vehicle) =>
    api.post("/vehicles", vehicle);

export const updateVehicle = (id, vehicle) =>
    api.put(`/vehicles/${id}`, vehicle);

export const deleteVehicle = (id) =>
    api.delete(`/vehicles/${id}`);

// Dashboard Statistics
export const getStatistics = () =>
    api.get("/vehicles/statistics");