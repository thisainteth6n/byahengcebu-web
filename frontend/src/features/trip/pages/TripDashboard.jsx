import "../styles/trip.css";

import { useNavigate } from "react-router-dom";
import { useState } from "react";

import { useTrips } from "../hooks/useTrips";

import AdminLayout from "../../../shared/components/AdminLayout";
import PageHeader from "../../../shared/components/PageHeader";

import StatCard from "../../../shared/ui/StatCard";
import Button from "../../../shared/ui/Button";

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

                    actions={

                        <Button

                            variant="secondary"

                            onClick={() => navigate("/admin/dashboard")}

                        >

                            ← Dashboard

                        </Button>

                    }

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

                <TripTable

                    trips={filteredTrips}

                    onDelete={handleDeleteTrip}

                />

            </div>

        </AdminLayout>

    );

}

export default TripDashboard;