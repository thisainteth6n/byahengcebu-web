import "./statcard.css";

function StatCard({

                      title,

                      value,

                      subtitle,

                      icon,

                      color = "#2563eb"

                  }) {

    return (

        <div className="shared-stat-card">

            <div
                className="shared-stat-icon"
                style={{ background: color }}
            >

                {icon}

            </div>

            <div className="shared-stat-content">

                <h3>{title}</h3>

                <h1>{value}</h1>

                {

                    subtitle &&

                    <p>{subtitle}</p>

                }

            </div>

        </div>

    );

}

export default StatCard;