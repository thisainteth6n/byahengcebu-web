import { useEffect, useState } from "react";

import {

    getStatistics,
    getVehicles,
    getDriverVehicles

} from "../services/vehicleService";

export function useVehicles() {

    const [vehicles, setVehicles] = useState([]);

    const [statistics, setStatistics] = useState({

        total: 0,
        active: 0,
        maintenance: 0

    });

    const [loading, setLoading] = useState(true);

    const refreshVehicles = async () => {

        try {

            const user = JSON.parse(
                localStorage.getItem("user")
            );

            if (!user) return;

            let response;

            if (user.role === "ADMIN") {

                response = await getVehicles();

                const stats = await getStatistics();

                setStatistics(stats.data);

            }

            else {

                response = await getDriverVehicles(
                    user.email
                );

                const list = response.data;

                setStatistics({

                    total: list.length,

                    active: list.filter(
                        v => v.status === "ACTIVE"
                    ).length,

                    maintenance: list.filter(
                        v => v.status === "MAINTENANCE"
                    ).length

                });

            }

            setVehicles(response.data);

        }

        finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        refreshVehicles();

    }, []);

    return {

        vehicles,
        statistics,
        loading,
        refreshVehicles

    };

}