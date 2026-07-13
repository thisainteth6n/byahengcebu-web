package com.byahengcebu.mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.model.Statistics
import com.byahengcebu.mobile.model.Vehicle
import com.byahengcebu.mobile.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehicleViewModel : ViewModel() {

    private val repository = VehicleRepository()

    var vehicles by mutableStateOf<List<Vehicle>>(emptyList())
        private set

    var statistics by mutableStateOf(
        Statistics(
            total = 0,
            active = 0,
            maintenance = 0
        )
    )
        private set

    var loading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadDashboard() {

        loading = true

        viewModelScope.launch {

            try {

                val statisticsResponse = repository.getStatistics()

                if (statisticsResponse.isSuccessful) {
                    statisticsResponse.body()?.let {
                        statistics = it
                    }
                }

                val vehiclesResponse = repository.getVehicles()

                if (vehiclesResponse.isSuccessful) {
                    vehicles = vehiclesResponse.body() ?: emptyList()
                }

            } catch (e: Exception) {

                errorMessage = e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

}