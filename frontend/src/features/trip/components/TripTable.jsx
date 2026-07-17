import { useState } from "react";

import EndTripModal from "./EndTripModal";

function TripTable({

    trips,

    onStart,

    onEnd,

    onDelete

}) {

    const [selectedTrip, setSelectedTrip] = useState(null);

    return (

        <>

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

                    trips.map((trip) => (

                        <tr key={trip.id}>

                            <td>{trip.driverName}</td>

                            <td>{trip.vehiclePlate}</td>

                            <td>{trip.route}</td>

                            <td>{trip.startOdometer}</td>

                            <td>

                                {

                                    trip.endOdometer ??

                                    "-"

                                }

                            </td>

                            <td>{trip.status}</td>

                            <td>{trip.passengerCount}</td>

                            <td>

                                {trip.status === "ONGOING" ? (
                                    <button onClick={() => onEnd(trip.id)}>
                                        End Trip
                                    </button>
                                ) : null}

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

            {

                selectedTrip && (

                    <EndTripModal

                        trip={selectedTrip}

                        onClose={() =>

                            setSelectedTrip(null)

                        }

                        onConfirm={async (id, endOdometer) => {

                            await onEnd(id, endOdometer);

                            setSelectedTrip(null);

                        }}

                    />

                )

            }

        </>

    );

}

export default TripTable;