import { useEffect, useState } from "react";

import {

    getMaintenance,
    getMaintenanceStatistics,
    getMaintenanceByStatus,
    scheduleMaintenance,
    completeMaintenance,
    deleteMaintenance

} from "../services/maintenanceService";

export function useMaintenance() {

    const [maintenance, setMaintenance] = useState([]);

    const [statistics, setStatistics] = useState({

        total: 0,
        scheduled: 0,
        completed: 0

    });

    const [loading, setLoading] = useState(true);

    // ==========================
    // LOAD DATA
    // ==========================

    const refreshMaintenance = async () => {

        try {

            const [

                maintenanceResponse,
                statisticsResponse

            ] = await Promise.all([

                getMaintenance(),
                getMaintenanceStatistics()

            ]);

            setMaintenance(
                maintenanceResponse.data
            );

            setStatistics(
                statisticsResponse.data
            );

        }

        catch (error) {

            console.error(error);

        }

        finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        refreshMaintenance();

    }, []);

    // ==========================
    // FILTER
    // ==========================

    const handleFilter = async (status) => {

        if (status === "ALL") {

            refreshMaintenance();

            return;

        }

        try {

            const response =
                await getMaintenanceByStatus(status);

            setMaintenance(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    // ==========================
    // SCHEDULE
    // ==========================

    const handleSchedule = async (record) => {

        try {

            await scheduleMaintenance(record);

            alert(
                "Maintenance scheduled successfully."
            );

            await refreshMaintenance();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to schedule maintenance."

            );

        }

    };

    // ==========================
    // COMPLETE
    // ==========================

    const handleComplete = async (

        id,

        remarks

    ) => {

        try {

            await completeMaintenance(id, {

                remarks

            });

            alert(
                "Maintenance completed successfully."
            );

            await refreshMaintenance();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to complete maintenance."

            );

        }

    };

    // ==========================
    // DELETE
    // ==========================

    const handleDelete = async (id) => {

        try {

            await deleteMaintenance(id);

            alert(
                "Maintenance record deleted."
            );

            await refreshMaintenance();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to delete maintenance."

            );

        }

    };

    return {

        maintenance,

        statistics,

        loading,

        refreshMaintenance,

        handleFilter,

        handleSchedule,

        handleComplete,

        handleDelete

    };

}