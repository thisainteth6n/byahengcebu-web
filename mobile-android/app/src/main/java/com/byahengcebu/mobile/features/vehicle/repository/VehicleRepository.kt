package com.byahengcebu.mobile.features.vehicle.repository

import com.byahengcebu.mobile.features.vehicle.model.Vehicle
import com.byahengcebu.mobile.shared.api.ApiClient
import retrofit2.Response

class VehicleRepository {

    suspend fun getAssignedVehicle(
        email: String
    ): Response<Vehicle> {

        return ApiClient.api.getAssignedVehicle(email)

    }

}