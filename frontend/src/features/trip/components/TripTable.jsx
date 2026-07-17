function TripTable({

    trips,

    onStart,

    onEnd,

    onDelete

}) {

    return (

        <table className="trip-table">

            <thead>

            <tr>

                <th>Driver</th>
                <th>Vehicle</th>
                <th>Route</th>
                <th>Status</th>
                <th>Passengers</th>
                <th>Actions</th>

            </tr>

            </thead>

            <tbody>

            {

                trips.map((trip) => (

                    <tr key={trip.id}>

                        <td>{trip.driverName}</td>

                        <td>{trip.vehiclePlate}</td>

                        <td>{trip.route}</td>

                        <td>{trip.status}</td>

                        <td>{trip.passengerCount}</td>

                        <td>

                            {

                                trip.status === "ONGOING"

                                    ? (

                                        <button
                                            onClick={() => onEnd(trip.id)}
                                        >
                                            End Trip
                                        </button>

                                    )

                                    : (

                                        <button
                                            onClick={() => onStart(trip)}
                                        >
                                            Start Trip
                                        </button>

                                    )

                            }

                            <button

                                onClick={() => {

                                    if (window.confirm("Delete this trip?")) {

                                        onDelete(trip.id);

                                    }

                                }}

                            >

                                Delete

                            </button>

                        </td>

                    </tr>

                ))

            }

            </tbody>

        </table>

    );

}

export default TripTable;