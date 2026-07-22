import { NavLink, useNavigate } from "react-router-dom";

import {
    FaHome,
    FaBus,
    FaRoute,
    FaTools,
    FaMoneyBillWave,
    FaCog,
    FaSignOutAlt
} from "react-icons/fa";

import "../styles/sidebar.css";

function Sidebar({ role }) {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.removeItem("user");

        navigate("/login", { replace: true });

    };

    return (

        <aside className="sidebar">

            <div className="sidebar-logo">

                <h2>ByahengCebu</h2>

                <span>

                    Fleet Management System

                </span>

            </div>

            <nav className="sidebar-nav">

                {
                    role === "ADMIN" && (

                        <>

                            <NavLink

                                to="/admin/dashboard"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaHome />

                                Dashboard

                            </NavLink>

                            <NavLink

                                to="/admin/trips"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaRoute />

                                Trips

                            </NavLink>

                            <NavLink

                                to="/admin/maintenance"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaTools />

                                Maintenance

                            </NavLink>

                            <NavLink

                                to="/admin/remittances"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaMoneyBillWave />

                                Remittances

                            </NavLink>

                            <NavLink

                                to="/admin/settings"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaCog />

                                Settings

                            </NavLink>

                        </>

                    )
                }

                {

                    role === "DRIVER" && (

                        <>

                            <NavLink

                                to="/driver/dashboard"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaBus />

                                Dashboard

                            </NavLink>

                            <NavLink

                                to="/driver/remittances"

                                className={({ isActive }) =>

                                    isActive

                                        ? "sidebar-link active"

                                        : "sidebar-link"

                                }

                            >

                                <FaMoneyBillWave />

                                Remittances

                            </NavLink>

                        </>

                    )

                }

            </nav>

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