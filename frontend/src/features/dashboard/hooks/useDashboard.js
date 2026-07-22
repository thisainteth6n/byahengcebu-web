import { useMemo } from "react";

import { useVehicles } from "../../vehicles/hooks/useVehicles";
import { useTrips } from "../../trip/hooks/useTrips";
import { useMaintenance } from "../../maintenance/hooks/useMaintenance";
import { useRemittance } from "../../remittance/hooks/useRemittance";

export function useDashboard() {

    const vehicleData = useVehicles();

    const tripData = useTrips();

    const maintenanceData = useMaintenance();

    const remittanceData = useRemittance();

    const recentTrips = useMemo(() => {

        return [...tripData.trips]
            .sort((a, b) => b.id - a.id)
            .slice(0, 5);

    }, [tripData.trips]);

    const maintenanceQueue = useMemo(() => {

        return maintenanceData.maintenance
            .filter(record => record.status === "SCHEDULED")
            .slice(0, 5);

    }, [maintenanceData.maintenance]);

    const pendingRemittances = useMemo(() => {

        return remittanceData.remittances
            .filter(record => record.status === "PENDING")
            .slice(0, 5);

    }, [remittanceData.remittances]);

    return {

        vehicles: vehicleData.vehicles,

        vehicleStatistics: vehicleData.statistics,

        refreshVehicles: vehicleData.refreshVehicles,

        recentTrips,

        maintenanceQueue,

        pendingRemittances

    };

}