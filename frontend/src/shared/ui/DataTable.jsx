import "./datatable.css";

import {
    FaSearch
} from "react-icons/fa";

function DataTable({

                       title,

                       subtitle,

                       columns,

                       search,

                       onSearch,

                       searchPlaceholder = "Search...",

                       actions,

                       children,

                       emptyMessage = "No records found."

                   }) {

    const hasRows =

        children &&

        (

            Array.isArray(children)

                ? children.length > 0

                : true

        );

    return (

        <section className="datatable-card">

            <div className="datatable-header">

                <div>

                    <h2>{title}</h2>

                    {

                        subtitle &&

                        <p>{subtitle}</p>

                    }

                </div>

                <div>

                    {actions}

                </div>

            </div>

            {

                onSearch && (

                    <div className="datatable-search">

                        <FaSearch />

                        <input

                            value={search}

                            onChange={(e) =>

                                onSearch(e.target.value)

                            }

                            placeholder={searchPlaceholder}

                        />

                    </div>

                )

            }

            <div className="datatable-container">

                <table className="datatable">

                    <thead>

                    <tr>

                        {

                            columns.map(column => (

                                <th key={column}>

                                    {column}

                                </th>

                            ))

                        }

                    </tr>

                    </thead>

                    <tbody>

                    {

                        hasRows

                            ? children

                            :

                            <tr>

                                <td

                                    colSpan={columns.length}

                                    className="datatable-empty"

                                >

                                    {emptyMessage}

                                </td>

                            </tr>

                    }

                    </tbody>

                </table>

            </div>

        </section>

    );

}

export default DataTable;