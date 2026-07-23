package com.byahengcebu.mobile.features.remittance.repository

import com.byahengcebu.mobile.features.remittance.model.Remittance
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.shared.api.ApiClient
import retrofit2.Response

class RemittanceRepository {

    suspend fun getEligibleTrips(

        driverName: String

    ): Response<List<Trip>> {

        return ApiClient.api.getEligibleTrips(driverName)

    }

    suspend fun submitRemittance(

        remittance: Remittance

    ): Response<Remittance> {

        return ApiClient.api.submitRemittance(remittance)

    }

    suspend fun getDriverRemittances(

        driverName: String

    ): Response<List<Remittance>> {

        return ApiClient.api.getDriverRemittances(driverName)

    }

}