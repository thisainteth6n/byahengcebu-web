import "../styles/trip.css";

import { useTrips } from "../hooks/useTrips";

import TripStatCard from "../components/TripStatCard";
import TripTable from "../components/TripTable";

function TripDashboard() {

    const {

        trips,
        statistics,
        loading

    } = useTrips();

    if (loading) {

        return <h2>Loading trips...</h2>;

    }

    return (

        <div className="trip-dashboard">

            <h1>Trip Management</h1>

            <section className="trip-stats">

                <TripStatCard
                    title="Total Trips"
                    value={statistics.total}
                />

                <TripStatCard
                    title="Ongoing Trips"
                    value={statistics.ongoing}
                />

                <TripStatCard
                    title="Completed Trips"
                    value={statistics.completed}
                />

            </section>

            <TripTable
                trips={trips}
            />

        </div>

    );

}

export default TripDashboard;