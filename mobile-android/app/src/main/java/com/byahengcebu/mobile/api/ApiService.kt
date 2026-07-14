package com.byahengcebu.mobile.api

import com.byahengcebu.mobile.model.Statistics
import com.byahengcebu.mobile.model.Trip
import com.byahengcebu.mobile.model.User
import com.byahengcebu.mobile.model.Vehicle
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ==========================
    // AUTH
    // ==========================

    @POST("auth/login")
    suspend fun login(
        @Body user: User
    ): Response<String>

    @POST("auth/register")
    suspend fun register(
        @Body user: User
    ): Response<String>


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