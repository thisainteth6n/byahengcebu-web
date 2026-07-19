import { useState } from "react";

import { useDriverVehicle } from "../hooks/useDriverVehicle";
import { useCurrentTrip } from "../hooks/useCurrentTrip";
import { useTrips } from "../hooks/useTrips";

import StartTripModal from "../components/StartTripModal";
import EndTripModal from "../components/EndTripModal";

function DriverTripDashboard() {

    const {

        vehicle,

        loading

    } = useDriverVehicle();

    const {

        currentTrip,

        refreshCurrentTrip

    } = useCurrentTrip();

    const {

        handleStartTrip,

        handleEndTrip

    } = useTrips();

    const [showStartModal, setShowStartModal] = useState(false);

    const [showEndModal, setShowEndModal] = useState(false);

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <div className="trip-dashboard">

            <h1>Driver Dashboard</h1>

            <div className="trip-stat-card">

                <h2>My Assigned Vehicle</h2>

                {

                    vehicle ? (

                        <>

                            <p>

                                <strong>Plate:</strong> {vehicle.plateNumber}

                            </p>

                            <p>

                                <strong>Route:</strong> {vehicle.route}

                            </p>

                            <p>

                                <strong>Status:</strong> {vehicle.status}

                            </p>

                            {

                                currentTrip ? (

                                    <button
                                        onClick={() => setShowEndModal(true)}
                                    >
                                        End Trip
                                    </button>

                                ) : (

                                    <button
                                        onClick={() => setShowStartModal(true)}
                                    >
                                        Start Trip
                                    </button>

                                )

                            }

                        </>

                    ) : (

                        <p>No vehicle assigned.</p>

                    )

                }

            </div>

            <StartTripModal

                show={showStartModal}

                onClose={() => setShowStartModal(false)}

                onStart={async (data) => {

                    await handleStartTrip(data);

                    setShowStartModal(false);

                    refreshCurrentTrip();

                }}

            />

            {
                currentTrip && (

                    <EndTripModal

                        show={showEndModal}

                        trip={currentTrip}

                        onClose={() => setShowEndModal(false)}

                        onConfirm={async (id, endOdometer) => {

                            await handleEndTrip(id, endOdometer);

                            setShowEndModal(false);

                            refreshCurrentTrip();

                        }}

                    />

                )

            }

        </div>

    );

}

export default DriverTripDashboard;