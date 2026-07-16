function TripStatCard({

                          title,
                          value

                      }) {

    return (

        <div className="trip-stat-card">

            <h3>{title}</h3>

            <h1>{value}</h1>

        </div>

    );

}

export default TripStatCard;