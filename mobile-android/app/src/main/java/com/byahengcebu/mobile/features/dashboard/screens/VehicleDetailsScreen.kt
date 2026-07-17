package com.byahengcebu.mobile.features.dashboard.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.features.vehicle.viewmodel.VehicleViewModel
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailsScreen(
    viewModel: VehicleViewModel,
    onBackClick: () -> Unit
) {

    val vehicle = viewModel.selectedVehicle

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text("Vehicle Details")

                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors()

            )

        }

    ) { padding ->

        if (vehicle == null) {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                verticalArrangement = Arrangement.Center

            ) {

                Text(
                    text = "No vehicle selected.",
                    modifier = Modifier.padding(24.dp)
                )

            }

            return@Scaffold

        }

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)

        ) {

            Text(

                text = vehicle.plateNumber,

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(

                modifier = Modifier.fillMaxWidth(),

                elevation = CardDefaults.cardElevation(4.dp)

            ) {

                Column(

                    modifier = Modifier.padding(20.dp)

                ) {

                    Text(
                        text = "Route",
                        fontWeight = FontWeight.Bold
                    )

                    Text(vehicle.route)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Vehicle Model",
                        fontWeight = FontWeight.Bold
                    )

                    Text(vehicle.model)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Current Status",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = vehicle.status,
                        color = MaterialTheme.colorScheme.primary
                    )

                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = onBackClick

            ) {

                Text("Back to Dashboard")

            }

        }

    }

}