import { useState, useEffect } from "react";

function StartTripModal({

                            show,
                            onClose,
                            onStart

                        }) {

    const [form, setForm] = useState({

        passengerCount: "",
        startOdometer: ""

    });

    useEffect(() => {

        if (show) {

            setForm({

                passengerCount: "",
                startOdometer: ""

            });

        }

    }, [show]);

    if (!show) return null;

    const handleChange = (e) => {

        setForm({

            ...form,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = () => {

        if (

            !form.passengerCount ||

            !form.startOdometer

        ) {

            alert("Please complete all fields.");

            return;

        }

        onStart({

            passengerCount: Number(form.passengerCount),

            startOdometer: Number(form.startOdometer)

        });

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Start Trip</h2>

                <p>

                    Enter today's trip details.

                </p>

                <input

                    type="number"

                    name="passengerCount"

                    placeholder="Passenger Count"

                    value={form.passengerCount}

                    onChange={handleChange}

                />

                <input

                    type="number"

                    name="startOdometer"

                    placeholder="Start Odometer"

                    value={form.startOdometer}

                    onChange={handleChange}

                />

                <div className="modal-buttons">

                    <button

                        className="cancel-btn"

                        onClick={onClose}

                    >

                        Cancel

                    </button>

                    <button

                        className="save-btn"

                        onClick={handleSubmit}

                    >

                        Start Trip

                    </button>

                </div>

            </div>

        </div>

    );

}

export default StartTripModal;