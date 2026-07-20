import Button from "../../../shared/ui/Button";
import Badge from "../../../shared/ui/Badge";

function MaintenanceTable({

                              maintenance,

                              onComplete,

                              onDelete

                          }) {

    return (

        <>

            {

                maintenance.map((record) => (

                    <tr key={record.id}>

                        <td>{record.vehiclePlate}</td>

                        <td>{record.maintenanceType}</td>

                        <td>{record.technician}</td>

                        <td>{record.scheduledDate}</td>

                        <td>

                            <Badge status={record.status} />

                        </td>

                        <td>

                            {

                                record.status === "SCHEDULED"

                                    ?

                                    <Button

                                        variant="primary"

                                        onClick={() =>

                                            onComplete(record)

                                        }

                                    >

                                        Complete

                                    </Button>

                                    :

                                    <Button

                                        variant="danger"

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

                                    </Button>

                            }

                        </td>

                    </tr>

                ))

            }

        </>

    );

}

export default MaintenanceTable;