import "../styles/trip.css";

import { useNavigate } from "react-router-dom";
import { useState } from "react";

import { useTrips } from "../hooks/useTrips";

import TripStatCard from "../components/TripStatCard";
import TripTable from "../components/TripTable";

function TripDashboard() {

    const navigate = useNavigate();

    const [statusFilter, setStatusFilter] = useState("ALL");

    const [search, setSearch] = useState("");

    const {

        trips,

        statistics,

        loading,

        handleSearch,

        handleStartTrip,

        handleEndTrip,

        handleDeleteTrip

    } = useTrips();

    if (loading) {

        return <h2>Loading trips...</h2>;

    }

    const filteredTrips = statusFilter === "ALL"

        ? trips

        : trips.filter(

            trip => trip.status === statusFilter

        );

    return (

        <div className="trip-dashboard">

            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    marginBottom: "20px"
                }}
            >

                <h1>Trip Management</h1>

                <button

                    onClick={() => navigate("/dashboard")}

                >

                    ← Dashboard

                </button>

            </div>

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

            <div
                style={{
                    display: "flex",
                    gap: "15px",
                    margin: "20px 0"
                }}
            >

                <input

                    type="text"

                    placeholder="Search Driver, Vehicle or Route..."

                    value={search}

                    onChange={(e) => {

                        setSearch(e.target.value);

                        handleSearch(e.target.value);

                    }}

                    style={{

                        flex: 1,

                        padding: "10px"

                    }}

                />

                <select

                    value={statusFilter}

                    onChange={(e) =>

                        setStatusFilter(e.target.value)

                    }

                >

                    <option value="ALL">All</option>

                    <option value="ONGOING">Ongoing</option>

                    <option value="COMPLETED">Completed</option>

                </select>

            </div>

            <TripTable

                trips={filteredTrips}

                onStart={handleStartTrip}

                onEnd={handleEndTrip}

                onDelete={handleDeleteTrip}

            />

        </div>

    );

}

export default TripDashboard;