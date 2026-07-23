package com.byahengcebu.mobile.features.trip.repository

import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.shared.api.ApiClient
import retrofit2.Response

class TripRepository {

    suspend fun getDriverTrips(
        email: String
    ): Response<List<Trip>> {

        return ApiClient.api.getDriverTrips(email)

    }

    suspend fun getCurrentTrip(
        email: String
    ): Response<Trip> {

        return ApiClient.api.getCurrentTrip(email)

    }

    suspend fun startTrip(
        email: String,
        trip: Trip
    ): Response<Trip> {

        return ApiClient.api.startTrip(email, trip)

    }

    suspend fun endTrip(
        id: Long,
        trip: Trip
    ): Response<Trip> {

        return ApiClient.api.endTrip(id, trip)

    }

}