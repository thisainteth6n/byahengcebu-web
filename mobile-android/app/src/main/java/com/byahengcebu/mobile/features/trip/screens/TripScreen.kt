package com.byahengcebu.mobile.features.trip.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.features.trip.viewmodel.TripViewModel
import com.byahengcebu.mobile.shared.utils.DateUtils

private val PrimaryColor = Color(0xFF0F766E)
private val BackgroundColor = Color(0xFFF5F7FA)

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
        },

        containerColor = BackgroundColor

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            item {

                Spacer(modifier = Modifier.height(10.dp))

                Text(

                    text = "Trip Management",

                    fontSize = 30.sp,

                    fontWeight = FontWeight.Bold,

                    color = PrimaryColor

                )

                Text(

                    text = "Manage your daily operations",

                    color = Color.Gray

                )

            }

            item {

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),

                    shape = RoundedCornerShape(20.dp)

                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            "Trip Summary",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            SummaryItem(
                                Icons.Default.DirectionsBus,
                                "Trips",
                                tripViewModel.trips.size.toString()
                            )

                            SummaryItem(
                                Icons.Default.Schedule,
                                "Status",
                                if (tripViewModel.currentTrip == null)
                                    "Ready"
                                else
                                    "Ongoing"
                            )

                        }

                    }

                }

            }

            item {

                if (tripViewModel.currentTrip == null) {

                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(20.dp)

                    ) {

                        Column(

                            modifier = Modifier.padding(20.dp)

                        ) {

                            Text(

                                "Start New Trip",

                                fontWeight = FontWeight.Bold,

                                fontSize = 22.sp

                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            OutlinedTextField(

                                value = passengers,

                                onValueChange = {
                                    passengers = it
                                },

                                modifier = Modifier.fillMaxWidth(),

                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Group,
                                        null
                                    )
                                },

                                label = {
                                    Text("Passenger Count")
                                },

                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )

                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            OutlinedTextField(

                                value = startOdometer,

                                onValueChange = {
                                    startOdometer = it
                                },

                                modifier = Modifier.fillMaxWidth(),

                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Speed,
                                        null
                                    )
                                },

                                label = {
                                    Text("Start Odometer")
                                },

                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                )

                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),

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

                                Icon(Icons.Default.Start, null)

                                Spacer(modifier = Modifier.width(8.dp))

                                Text("START TRIP")

                            }

                        }

                    }

                } else {

                    CurrentTripCard(

                        trip = tripViewModel.currentTrip!!,

                        tripViewModel = tripViewModel

                    )

                }

            }

            item {

                Text(

                    "Trip History",

                    fontWeight = FontWeight.Bold,

                    fontSize = 22.sp

                )

            }

            items(tripViewModel.trips) { trip ->

                HistoryCard(trip)

            }

            item {

                Spacer(modifier = Modifier.height(20.dp))

            }

        }

    }

}
@Composable
private fun CurrentTripCard(

    trip: Trip,

    tripViewModel: TripViewModel

) {

    var endOdometer by remember {
        mutableStateOf("")
    }

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        shape = RoundedCornerShape(20.dp)

    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(

                text = "Current Trip",

                fontWeight = FontWeight.Bold,

                fontSize = 22.sp

            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoLine(
                Icons.Default.DirectionsBus,
                "Vehicle",
                trip.vehiclePlate
            )

            InfoLine(
                Icons.Default.Route,
                "Route",
                trip.route
            )

            InfoLine(
                Icons.Default.Group,
                "Passengers",
                trip.passengerCount.toString()
            )

            InfoLine(
                Icons.Default.Speed,
                "Start Odometer",
                trip.startOdometer.toString()
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(

                value = endOdometer,

                onValueChange = {
                    endOdometer = it
                },

                modifier = Modifier.fillMaxWidth(),

                leadingIcon = {
                    Icon(Icons.Default.Stop, null)
                },

                label = {
                    Text("End Odometer")
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )

            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDC2626)
                ),

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

                Icon(Icons.Default.Stop, null)

                Spacer(modifier = Modifier.width(8.dp))

                Text("END TRIP")

            }

        }

    }

}

@Composable
private fun HistoryCard(
    trip: Trip
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),

        shape = RoundedCornerShape(18.dp)

    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(

                    trip.vehiclePlate,

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp

                )

                StatusChip(trip.status)

            }

            Spacer(modifier = Modifier.height(12.dp))

            InfoLine(
                Icons.Default.Route,
                "Route",
                trip.route
            )

            InfoLine(
                Icons.Default.Group,
                "Passengers",
                trip.passengerCount.toString()
            )

            InfoLine(
                Icons.Default.Schedule,
                "Started",
                DateUtils.formatDateTime(trip.startTime)
            )

            InfoLine(
                Icons.Default.Stop,
                "Ended",
                DateUtils.formatDateTime(trip.endTime)
            )

            InfoLine(
                Icons.Default.Schedule,
                "Duration",
                DateUtils.duration(
                    trip.startTime,
                    trip.endTime
                )
            )

        }

    }

}

@Composable
private fun SummaryItem(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title: String,

    value: String

) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryColor
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Text(
            text = title,
            color = Color.Gray
        )

    }

}

@Composable
private fun InfoLine(

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    title: String,

    value: String

) {

    Row(

        modifier = Modifier.padding(vertical = 6.dp),

        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(

            imageVector = icon,

            contentDescription = null,

            tint = PrimaryColor

        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {

            Text(
                text = title,
                color = Color.Gray,
                fontSize = 13.sp
            )

            Text(
                text = value,
                fontWeight = FontWeight.SemiBold
            )

        }

    }

}

@Composable
private fun StatusChip(
    status: String
) {

    val color = when (status.uppercase()) {
        "ONGOING" -> Color(0xFF16A34A)
        "COMPLETED" -> Color(0xFF2563EB)
        else -> Color.Gray
    }

    Surface(

        color = color.copy(alpha = 0.15f),

        shape = RoundedCornerShape(50)

    ) {

        Text(

            text = status,

            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 5.dp
            ),

            color = color,

            fontWeight = FontWeight.Bold,

            fontSize = 12.sp

        )

    }

}
