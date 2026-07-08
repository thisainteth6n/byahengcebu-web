import { useEffect, useState } from "react";
import "./dashboard.css";

import Header from "./components/Header";
import WelcomeCard from "./components/WelcomeCard";
import StatCard from "./components/StatCard";
import FleetTable from "./components/FleetTable";

import { getStatistics } from "../../services/vehicleService";

function Dashboard() {

    const [statistics, setStatistics] = useState({
        total: 0,
        active: 0,
        maintenance: 0
    });

    useEffect(() => {

        loadStatistics();

    }, []);

    const loadStatistics = async () => {

        try {

            const response = await getStatistics();

            setStatistics(response.data);

        } catch (error) {

            console.error(error);

        }

    };

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

            <FleetTable />

        </div>

    );

}

export default Dashboard;