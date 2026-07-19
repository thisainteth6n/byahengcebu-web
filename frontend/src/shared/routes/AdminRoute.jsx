import { Navigate } from "react-router-dom";

function AdminRoute({ children }) {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!user) {

        return <Navigate to="/login" replace />;

    }

    if (user.role !== "ADMIN") {

        return <Navigate to="/driver/dashboard" replace />;

    }

    return children;

}

export default AdminRoute;