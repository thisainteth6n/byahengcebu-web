package com.byahengcebu.mobile.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.viewmodel.VehicleViewModel

@Composable
fun DashboardScreen() {

    val viewModel: VehicleViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadDashboard()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        item {

            Text(
                text = "🚍 ByahengCebu",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Fleet Management Dashboard",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Fleet Statistics",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("🚍 Total Vehicles: ${viewModel.statistics.total}")

                    Text("🟢 Active Vehicles: ${viewModel.statistics.active}")

                    Text("🔧 Maintenance: ${viewModel.statistics.maintenance}")

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Vehicle List",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

        }

        items(viewModel.vehicles) { vehicle ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        vehicle.plateNumber,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Route: ${vehicle.route}")

                    Text("Model: ${vehicle.model}")

                    Text("Status: ${vehicle.status}")

                }

            }

        }

        if (viewModel.vehicles.isEmpty() && !viewModel.loading) {

            item {

                Text(
                    "No vehicles found.",
                    modifier = Modifier.padding(20.dp)
                )

            }

        }

    }

}