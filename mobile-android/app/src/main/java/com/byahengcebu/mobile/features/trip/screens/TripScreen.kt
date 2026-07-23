package com.byahengcebu.mobile.features.trip.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.features.trip.viewmodel.TripViewModel

@Composable
fun TripScreen(
    tripViewModel: TripViewModel
) {

    LaunchedEffect(Unit) {
        tripViewModel.loadTrips()
    }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(tripViewModel.errorMessage) {

        if (tripViewModel.errorMessage.isNotBlank()) {

            snackbarHostState.showSnackbar(
                tripViewModel.errorMessage
            )

            tripViewModel.clearError()

        }

    }

    var passengers by remember { mutableStateOf("") }
    var startOdometer by remember { mutableStateOf("") }

    Scaffold(

        snackbarHost = {

            SnackbarHost(snackbarHostState)

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp)

        ) {

            Text(
                text = "My Trips",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (tripViewModel.currentTrip == null) {

                Text(
                    "No active trip.",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = passengers,

                    onValueChange = {
                        passengers = it
                    },

                    label = {
                        Text("Passenger Count")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    modifier = Modifier.fillMaxWidth()

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = startOdometer,

                    onValueChange = {
                        startOdometer = it
                    },

                    label = {
                        Text("Start Odometer")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    modifier = Modifier.fillMaxWidth()

                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(

                    modifier = Modifier.fillMaxWidth(),

                    onClick = {

                        if (
                            passengers.isBlank() ||
                            startOdometer.isBlank()
                        ) return@Button

                        tripViewModel.startTrip(

                            Trip(

                                passengerCount = passengers.toInt(),

                                startOdometer = startOdometer.toInt()

                            )

                        )

                        passengers = ""
                        startOdometer = ""

                    }

                ) {

                    Text("START TRIP")

                }

            } else {

                CurrentTripCard(

                    trip = tripViewModel.currentTrip!!,

                    tripViewModel = tripViewModel

                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(

                text = "Trip History",

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {

                items(tripViewModel.trips) { trip ->

                    HistoryCard(trip)

                }

            }

        }

    }

}

@Composable
fun CurrentTripCard(

    trip: Trip,

    tripViewModel: TripViewModel

) {

    var endOdometer by remember {

        mutableStateOf("")

    }

    Card(

        modifier = Modifier.fillMaxWidth()

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                "CURRENT TRIP",

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Vehicle : ${trip.vehiclePlate}")

            Text("Route : ${trip.route}")

            Text("Passengers : ${trip.passengerCount}")

            Text("Started : ${trip.startOdometer}")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = endOdometer,

                onValueChange = {

                    endOdometer = it

                },

                label = {

                    Text("End Odometer")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Number

                ),

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    if (endOdometer.isBlank()) return@Button

                    trip.id?.let {

                        tripViewModel.endTrip(

                            it,

                            trip.copy(

                                endOdometer = endOdometer.toInt()

                            )

                        )

                    }

                }

            ) {

                Text("END TRIP")

            }

        }

    }

}

@Composable
fun HistoryCard(

    trip: Trip

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                trip.vehiclePlate,

                fontWeight = FontWeight.Bold

            )

            Text("Route : ${trip.route}")

            Text("Passengers : ${trip.passengerCount}")

            Text("Status : ${trip.status}")

        }

    }

}