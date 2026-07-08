import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import {
    FaEnvelope,
    FaLock,
    FaUser,
    FaEye,
    FaEyeSlash
} from "react-icons/fa";

import Logo from "../../components/Logo";
import AuthCard from "../../components/AuthCard";
import Button from "../../components/Button";

import { registerUser } from "../../services/authService";

import "../../styles/auth.css";

function Register() {

    const navigate = useNavigate();

    const [showPassword, setShowPassword] = useState(false);

    const [loading, setLoading] = useState(false);

    const [user, setUser] = useState({
        fullname: "",
        email: "",
        password: "",
        role: "DRIVER"
    });

    const register = async (e) => {

        e.preventDefault();

        setLoading(true);

        try {

            const response = await registerUser(user);

            console.log(response.data);

            alert("Registration successful!");

            navigate("/login");

        }

        catch (error) {

            console.error(error);

            alert("Registration failed.");

        }

        finally {

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

                    Create Account ✨

                </h2>

                <p className="welcome-subtitle">

                    Register to continue.

                </p>

                <form onSubmit={register}>

                    <div className="input-group">

                        <FaUser className="icon" />

                        <input
                            className="modern-input"
                            placeholder="Full Name"
                            value={user.fullname}
                            onChange={(e) =>
                                setUser({
                                    ...user,
                                    fullname: e.target.value
                                })
                            }
                            required
                        />

                    </div>

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

                    <select
                        className="modern-input"
                        value={user.role}
                        onChange={(e) =>
                            setUser({
                                ...user,
                                role: e.target.value
                            })
                        }
                    >
                        <option value="DRIVER">Driver</option>
                        <option value="ADMIN">Administrator</option>
                    </select>

                    <Button disabled={loading}>

                        {loading ? "Creating Account..." : "Create Account"}

                    </Button>

                </form>

                <p className="switch-page">

                    Already have an account?

                    <Link to="/login">

                        Login

                    </Link>

                </p>

            </AuthCard>

        </div>

    );

}

export default Register;