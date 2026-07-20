import Sidebar from "./Sidebar";

function AdminLayout({ children }) {

    return (

        <div className="page-layout">

            <Sidebar />

            <main className="page-content">

                {children}

            </main>

        </div>

    );

}

export default AdminLayout;