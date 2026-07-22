import "./DashboardWidget.css";

function MaintenanceWidget({ maintenance }) {

    return (

        <div className="dashboard-widget">

            <h3>Maintenance Queue</h3>

            {

                maintenance.length === 0 ? (

                    <p className="empty-text">

                        No scheduled maintenance.

                    </p>

                ) : (

                    <table className="widget-table">

                        <thead>

                        <tr>

                            <th>Vehicle</th>

                            <th>Technician</th>

                            <th>Date</th>

                        </tr>

                        </thead>

                        <tbody>

                        {

                            maintenance.map(record => (

                                <tr key={record.id}>

                                    <td>{record.vehiclePlate}</td>

                                    <td>{record.technician}</td>

                                    <td>{record.scheduledDate}</td>

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

export default MaintenanceWidget;