package com.byahengcebu.mobile.features.profile.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(

    fullname: String,

    email: String

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {

        Text(

            "Driver Profile",

            fontSize = 28.sp

        )

        Spacer(modifier = Modifier.height(20.dp))

        Card {

            Column(

                modifier = Modifier.padding(20.dp)

            ) {

                Text("Name")

                Text(fullname)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Email")

                Text(email)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Role")

                Text("Driver")

            }

        }

    }

}