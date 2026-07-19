import { useNavigate } from "react-router-dom";
import { useState } from "react";

import { useMaintenance } from "../hooks/useMaintenance";

import MaintenanceStatCard from "../components/MaintenanceStatCard";
import MaintenanceTable from "../components/MaintenanceTable";
import AddMaintenanceModal from "../components/AddMaintenanceModal";
import CompleteMaintenanceModal from "../components/CompleteMaintenanceModal";

import "../styles/maintenance.css";

function MaintenanceDashboard() {

    const navigate = useNavigate();

    const [showAddModal, setShowAddModal] = useState(false);

    const [selectedMaintenance, setSelectedMaintenance] = useState(null);

    const [statusFilter, setStatusFilter] = useState("ALL");

    const {

        maintenance,

        statistics,

        loading,

        handleFilter,

        handleSchedule,

        handleComplete,

        handleDelete

    } = useMaintenance();

    if (loading) {

        return <h2>Loading Maintenance...</h2>;

    }

    return (

        <div className="maintenance-dashboard">

            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    marginBottom: "20px"
                }}
            >

                <h1>Maintenance Management</h1>

                <div
                    style={{
                        display: "flex",
                        gap: "10px"
                    }}
                >

                    <button
                        onClick={() => setShowAddModal(true)}
                    >
                        + Schedule Maintenance
                    </button>

                    <button
                        onClick={() => navigate("/dashboard")}
                    >
                        ← Dashboard
                    </button>

                </div>

            </div>

            <section
                style={{
                    display: "flex",
                    gap: "20px",
                    marginBottom: "25px"
                }}
            >

                <MaintenanceStatCard

                    title="Total"

                    value={statistics.total}

                />

                <MaintenanceStatCard

                    title="Scheduled"

                    value={statistics.scheduled}

                />

                <MaintenanceStatCard

                    title="Completed"

                    value={statistics.completed}

                />

            </section>

            <div
                style={{
                    marginBottom: "20px"
                }}
            >

                <select

                    value={statusFilter}

                    onChange={(e) => {

                        setStatusFilter(e.target.value);

                        handleFilter(e.target.value);

                    }}

                >

                    <option value="ALL">All</option>

                    <option value="SCHEDULED">

                        Scheduled

                    </option>

                    <option value="COMPLETED">

                        Completed

                    </option>

                </select>

            </div>

            <MaintenanceTable

                maintenance={maintenance}

                onComplete={(record) =>

                    setSelectedMaintenance(record)

                }

                onDelete={handleDelete}

            />

            <AddMaintenanceModal

                show={showAddModal}

                onClose={() =>

                    setShowAddModal(false)

                }

                onSave={async (record) => {

                    await handleSchedule(record);

                    setShowAddModal(false);

                }}

            />

            {

                selectedMaintenance && (

                    <CompleteMaintenanceModal

                        maintenance={selectedMaintenance}

                        onClose={() =>

                            setSelectedMaintenance(null)

                        }

                        onConfirm={async (remarks) => {

                            await handleComplete(

                                selectedMaintenance.id,

                                remarks

                            );

                            setSelectedMaintenance(null);

                        }}

                    />

                )

            }

        </div>

    );

}

export default MaintenanceDashboard;