package com.byahengcebu.mobile.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.model.Vehicle
import com.byahengcebu.mobile.viewmodel.VehicleViewModel

@Composable
fun DashboardScreen(
    viewModel: VehicleViewModel,
    onVehicleClick: (Vehicle) -> Unit,
    onTripClick: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.loadDashboard()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp)
    ) {

        item {

            Text(
                text = "🚍 ByahengCebu",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Fleet Management Dashboard",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {

            Text(
                text = "Fleet Statistics",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = false
            ) {

                item {
                    StatisticCard(
                        "Total",
                        viewModel.statistics.total.toString()
                    )
                }

                item {
                    StatisticCard(
                        "Active",
                        viewModel.statistics.active.toString()
                    )
                }

                item {
                    StatisticCard(
                        "Maintenance",
                        viewModel.statistics.maintenance.toString()
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onTripClick
            ) {
                Text("Trip Management")
            }

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Fleet Vehicles",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        if (viewModel.loading) {

            item {

                CircularProgressIndicator()

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        items(viewModel.vehicles.size) { index ->

            val vehicle = viewModel.vehicles[index]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clickable {
                        onVehicleClick(vehicle)
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = vehicle.plateNumber,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text("Route: ${vehicle.route}")
                    Text("Model: ${vehicle.model}")
                    Text("Status: ${vehicle.status}")

                }

            }

        }

        if (!viewModel.loading && viewModel.vehicles.isEmpty()) {

            item {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {

                        Text(
                            text = "🚍",
                            fontSize = 42.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "No vehicles found.",
                            fontWeight = FontWeight.Bold
                        )

                    }

                }

            }

        }

    }

}

@Composable
fun StatisticCard(
    title: String,
    value: String
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )

        }

    }

}