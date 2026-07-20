import "./badge.css";

import {
    FaCheckCircle,
    FaClock,
    FaTools,
    FaBus,
    FaTimesCircle
} from "react-icons/fa";

function Badge({ status }) {

    const getConfig = () => {

        switch (status) {

            case "ACTIVE":

                return {
                    className: "badge active",
                    icon: <FaBus />,
                    text: "Active"
                };

            case "MAINTENANCE":

                return {
                    className: "badge maintenance",
                    icon: <FaTools />,
                    text: "Maintenance"
                };

            case "PENDING":

                return {
                    className: "badge pending",
                    icon: <FaClock />,
                    text: "Pending"
                };

            case "VERIFIED":

                return {
                    className: "badge verified",
                    icon: <FaCheckCircle />,
                    text: "Verified"
                };

            case "COMPLETED":

                return {
                    className: "badge completed",
                    icon: <FaCheckCircle />,
                    text: "Completed"
                };

            case "CANCELLED":

                return {
                    className: "badge cancelled",
                    icon: <FaTimesCircle />,
                    text: "Cancelled"
                };

            default:

                return {
                    className: "badge",
                    icon: null,
                    text: status
                };

        }

    };

    const config = getConfig();

    return (

        <span className={config.className}>

            {config.icon}

            {config.text}

        </span>

    );

}

export default Badge;