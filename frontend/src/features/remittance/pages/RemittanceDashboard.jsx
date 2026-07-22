import { useState } from "react";

import "../styles/remittance.css";

import { useRemittance } from "../hooks/useRemittance";

import StatCard from "../../../shared/ui/StatCard";
import RemittanceTable from "../components/RemittanceTable";
import VerifyRemittanceModal from "../components/VerifyRemittanceModal";
import AdminLayout from "../../../shared/components/AdminLayout";

function RemittanceDashboard() {


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

        <AdminLayout>

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

            </div>

                <section className="stats">

                    <StatCard
                        title="Total Remittances"
                        value={statistics.total}
                        subtitle="All Submitted"
                    />

                    <StatCard
                        title="Pending"
                        value={statistics.pending}
                        subtitle="Awaiting Verification"
                    />

                    <StatCard
                        title="Verified"
                        value={statistics.verified}
                        subtitle="Successfully Approved"
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

                isAdmin={true}

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

        </AdminLayout>

    );

}

export default RemittanceDashboard;