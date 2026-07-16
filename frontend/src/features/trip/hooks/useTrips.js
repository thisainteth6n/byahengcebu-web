import { useEffect, useState } from "react";

import {

    getTrips,
    getTripStatistics

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

        finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        refreshTrips();

    }, []);

    return {

        trips,
        statistics,
        loading,
        refreshTrips

    };

}