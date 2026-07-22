import "./DashboardWidget.css";

function RecentTripsWidget({ trips }) {

    return (

        <div className="dashboard-widget">

            <h3>Recent Trips</h3>

            {

                trips.length === 0 ? (

                    <p className="empty-text">

                        No recent trips.

                    </p>

                ) : (

                    <table className="widget-table">

                        <thead>

                        <tr>

                            <th>Driver</th>

                            <th>Vehicle</th>

                            <th>Status</th>

                        </tr>

                        </thead>

                        <tbody>

                        {

                            trips.map(trip => (

                                <tr key={trip.id}>

                                    <td>{trip.driverName}</td>

                                    <td>{trip.vehiclePlate}</td>

                                    <td>{trip.status}</td>

                                </tr>

                            ))

                        }

                        </tbody>

                    </table>

                )

            }

        </div>

    );

}

export default RecentTripsWidget;