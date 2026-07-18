import api from "../../../shared/services/api";

// ==========================
// GET ALL TRIPS
// ==========================

export const getTrips = () => api.get("/trips");

// ==========================
// GET ONGOING TRIPS
// ==========================

export const getOngoingTrips = () =>
    api.get("/trips/ongoing");

// ==========================
// SEARCH
// ==========================

export const searchTrips = (keyword) =>
    api.get(`/trips/search?keyword=${keyword}`);

// ==========================
// FILTER
// ==========================

export const getTripsByStatus = (status) =>
    api.get(`/trips/status/${status}`);

// ==========================
// STATISTICS
// ==========================

export const getTripStatistics = () =>
    api.get("/trips/statistics");

// ==========================
// START TRIP
// ==========================

export const startTrip = (trip) =>
    api.post("/trips/start", trip);

// ==========================
// END TRIP
// ==========================

export const endTrip = (id, trip = {}) =>
    api.put(`/trips/end/${id}`, trip);

// ==========================
// DELETE
// ==========================

export const deleteTrip = (id) =>
    api.delete(`/trips/${id}`);

export const getDriverTrips = (email) =>
    api.get(`/trips/driver/${email}`);