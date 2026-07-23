package com.byahengcebu.mobile.features.trip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.features.trip.repository.TripRepository
import kotlinx.coroutines.launch

class TripViewModel : ViewModel() {

    private val repository = TripRepository()

    var trips by mutableStateOf<List<Trip>>(emptyList())
        private set

    var currentTrip by mutableStateOf<Trip?>(null)
        private set

    var loading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadTrips(email: String) {

        loading = true

        viewModelScope.launch {

            try {

                val tripsResponse =
                    repository.getDriverTrips(email)

                if (tripsResponse.isSuccessful) {
                    trips = tripsResponse.body() ?: emptyList()
                }

                val currentTripResponse =
                    repository.getCurrentTrip(email)

                if (currentTripResponse.isSuccessful) {
                    currentTrip = currentTripResponse.body()
                }

            } catch (e: Exception) {

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

    fun startTrip(
        email: String,
        trip: Trip
    ) {

        viewModelScope.launch {

            try {

                repository.startTrip(email, trip)

                loadTrips(email)

            } catch (_: Exception) {

            }

        }

    }

    fun endTrip(
        email: String,
        id: Long,
        trip: Trip
    ) {

        viewModelScope.launch {

            try {

                repository.endTrip(id, trip)

                loadTrips(email)

            } catch (_: Exception) {

            }

        }

    }

}