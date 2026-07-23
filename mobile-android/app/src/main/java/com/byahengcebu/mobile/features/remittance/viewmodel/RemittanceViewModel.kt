package com.byahengcebu.mobile.features.remittance.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.remittance.model.Remittance
import com.byahengcebu.mobile.features.remittance.repository.RemittanceRepository
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.shared.session.SessionManager
import kotlinx.coroutines.launch

class RemittanceViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RemittanceRepository()

    private val session =
        SessionManager(application)

    var eligibleTrips by mutableStateOf<List<Trip>>(emptyList())
        private set

    var remittances by mutableStateOf<List<Remittance>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun loadData() {

        loading = true

        viewModelScope.launch {

            val driver = session.getFullname()

            repository.getEligibleTrips(driver).body()?.let {

                eligibleTrips = it

            }

            repository.getDriverRemittances(driver).body()?.let {

                remittances = it

            }

            loading = false

        }

    }

    fun submit(

        trip: Trip,

        total: Double,

        expenses: Double,

        remarks: String

    ) {

        viewModelScope.launch {

            repository.submitRemittance(

                Remittance(

                    tripId = trip.id!!,

                    totalCollection = total,

                    expenses = expenses,

                    remarks = remarks

                )

            )

            loadData()

        }

    }

}