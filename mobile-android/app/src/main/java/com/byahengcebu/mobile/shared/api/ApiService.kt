package com.byahengcebu.mobile.shared.api

import com.byahengcebu.mobile.features.auth.model.AuthResponse
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

    @GET("vehicles/assigned/{email}")
    suspend fun getAssignedVehicle(
        @Path("email") email: String
    ): Response<Vehicle>

    @GET("vehicles/{id}")
    suspend fun getVehicleById(
        @Path("id") id: Long
    ): Response<Vehicle>


    // ==========================
    // TRIPS
    // ==========================

    @GET("trips/driver/{email}")
    suspend fun getDriverTrips(
        @Path("email") email: String
    ): Response<List<Trip>>

    @GET("trips/driver/{email}/ongoing")
    suspend fun getCurrentTrip(
        @Path("email") email: String
    ): Response<Trip>

    @POST("trips/start/{email}")
    suspend fun startTrip(
        @Path("email") email: String,
        @Body trip: Trip
    ): Response<Trip>

    @PUT("trips/end/{id}")
    suspend fun endTrip(
        @Path("id") id: Long,
        @Body trip: Trip
    ): Response<Trip>

}