import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import {
    FaEnvelope,
    FaLock,
    FaEye,
    FaEyeSlash
} from "react-icons/fa";

import Logo from "../components/Logo.jsx";
import AuthCard from "../components/AuthCard.jsx";
import Button from "../components/Button.jsx";

import { loginUser } from "../services/authService.js";

import "../styles/auth.css";

function Login() {

    const navigate = useNavigate();

    const [showPassword, setShowPassword] = useState(false);

    const [loading, setLoading] = useState(false);

    const [user, setUser] = useState({
        email: "",
        password: ""
    });

    const login = async (e) => {

        e.preventDefault();

        setLoading(true);

        try {

            const response = await loginUser(user);

            console.log(response.data);

            // Stop login if backend says login failed
            if (!response.data.success) {

                alert(response.data.message);

                return;

            }

            // Save login session
            localStorage.setItem("isLoggedIn", "true");

            // Save logged-in user
            localStorage.setItem(
                "user",
                JSON.stringify(response.data)
            );

            // Redirect to dashboard
            // Redirect based on role

            if (response.data.role === "ADMIN") {

                navigate("/admin/dashboard");

            }

            else if (response.data.role === "DRIVER") {

                navigate("/driver/dashboard");

            }

            else {

                alert("Unknown user role.");

            }

        } catch (error) {

            console.error(error);

            alert("Unable to connect to the server.");

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="auth-page">

            <AuthCard>

                <Logo />

                <h1 className="auth-title">
                    ByahengCebu
                </h1>

                <p className="auth-subtitle">
                    Fleet Management Information System
                </p>

                <h2 className="welcome-title">
                    Welcome Back 👋
                </h2>

                <p className="welcome-subtitle">
                    Sign in to continue.
                </p>

                <form onSubmit={login}>

                    <div className="input-group">

                        <FaEnvelope className="icon" />

                        <input
                            className="modern-input"
                            type="email"
                            placeholder="Email Address"
                            value={user.email}
                            onChange={(e) =>
                                setUser({
                                    ...user,
                                    email: e.target.value
                                })
                            }
                            required
                        />

                    </div>

                    <div className="input-group">

                        <FaLock className="icon" />

                        <input
                            className="modern-input"
                            type={showPassword ? "text" : "password"}
                            placeholder="Password"
                            value={user.password}
                            onChange={(e) =>
                                setUser({
                                    ...user,
                                    password: e.target.value
                                })
                            }
                            required
                        />

                        <button
                            type="button"
                            className="eye-button"
                            onClick={() => setShowPassword(!showPassword)}
                        >
                            {showPassword ? <FaEyeSlash /> : <FaEye />}
                        </button>

                    </div>

                    <Button disabled={loading}>

                        {loading ? "Signing In..." : "Login"}

                    </Button>

                </form>

                <p className="switch-page">

                    Don't have an account?

                    <Link to="/register">

                        Register

                    </Link>

                </p>

            </AuthCard>

        </div>

    );

}

export default Login;