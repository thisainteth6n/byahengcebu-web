import { NavLink, useNavigate } from "react-router-dom";

import "./layout.css";

function Sidebar() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.removeItem("user");

        navigate("/login", { replace: true });

    };

    const linkStyle = ({ isActive }) => ({

        background: isActive ? "#2563eb" : "transparent",

        color: "#fff",

        padding: "12px 16px",

        borderRadius: "8px",

        textDecoration: "none",

        display: "block",

        fontWeight: "600",

        transition: "0.2s"

    });

    return (

        <aside className="sidebar">

            <div className="sidebar-logo">

                <h2>🚍 ByahengCebu</h2>

                <p>Fleet Management</p>

            </div>

            <nav className="sidebar-links">

                <NavLink
                    to="/admin/dashboard"
                    style={linkStyle}
                >
                    🏠 Dashboard
                </NavLink>

                <NavLink
                    to="/admin/trips"
                    style={linkStyle}
                >
                    🛣 Trip Management
                </NavLink>

                <NavLink
                    to="/admin/maintenance"
                    style={linkStyle}
                >
                    🔧 Maintenance
                </NavLink>

                <NavLink
                    to="/admin/remittances"
                    style={linkStyle}
                >
                    💰 Remittances
                </NavLink>

            </nav>

            <button

                className="logout-button"

                onClick={logout}

            >

                🚪 Logout

            </button>

        </aside>

    );

}

export default Sidebar;