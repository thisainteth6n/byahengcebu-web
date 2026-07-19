import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./features/auth/pages/Login.jsx";
import Register from "./features/auth/pages/Register.jsx";

import Dashboard from "./features/dashboard/Dashboard.jsx";
import DriverTripDashboard from "./features/trip/pages/DriverTripDashboard.jsx";
import TripDashboard from "./features/trip/pages/TripDashboard.jsx";

import ProtectedRoute from "./shared/routes/ProtectedRoute.jsx";
import AdminRoute from "./shared/routes/AdminRoute.jsx";
import DriverRoute from "./shared/routes/DriverRoute.jsx";

function App() {

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/"
                    element={<Navigate to="/login" replace />}
                />

                <Route
                    path="/login"
                    element={<Login />}
                />

                <Route
                    path="/register"
                    element={<Register />}
                />

                {/* ==========================
                    ADMIN
                ========================== */}

                <Route
                    path="/admin/dashboard"
                    element={
                        <ProtectedRoute>
                            <AdminRoute>
                                <Dashboard />
                            </AdminRoute>
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/admin/trips"
                    element={
                        <ProtectedRoute>
                            <AdminRoute>
                                <TripDashboard />
                            </AdminRoute>
                        </ProtectedRoute>
                    }
                />

                {/* ==========================
                    DRIVER
                ========================== */}

                <Route
                    path="/driver/dashboard"
                    element={
                        <ProtectedRoute>
                            <DriverRoute>
                                <DriverTripDashboard />
                            </DriverRoute>
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="*"
                    element={<Navigate to="/login" replace />}
                />

            </Routes>

        </BrowserRouter>

    );

}

export default App;