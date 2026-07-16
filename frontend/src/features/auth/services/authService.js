import api from "../../../shared/services/api.js";

export const loginUser = (user) =>
    api.post("/auth/login", user);

export const registerUser = (user) =>
    api.post("/auth/register", user);
