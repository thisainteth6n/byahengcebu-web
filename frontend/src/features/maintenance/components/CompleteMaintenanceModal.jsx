import { useEffect, useState } from "react";

function CompleteMaintenanceModal({

                                      maintenance,
                                      onClose,
                                      onConfirm

                                  }) {

    const [remarks, setRemarks] = useState("");

    useEffect(() => {

        setRemarks("");

    }, [maintenance]);

    if (!maintenance) {

        return null;

    }

    const submit = () => {

        onConfirm(remarks);

    };

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Complete Maintenance</h2>

                <p>

                    <strong>Vehicle:</strong>

                    {" "}

                    {maintenance.vehiclePlate}

                </p>

                <p>

                    <strong>Type:</strong>

                    {" "}

                    {maintenance.maintenanceType}

                </p>

                <textarea

                    placeholder="Remarks"

                    value={remarks}

                    onChange={(e) =>

                        setRemarks(e.target.value)

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

                        Complete

                    </button>

                </div>

            </div>

        </div>

    );

}

export default CompleteMaintenanceModal;