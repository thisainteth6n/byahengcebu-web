import { deleteVehicle } from "../../../services/vehicleService";

function DeleteVehicleModal({
    vehicle,
    onClose,
    onDeleted
}) {

    const handleDelete = async () => {

        try {

            await deleteVehicle(vehicle.id);

            onDeleted();

            onClose();

        } catch (error) {

            console.error(error);

            alert("Failed to delete vehicle.");

        }

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Delete Vehicle</h2>

                <p>

                    Are you sure you want to delete

                    <strong>

                        {" "}
                        {vehicle.plateNumber}

                    </strong>

                    ?

                </p>

                <div className="modal-buttons">

                    <button
                        className="cancel-btn"
                        onClick={onClose}
                    >
                        Cancel
                    </button>

                    <button
                        className="delete-btn"
                        onClick={handleDelete}
                    >
                        Delete Vehicle
                    </button>

                </div>

            </div>

        </div>

    );

}

export default DeleteVehicleModal;