import "./dashboard.css";

import { useNavigate } from "react-router-dom";

import {
    FaBus,
    FaCheckCircle,
    FaTools
} from "react-icons/fa";

import Header from "./components/Header.jsx";
import WelcomeCard from "./components/WelcomeCard.jsx";

import StatCard from "../../shared/ui/StatCard";

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
                    gap: "10px",
                    margin: "20px 0"
                }}
            >

                <button
                    onClick={() => navigate("/admin/trips")}
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

                <button
                    onClick={() => navigate("/admin/maintenance")}
                    style={{
                        background: "#059669",
                        color: "#fff",
                        border: "none",
                        borderRadius: "8px",
                        padding: "10px 18px",
                        cursor: "pointer",
                        fontWeight: "600"
                    }}
                >
                    Maintenance
                </button>

                <button
                    onClick={() => navigate("/admin/remittances")}
                    style={{
                        background: "#7c3aed",
                        color: "#fff",
                        border: "none",
                        borderRadius: "8px",
                        padding: "10px 18px",
                        cursor: "pointer",
                        fontWeight: "600"
                    }}
                >
                    Cash Remittances
                </button>

            </div>

            <section className="stats">

                <StatCard
                    title="Total Vehicles"
                    value={statistics.total}
                    subtitle="Registered Units"
                    icon={<FaBus />}
                    color="#2563eb"
                />

                <StatCard
                    title="Active Vehicles"
                    value={statistics.active}
                    subtitle="Ready for Operation"
                    icon={<FaCheckCircle />}
                    color="#22c55e"
                />

                <StatCard
                    title="Maintenance"
                    value={statistics.maintenance}
                    subtitle="Currently Under Repair"
                    icon={<FaTools />}
                    color="#f59e0b"
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