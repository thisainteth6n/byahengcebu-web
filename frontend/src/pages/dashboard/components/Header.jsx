import { useNavigate } from "react-router-dom";

function Header() {

    const navigate = useNavigate();

    const today = new Date().toLocaleDateString("en-US", {
        weekday: "long",
        month: "long",
        day: "numeric",
        year: "numeric"
    });

    const handleLogout = () => {

        const confirmed = window.confirm(
            "Are you sure you want to logout?"
        );

        if (!confirmed) return;

        localStorage.clear();
        sessionStorage.clear();

        navigate("/login");

    };

    return (

        <header className="dashboard-header">

            <div>

                <h1>🚍 ByahengCebu</h1>

                <p>Fleet Management & Maintenance Information System</p>

            </div>

            <div className="header-right">

                <div className="date-card">

                    <span>Today</span>

                    <strong>{today}</strong>

                </div>

                <button
                    className="logout-btn"
                    onClick={handleLogout}
                >
                    Logout
                </button>

            </div>

        </header>

    );

}

export default Header;