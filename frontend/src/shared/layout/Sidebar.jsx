import { NavLink, useNavigate } from "react-router-dom";
import "./sidebar.css";

function Sidebar() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.removeItem("isLoggedIn");
        localStorage.removeItem("user");

        navigate("/login");

    };

    return (

        <aside className="sidebar">

            <div className="sidebar-logo">

                <h2>🚌 ByahengCebu</h2>

            </div>

            <nav>

                <NavLink
                    to="/dashboard"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >
                    Dashboard
                </NavLink>

                <NavLink
                    to="/trips"
                    className={({ isActive }) =>
                        isActive ? "active-link" : ""
                    }
                >
                    Trips
                </NavLink>

            </nav>

            <button
                className="logout-btn"
                onClick={logout}
            >
                Logout
            </button>

        </aside>

    );

}

export default Sidebar;