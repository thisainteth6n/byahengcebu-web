import { useEffect, useState } from "react";

import { getAssignedVehicle } from "../../vehicles/services/vehicleService";
import { getDriverCurrentTrip } from "../services/tripService";

export function useDriverVehicle() {

    const [vehicle, setVehicle] = useState(null);

    const [currentTrip, setCurrentTrip] = useState(null);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadDashboard();

    }, []);

    const loadDashboard = async () => {

        try {

            const user = JSON.parse(
                localStorage.getItem("user")
            );

            const [

                vehicleResponse,

                tripResponse

            ] = await Promise.all([

                getAssignedVehicle(user.email),

                getDriverCurrentTrip(user.email)

            ]);

            setVehicle(vehicleResponse.data);

            setCurrentTrip(tripResponse.data);

        }

        catch (error) {

            console.error(error);

        }

        finally {

            setLoading(false);

        }

    };

    return {

        vehicle,

        currentTrip,

        loading,

        refreshDashboard: loadDashboard

    };

}