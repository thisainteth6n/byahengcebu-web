package com.byahengcebu.mobile.features.dashboard.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.features.issue.viewmodel.IssueViewModel
import com.byahengcebu.mobile.features.remittance.viewmodel.RemittanceViewModel
import com.byahengcebu.mobile.features.trip.viewmodel.TripViewModel
import com.byahengcebu.mobile.features.vehicle.viewmodel.VehicleViewModel
import com.byahengcebu.mobile.shared.components.SectionTitle
import com.byahengcebu.mobile.shared.components.StatisticCard

private val PrimaryColor = Color(0xFF0F766E)
private val BackgroundColor = Color(0xFFF5F7FA)

@Composable
fun DashboardScreen(

    vehicleViewModel: VehicleViewModel,
    tripViewModel: TripViewModel,
    remittanceViewModel: RemittanceViewModel,
    issueViewModel: IssueViewModel,

    onTripClick: () -> Unit,
    onRemittanceClick: () -> Unit,
    onReportIssueClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogout: () -> Unit

) {

    LaunchedEffect(Unit) {

        vehicleViewModel.loadAssignedVehicle()
        tripViewModel.loadTrips()
        remittanceViewModel.loadData()
        issueViewModel.loadIssues()

    }

    val vehicle = vehicleViewModel.assignedVehicle

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        item {

            Text(

                text = "Good Day 👋",

                fontSize = 16.sp,

                color = Color.Gray

            )

            Text(

                text = "Driver Dashboard",

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold,

                color = PrimaryColor

            )

        }

        item {

            Card(

                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(

                    containerColor = Color.White

                ),

                elevation = CardDefaults.cardElevation(8.dp),

                shape = RoundedCornerShape(20.dp)

            ) {

                Column(

                    modifier = Modifier.padding(20.dp)

                ) {

                    Text(

                        "Assigned Vehicle",

                        fontWeight = FontWeight.Bold,

                        fontSize = 20.sp

                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (vehicleViewModel.loading) {

                        CircularProgressIndicator()

                    } else {

                        if (vehicle == null) {

                            Text(

                                "No assigned vehicle",

                                color = Color.Red

                            )

                        } else {

                            InfoRow("Plate Number", vehicle.plateNumber)
                            InfoRow("Route", vehicle.route)
                            InfoRow("Model", vehicle.model)
                            InfoRow("Status", vehicle.status)

                        }

                    }

                }

            }

        }

        item {

            SectionTitle("Overview")

        }

        item {

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                modifier = Modifier.height(240.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp),

                verticalArrangement = Arrangement.spacedBy(12.dp),

                userScrollEnabled = false

            ) {

                items(

                    listOf(

                        Triple(
                            "Trips",
                            tripViewModel.trips.size.toString(),
                            Icons.Default.DirectionsBus
                        ),

                        Triple(
                            "Remittances",
                            remittanceViewModel.remittances.size.toString(),
                            Icons.Default.Payments
                        ),

                        Triple(
                            "Issues",
                            issueViewModel.issues.size.toString(),
                            Icons.Default.Warning
                        ),

                        Triple(
                            "Vehicle",
                            if (vehicle != null) "✓" else "--",
                            Icons.Default.Build
                        )

                    )

                ) { stat ->

                    StatisticCard(

                        title = stat.first,

                        value = stat.second,

                        icon = stat.third

                    )

                }

            }

        }

        item {

            SectionTitle("Quick Actions")

        }

        item {

            DashboardButton(

                text = "Trip Management",

                icon = Icons.Default.DirectionsBus,

                onClick = onTripClick

            )

        }

        item {

            DashboardButton(

                text = "Submit Remittance",

                icon = Icons.Default.Payments,

                onClick = onRemittanceClick

            )

        }

        item {

            DashboardButton(

                text = "Report Vehicle Issue",

                icon = Icons.Default.Build,

                onClick = onReportIssueClick

            )

        }

        item {

            DashboardButton(

                text = "Profile",

                icon = Icons.Default.AccountCircle,

                onClick = onProfileClick

            )

        }

        item {

            OutlinedButton(

                modifier = Modifier.fillMaxWidth(),

                onClick = onLogout

            ) {

                Icon(

                    imageVector = Icons.AutoMirrored.Filled.Logout,

                    contentDescription = null

                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Logout")

            }

        }
    }

}

@Composable
private fun InfoRow(

    label: String,

    value: String

) {

    Column(

        modifier = Modifier.padding(bottom = 12.dp)

    ) {

        Text(

            text = label,

            fontSize = 13.sp,

            color = Color.Gray

        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(

            text = value,

            fontSize = 17.sp,

            fontWeight = FontWeight.SemiBold,

            color = Color.Black

        )

    }

}

@Composable
private fun DashboardButton(

    text: String,

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    onClick: () -> Unit

) {

    ElevatedButton(

        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp),

        shape = RoundedCornerShape(18.dp),

        colors = ButtonDefaults.elevatedButtonColors(

            containerColor = Color.White,

            contentColor = PrimaryColor

        ),

        elevation = ButtonDefaults.elevatedButtonElevation(

            defaultElevation = 6.dp

        ),

        onClick = onClick

    ) {

        Icon(

            imageVector = icon,

            contentDescription = null

        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(

            text = text,

            fontSize = 17.sp,

            fontWeight = FontWeight.SemiBold

        )

    }

}

