import { NavLink, useNavigate } from "react-router-dom";

import {
    FaHome,
    FaRoute,
    FaTools,
    FaMoneyBillWave,
    FaSignOutAlt,
    FaBus
} from "react-icons/fa";

import "./layout.css";

function Sidebar() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.removeItem("user");

        navigate("/login", { replace: true });

    };

    const menuItems = [

        {
            path: "/admin/dashboard",
            label: "Dashboard",
            icon: <FaHome />
        },

        {
            path: "/admin/trips",
            label: "Trip Management",
            icon: <FaRoute />
        },

        {
            path: "/admin/maintenance",
            label: "Maintenance",
            icon: <FaTools />
        },

        {
            path: "/admin/remittances",
            label: "Remittances",
            icon: <FaMoneyBillWave />
        }

    ];

    return (

        <aside className="sidebar">

            <div>

                <div className="sidebar-logo">

                    <div className="logo-circle">

                        <FaBus />

                    </div>

                    <h2>

                        ByahengCebu

                    </h2>

                    <p>

                        Fleet Management System

                    </p>

                </div>

                <nav className="sidebar-links">

                    {

                        menuItems.map(item => (

                            <NavLink

                                key={item.path}

                                to={item.path}

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <span>

                                    {item.icon}

                                </span>

                                {item.label}

                            </NavLink>

                        ))

                    }

                </nav>

            </div>

            <button

                className="logout-button"

                onClick={logout}

            >

                <FaSignOutAlt />

                Logout

            </button>

        </aside>

    );

}

export default Sidebar;