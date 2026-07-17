import { useEffect, useState } from "react";

import {

    getTrips,
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

            const tripResponse = await getTrips();

            const statResponse = await getTripStatistics();

            setTrips(tripResponse.data);

            setStatistics(statResponse.data);

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

    const handleSearch = async (keyword) => {

        try {

            if (keyword.trim() === "") {

                refreshTrips();

                return;

            }

            const response = await searchTrips(keyword);

            setTrips(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    const handleStartTrip = async (trip) => {

        try {

            await startTrip(trip);

            refreshTrips();

        }

        catch (error) {

            alert(error.response?.data || "Unable to start trip.");

        }

    };

    const handleEndTrip = async (id, endOdometer) => {

        try {

            await endTrip(id, {

                endOdometer

            });

            refreshTrips();

        }

        catch (error) {

            alert(error.response?.data || "Unable to end trip.");

        }

    };

    const handleDeleteTrip = async (id) => {

        try {

            await deleteTrip(id);

            refreshTrips();

        }

        catch (error) {

            alert(error.response?.data || "Unable to delete trip.");

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