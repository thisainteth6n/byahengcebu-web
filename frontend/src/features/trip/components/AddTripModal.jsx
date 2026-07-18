import { useEffect, useState } from "react";

import { getDrivers } from "../../auth/services/userService";
import { getAssignedVehicle } from "../../vehicles/services/vehicleService";

function AddTripModal({

                          show,

                          onClose,

                          onSave

                      }) {

    const [drivers, setDrivers] = useState([]);

    const [trip, setTrip] = useState({

        driverName: "",

        vehiclePlate: "",

        route: "",

        passengerCount: "",

        startOdometer: ""

    });

    useEffect(() => {

        if (show) {

            loadDrivers();

            setTrip({

                driverName: "",

                vehiclePlate: "",

                route: "",

                passengerCount: "",

                startOdometer: ""

            });

        }

    }, [show]);

    const loadDrivers = async () => {

        try {

            const response = await getDrivers();

            setDrivers(response.data);

        }

        catch (error) {

            console.error(error);

            alert("Unable to load drivers.");

        }

    };

    const handleChange = async (e) => {

        const { name, value } = e.target;

        if (name === "driverName") {

            const selectedDriver = drivers.find(

                driver => driver.fullname === value

            );

            setTrip({

                ...trip,

                driverName: value,

                vehiclePlate: "",

                route: ""

            });

            if (!selectedDriver) return;

            try {

                const response = await getAssignedVehicle(

                    selectedDriver.email

                );

                setTrip({

                    driverName: value,

                    vehiclePlate: response.data.plateNumber,

                    route: response.data.route,

                    passengerCount: trip.passengerCount,

                    startOdometer: trip.startOdometer

                });

            }

            catch (error) {

                alert(

                    error.response?.data ||

                    "Selected driver has no assigned vehicle."

                );

            }

            return;

        }

        setTrip({

            ...trip,

            [name]: value

        });

    };

    const handleSubmit = () => {

        if (

            !trip.driverName ||

            !trip.vehiclePlate ||

            !trip.route ||

            !trip.passengerCount ||

            !trip.startOdometer

        ) {

            alert("Please complete all fields.");

            return;

        }

        onSave({

            ...trip,

            passengerCount: Number(trip.passengerCount),

            startOdometer: Number(trip.startOdometer)

        });

    };

    if (!show) return null;

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Start New Trip</h2>

                <select

                    name="driverName"

                    value={trip.driverName}

                    onChange={handleChange}

                >

                    <option value="">

                        Select Driver

                    </option>

                    {

                        drivers.map(driver => (

                            <option

                                key={driver.id}

                                value={driver.fullname}

                            >

                                {driver.fullname}

                            </option>

                        ))

                    }

                </select>

                <input

                    value={trip.vehiclePlate}

                    readOnly

                    placeholder="Vehicle Plate"

                />

                <input

                    value={trip.route}

                    readOnly

                    placeholder="Route"

                />

                <input

                    type="number"

                    name="passengerCount"

                    placeholder="Passenger Count"

                    value={trip.passengerCount}

                    onChange={handleChange}

                />

                <input

                    type="number"

                    name="startOdometer"

                    placeholder="Start Odometer"

                    value={trip.startOdometer}

                    onChange={handleChange}

                />

                <div

                    style={{

                        display: "flex",

                        justifyContent: "flex-end",

                        gap: "10px",

                        marginTop: "20px"

                    }}

                >

                    <button onClick={onClose}>

                        Cancel

                    </button>

                    <button onClick={handleSubmit}>

                        Start Trip

                    </button>

                </div>

            </div>

        </div>

    );

}

export default AddTripModal;