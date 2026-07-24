package com.byahengcebu.mobile.features.issue.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.features.issue.viewmodel.IssueViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportIssueScreen(

    viewModel: IssueViewModel = viewModel()

) {

    LaunchedEffect(Unit) {

        viewModel.loadIssues()

    }

    var issueType by remember {

        mutableStateOf("ENGINE")

    }

    var description by remember {

        mutableStateOf("")

    }

    val issueTypes = listOf(

        "ENGINE",
        "BRAKES",
        "TIRES",
        "LIGHTS",
        "AIRCON",
        "BODY",
        "OTHER"

    )

    var expanded by remember {

        mutableStateOf(false)

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            text = "Report Vehicle Issue",

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(

            text = "Assigned Vehicle",

            fontWeight = FontWeight.Bold

        )

        Text(viewModel.assignedPlate)

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(

            expanded = expanded,

            onExpandedChange = {

                expanded = !expanded

            }

        ) {

            OutlinedTextField(

                value = issueType,

                onValueChange = {},

                readOnly = true,

                label = {

                    Text("Issue Type")

                },

                trailingIcon = {

                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)

                },

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()

            )

            ExposedDropdownMenu(

                expanded = expanded,

                onDismissRequest = {

                    expanded = false

                }

            ) {

                issueTypes.forEach {

                    DropdownMenuItem(

                        text = {

                            Text(it)

                        },

                        onClick = {

                            issueType = it

                            expanded = false

                        }

                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = description,

            onValueChange = {

                description = it

            },

            label = {

                Text("Describe the issue")

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                if (description.isBlank()) return@Button

                viewModel.submitIssue(

                    issueType,

                    description

                )

                description = ""

            }

        ) {

            Text("SUBMIT ISSUE")

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(

            text = "My Submitted Issues",

            fontSize = 22.sp,

            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {

            items(viewModel.issues) { issue ->

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)

                ) {

                    Column(

                        modifier = Modifier.padding(16.dp)

                    ) {

                        Text(

                            issue.issueType,

                            fontWeight = FontWeight.Bold,

                            fontSize = 18.sp

                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(issue.description)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Vehicle : ${issue.vehiclePlate}")

                        Text("Status : ${issue.status}")

                    }

                }

            }

        }

    }

}