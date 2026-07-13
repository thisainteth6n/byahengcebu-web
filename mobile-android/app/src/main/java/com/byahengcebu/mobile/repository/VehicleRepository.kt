package com.byahengcebu.mobile.repository

import com.byahengcebu.mobile.api.ApiClient
import com.byahengcebu.mobile.model.Statistics
import com.byahengcebu.mobile.model.Vehicle
import retrofit2.Response

class VehicleRepository {

    suspend fun getVehicles(): Response<List<Vehicle>> {
        return ApiClient.api.getVehicles()
    }

    suspend fun getStatistics(): Response<Statistics> {
        return ApiClient.api.getStatistics()
    }

}