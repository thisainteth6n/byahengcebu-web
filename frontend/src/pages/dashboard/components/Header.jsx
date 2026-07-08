import { useNavigate } from "react-router-dom";

function Header() {

    const navigate = useNavigate();

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

                <p>
                    Fleet Management and Maintenance Information System
                </p>

            </div>

            <button
                className="logout-btn"
                onClick={handleLogout}
            >
                Logout
            </button>

        </header>

    );

}

export default Header;