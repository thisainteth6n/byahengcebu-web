import api from "../../../shared/services/api";

// ==========================
// GET ALL DRIVERS
// ==========================

export const getDrivers = () => {
    return api.get("/users/drivers");
};