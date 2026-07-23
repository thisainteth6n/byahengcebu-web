package com.byahengcebu.mobile.features.remittance.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.features.remittance.model.Remittance
import com.byahengcebu.mobile.features.remittance.viewmodel.RemittanceViewModel
import com.byahengcebu.mobile.features.trip.model.Trip

@Composable
fun RemittanceScreen() {

    val viewModel: RemittanceViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Remittance",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (viewModel.eligibleTrips.isEmpty()) {

            Text("No completed trips waiting for remittance.")

        } else {

            LazyColumn {

                items(viewModel.eligibleTrips) { trip ->

                    SubmitRemittanceCard(
                        trip = trip,
                        viewModel = viewModel
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            "Previous Remittances",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {

            items(viewModel.remittances) {

                RemittanceHistoryCard(it)

            }

        }

    }

}

@Composable
fun SubmitRemittanceCard(

    trip: Trip,

    viewModel: RemittanceViewModel

) {

    var total by remember {

        mutableStateOf("")

    }

    var expenses by remember {

        mutableStateOf("")

    }

    var remarks by remember {

        mutableStateOf("")

    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                trip.vehiclePlate,
                fontWeight = FontWeight.Bold
            )

            Text("Route : ${trip.route}")

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = total,

                onValueChange = {

                    total = it

                },

                label = {

                    Text("Total Collection")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Number

                ),

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = expenses,

                onValueChange = {

                    expenses = it

                },

                label = {

                    Text("Expenses")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Number

                ),

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(

                value = remarks,

                onValueChange = {

                    remarks = it

                },

                label = {

                    Text("Remarks")

                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    if (

                        total.isBlank() ||

                        expenses.isBlank()

                    ) return@Button

                    viewModel.submit(

                        trip,

                        total.toDouble(),

                        expenses.toDouble(),

                        remarks

                    )

                }

            ) {

                Text("SUBMIT REMITTANCE")

            }

        }

    }

}

@Composable
fun RemittanceHistoryCard(

    remittance: Remittance

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
                remittance.vehiclePlate,
                fontWeight = FontWeight.Bold
            )

            Text("Collection : ₱${remittance.totalCollection}")

            Text("Expenses : ₱${remittance.expenses}")

            Text("Net : ₱${remittance.netCollection}")

            Text("Status : ${remittance.status}")

        }

    }

}