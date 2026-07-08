import "./dashboard.css";

import Header from "./components/Header";
import WelcomeCard from "./components/WelcomeCard";
import StatCard from "./components/StatCard";
import FleetTable from "./components/FleetTable";

function Dashboard() {

    return (

        <div className="dashboard">

            <Header />

            <WelcomeCard />

            <section className="stats">

                <StatCard
                    title="Total Vehicles"
                    value="25"
                    subtitle="Registered Units"
                />

                <StatCard
                    title="Drivers"
                    value="18"
                    subtitle="Active Drivers"
                />

                <StatCard
                    title="Maintenance"
                    value="4"
                    subtitle="Vehicles in Service"
                />

            </section>

            <FleetTable />

        </div>

    );

}

export default Dashboard;