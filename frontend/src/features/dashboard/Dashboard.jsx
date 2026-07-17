import "./dashboard.css";

import { useNavigate } from "react-router-dom";

import Header from "./components/Header.jsx";
import WelcomeCard from "./components/WelcomeCard.jsx";
import StatCard from "./components/StatCard.jsx";

import FleetTable from "../vehicles/components/FleetTable.jsx";

import { useVehicles } from "../vehicles/hooks/useVehicles.js";

function Dashboard() {

    const navigate = useNavigate();

    const {

        vehicles,
        statistics,
        refreshVehicles

    } = useVehicles();

    return (

        <div className="dashboard">

            <Header />

            <WelcomeCard />

            <div
                style={{
                    display: "flex",
                    justifyContent: "flex-end",
                    margin: "20px 0"
                }}
            >

                <button
                    onClick={() => navigate("/trips")}
                    style={{
                        background: "#2563eb",
                        color: "#fff",
                        border: "none",
                        borderRadius: "8px",
                        padding: "10px 18px",
                        cursor: "pointer",
                        fontWeight: "600"
                    }}
                >
                    Trip Management
                </button>

            </div>

            <section className="stats">

                <StatCard
                    title="Total Vehicles"
                    value={statistics.total}
                    subtitle="Registered Units"
                />

                <StatCard
                    title="Active Vehicles"
                    value={statistics.active}
                    subtitle="Ready for Operation"
                />

                <StatCard
                    title="Maintenance"
                    value={statistics.maintenance}
                    subtitle="Currently Under Repair"
                />

            </section>

            <FleetTable
                vehicles={vehicles}
                refreshVehicles={refreshVehicles}
            />

        </div>

    );

}

export default Dashboard;