package com.byahengcebu.mobile.shared.api

import com.byahengcebu.mobile.features.auth.model.AuthResponse
import com.byahengcebu.mobile.features.trip.model.Statistics
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.features.auth.model.User
import com.byahengcebu.mobile.features.vehicle.model.Vehicle
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ==========================
    // AUTH
    // ==========================

    @POST("auth/login")
    suspend fun login(
        @Body user: User
    ): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(
        @Body user: User
    ): Response<AuthResponse>


    // ==========================
    // VEHICLES
    // ==========================

    @GET("vehicles")
    suspend fun getVehicles(): Response<List<Vehicle>>

    @GET("vehicles/statistics")
    suspend fun getStatistics(): Response<Statistics>

    @GET("vehicles/{id}")
    suspend fun getVehicleById(
        @Path("id") id: Long
    ): Response<Vehicle>


    // ==========================
    // TRIPS
    // ==========================

    @GET("trips")
    suspend fun getTrips(): Response<List<Trip>>

    @GET("trips/ongoing")
    suspend fun getOngoingTrips(): Response<List<Trip>>

    @POST("trips/start")
    suspend fun startTrip(
        @Body trip: Trip
    ): Response<Trip>

    @PUT("trips/end/{id}")
    suspend fun endTrip(
        @Path("id") id: Long,
        @Body trip: Trip
    ): Response<Trip>

}