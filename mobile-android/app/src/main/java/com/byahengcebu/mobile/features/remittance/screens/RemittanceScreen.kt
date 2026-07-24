package com.byahengcebu.mobile.features.remittance.screens

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.features.remittance.model.Remittance
import com.byahengcebu.mobile.features.remittance.viewmodel.RemittanceViewModel
import com.byahengcebu.mobile.features.trip.model.Trip
import com.byahengcebu.mobile.shared.components.StatusChip
import com.byahengcebu.mobile.shared.utils.CurrencyUtils
import com.byahengcebu.mobile.shared.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemittanceScreen() {

    val viewModel: RemittanceViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        "Remittance"
                    )

                }

            )

        },

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            item {

                Text(

                    text = "Pending Remittances",

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold

                )

            }

            if (viewModel.loading) {

                item {

                    CircularProgressIndicator()

                }

            }

            else if (viewModel.eligibleTrips.isEmpty()) {

                item {

                    Card(

                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )

                    ) {

                        Column(

                            modifier = Modifier.padding(20.dp)

                        ) {

                            Text(

                                "No Pending Remittances",

                                fontWeight = FontWeight.Bold

                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(

                                "Complete a trip first before submitting a remittance."

                            )

                        }

                    }

                }

            }

            else {

                items(viewModel.eligibleTrips) { trip ->

                    SubmitRemittanceCard(

                        trip = trip,

                        viewModel = viewModel

                    )

                }

            }

            item {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Divider()

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(

                    text = "Remittance History",

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold

                )

            }

            if (viewModel.remittances.isEmpty()) {

                item {

                    Text(

                        "No remittance history yet."

                    )

                }

            }

            else {

                items(viewModel.remittances) {

                    RemittanceHistoryCard(it)

                }

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

        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                text = "🚍 ${trip.vehiclePlate}",

                fontWeight = FontWeight.Bold,

                fontSize = 20.sp

            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(

                text = "Route: ${trip.route}"

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = total,

                onValueChange = {

                    total = it

                },

                label = {

                    Text("Gross Collection")

                },

                leadingIcon = {

                    Icon(

                        Icons.Default.AttachMoney,

                        contentDescription = null

                    )

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Number

                ),

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(

                value = expenses,

                onValueChange = {

                    expenses = it

                },

                label = {

                    Text("Expenses")

                },

                leadingIcon = {

                    Icon(

                        Icons.Default.Payments,

                        contentDescription = null

                    )

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Number

                ),

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(

                value = remarks,

                onValueChange = {

                    remarks = it

                },

                label = {

                    Text("Remarks (Optional)")

                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(

                modifier = Modifier.fillMaxWidth(),

                enabled = !viewModel.loading,

                onClick = {

                    val totalValue = total.toDoubleOrNull()

                    val expenseValue = expenses.toDoubleOrNull()

                    if (

                        totalValue == null ||

                        expenseValue == null

                    ) {

                        return@Button

                    }

                    viewModel.submit(

                        trip,

                        totalValue,

                        expenseValue,

                        remarks

                    )

                    total = ""

                    expenses = ""

                    remarks = ""

                }

            ) {

                if (viewModel.loading) {

                    CircularProgressIndicator()

                } else {

                    Text(

                        "SUBMIT REMITTANCE"

                    )

                }

            }

        }

    }

}
@Composable
fun RemittanceHistoryCard(

    remittance: Remittance

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(

            defaultElevation = 5.dp

        )

    ) {

        Column(

            modifier = Modifier.padding(16.dp)

        ) {

            Text(

                text = "🚍 ${remittance.vehiclePlate}",

                fontWeight = FontWeight.Bold,

                fontSize = 20.sp

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = "Submitted",

                fontWeight = FontWeight.SemiBold

            )

            Text(

                text = DateUtils.formatDate(

                    remittance.submittedDate

                )

            )

            Spacer(modifier = Modifier.height(12.dp))

            Divider()

            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = "Gross Collection",

                fontWeight = FontWeight.SemiBold

            )

            Text(

                text = CurrencyUtils.format(

                    remittance.totalCollection

                )

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "Expenses",

                fontWeight = FontWeight.SemiBold

            )

            Text(

                text = CurrencyUtils.format(

                    remittance.expenses

                )

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "Net Collection",

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary

            )

            Text(

                text = CurrencyUtils.format(

                    remittance.netCollection

                ),

                fontWeight = FontWeight.Bold,

                fontSize = 18.sp

            )

            Spacer(modifier = Modifier.height(14.dp))

            StatusChip(

                remittance.status

            )

        }

    }

}

