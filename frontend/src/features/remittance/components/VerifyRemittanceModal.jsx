function VerifyRemittanceModal({

                                   show,

                                   remittance,

                                   onClose,

                                   onVerify

                               }) {

    if (!show || !remittance) {

        return null;

    }

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>

                    Verify Remittance

                </h2>

                <p>

                    Driver:

                    {" "}

                    <strong>

                        {remittance.driverName}

                    </strong>

                </p>

                <p>

                    Vehicle:

                    {" "}

                    <strong>

                        {remittance.vehiclePlate}

                    </strong>

                </p>

                <p>

                    Net Collection:

                    {" "}

                    <strong>

                        ₱{remittance.netCollection}

                    </strong>

                </p>

                <div className="modal-buttons">

                    <button

                        onClick={onClose}

                    >

                        Cancel

                    </button>

                    <button

                        onClick={() =>

                            onVerify(remittance.id)

                        }

                    >

                        Verify

                    </button>

                </div>

            </div>

        </div>

    );

}

export default VerifyRemittanceModal;