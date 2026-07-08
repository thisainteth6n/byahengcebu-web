import { useState } from "react";
import {
    addVehicle,
    updateVehicle
} from "../../../services/vehicleService";

function AddVehicleModal({
    onClose,
    onVehicleAdded,
    editVehicle = null
}) {

    const [vehicle, setVehicle] = useState(
        editVehicle ?? {
            plateNumber:"",
            route:"",
            model:"",
            status:"ACTIVE"
        }
    );

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if(editVehicle){

                await updateVehicle(editVehicle.id, vehicle);

            }else{

                await addVehicle(vehicle);

            }

            alert("Vehicle added successfully!");

            onVehicleAdded();

            onClose();

        } catch (error) {

            console.error(error);

            alert("Failed to add vehicle.");

        }

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>
                    {editVehicle ? "Edit Vehicle" : "Add New Vehicle"}
                </h2>

                <form onSubmit={handleSubmit}>

                    <label>Plate Number</label>

                    <input
                        type="text"
                        value={vehicle.plateNumber}
                        onChange={(e)=>
                            setVehicle({
                                ...vehicle,
                                plateNumber:e.target.value
                            })
                        }
                    />

                    <label>Route</label>

                    <input
                        type="text"
                        value={vehicle.route}
                        onChange={(e)=>
                            setVehicle({
                                ...vehicle,
                                route:e.target.value
                            })
                        }
                    />

                    <label>Model</label>

                    <input
                        type="text"
                        value={vehicle.model}
                        onChange={(e)=>
                            setVehicle({
                                ...vehicle,
                                model:e.target.value
                            })
                        }
                    />

                    <label>Status</label>

                    <select
                        value={vehicle.status}
                        onChange={(e)=>
                            setVehicle({
                                ...vehicle,
                                status:e.target.value
                            })
                        }
                    >

                        <option>ACTIVE</option>

                        <option>MAINTENANCE</option>

                    </select>

                    <div className="modal-buttons">

                        <button
                            type="button"
                            className="cancel-btn"
                            onClick={onClose}
                        >
                            Cancel
                        </button>

                        <button
                            type="submit"
                            className="save-btn"
                        >
                            {editVehicle ? "Update Vehicle" : "Save Vehicle"}
                        </button>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default AddVehicleModal;