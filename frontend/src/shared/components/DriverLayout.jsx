import Sidebar from "./Sidebar";

import "../styles/sidebar.css";

function DriverLayout({ children }) {

    return (

        <div className="layout">

            <Sidebar role="DRIVER" />

            <main className="main-content">

                {children}

            </main>

        </div>

    );

}

export default DriverLayout;