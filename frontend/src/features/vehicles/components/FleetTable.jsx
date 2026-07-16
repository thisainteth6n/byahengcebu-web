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

            <section className="fleet-section">

                <div className="fleet-header">

                    <h2>Fleet Overview</h2>

                    {

                        isAdmin && (

                            <button

                                className="primary-btn"

                                onClick={() => {

                                    setSelectedVehicle(null);
                                    setShowModal(true);

                                }}

                            >

                                <FaPlus />

                                <span>Add Vehicle</span>

                            </button>

                        )

                    }

                </div>

                <div className="search-container">

                    <FaSearch className="search-icon" />

                    <input

                        type="text"

                        className="search-input"

                        placeholder="Search plate number, route or model..."

                        value={search}

                        onChange={(e) => setSearch(e.target.value)}

                    />

                </div>

                <table>

                    <thead>

                    <tr>

                        <th>Plate Number</th>
                        <th>Route</th>
                        <th>Model</th>
                        <th>Assigned Driver</th>
                        <th>Status</th>

                        {

                            isAdmin &&

                            <th>Action</th>

                        }

                    </tr>

                    </thead>

                    <tbody>

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

                                    <span

                                        className={

                                            vehicle.status === "ACTIVE"

                                                ? "status active"

                                                : "status maintenance"

                                        }

                                    >

                                        {vehicle.status}

                                    </span>

                                </td>

                                {

                                    isAdmin && (

                                        <td>

                                            <button

                                                className="edit-btn"

                                                onClick={() => {

                                                    setSelectedVehicle(vehicle);
                                                    setShowModal(true);

                                                }}

                                            >

                                                <FaEdit />

                                                Edit

                                            </button>

                                            <button

                                                className="delete-btn"

                                                onClick={() => {

                                                    setSelectedVehicle(vehicle);
                                                    setShowDeleteModal(true);

                                                }}

                                            >

                                                <FaTrash />

                                                Delete

                                            </button>

                                        </td>

                                    )

                                }

                            </tr>

                        ))

                    }

                    {

                        filteredVehicles.length === 0 && (

                            <tr>

                                <td

                                    colSpan={isAdmin ? 6 : 5}

                                    style={{

                                        textAlign: "center",

                                        padding: "30px",

                                        color: "#777"

                                    }}

                                >

                                    No vehicles found.

                                </td>

                            </tr>

                        )

                    }

                    </tbody>

                </table>

            </section>

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