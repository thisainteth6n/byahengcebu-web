import { useEffect, useState } from "react";

function EndTripModal({

                          show,

                          trip,

                          onClose,

                          onConfirm

                      }) {

    const [endOdometer, setEndOdometer] = useState("");

    useEffect(() => {

        if (show) {

            setEndOdometer("");

        }

    }, [show]);

    if (!show || !trip) {

        return null;

    }

    const submit = () => {

        if (!endOdometer) {

            alert("Please enter the end odometer.");

            return;

        }

        if (Number(endOdometer) <= trip.startOdometer) {

            alert(
                "End odometer must be greater than start odometer."
            );

            return;

        }

        console.log("Trip:", trip);
        console.log("Trip ID:", trip.id);

        onConfirm(
            trip.id,
            Number(endOdometer)
        );

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>End Trip</h2>

                <p>

                    <strong>Driver:</strong>{" "}

                    {trip.driverName}

                </p>

                <p>

                    <strong>Vehicle:</strong>{" "}

                    {trip.vehiclePlate}

                </p>

                <p>

                    <strong>Route:</strong>{" "}

                    {trip.route}

                </p>

                <p>

                    <strong>Start Odometer:</strong>{" "}

                    {trip.startOdometer}

                </p>

                <input

                    type="number"

                    placeholder="End Odometer"

                    value={endOdometer}

                    onChange={(e) =>

                        setEndOdometer(e.target.value)

                    }

                />

                <div className="modal-buttons">

                    <button
                        onClick={onClose}
                    >

                        Cancel

                    </button>

                    <button
                        onClick={submit}
                    >

                        End Trip

                    </button>

                </div>

            </div>

        </div>

    );

}

export default EndTripModal;