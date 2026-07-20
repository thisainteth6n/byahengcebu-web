import { useState } from "react";

import {
    addVehicle,
    updateVehicle
} from "../services/vehicleService.js";

import AddVehicleModal from "./AddVehicleModal.jsx";
import DeleteVehicleModal from "./DeleteVehicleModal.jsx";

import {
    FaPlus,
    FaEdit,
    FaTrash,
    FaSearch
} from "react-icons/fa";

import Button from "../../../shared/ui/Button";

import Badge from "../../../shared/ui/Badge";

import DataTable from "../../../shared/ui/DataTable";

function FleetTable({

                        vehicles,
                        refreshVehicles

                    }) {

    const [search, setSearch] = useState("");

    const [showModal, setShowModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);

    const [selectedVehicle, setSelectedVehicle] = useState(null);

    const user = JSON.parse(localStorage.getItem("user"));

    const isAdmin = user?.role === "ADMIN";

    const filteredVehicles = vehicles.filter(vehicle => {

        const keyword = search.toLowerCase();

        return (

            vehicle.plateNumber.toLowerCase().includes(keyword) ||

            vehicle.route.toLowerCase().includes(keyword) ||

            vehicle.model.toLowerCase().includes(keyword)

        );

    });

    return (

        <>

            <DataTable

                title="Fleet Overview"

                subtitle="Manage all registered fleet vehicles."

                columns={[

                    "Plate Number",

                    "Route",

                    "Model",

                    "Assigned Driver",

                    "Status",

                    ...(isAdmin ? ["Actions"] : [])

                ]}

                search={search}

                onSearch={setSearch}

                searchPlaceholder="Search plate number, route or model..."

                actions={

                    isAdmin && (

                        <Button

                            variant="primary"

                            icon={<FaPlus />}

                            onClick={() => {

                                setSelectedVehicle(null);

                                setShowModal(true);

                            }}

                        >

                            Add Vehicle

                        </Button>

                    )

                }

            >

                {

                    filteredVehicles.map(vehicle => (

                        <tr key={vehicle.id}>

                            <td>{vehicle.plateNumber}</td>

                            <td>{vehicle.route}</td>

                            <td>{vehicle.model}</td>

                            <td>

                                {

                                    vehicle.assignedDriverEmail || "-"

                                }

                            </td>

                            <td>

                                <Badge status={vehicle.status} />

                            </td>

                            {

                                isAdmin && (

                                    <td>

                                        <Button

                                            variant="primary"

                                            icon={<FaEdit />}

                                            onClick={() => {

                                                setSelectedVehicle(vehicle);

                                                setShowModal(true);

                                            }}

                                        >

                                            Edit

                                        </Button>

                                        {" "}

                                        <Button

                                            variant="danger"

                                            icon={<FaTrash />}

                                            onClick={() => {

                                                setSelectedVehicle(vehicle);

                                                setShowDeleteModal(true);

                                            }}

                                        >

                                            Delete

                                        </Button>

                                    </td>

                                )

                            }

                        </tr>

                    ))

                }

            </DataTable>

            {

                isAdmin && (

                    <>

                        <AddVehicleModal

                            show={showModal}

                            vehicle={selectedVehicle}

                            onClose={() => {

                                setShowModal(false);
                                setSelectedVehicle(null);

                            }}

                            onSave={async (vehicleData) => {

                                try {

                                    if (selectedVehicle) {

                                        await updateVehicle(

                                            selectedVehicle.id,

                                            vehicleData

                                        );

                                    }

                                    else {

                                        await addVehicle(vehicleData);

                                    }

                                    setShowModal(false);

                                    setSelectedVehicle(null);

                                    refreshVehicles();

                                }

                                catch (error) {

                                    console.error(error);

                                    if (error.response?.data) {

                                        alert(error.response.data);

                                    }

                                    else {

                                        alert("Unable to connect to the server.");

                                    }

                                }

                            }}

                        />

                        {

                            showDeleteModal && (

                                <DeleteVehicleModal

                                    vehicle={selectedVehicle}

                                    onClose={() => {

                                        setShowDeleteModal(false);
                                        setSelectedVehicle(null);

                                    }}

                                    onDeleted={refreshVehicles}

                                />

                            )

                        }

                    </>

                )

            }

        </>

    );

}

export default FleetTable;