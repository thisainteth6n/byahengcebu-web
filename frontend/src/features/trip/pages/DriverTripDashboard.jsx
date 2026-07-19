import { useState } from "react";

import { useDriverVehicle } from "../hooks/useDriverVehicle";
import { useTrips } from "../hooks/useTrips";

import StartTripModal from "../components/StartTripModal";
import EndTripModal from "../components/EndTripModal";

function DriverTripDashboard() {

    const {

        vehicle,

        currentTrip,

        loading,

        refreshDashboard

    } = useDriverVehicle();

    const {

        handleStartTrip,

        handleEndTrip

    } = useTrips();

    const [showStartModal, setShowStartModal] = useState(false);

    const [showEndModal, setShowEndModal] = useState(false);

    const user = JSON.parse(localStorage.getItem("user"));

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <div className="trip-dashboard">

            <h1>Driver Dashboard</h1>

            {

                currentTrip ? (

                    <div className="trip-stat-card">

                        <h2>Current Trip</h2>

                        <p>

                            <strong>Driver:</strong>{" "}

                            {currentTrip.driverName}

                        </p>

                        <p>

                            <strong>Vehicle:</strong>{" "}

                            {currentTrip.vehiclePlate}

                        </p>

                        <p>

                            <strong>Route:</strong>{" "}

                            {currentTrip.route}

                        </p>

                        <p>

                            <strong>Passengers:</strong>{" "}

                            {currentTrip.passengerCount}

                        </p>

                        <p>

                            <strong>Start Odometer:</strong>{" "}

                            {currentTrip.startOdometer}

                        </p>

                        <p>

                            <strong>Started:</strong>{" "}

                            {new Date(currentTrip.startTime).toLocaleString()}

                        </p>

                        <button

                            onClick={() =>

                                setShowEndModal(true)

                            }

                        >

                            End Trip

                        </button>

                    </div>

                ) : (

                    <div className="trip-stat-card">

                        <h2>My Assigned Vehicle</h2>

                        {

                            vehicle ? (

                                <>

                                    <p>

                                        <strong>Plate:</strong>{" "}

                                        {vehicle.plateNumber}

                                    </p>

                                    <p>

                                        <strong>Route:</strong>{" "}

                                        {vehicle.route}

                                    </p>

                                    <p>

                                        <strong>Status:</strong>{" "}

                                        {vehicle.status}

                                    </p>

                                    <button

                                        onClick={() =>

                                            setShowStartModal(true)

                                        }

                                    >

                                        Start Trip

                                    </button>

                                </>

                            ) : (

                                <p>

                                    No assigned vehicle.

                                </p>

                            )

                        }

                    </div>

                )

            }

            <StartTripModal

                show={showStartModal}

                onClose={() =>

                    setShowStartModal(false)

                }

                onStart={async (tripData) => {

                    try {

                        await handleStartTrip({

                            driverName: user.fullname,

                            vehiclePlate: vehicle.plateNumber,

                            route: vehicle.route,

                            passengerCount: tripData.passengerCount,

                            startOdometer: tripData.startOdometer

                        });

                        setShowStartModal(false);

                        await refreshDashboard();

                    }

                    catch (error) {

                        console.error(error);

                    }

                }}

            />

            {

                currentTrip && (

                    <EndTripModal
                        show={showEndModal}
                        trip={currentTrip}
                        onClose={() => setShowEndModal(false)}
                        onConfirm={async (endOdometer) => {

                            await handleEndTrip(

                                currentTrip.id,

                                endOdometer

                            );

                            setShowEndModal(false);

                            await refreshDashboard();

                        }}
                    />

                )

            }

        </div>

    );

}

export default DriverTripDashboard;