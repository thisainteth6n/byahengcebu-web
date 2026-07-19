import { useEffect, useState } from "react";

import { getAssignedVehicle } from "../../vehicles/services/vehicleService";

import {
    getTrips,
    getDriverTrips,
    getTripStatistics,
    startTrip,
    endTrip,
    deleteTrip,
    searchTrips
} from "../services/tripService";

export function useTrips() {

    const [trips, setTrips] = useState([]);

    const [statistics, setStatistics] = useState({

        total: 0,
        ongoing: 0,
        completed: 0

    });

    const [loading, setLoading] = useState(true);

    const refreshTrips = async () => {

        try {

            const user = JSON.parse(
                localStorage.getItem("user")
            );

            let tripResponse;

            if (user.role === "ADMIN") {

                tripResponse = await getTrips();

                const statResponse = await getTripStatistics();

                setStatistics(statResponse.data);

            } else {

                tripResponse = await getDriverTrips(
                    user.email
                );

                const list = tripResponse.data;

                setStatistics({

                    total: list.length,

                    ongoing: list.filter(
                        t => t.status === "ONGOING"
                    ).length,

                    completed: list.filter(
                        t => t.status === "COMPLETED"
                    ).length

                });

            }

            setTrips(tripResponse.data);

        }

        catch (error) {

            console.error(error);

        }

        finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        refreshTrips();

    }, []);

    // ==========================
    // SEARCH
    // ==========================

    const handleSearch = async (keyword) => {

        if (!keyword.trim()) {

            refreshTrips();

            return;

        }

        try {

            const response = await searchTrips(keyword);

            setTrips(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    // ==========================
    // START TRIP
    // ==========================

    const handleStartTrip = async (trip) => {

        try {

            const user = JSON.parse(
                localStorage.getItem("user")
            );

            // DRIVER starts their own trip
            if (user.role === "DRIVER") {

                const response = await getAssignedVehicle(
                    user.email
                );

                const vehicle = response.data;

                trip = {

                    driverName: user.fullname,

                    vehiclePlate: vehicle.plateNumber,

                    route: vehicle.route,

                    passengerCount: trip.passengerCount,

                    startOdometer: trip.startOdometer

                };

            }

            await startTrip(
                user.email,
                trip
            );

            alert("Trip started successfully.");

            await refreshTrips();

        }

        catch (error) {

            alert(
                error.response?.data ||
                "Unable to start trip."
            );

            throw error;

        }

    };

    // ==========================
    // END TRIP
    // ==========================

    const handleEndTrip = async (id, endOdometer) => {

        try {

            await endTrip(id, {

                endOdometer

            });

            alert("Trip completed successfully.");

            await refreshTrips();

        }

        catch (error) {

            alert(
                error.response?.data ||
                "Unable to end trip."
            );

        }

    };

    // ==========================
    // DELETE
    // ==========================

    const handleDeleteTrip = async (id) => {

        try {

            await deleteTrip(id);

            alert("Trip deleted successfully.");

            await refreshTrips();

        }

        catch (error) {

            alert(
                error.response?.data ||
                "Unable to delete trip."
            );

        }

    };

    return {

        trips,

        statistics,

        loading,

        refreshTrips,

        handleSearch,

        handleStartTrip,

        handleEndTrip,

        handleDeleteTrip

    };

}