import { useState } from "react";
import { useNavigate } from "react-router-dom";

import "../styles/maintenance.css";

import { useMaintenance } from "../hooks/useMaintenance";

import MaintenanceStatCard from "../components/MaintenanceStatCard";
import MaintenanceTable from "../components/MaintenanceTable";

import AddMaintenanceModal from "../components/AddMaintenanceModal";
import CompleteMaintenanceModal from "../components/CompleteMaintenanceModal";

import DataTable from "../../../shared/ui/DataTable";
import Button from "../../../shared/ui/Button";

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

    const filteredMaintenance =

        statusFilter === "ALL"

            ? maintenance

            : maintenance.filter(

                item => item.status === statusFilter

            );

    return (

        <div className="maintenance-dashboard">

            <section
                style={{
                    display: "flex",
                    gap: "20px",
                    marginBottom: "30px"
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

            <DataTable

                title="Maintenance Management"

                subtitle="Monitor all maintenance schedules."

                columns={[

                    "Vehicle",

                    "Type",

                    "Technician",

                    "Scheduled",

                    "Status",

                    "Actions"

                ]}

                search=""

                onSearch={() => {}}

                searchPlaceholder="Search maintenance..."

                actions={

                    <>

                        <select

                            value={statusFilter}

                            onChange={(e) => {

                                setStatusFilter(

                                    e.target.value

                                );

                                handleFilter(

                                    e.target.value

                                );

                            }}

                        >

                            <option value="ALL">

                                All

                            </option>

                            <option value="SCHEDULED">

                                Scheduled

                            </option>

                            <option value="COMPLETED">

                                Completed

                            </option>

                        </select>

                        <Button

                            variant="primary"

                            onClick={() =>

                                setShowAddModal(true)

                            }

                        >

                            Schedule

                        </Button>

                        <Button

                            variant="secondary"

                            onClick={() =>

                                navigate("/admin/dashboard")

                            }

                        >

                            Dashboard

                        </Button>

                    </>

                }

            >

                <MaintenanceTable

                    maintenance={filteredMaintenance}

                    onComplete={(record) =>

                        setSelectedMaintenance(record)

                    }

                    onDelete={handleDelete}

                />

            </DataTable>

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