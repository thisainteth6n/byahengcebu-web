import { useState } from "react";
import { useNavigate } from "react-router-dom";

import "../styles/remittance.css";

import { useRemittance } from "../hooks/useRemittance";

import RemittanceStatCard from "../components/RemittanceStatCard";
import RemittanceTable from "../components/RemittanceTable";
import VerifyRemittanceModal from "../components/VerifyRemittanceModal";

function RemittanceDashboard() {

    const navigate = useNavigate();

    const {

        remittances,

        statistics,

        loading,

        handleVerifyRemittance,

        handleDeleteRemittance

    } = useRemittance();

    const [selectedRemittance, setSelectedRemittance] = useState(null);

    const [showVerifyModal, setShowVerifyModal] = useState(false);

    if (loading) {

        return <h2>Loading...</h2>;

    }

    return (

        <div className="trip-dashboard">

            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    marginBottom: "20px"
                }}
            >

                <h1>Cash Remittance Management</h1>

                <button
                    onClick={() => navigate("/admin/dashboard")}
                    style={{
                        background: "#2563eb",
                        color: "#fff",
                        border: "none",
                        borderRadius: "8px",
                        padding: "10px 18px",
                        cursor: "pointer",
                        fontWeight: "600"
                    }}
                >
                    ← Dashboard
                </button>

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

                onVerify={(id) => {

                    const remittance = remittances.find(
                        r => r.id === id
                    );

                    setSelectedRemittance(remittance);

                    setShowVerifyModal(true);

                }}

                onDelete={handleDeleteRemittance}

            />

            <VerifyRemittanceModal

                show={showVerifyModal}

                remittance={selectedRemittance}

                onClose={() => {

                    setShowVerifyModal(false);

                    setSelectedRemittance(null);

                }}

                onVerify={async (id) => {

                    await handleVerifyRemittance(id);

                    setShowVerifyModal(false);

                    setSelectedRemittance(null);

                }}

            />

        </div>

    );

}

export default RemittanceDashboard;