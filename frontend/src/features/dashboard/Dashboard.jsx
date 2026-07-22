import "./dashboard.css";

import {
    FaBus,
    FaCheckCircle,
    FaTools
} from "react-icons/fa";

import WelcomeCard from "./components/WelcomeCard.jsx";

import StatCard from "../../shared/ui/StatCard";

import FleetTable from "../vehicles/components/FleetTable.jsx";

import { useDashboard } from "./hooks/useDashboard";

import AdminLayout from "../../shared/components/AdminLayout";

import RecentTripsWidget from "./components/RecentTripsWidget";
import MaintenanceWidget from "./components/MaintenanceWidget";
import RemittanceWidget from "./components/RemittanceWidget";

function Dashboard() {

    const {

        vehicles,

        vehicleStatistics,

        refreshVehicles,

        recentTrips,

        maintenanceQueue,

        pendingRemittances

    } = useDashboard();

    return (

        <AdminLayout>

            <div className="dashboard">

                <WelcomeCard />

                <section className="stats">

                    <StatCard
                        title="Total Vehicles"
                        value={vehicleStatistics.total}
                        subtitle="Registered Units"
                        icon={<FaBus />}
                        color="#2563eb"
                    />

                    <StatCard
                        title="Active Vehicles"
                        value={vehicleStatistics.active}
                        subtitle="Ready for Operation"
                        icon={<FaCheckCircle />}
                        color="#22c55e"
                    />

                    <StatCard
                        title="Maintenance"
                        value={vehicleStatistics.maintenance}
                        subtitle="Currently Under Repair"
                        icon={<FaTools />}
                        color="#f59e0b"
                    />

                </section>

                <FleetTable
                    vehicles={vehicles}
                    refreshVehicles={refreshVehicles}
                />

                <RecentTripsWidget
                    trips={recentTrips}
                />

                <MaintenanceWidget
                    maintenance={maintenanceQueue}
                />

                <RemittanceWidget
                    remittances={pendingRemittances}
                />

            </div>

        </AdminLayout>

    );

}

export default Dashboard;