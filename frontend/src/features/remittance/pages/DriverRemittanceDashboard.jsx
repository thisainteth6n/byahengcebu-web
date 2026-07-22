import { useState } from "react";

import "../styles/remittance.css";

import { useRemittance } from "../hooks/useRemittance";

import RemittanceStatCard from "../components/RemittanceStatCard";
import RemittanceTable from "../components/RemittanceTable";
import SubmitRemittanceModal from "../components/SubmitRemittanceModal";
import DriverLayout from "../../../shared/components/DriverLayout";

function DriverRemittanceDashboard() {


    const {

        remittances,

        eligibleTrips,

        statistics,

        loading,

        handleSubmitRemittance

    } = useRemittance();

    const [showSubmitModal, setShowSubmitModal] = useState(false);

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <DriverLayout>

            <div className="trip-dashboard">

            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    marginBottom: "20px"
                }}
            >

                <h1>My Cash Remittances</h1>

                <div
                    style={{
                        display: "flex",
                        gap: "10px"
                    }}
                >

                    <button

                        onClick={() => setShowSubmitModal(true)}

                        disabled={eligibleTrips.length === 0}

                        style={{
                            background: "#059669",
                            color: "#fff",
                            border: "none",
                            borderRadius: "8px",
                            padding: "10px 18px",
                            cursor: "pointer",
                            fontWeight: "600"
                        }}

                    >

                        Submit Remittance

                    </button>

                </div>

            </div>

            <section className="trip-stats">

                <RemittanceStatCard

                    title="Total"

                    value={statistics.total}

                />

                <RemittanceStatCard

                    title="Pending"

                    value={statistics.pending}

                />

                <RemittanceStatCard

                    title="Verified"

                    value={statistics.verified}

                />

            </section>

            <RemittanceTable

                remittances={remittances}

                onVerify={() => {}}

                onDelete={() => {}}

            />

            <SubmitRemittanceModal

                show={showSubmitModal}

                eligibleTrips={eligibleTrips}

                onClose={() =>

                    setShowSubmitModal(false)

                }

                onSubmit={async (remittance) => {

                    await handleSubmitRemittance(

                        remittance

                    );

                    setShowSubmitModal(false);

                }}

            />

            </div>

        </DriverLayout>

    );

}

export default DriverRemittanceDashboard;