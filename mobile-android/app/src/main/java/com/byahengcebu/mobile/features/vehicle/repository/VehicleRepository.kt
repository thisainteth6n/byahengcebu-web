package com.byahengcebu.mobile.features.vehicle.repository

import com.byahengcebu.mobile.features.trip.model.Statistics
import com.byahengcebu.mobile.features.vehicle.model.Vehicle
import com.byahengcebu.mobile.shared.api.ApiClient
import retrofit2.Response

class VehicleRepository {

    suspend fun getVehicles(): Response<List<Vehicle>> {
        return ApiClient.api.getVehicles()
    }

    suspend fun getStatistics(): Response<Statistics> {
        return ApiClient.api.getStatistics()
    }

}