import AdminLayout from "../../shared/components/AdminLayout";

import "./settings.css";

function Settings() {

    return (

        <AdminLayout>

            <div className="settings-page">

                <div className="settings-card">

                    <h1>System Settings</h1>

                    <p>
                        Configure system preferences and application settings.
                    </p>

                    <hr />

                    <div className="setting-group">

                        <label>Application Name</label>

                        <input
                            type="text"
                            value="ByahengCebu Fleet Management System"
                            disabled
                        />

                    </div>

                    <div className="setting-group">

                        <label>Version</label>

                        <input
                            type="text"
                            value="Version 1.0"
                            disabled
                        />

                    </div>

                    <div className="setting-group">

                        <label>Database</label>

                        <input
                            type="text"
                            value="Supabase PostgreSQL"
                            disabled
                        />

                    </div>

                    <button className="save-btn" disabled>

                        Save Changes (Coming Soon)

                    </button>

                </div>

            </div>

        </AdminLayout>

    );

}

export default Settings;