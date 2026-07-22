function RemittanceTable({

                             remittances,

                             onVerify,

                             onDelete,

                             isAdmin = false

                         }) {

    return (

        <table className="remittance-table">

            <thead>

            <tr>

                <th>Driver</th>

                <th>Plate Number</th>

                <th>Total Collection</th>

                <th>Expenses</th>

                <th>Net Collection</th>

                <th>Status</th>

                {

                    isAdmin &&

                    <th>Actions</th>

                }

            </tr>

            </thead>

            <tbody>

            {

                remittances.length > 0 ? (

                    remittances.map(remittance => (

                        <tr key={remittance.id}>

                            <td>{remittance.driverName}</td>

                            <td>{remittance.vehiclePlate}</td>

                            <td>₱{remittance.totalCollection}</td>

                            <td>₱{remittance.expenses}</td>

                            <td>₱{remittance.netCollection}</td>

                            <td>

                                    <span
                                        className={
                                            remittance.status === "VERIFIED"
                                                ? "status verified"
                                                : "status pending"
                                        }
                                    >

                                        {remittance.status}

                                    </span>

                            </td>

                            {

                                isAdmin && (

                                    <td>

                                        {

                                            remittance.status === "PENDING" && (

                                                <button

                                                    className="verify-btn"

                                                    onClick={() =>
                                                        onVerify(remittance.id)
                                                    }

                                                >

                                                    Verify

                                                </button>

                                            )

                                        }

                                        <button

                                            className="delete-btn"

                                            onClick={() => {

                                                if (

                                                    window.confirm(

                                                        "Delete this remittance?"

                                                    )

                                                ) {

                                                    onDelete(remittance.id);

                                                }

                                            }}

                                        >

                                            Delete

                                        </button>

                                    </td>

                                )

                            }

                        </tr>

                    ))

                ) : (

                    <tr>

                        <td

                            colSpan={isAdmin ? 7 : 6}

                            style={{

                                textAlign: "center",

                                padding: "30px"

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