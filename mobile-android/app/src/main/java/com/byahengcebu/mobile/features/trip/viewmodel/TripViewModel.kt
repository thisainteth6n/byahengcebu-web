package com.byahengcebu.mobile.features.trip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.features.trip.repository.TripRepository
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

                    println("Trips Loaded: ${ongoingTrips.size}")

                } else {

                    println("Load Trips Failed")
                    println("HTTP ${response.code()}")
                    println(response.errorBody()?.string())

                }

            } catch (e: Exception) {

                errorMessage = e.localizedMessage ?: "Connection Error"
                e.printStackTrace()

            }

            loading = false

        }

    }

    fun startTrip(trip: Trip) {

        viewModelScope.launch {

            try {

                val response = repository.startTrip(trip)

                if (response.isSuccessful) {

                    println("Trip Created Successfully")
                    println(response.body())

                    loadTrips()

                } else {

                    println("START TRIP FAILED")
                    println("HTTP ${response.code()}")
                    println(response.errorBody()?.string())

                }

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }

    }

    fun endTrip(id: Long, trip: Trip) {

        viewModelScope.launch {

            try {

                val response = repository.endTrip(id, trip)

                if (response.isSuccessful) {

                    println("Trip Ended Successfully")

                    loadTrips()

                } else {

                    println("END TRIP FAILED")
                    println("HTTP ${response.code()}")
                    println(response.errorBody()?.string())

                }

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }

    }

}