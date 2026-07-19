import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./features/auth/pages/Login.jsx";
import Register from "./features/auth/pages/Register.jsx";

import Dashboard from "./features/dashboard/Dashboard.jsx";

import TripDashboard from "./features/trip/pages/TripDashboard.jsx";
import DriverTripDashboard from "./features/trip/pages/DriverTripDashboard.jsx";

function ProtectedRoute({ children }) {

    const isLoggedIn = localStorage.getItem("isLoggedIn");

    if (!isLoggedIn) {
        return <Navigate to="/login" replace />;
    }

    return children;

}

function TripRoute() {

    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
        return <Navigate to="/login" replace />;
    }

    if (user.role === "ADMIN") {
        return <TripDashboard />;
    }

    if (user.role === "DRIVER") {
        return <DriverTripDashboard />;
    }

    return <Navigate to="/dashboard" replace />;

}

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

                <Route
                    path="/dashboard"
                    element={
                        <ProtectedRoute>
                            <Dashboard />
                        </ProtectedRoute>
                    }
                />

                <Route
                    path="/trips"
                    element={
                        <ProtectedRoute>
                            <TripRoute />
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