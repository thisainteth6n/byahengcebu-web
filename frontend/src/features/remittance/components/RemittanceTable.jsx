function RemittanceTable({

                             remittances,

                             onVerify,

                             onDelete,

                             isAdmin = false

                         }) {

    return (

        <table className="trip-table">

            <thead>

            <tr>

                <th>Driver</th>

                <th>Vehicle</th>

                <th>Total</th>

                <th>Expenses</th>

                <th>Net</th>

                <th>Status</th>

                {

                    isAdmin && (

                        <th>Actions</th>

                    )

                }

            </tr>

            </thead>

            <tbody>

            {

                remittances.length > 0 ?

                    (

                        remittances.map(

                            remittance => (

                                <tr key={remittance.id}>

                                    <td>

                                        {remittance.driverName}

                                    </td>

                                    <td>

                                        {remittance.vehiclePlate}

                                    </td>

                                    <td>

                                        ₱{remittance.totalCollection}

                                    </td>

                                    <td>

                                        ₱{remittance.expenses}

                                    </td>

                                    <td>

                                        ₱{remittance.netCollection}

                                    </td>

                                    <td>

                                        {remittance.status}

                                    </td>

                                    {

                                        isAdmin && (

                                            <td>

                                                {

                                                    remittance.status === "PENDING" && (

                                                        <button

                                                            onClick={() =>

                                                                onVerify(

                                                                    remittance.id

                                                                )

                                                            }

                                                        >

                                                            Verify

                                                        </button>

                                                    )

                                                }

                                                <button

                                                    onClick={() => {

                                                        if (

                                                            window.confirm(

                                                                "Delete this remittance?"

                                                            )

                                                        ) {

                                                            onDelete(

                                                                remittance.id

                                                            );

                                                        }

                                                    }}

                                                >

                                                    Delete

                                                </button>

                                            </td>

                                        )

                                    }

                                </tr>

                            )

                        )

                    )

                    :

                    (

                        <tr>

                            <td

                                colSpan={isAdmin ? 7 : 6}

                                style={{

                                    textAlign: "center",

                                    padding: "20px"

                                }}

                            >

                                No remittances found.

                            </td>

                        </tr>

                    )

            }

            </tbody>

        </table>

    );

}

export default RemittanceTable;