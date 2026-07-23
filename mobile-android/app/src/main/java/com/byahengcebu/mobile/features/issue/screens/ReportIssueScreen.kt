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
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportIssueScreen(

    viewModel: IssueViewModel = viewModel()

) {

    LaunchedEffect(Unit) {

        viewModel.loadIssues()

    }

    var title by remember {

        mutableStateOf("")

    }

    var description by remember {

        mutableStateOf("")

    }

    var severity by remember {

        mutableStateOf("LOW")

    }

    val severityOptions = listOf(

        "LOW",
        "MEDIUM",
        "HIGH"

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

        OutlinedTextField(

            value = title,

            onValueChange = {

                title = it

            },

            label = {

                Text("Issue Title")

            },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(

            value = description,

            onValueChange = {

                description = it

            },

            label = {

                Text("Description")

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))

        ExposedDropdownMenuBox(

            expanded = expanded,

            onExpandedChange = {

                expanded = !expanded

            }

        ) {

            OutlinedTextField(

                value = severity,

                onValueChange = {},

                readOnly = true,

                label = {

                    Text("Severity")

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

                severityOptions.forEach {

                    DropdownMenuItem(

                        text = {

                            Text(it)

                        },

                        onClick = {

                            severity = it

                            expanded = false

                        }

                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

                if (

                    title.isBlank() ||

                    description.isBlank()

                ) return@Button

                viewModel.submitIssue(

                    title,

                    description,

                    severity

                )

                title = ""

                description = ""

                severity = "LOW"

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

        Spacer(modifier = Modifier.height(10.dp))

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

                            issue.title,

                            fontWeight = FontWeight.Bold

                        )

                        Text(issue.description)

                        Text("Severity : ${issue.severity}")

                        Text("Status : ${issue.status}")

                    }

                }

            }

        }

    }

}