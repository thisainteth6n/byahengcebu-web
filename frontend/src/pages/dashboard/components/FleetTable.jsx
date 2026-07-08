import fleetData from "../data/sampleData";

function FleetTable() {

    return (

        <section className="fleet-section">

            <div className="fleet-header">

                <h2>Fleet Overview</h2>

                <button className="primary-btn">
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

                {fleetData.map(vehicle => (

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

                            <button className="edit-btn">
                                Edit
                            </button>

                            <button className="delete-btn">
                                Delete
                            </button>

                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

        </section>

    );

}

export default FleetTable;