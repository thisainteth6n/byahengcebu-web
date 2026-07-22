import "./DashboardWidget.css";

function RemittanceWidget({ remittances }) {

    return (

        <div className="dashboard-widget">

            <h3>Pending Remittances</h3>

            {

                remittances.length === 0 ? (

                    <p className="empty-text">

                        No pending remittances.

                    </p>

                ) : (

                    <table className="widget-table">

                        <thead>

                        <tr>

                            <th>Driver</th>

                            <th>Collection</th>

                            <th>Status</th>

                        </tr>

                        </thead>

                        <tbody>

                        {

                            remittances.map(remittance => (

                                <tr key={remittance.id}>

                                    <td>{remittance.driverName}</td>

                                    <td>₱{remittance.netCollection}</td>

                                    <td>{remittance.status}</td>

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

export default RemittanceWidget;