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

    fun clearError() {
        errorMessage = ""
    }

    fun loadTrips() {

        loading = true

        viewModelScope.launch {

            try {

                val email = session.getEmail()

                // Trip history
                val tripsResponse = repository.getDriverTrips(email)

                if (tripsResponse.isSuccessful) {

                    trips = tripsResponse.body() ?: emptyList()

                }

                // Current trip
                val currentResponse = repository.getCurrentTrip(email)

                if (
                    currentResponse.isSuccessful &&
                    currentResponse.body() != null
                ) {

                    currentTrip = currentResponse.body()

                } else {

                    currentTrip = null

                }

            } catch (e: Exception) {

                currentTrip = null

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

    fun startTrip(
        trip: Trip
    ) {

        viewModelScope.launch {

            try {

                val email = session.getEmail()

                val response =
                    repository.startTrip(email, trip)

                if (response.isSuccessful) {

                    loadTrips()

                } else {

                    errorMessage =
                        response.errorBody()?.string()
                            ?: "Unable to start trip."

                }

            } catch (e: Exception) {

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

        }

    }

    fun endTrip(
        id: Long,
        trip: Trip
    ) {

        viewModelScope.launch {

            try {

                val response =
                    repository.endTrip(id, trip)

                if (response.isSuccessful) {

                    loadTrips()

                } else {

                    errorMessage =
                        response.errorBody()?.string()
                            ?: "Unable to end trip."

                }

            } catch (e: Exception) {

                errorMessage =
                    e.localizedMessage ?: "Connection Error"

            }

        }

    }

}