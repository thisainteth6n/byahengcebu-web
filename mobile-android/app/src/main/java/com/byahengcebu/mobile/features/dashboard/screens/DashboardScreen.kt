package com.byahengcebu.mobile.features.dashboard.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.features.vehicle.viewmodel.VehicleViewModel

@Composable
fun DashboardScreen(

    viewModel: VehicleViewModel,

    onTripClick: () -> Unit,

    onRemittanceClick: () -> Unit,

    onReportIssueClick: () -> Unit,

    onProfileClick: () -> Unit,

    onLogout: () -> Unit

) {

    LaunchedEffect(Unit) {

        viewModel.loadAssignedVehicle()

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            text = "🚍 ByahengCebu",

            fontSize = 30.sp,

            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(

            text = "Driver Dashboard",

            style = MaterialTheme.typography.bodyLarge

        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp)

        ) {

            Column(

                modifier = Modifier.padding(20.dp)

            ) {

                Text(

                    text = "Assigned Vehicle",

                    fontWeight = FontWeight.Bold,

                    fontSize = 20.sp

                )

                Spacer(modifier = Modifier.height(12.dp))

                if (viewModel.loading) {

                    CircularProgressIndicator()

                } else {

                    val vehicle = viewModel.assignedVehicle

                    if (vehicle == null) {

                        Text("No assigned vehicle.")

                    } else {

                        Text("Plate: ${vehicle.plateNumber}")

                        Text("Route: ${vehicle.route}")

                        Text("Model: ${vehicle.model}")

                        Text("Status: ${vehicle.status}")

                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(30.dp))

        ActionButton(

            "🚍  My Trips",

            onTripClick

        )

        Spacer(modifier = Modifier.height(12.dp))

        ActionButton(

            "💰  Remittance",

            onRemittanceClick

        )

        Spacer(modifier = Modifier.height(12.dp))

        ActionButton(

            "⚠  Report Vehicle Issue",

            onReportIssueClick

        )

        Spacer(modifier = Modifier.height(12.dp))

        ActionButton(

            "👤  Profile",

            onProfileClick

        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(

            modifier = Modifier.fillMaxWidth(),

            onClick = onLogout

        ) {

            Icon(

                Icons.Default.Logout,

                null

            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Logout")

        }

    }

}

@Composable
private fun ActionButton(

    title: String,

    onClick: () -> Unit

) {

    Button(

        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        onClick = onClick

    ) {

        Text(

            title,

            fontSize = 16.sp

        )

    }

}