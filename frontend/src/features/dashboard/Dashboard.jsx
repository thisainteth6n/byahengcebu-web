import "./dashboard.css";

import Header from "./components/Header.jsx";
import WelcomeCard from "./components/WelcomeCard.jsx";
import StatCard from "./components/StatCard.jsx";

import FleetTable from "../vehicles/components/FleetTable.jsx";

import { useVehicles } from "../vehicles/hooks/useVehicles.js";

function Dashboard() {

    const {

        vehicles,
        statistics,
        refreshVehicles

    } = useVehicles();

    return (

        <div className="dashboard">

            <Header />

            <WelcomeCard />

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