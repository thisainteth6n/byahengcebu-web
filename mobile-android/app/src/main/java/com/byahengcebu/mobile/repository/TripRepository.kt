package com.byahengcebu.mobile.repository

import com.byahengcebu.mobile.api.ApiClient
import com.byahengcebu.mobile.model.Trip
import retrofit2.Response

class TripRepository {

    suspend fun getTrips(): Response<List<Trip>> {
        return ApiClient.api.getTrips()
    }

    suspend fun getOngoingTrips(): Response<List<Trip>> {
        return ApiClient.api.getOngoingTrips()
    }

    suspend fun startTrip(
        trip: Trip
    ): Response<Trip> {
        return ApiClient.api.startTrip(trip)
    }

    suspend fun endTrip(
        id: Long,
        trip: Trip
    ): Response<Trip> {
        return ApiClient.api.endTrip(id, trip)
    }

}