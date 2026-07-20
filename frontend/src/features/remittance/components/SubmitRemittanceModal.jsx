import { useEffect, useState } from "react";

function SubmitRemittanceModal({

                                   show,

                                   eligibleTrips,

                                   onClose,

                                   onSubmit

                               }) {

    const [form, setForm] = useState({

        tripId: "",

        totalCollection: "",

        expenses: "",

        remarks: ""

    });

    useEffect(() => {

        if (show) {

            setForm({

                tripId: "",

                totalCollection: "",

                expenses: "",

                remarks: ""

            });

        }

    }, [show]);

    if (!show) {

        return null;

    }

    const handleChange = (e) => {

        setForm({

            ...form,

            [e.target.name]: e.target.value

        });

    };

    const submit = () => {

        if (

            !form.tripId ||

            !form.totalCollection ||

            form.expenses === ""

        ) {

            alert("Please complete all required fields.");

            return;

        }

        onSubmit({

            tripId: Number(form.tripId),

            totalCollection: Number(form.totalCollection),

            expenses: Number(form.expenses),

            remarks: form.remarks

        });

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>

                    Submit Remittance

                </h2>

                <select

                    name="tripId"

                    value={form.tripId}

                    onChange={handleChange}

                >

                    <option value="">

                        Select Completed Trip

                    </option>

                    {

                        eligibleTrips.map(

                            trip => (

                                <option

                                    key={trip.id}

                                    value={trip.id}

                                >

                                    {trip.route} • {trip.vehiclePlate}

                                </option>

                            )

                        )

                    }

                </select>

                <input

                    type="number"

                    name="totalCollection"

                    placeholder="Total Collection"

                    value={form.totalCollection}

                    onChange={handleChange}

                />

                <input

                    type="number"

                    name="expenses"

                    placeholder="Expenses"

                    value={form.expenses}

                    onChange={handleChange}

                />

                <textarea

                    name="remarks"

                    placeholder="Remarks (Optional)"

                    value={form.remarks}

                    onChange={handleChange}

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

                        Submit

                    </button>

                </div>

            </div>

        </div>

    );

}

export default SubmitRemittanceModal;