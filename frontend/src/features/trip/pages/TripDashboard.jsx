import "../styles/trip.css";

import { useState } from "react";

import { useTrips } from "../hooks/useTrips";

import AdminLayout from "../../../shared/components/AdminLayout";
import PageHeader from "../../../shared/components/PageHeader";

import StatCard from "../../../shared/ui/StatCard";

import TripTable from "../components/TripTable";

function TripDashboard() {

    const [statusFilter, setStatusFilter] = useState("ALL");

    const [search, setSearch] = useState("");

    const {

        trips,
        statistics,
        loading,
        handleSearch,
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

        <AdminLayout>

            <div className="trip-dashboard">

                <PageHeader

                    title="Trip Monitoring"

                    subtitle="Monitor all ongoing and completed trips"

                />

                <section className="stats">

                    <StatCard

                        title="Total Trips"

                        value={statistics.total}

                        subtitle="All Recorded Trips"

                    />

                    <StatCard

                        title="Ongoing Trips"

                        value={statistics.ongoing}

                        subtitle="Currently Running"

                    />

                    <StatCard

                        title="Completed Trips"

                        value={statistics.completed}

                        subtitle="Finished Trips"

                    />

                </section>

                <div className="trip-toolbar">

                    <div className="search-container">

                        <input

                            className="search-input"

                            type="text"

                            placeholder="Search Driver, Vehicle or Route..."

                            value={search}

                            onChange={(e) => {

                                setSearch(e.target.value);

                                handleSearch(e.target.value);

                            }}

                        />

                    </div>

                    <select

                        className="trip-filter"

                        value={statusFilter}

                        onChange={(e) =>

                            setStatusFilter(e.target.value)

                        }

                    >

                        <option value="ALL">

                            All Trips

                        </option>

                        <option value="ONGOING">

                            Ongoing

                        </option>

                        <option value="COMPLETED">

                            Completed

                        </option>

                    </select>

                </div>

                <div className="trip-table-container">

                    <table className="trip-table">

                        <thead>

                        <tr>

                            <th>Driver Name</th>

                            <th>Plate Number</th>

                            <th>Route</th>

                            <th>Start Odometer</th>

                            <th>End Odometer</th>

                            <th>Status</th>

                            <th>Passengers</th>

                            <th>Actions</th>

                        </tr>

                        </thead>

                        <TripTable

                            trips={filteredTrips}

                            onDelete={handleDeleteTrip}

                        />

                    </table>

                </div>

            </div>

        </AdminLayout>

    );

}

export default TripDashboard;