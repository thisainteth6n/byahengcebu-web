import api from "../../../shared/services/api";

// ==========================
// GET ALL TRIPS
// ==========================

export const getTrips = () => {
    return api.get("/trips");
};

// ==========================
// GET ONGOING TRIPS
// ==========================

export const getOngoingTrips = () => {
    return api.get("/trips/ongoing");
};

// ==========================
// SEARCH TRIPS
// ==========================

export const searchTrips = (keyword) => {
    return api.get(`/trips/search?keyword=${keyword}`);
};

// ==========================
// FILTER BY STATUS
// ==========================

export const getTripsByStatus = (status) => {
    return api.get(`/trips/status/${status}`);
};

// ==========================
// DASHBOARD STATISTICS
// ==========================

export const getTripStatistics = () => {
    return api.get("/trips/statistics");
};

// ==========================
// START TRIP
// ==========================

export const startTrip = (trip) => {
    return api.post("/trips/start", trip);
};

// ==========================
// END TRIP
// ==========================

export const endTrip = (id, trip) => {
    return api.put(`/trips/end/${id}`, trip);
};

// ==========================
// DELETE TRIP
// ==========================

export const deleteTrip = (id) => {
    return api.delete(`/trips/${id}`);
};