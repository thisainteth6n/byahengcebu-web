package com.byahengcebu.mobile.features.issue.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReportIssueScreen() {

    var issue by remember {
        mutableStateOf("")
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            "Report Vehicle Issue",

            fontSize = 28.sp

        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = issue,

            onValueChange = {

                issue = it

            },

            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),

            label = {

                Text("Describe the issue")

            }

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            modifier = Modifier.fillMaxWidth(),

            onClick = {

            }

        ) {

            Text("SEND REPORT")

        }

    }

}