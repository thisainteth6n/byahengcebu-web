import { useEffect, useState } from "react";

function AddTripModal({

    show,

    onClose,

    onSave

}) {

    const [trip, setTrip] = useState({

        driverName: "",

        vehiclePlate: "",

        route: "",

        passengerCount: "",

        startOdometer: ""

    });

    useEffect(() => {

        if (show) {

            setTrip({

                driverName: "",

                vehiclePlate: "",

                route: "",

                passengerCount: "",

                startOdometer: ""

            });

        }

    }, [show]);

    if (!show) return null;

    const handleChange = (e) => {

        setTrip({

            ...trip,

            [e.target.name]: e.target.value

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

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Start New Trip</h2>

                <input

                    name="driverName"

                    placeholder="Driver Name"

                    value={trip.driverName}

                    onChange={handleChange}

                />

                <input

                    name="vehiclePlate"

                    placeholder="Vehicle Plate"

                    value={trip.vehiclePlate}

                    onChange={handleChange}

                />

                <input

                    name="route"

                    placeholder="Route"

                    value={trip.route}

                    onChange={handleChange}

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