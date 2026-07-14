package com.byahengcebu.mobile.screens.trip

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.model.Trip
import com.byahengcebu.mobile.viewmodel.TripViewModel

@Composable
fun TripScreen(
    viewModel: TripViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.loadTrips()
    }

    var driverName by remember { mutableStateOf("") }
    var vehiclePlate by remember { mutableStateOf("") }
    var route by remember { mutableStateOf("") }
    var passengers by remember { mutableStateOf("") }
    var startOdometer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Trip Management",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = driverName,
            onValueChange = { driverName = it },
            label = { Text("Driver Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = vehiclePlate,
            onValueChange = { vehiclePlate = it },
            label = { Text("Vehicle Plate") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = route,
            onValueChange = { route = it },
            label = { Text("Route") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = passengers,
            onValueChange = { passengers = it },
            label = { Text("Passenger Count") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = startOdometer,
            onValueChange = { startOdometer = it },
            label = { Text("Start Odometer") },
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
                    driverName.isBlank() ||
                    vehiclePlate.isBlank() ||
                    route.isBlank() ||
                    passengers.isBlank() ||
                    startOdometer.isBlank()
                ) return@Button

                val trip = Trip(

                    driverName = driverName,

                    vehiclePlate = vehiclePlate,

                    route = route,

                    passengerCount = passengers.toInt(),

                    startOdometer = startOdometer.toInt()

                )

                viewModel.startTrip(trip)

                driverName = ""
                vehiclePlate = ""
                route = ""
                passengers = ""
                startOdometer = ""

            }

        ) {

            Text("START TRIP")

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Ongoing Trips",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {

            items(viewModel.ongoingTrips) { trip ->

                TripCard(
                    trip,
                    viewModel
                )

            }

        }

    }

}

@Composable
fun TripCard(
    trip: Trip,
    viewModel: TripViewModel
) {

    var endOdometer by remember {

        mutableStateOf("")

    }

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text("Driver: ${trip.driverName}")

            Text("Vehicle: ${trip.vehiclePlate}")

            Text("Route: ${trip.route}")

            Text("Passengers: ${trip.passengerCount}")

            Text("Started: ${trip.startOdometer}")

            Spacer(modifier = Modifier.height(10.dp))

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

                )

            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    if (endOdometer.isBlank()) return@Button

                    viewModel.endTrip(

                        trip.id,

                        trip.copy(

                            endOdometer = endOdometer.toInt()

                        )

                    )

                }

            ) {

                Text("END TRIP")

            }

        }

    }

}