import {
    FaBus,
    FaCheckCircle,
    FaTools
} from "react-icons/fa";

function StatCard({ title, value, subtitle }) {

    const getIcon = () => {

        switch (title) {

            case "Total Vehicles":
                return <FaBus />;

            case "Drivers":
                return <FaCheckCircle />;

            case "Maintenance":
                return <FaTools />;

            default:
                return <FaBus />;

        }

    };

    return (

        <div className="stat-card">

            <div className="stat-icon">

                {getIcon()}

            </div>

            <div className="stat-content">

                <h3>{title}</h3>

                <h1>{value}</h1>

                <p>{subtitle}</p>

            </div>

        </div>

    );

}

export default StatCard;