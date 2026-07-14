package com.byahengcebu.mobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.model.Trip
import com.byahengcebu.mobile.repository.TripRepository
import kotlinx.coroutines.launch

class TripViewModel : ViewModel() {

    private val repository = TripRepository()

    var ongoingTrips by mutableStateOf<List<Trip>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loadTrips() {

        loading = true

        viewModelScope.launch {

            try {

                val response = repository.getOngoingTrips()

                if (response.isSuccessful) {
                    ongoingTrips = response.body() ?: emptyList()
                }

            } catch (e: Exception) {

                errorMessage = e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

    fun startTrip(trip: Trip) {

        viewModelScope.launch {

            repository.startTrip(trip)

            loadTrips()

        }

    }

    fun endTrip(id: Long, trip: Trip) {

        viewModelScope.launch {

            repository.endTrip(id, trip)

            loadTrips()

        }

    }

}