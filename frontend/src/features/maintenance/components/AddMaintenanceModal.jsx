import { useEffect, useState } from "react";

function AddMaintenanceModal({

                                 show,
                                 onClose,
                                 onSave

                             }) {

    const [form, setForm] = useState({

        vehiclePlate: "",
        maintenanceType: "",
        description: "",
        scheduledDate: "",
        technician: ""

    });

    useEffect(() => {

        if (show) {

            setForm({

                vehiclePlate: "",
                maintenanceType: "",
                description: "",
                scheduledDate: "",
                technician: ""

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

            !form.vehiclePlate ||

            !form.maintenanceType ||

            !form.scheduledDate ||

            !form.technician

        ) {

            alert("Please complete all required fields.");

            return;

        }

        onSave(form);

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Schedule Maintenance</h2>

                <input

                    type="text"

                    name="vehiclePlate"

                    placeholder="Vehicle Plate"

                    value={form.vehiclePlate}

                    onChange={handleChange}

                />

                <input

                    type="text"

                    name="maintenanceType"

                    placeholder="Maintenance Type"

                    value={form.maintenanceType}

                    onChange={handleChange}

                />

                <textarea

                    name="description"

                    placeholder="Description"

                    value={form.description}

                    onChange={handleChange}

                />

                <input

                    type="date"

                    name="scheduledDate"

                    value={form.scheduledDate}

                    onChange={handleChange}

                />

                <input

                    type="text"

                    name="technician"

                    placeholder="Technician"

                    value={form.technician}

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

                        Schedule

                    </button>

                </div>

            </div>

        </div>

    );

}

export default AddMaintenanceModal;