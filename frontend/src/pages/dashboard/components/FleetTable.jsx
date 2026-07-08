import { useEffect, useState } from "react";
import {
    getVehicles,
    deleteVehicle
} from "../../../services/vehicleService";

import AddVehicleModal from "./AddVehicleModal";

function FleetTable() {

    const [vehicles, setVehicles] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [selectedVehicle, setSelectedVehicle] = useState(null);

    useEffect(() => {
        loadVehicles();
    }, []);

    const loadVehicles = async () => {
        try {
            const response = await getVehicles();
            setVehicles(response.data);
        } catch (error) {
            console.error(error);
            alert("Failed to load vehicles.");
        }
    };

    const handleDelete = async (id) => {

        const confirmed = window.confirm(
            "Are you sure you want to delete this vehicle?"
        );

        if (!confirmed) return;

        try {
            await deleteVehicle(id);
            loadVehicles();
        } catch (error) {
            console.error(error);
            alert("Failed to delete vehicle.");
        }
    };

    return (
        <>

            <section className="fleet-section">

                <div className="fleet-header">

                    <h2>Fleet Overview</h2>

                    <button
                        className="primary-btn"
                        onClick={() => {
                            setSelectedVehicle(null);
                            setShowModal(true);
                        }}
                    >
                        + Add Vehicle
                    </button>

                </div>

                <table>

                    <thead>

                    <tr>

                        <th>Plate Number</th>
                        <th>Route</th>
                        <th>Model</th>
                        <th>Status</th>
                        <th>Action</th>

                    </tr>

                    </thead>

                    <tbody>

                    {vehicles.map((vehicle) => (

                        <tr key={vehicle.id}>

                            <td>{vehicle.plateNumber}</td>

                            <td>{vehicle.route}</td>

                            <td>{vehicle.model}</td>

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

                            <td>

                                <button
                                    className="edit-btn"
                                    onClick={() => {
                                        setSelectedVehicle(vehicle);
                                        setShowModal(true);
                                    }}
                                >
                                    Edit
                                </button>

                                <button
                                    className="delete-btn"
                                    onClick={() =>
                                        handleDelete(vehicle.id)
                                    }
                                >
                                    Delete
                                </button>

                            </td>

                        </tr>

                    ))}

                    </tbody>

                </table>

            </section>

            {showModal && (

                <AddVehicleModal
                    editVehicle={selectedVehicle}
                    onClose={() => {
                        setShowModal(false);
                        setSelectedVehicle(null);
                    }}
                    onVehicleAdded={loadVehicles}
                />

            )}

        </>

    );

}

export default FleetTable;