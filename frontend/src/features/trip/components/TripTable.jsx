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
                <th>Start Odometer</th>
                <th>End Odometer</th>
                <th>Status</th>
                <th>Passengers</th>
                <th>Actions</th>

            </tr>

            </thead>

            <tbody>

            {

                trips.length > 0 ? (

                    trips.map((trip) => (

                        <tr key={trip.id}>

                            <td>{trip.driverName}</td>

                            <td>{trip.vehiclePlate}</td>

                            <td>{trip.route}</td>

                            <td>{trip.startOdometer}</td>

                            <td>{trip.endOdometer ?? "-"}</td>

                            <td>{trip.status}</td>

                            <td>{trip.passengerCount}</td>

                            <td>

                                {trip.status === "ONGOING" && (

                                    <button
                                        onClick={() => onEnd(trip.id)}
                                    >
                                        End Trip
                                    </button>

                                )}

                                {trip.status === "COMPLETED" && (

                                    <button

                                        onClick={() => {

                                            if (
                                                window.confirm(
                                                    "Delete this completed trip?"
                                                )
                                            ) {

                                                onDelete(trip.id);

                                            }

                                        }}

                                    >

                                        Delete

                                    </button>

                                )}

                            </td>

                        </tr>

                    ))

                ) : (

                    <tr>

                        <td
                            colSpan="8"
                            style={{
                                textAlign: "center",
                                padding: "20px"
                            }}
                        >
                            No trips found.
                        </td>

                    </tr>

                )

            }

            </tbody>

        </table>

    );

}

export default TripTable;