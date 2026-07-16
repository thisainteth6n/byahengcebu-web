function TripTable({

                       trips

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

            </tr>

            </thead>

            <tbody>

            {

                trips.map(trip => (

                    <tr key={trip.id}>

                        <td>{trip.driverName}</td>

                        <td>{trip.vehiclePlate}</td>

                        <td>{trip.route}</td>

                        <td>{trip.status}</td>

                        <td>{trip.passengerCount}</td>

                    </tr>

                ))

            }

            </tbody>

        </table>

    );

}

export default TripTable;