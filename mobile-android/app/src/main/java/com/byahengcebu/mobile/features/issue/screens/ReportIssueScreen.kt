package com.byahengcebu.mobile.features.issue.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.features.issue.viewmodel.IssueViewModel
import com.byahengcebu.mobile.shared.utils.DateUtils

private val PrimaryColor = Color(0xFF0F766E)
private val BackgroundColor = Color(0xFFF5F7FA)

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

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 20.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        item {

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = "Vehicle Issues",

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold,

                color = PrimaryColor

            )

            Text(

                "Report and monitor vehicle problems",

                color = Color.Gray

            )

        }

        item {

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp),

                elevation = CardDefaults.cardElevation(6.dp)

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

                    Row(

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(

                            Icons.Default.DirectionsBus,

                            contentDescription = null,

                            tint = PrimaryColor

                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(

                            if (viewModel.assignedPlate.isBlank())
                                "No Vehicle Assigned"
                            else
                                viewModel.assignedPlate,

                            fontWeight = FontWeight.SemiBold,

                            fontSize = 18.sp

                        )

                    }

                }

            }

        }

        item {

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp)

            ) {

                Column(

                    modifier = Modifier.padding(20.dp)

                ) {

                    Text(

                        "Submit New Issue",

                        fontWeight = FontWeight.Bold,

                        fontSize = 20.sp

                    )

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

                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),

                            label = {

                                Text("Issue Type")

                            },

                            leadingIcon = {

                                Icon(
                                    Icons.Default.Build,
                                    null
                                )

                            },

                            trailingIcon = {

                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded
                                )

                            }

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

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),

                        label = {

                            Text("Describe the issue")

                        },

                        leadingIcon = {

                            Icon(
                                Icons.Default.Description,
                                null
                            )

                        }

                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),

                        onClick = {

                            if (description.isBlank()) return@Button

                            viewModel.submitIssue(

                                issueType,

                                description

                            )

                            description = ""

                        }

                    ) {

                        Icon(
                            Icons.Default.Warning,
                            null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("SUBMIT ISSUE")

                    }

                }

            }

        }

        item {

            Text(

                "Issue History",

                fontSize = 22.sp,

                fontWeight = FontWeight.Bold

            )

        }

        items(viewModel.issues) {

            IssueHistoryCard(it)

        }

        item {

            Spacer(modifier = Modifier.height(20.dp))

        }

    }

}
@Composable
private fun IssueHistoryCard(
    issue: com.byahengcebu.mobile.features.issue.model.Issue
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(18.dp)

        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(

                    issue.issueType,

                    fontWeight = FontWeight.Bold,

                    fontSize = 18.sp

                )

                IssueStatusChip(issue.status)

            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                issue.description,

                style = MaterialTheme.typography.bodyMedium

            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    Icons.Default.DirectionsBus,

                    contentDescription = null,

                    tint = PrimaryColor

                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(issue.vehiclePlate)

            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(

                verticalAlignment = Alignment.CenterVertically

            ) {

                Icon(

                    Icons.Default.Description,

                    contentDescription = null,

                    tint = Color.Gray

                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(

                    DateUtils.formatDateTime(issue.reportedDate),

                    color = Color.Gray

                )

            }

        }

    }

}

@Composable
private fun IssueStatusChip(
    status: String
) {

    val color = when (status.uppercase()) {

        "RESOLVED" -> Color(0xFF2E7D32)

        "IN_PROGRESS" -> Color(0xFFF9A825)

        "PENDING" -> Color(0xFFD32F2F)

        else -> PrimaryColor

    }

    Surface(

        color = color.copy(alpha = 0.15f),

        shape = RoundedCornerShape(50)

    ) {

        Text(

            text = status.replace("_", " "),

            color = color,

            fontWeight = FontWeight.Bold,

            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            )

        )

    }

}

