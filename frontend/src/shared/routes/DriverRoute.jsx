import { Navigate } from "react-router-dom";

function DriverRoute({ children }) {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!user) {

        return <Navigate to="/login" replace />;

    }

    if (user.role !== "DRIVER") {

        return <Navigate to="/admin/dashboard" replace />;

    }

    return children;

}

export default DriverRoute;