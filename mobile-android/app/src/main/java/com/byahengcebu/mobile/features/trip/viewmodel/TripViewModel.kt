package com.byahengcebu.mobile.features.trip.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.features.trip.repository.TripRepository
import com.byahengcebu.mobile.shared.session.SessionManager
import kotlinx.coroutines.launch

class TripViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = TripRepository()

    private val session =
        SessionManager(application)

    var trips by mutableStateOf<List<Trip>>(emptyList())
        private set

    var currentTrip by mutableStateOf<Trip?>(null)
        private set

    var loading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadTrips() {

        loading = true

        viewModelScope.launch {

            try {

                val email = session.getEmail()

                val response =
                    repository.getDriverTrips(email)

                if (response.isSuccessful) {

                    trips = response.body() ?: emptyList()

                }

                val current =
                    repository.getCurrentTrip(email)

                if (current.isSuccessful) {

                    currentTrip = current.body()

                }

            } catch (e: Exception) {

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

    fun startTrip(trip: Trip) {

        viewModelScope.launch {

            val email = session.getEmail()

            repository.startTrip(email, trip)

            loadTrips()

        }

    }

    fun endTrip(
        id: Long,
        trip: Trip
    ) {

        viewModelScope.launch {

            repository.endTrip(id, trip)

            loadTrips()

        }

    }

}