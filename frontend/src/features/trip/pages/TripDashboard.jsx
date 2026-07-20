import "../styles/trip.css";

import { useNavigate } from "react-router-dom";
import { useState } from "react";

import { useTrips } from "../hooks/useTrips";

import TripStatCard from "../components/TripStatCard";
import TripTable from "../components/TripTable";

import DataTable from "../../../shared/ui/DataTable";
import Button from "../../../shared/ui/Button";

function TripDashboard() {

    const navigate = useNavigate();

    const [statusFilter, setStatusFilter] = useState("ALL");

    const [search, setSearch] = useState("");

    const {

        trips,
        statistics,
        loading,
        handleSearch,
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

                <h1>Trip Monitoring</h1>

                <button
                    onClick={() => navigate("/admin/dashboard")}
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

            <DataTable

                title="Trip Monitoring"

                subtitle="Monitor all completed and ongoing trips."

                columns={[

                    "Driver",

                    "Vehicle",

                    "Route",

                    "Start",

                    "End",

                    "Status",

                    "Passengers",

                    "Actions"

                ]}

                search={search}

                onSearch={(value)=>{

                    setSearch(value);

                    handleSearch(value);

                }}

                searchPlaceholder="Search driver, vehicle or route..."

                actions={

                    <Button

                        variant="secondary"

                        onClick={()=>navigate("/admin/dashboard")}

                    >

                        Dashboard

                    </Button>

                }

            >

                <TripTable

                    trips={filteredTrips}

                    onDelete={handleDeleteTrip}

                />

            </DataTable>

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

                onEnd={handleEndTrip}

                onDelete={handleDeleteTrip}

            />

        </div>

    );

}

export default TripDashboard;