import Button from "../../../shared/ui/Button";
import Badge from "../../../shared/ui/Badge";

function TripTable({

                       trips,
                       onDelete,
                       showDelete = true

                   }) {

    return (

        <tbody>

        {

            trips.map(trip => (

                <tr key={trip.id}>

                    <td>{trip.driverName}</td>

                    <td>{trip.vehiclePlate}</td>

                    <td>{trip.route}</td>

                    <td>{trip.startOdometer}</td>

                    <td>{trip.endOdometer ?? "-"}</td>

                    <td>

                        <Badge status={trip.status} />

                    </td>

                    <td>{trip.passengerCount}</td>

                    <td>

                        {

                            showDelete ? (

                                trip.status === "ONGOING"

                                    ? (

                                        <span
                                            style={{
                                                color:"#2563eb",
                                                fontWeight:"600"
                                            }}
                                        >

                                            In Progress

                                        </span>

                                    )

                                    : (

                                        <Button

                                            variant="danger"

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

                                        </Button>

                                    )

                            ) : (

                                <span
                                    style={{
                                        color:"#64748b",
                                        fontWeight:"500"
                                    }}
                                >

                                    —

                                </span>

                            )

                        }

                    </td>

                </tr>

            ))

        }

        </tbody>

    );

}

export default TripTable;