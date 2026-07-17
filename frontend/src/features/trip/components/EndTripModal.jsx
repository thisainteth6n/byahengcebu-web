import { useState } from "react";

function EndTripModal({

    trip,
    onClose,
    onConfirm

}) {

    const [endOdometer, setEndOdometer] = useState("");

    const submit = () => {

        if (endOdometer.trim() === "") {

            alert("Please enter the end odometer.");

            return;

        }

        if (isNaN(endOdometer)) {

            alert("End odometer must be a number.");

            return;

        }

        onConfirm(

            trip.id,

            Number(endOdometer)

        );

    };

    return (

        <div
            style={{
                position: "fixed",
                top: 0,
                left: 0,
                right: 0,
                bottom: 0,
                background: "rgba(0,0,0,0.5)",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                zIndex: 999
            }}
        >

            <div
                style={{
                    background: "white",
                    padding: "25px",
                    borderRadius: "10px",
                    width: "350px"
                }}
            >

                <h2>End Trip</h2>

                <p>

                    Driver:

                    <strong> {trip.driverName}</strong>

                </p>

                <p>

                    Vehicle:

                    <strong> {trip.vehiclePlate}</strong>

                </p>

                <input

                    type="number"

                    placeholder="End Odometer"

                    value={endOdometer}

                    onChange={(e) =>

                        setEndOdometer(e.target.value)

                    }

                    style={{
                        width: "100%",
                        padding: "10px",
                        marginTop: "15px",
                        marginBottom: "20px"
                    }}

                />

                <div
                    style={{
                        display: "flex",
                        justifyContent: "flex-end",
                        gap: "10px"
                    }}
                >

                    <button onClick={onClose}>

                        Cancel

                    </button>

                    <button onClick={submit}>

                        End Trip

                    </button>

                </div>

            </div>

        </div>

    );

}

export default EndTripModal;