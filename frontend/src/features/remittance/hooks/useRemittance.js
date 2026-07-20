import { useEffect, useState } from "react";

import {

    getRemittances,
    getDriverRemittances,
    getEligibleTrips,
    submitRemittance,
    verifyRemittance,
    deleteRemittance,
    getRemittanceStatistics

} from "../services/remittanceService";

export function useRemittance() {

    const [remittances, setRemittances] = useState([]);

    const [eligibleTrips, setEligibleTrips] = useState([]);

    const [statistics, setStatistics] = useState({

        total: 0,
        pending: 0,
        verified: 0

    });

    const [loading, setLoading] = useState(true);

    const refreshRemittances = async () => {

        try {

            const user = JSON.parse(
                localStorage.getItem("user")
            );

            if (user.role === "ADMIN") {

                const remittanceResponse =
                    await getRemittances();

                const statisticsResponse =
                    await getRemittanceStatistics();

                setRemittances(
                    remittanceResponse.data
                );

                setStatistics(
                    statisticsResponse.data
                );

            }

            else {

                const remittanceResponse =
                    await getDriverRemittances(
                        user.fullname
                    );

                const eligibleResponse =
                    await getEligibleTrips(
                        user.fullname
                    );

                setRemittances(
                    remittanceResponse.data
                );

                setEligibleTrips(
                    eligibleResponse.data
                );

                setStatistics({

                    total:
                    remittanceResponse.data.length,

                    pending:
                    remittanceResponse.data.filter(

                        r => r.status === "PENDING"

                    ).length,

                    verified:
                    remittanceResponse.data.filter(

                        r => r.status === "VERIFIED"

                    ).length

                });

            }

        }

        catch (error) {

            console.error(error);

        }

        finally {

            setLoading(false);

        }

    };

    useEffect(() => {

        refreshRemittances();

    }, []);

    // ==========================
    // SUBMIT
    // ==========================

    const handleSubmitRemittance = async (

        remittance

    ) => {

        try {

            await submitRemittance(
                remittance
            );

            alert(
                "Remittance submitted successfully."
            );

            await refreshRemittances();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to submit remittance."

            );

        }

    };

    // ==========================
    // VERIFY
    // ==========================

    const handleVerifyRemittance = async (

        id

    ) => {

        try {

            await verifyRemittance(id);

            alert(
                "Remittance verified successfully."
            );

            await refreshRemittances();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to verify remittance."

            );

        }

    };

    // ==========================
    // DELETE
    // ==========================

    const handleDeleteRemittance = async (

        id

    ) => {

        try {

            await deleteRemittance(id);

            alert(
                "Remittance deleted successfully."
            );

            await refreshRemittances();

        }

        catch (error) {

            alert(

                error.response?.data ||

                "Unable to delete remittance."

            );

        }

    };

    return {

        remittances,

        eligibleTrips,

        statistics,

        loading,

        refreshRemittances,

        handleSubmitRemittance,

        handleVerifyRemittance,

        handleDeleteRemittance

    };

}