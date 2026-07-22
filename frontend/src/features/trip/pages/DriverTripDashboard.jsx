import { useState } from "react";
import { useNavigate } from "react-router-dom";

import { useDriverVehicle } from "../hooks/useDriverVehicle";
import { useCurrentTrip } from "../hooks/useCurrentTrip";
import { useTrips } from "../hooks/useTrips";

import StartTripModal from "../components/StartTripModal";
import EndTripModal from "../components/EndTripModal";
import DriverTripTable from "../components/DriverTripTable";
import DriverLayout from "../../../shared/components/DriverLayout";

function DriverTripDashboard() {

    const navigate = useNavigate();

    const {

        vehicle,

        loading

    } = useDriverVehicle();

    const {

        currentTrip,

        refreshCurrentTrip

    } = useCurrentTrip();

    const {

        trips,

        handleStartTrip,

        handleEndTrip,

        refreshTrips

    } = useTrips();

    const [showStartModal, setShowStartModal] = useState(false);

    const [showEndModal, setShowEndModal] = useState(false);

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <DriverLayout>

            <div className="trip-dashboard">

            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    marginBottom: "20px"
                }}
            >

                <h1>Driver Dashboard</h1>

                <div
                    style={{
                        display: "flex",
                        gap: "10px"
                    }}
                >

                </div>

            </div>

            {/* Assigned Vehicle */}

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
                                        style={{
                                            background: "#dc2626",
                                            color: "white",
                                            border: "none",
                                            borderRadius: "8px",
                                            padding: "10px 18px",
                                            cursor: "pointer",
                                            fontWeight: "600"
                                        }}
                                    >
                                        End Trip
                                    </button>

                                ) : (

                                    <button
                                        onClick={() => setShowStartModal(true)}
                                        style={{
                                            background: "#059669",
                                            color: "white",
                                            border: "none",
                                            borderRadius: "8px",
                                            padding: "10px 18px",
                                            cursor: "pointer",
                                            fontWeight: "600"
                                        }}
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

            {/* Current Trip */}

            {

                currentTrip && (

                    <div className="trip-stat-card" style={{ marginTop: "20px" }}>

                        <h2>Current Trip</h2>

                        <p>
                            <strong>Driver:</strong> {currentTrip.driverName}
                        </p>

                        <p>
                            <strong>Vehicle:</strong> {currentTrip.vehiclePlate}
                        </p>

                        <p>
                            <strong>Route:</strong> {currentTrip.route}
                        </p>

                        <p>
                            <strong>Passengers:</strong> {currentTrip.passengerCount}
                        </p>

                        <p>
                            <strong>Started:</strong> {new Date(currentTrip.startTime).toLocaleString()}
                        </p>

                        <p>
                            <strong>Status:</strong> {currentTrip.status}
                        </p>

                    </div>

                )

            }

            {/* Trip History */}

            <div className="trip-stat-card" style={{ marginTop: "20px" }}>

                <h2>My Trip History</h2>

                <DriverTripTable trips={trips} />

            </div>

            {/* Start Trip Modal */}

            <StartTripModal

                show={showStartModal}

                onClose={() => setShowStartModal(false)}

                onStart={async (data) => {

                    await handleStartTrip(data);

                    setShowStartModal(false);

                    await refreshTrips();

                    refreshCurrentTrip();

                }}

            />

            {/* End Trip Modal */}

            {

                currentTrip && (

                    <EndTripModal

                        show={showEndModal}

                        trip={currentTrip}

                        onClose={() => setShowEndModal(false)}

                        onConfirm={async (id, endOdometer) => {

                            await handleEndTrip(id, endOdometer);

                            setShowEndModal(false);

                            await refreshTrips();

                            refreshCurrentTrip();

                        }}

                    />

                )

            }

            </div>

        </DriverLayout>

    );

}

export default DriverTripDashboard;