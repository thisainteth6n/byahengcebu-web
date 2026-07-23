package com.byahengcebu.mobile.features.vehicle.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.vehicle.model.Vehicle
import com.byahengcebu.mobile.features.vehicle.repository.VehicleRepository
import com.byahengcebu.mobile.shared.session.SessionManager
import kotlinx.coroutines.launch

class VehicleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VehicleRepository()

    private val session =
        SessionManager(application.applicationContext)

    var assignedVehicle by mutableStateOf<Vehicle?>(null)
        private set

    var loading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadAssignedVehicle() {

        loading = true

        viewModelScope.launch {

            try {

                val email = session.getEmail()

                val response = repository.getAssignedVehicle(email)

                if (response.isSuccessful) {

                    assignedVehicle = response.body()

                } else {

                    errorMessage = "No assigned vehicle."

                }

            } catch (e: Exception) {

                errorMessage = e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

}