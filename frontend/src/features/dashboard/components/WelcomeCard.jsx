function WelcomeCard() {

    const user = JSON.parse(localStorage.getItem("user"));

    const fullname = user?.fullname || "User";
    const role = user?.role || "";

    return (

        <section className="welcome-card">

            <h2>

                Welcome back, {fullname} 👋

            </h2>

            <p>

                {role === "ADMIN"
                    ? "Manage your cooperative's fleet, monitor vehicle status, and keep maintenance records organized."
                    : "Welcome back! You can view your assigned vehicle, start trips, end trips, and manage your daily operations."}

            </p>

        </section>

    );

}

export default WelcomeCard;