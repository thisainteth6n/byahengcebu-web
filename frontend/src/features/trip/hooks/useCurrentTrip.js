import { useEffect, useState } from "react";

import { getDriverCurrentTrip } from "../services/tripService";

export function useCurrentTrip() {

    const [currentTrip, setCurrentTrip] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadCurrentTrip();

    }, []);

    const loadCurrentTrip = async () => {

        try {

            const user = JSON.parse(
                localStorage.getItem("user")
            );

            const response = await getDriverCurrentTrip(
                user.email
            );

            setCurrentTrip(response.data);

        }

        catch (error) {

            // No ongoing trip is normal.
            setCurrentTrip(null);

        }

        finally {

            setLoading(false);

        }

    };

    return {

        currentTrip,

        loading,

        refreshCurrentTrip: loadCurrentTrip

    };

}