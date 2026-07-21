import Badge from "../../../shared/ui/Badge";

function DriverTripTable({ trips }) {

    return (

        <table className="trip-table">

            <thead>

            <tr>

                <th>Vehicle</th>
                <th>Route</th>
                <th>Start</th>
                <th>End</th>
                <th>Status</th>
                <th>Passengers</th>

            </tr>

            </thead>

            <tbody>

            {trips.map((trip) => (

                <tr key={trip.id}>

                    <td>{trip.vehiclePlate}</td>

                    <td>{trip.route}</td>

                    <td>{trip.startOdometer}</td>

                    <td>{trip.endOdometer ?? "-"}</td>

                    <td>
                        <Badge status={trip.status} />
                    </td>

                    <td>{trip.passengerCount}</td>

                </tr>

            ))}

            </tbody>

        </table>

    );

}

export default DriverTripTable;