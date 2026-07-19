function MaintenanceTable({

                              maintenance,

                              onComplete,

                              onDelete

                          }) {

    return (

        <table className="trip-table">

            <thead>

            <tr>

                <th>Vehicle</th>

                <th>Type</th>

                <th>Technician</th>

                <th>Scheduled</th>

                <th>Status</th>

                <th>Actions</th>

            </tr>

            </thead>

            <tbody>

            {

                maintenance.length > 0 ? (

                    maintenance.map((record) => (

                        <tr key={record.id}>

                            <td>

                                {record.vehiclePlate}

                            </td>

                            <td>

                                {record.maintenanceType}

                            </td>

                            <td>

                                {record.technician}

                            </td>

                            <td>

                                {record.scheduledDate}

                            </td>

                            <td>

                                {record.status}

                            </td>

                            <td>

                                {

                                    record.status === "SCHEDULED" && (

                                        <button

                                            onClick={() =>

                                                onComplete(record)

                                            }

                                        >

                                            Complete

                                        </button>

                                    )

                                }

                                {

                                    record.status === "COMPLETED" && (

                                        <button

                                            onClick={() => {

                                                if (

                                                    window.confirm(

                                                        "Delete this maintenance record?"

                                                    )

                                                ) {

                                                    onDelete(record.id);

                                                }

                                            }}

                                        >

                                            Delete

                                        </button>

                                    )

                                }

                            </td>

                        </tr>

                    ))

                ) : (

                    <tr>

                        <td

                            colSpan="6"

                            style={{

                                textAlign: "center",

                                padding: "25px"

                            }}

                        >

                            No maintenance records.

                        </td>

                    </tr>

                )

            }

            </tbody>

        </table>

    );

}

export default MaintenanceTable;