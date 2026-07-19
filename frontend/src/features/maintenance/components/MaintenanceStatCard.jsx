function MaintenanceStatCard({

                                 title,

                                 value

                             }) {

    return (

        <div
            style={{
                flex: 1,
                background: "#ffffff",
                borderRadius: "12px",
                padding: "20px",
                boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
                textAlign: "center"
            }}
        >

            <h3
                style={{
                    marginBottom: "10px"
                }}
            >

                {title}

            </h3>

            <h1
                style={{
                    color: "#2563eb"
                }}
            >

                {value}

            </h1>

        </div>

    );

}

export default MaintenanceStatCard;