package com.byahengcebu.mobile.features.dashboard.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byahengcebu.mobile.features.vehicle.viewmodel.VehicleViewModel
import androidx.compose.material.icons.automirrored.filled.Logout

private val PrimaryColor = Color(0xFF0F766E)
private val BackgroundColor = Color(0xFFF5F7FA)

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

    val vehicle = viewModel.assignedVehicle

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp)

    ) {

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

        Spacer(modifier = Modifier.height(24.dp))

        Card(

            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(

                containerColor = Color.White

            ),

            elevation = CardDefaults.cardElevation(

                defaultElevation = 8.dp

            ),

            shape = RoundedCornerShape(20.dp)

        ) {

            Column(

                modifier = Modifier.padding(20.dp)

            ) {

                Text(

                    text = "Assigned Vehicle",

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold

                )

                Spacer(modifier = Modifier.height(16.dp))

                if (viewModel.loading) {

                    Box(

                        modifier = Modifier.fillMaxWidth(),

                        contentAlignment = Alignment.Center

                    ) {

                        CircularProgressIndicator()

                    }

                } else {

                    if (vehicle == null) {

                        Text(

                            "No vehicle assigned.",

                            color = Color.Red

                        )

                    } else {

                        InfoRow(

                            "Plate Number",

                            vehicle.plateNumber

                        )

                        InfoRow(

                            "Route",

                            vehicle.route

                        )

                        InfoRow(

                            "Model",

                            vehicle.model

                        )

                        InfoRow(

                            "Status",

                            vehicle.status

                        )

                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(28.dp))

        DashboardButton(

            text = "Trip Management",

            icon = Icons.Default.DirectionsBus,

            onClick = onTripClick

        )

        Spacer(modifier = Modifier.height(14.dp))

        DashboardButton(

            text = "Submit Remittance",

            icon = Icons.Default.Payments,

            onClick = onRemittanceClick

        )

        Spacer(modifier = Modifier.height(14.dp))

        DashboardButton(

            text = "Report Vehicle Issue",

            icon = Icons.Default.Build,

            onClick = onReportIssueClick

        )

        Spacer(modifier = Modifier.height(14.dp))

        DashboardButton(

            text = "Profile",

            icon = Icons.Default.AccountCircle,

            onClick = onProfileClick

        )

        Spacer(modifier = Modifier.weight(1f))

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

@Composable
private fun InfoRow(

    label: String,

    value: String

) {

    Column(

        modifier = Modifier.padding(bottom = 10.dp)

    ) {

        Text(

            text = label,

            color = Color.Gray,

            fontSize = 13.sp

        )

        Text(

            text = value,

            fontWeight = FontWeight.SemiBold,

            fontSize = 17.sp

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
            .height(68.dp),

        colors = ButtonDefaults.elevatedButtonColors(

            containerColor = Color.White,

            contentColor = PrimaryColor

        ),

        elevation = ButtonDefaults.elevatedButtonElevation(

            defaultElevation = 6.dp

        ),

        shape = RoundedCornerShape(18.dp),

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