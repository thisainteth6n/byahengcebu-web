import { useEffect, useState } from "react";

function AddVehicleModal({

    show,
    onClose,
    onSave,
    vehicle

}) {

    const [form, setForm] =useState({

        plateNumber:"",
        route:"",
        model:"",
        status:"ACTIVE",
        assignedDriverEmail:""

    });

    useEffect(()=>{

        if(vehicle){

            setForm({

                plateNumber:vehicle.plateNumber,
                route:vehicle.route,
                model:vehicle.model,
                status:vehicle.status,
                assignedDriverEmail:vehicle.assignedDriverEmail || ""

            });

        }else{

            setForm({

                plateNumber:"",
                route:"",
                model:"",
                status:"ACTIVE",
                assignedDriverEmail:""

            });

        }

    },[vehicle]);

    if(!show) return null;

    const handleChange=(e)=>{

        setForm({

            ...form,
            [e.target.name]:e.target.value

        });

    };

    const handleSubmit=(e)=>{

        e.preventDefault();

        onSave(form);

    };

    return(

        <div className="modal-overlay">

            <div className="modal">

                <h2>

                    {vehicle ? "Edit Vehicle" : "Add Vehicle"}

                </h2>

                <p>

                    Fill in the vehicle information below.

                </p>

                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label>Plate Number</label>

                        <input
                            name="plateNumber"
                            value={form.plateNumber}
                            onChange={handleChange}
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>Route</label>

                        <input
                            name="route"
                            value={form.route}
                            onChange={handleChange}
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>Vehicle Model</label>

                        <input
                            name="model"
                            value={form.model}
                            onChange={handleChange}
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>Status</label>

                        <select
                            name="status"
                            value={form.status}
                            onChange={handleChange}
                        >

                            <option value="ACTIVE">ACTIVE</option>

                            <option value="MAINTENANCE">MAINTENANCE</option>

                        </select>

                    </div>

                    <div className="form-group">

                        <label>Assigned Driver Email</label>

                        <input

                            type="email"

                            name="assignedDriverEmail"

                            value={form.assignedDriverEmail}

                            onChange={handleChange}

                            placeholder="driver@email.com"

                        />

                    </div>

                    <div className="modal-buttons">

                        <button
                            type="button"
                            className="cancel-btn"
                            onClick={onClose}
                        >

                            Cancel

                        </button>

                        <button
                            type="submit"
                            className="save-btn"
                        >

                            {vehicle ? "Update Vehicle" : "Save Vehicle"}

                        </button>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default AddVehicleModal;