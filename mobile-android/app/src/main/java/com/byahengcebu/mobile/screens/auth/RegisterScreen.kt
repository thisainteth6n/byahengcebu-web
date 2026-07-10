package com.byahengcebu.mobile.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(

    onRegisterSuccess: () -> Unit,

    onBackClick: () -> Unit

) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            Text(

                text = "Create Account",

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(28.dp))

            OutlinedTextField(

                value = fullName,

                onValueChange = {

                    fullName = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Full Name")

                },

                shape = RoundedCornerShape(18.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = email,

                onValueChange = {

                    email = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Email")

                },

                shape = RoundedCornerShape(18.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(

                value = password,

                onValueChange = {

                    password = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Password")

                },

                shape = RoundedCornerShape(18.dp)

            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(

                onClick = onRegisterSuccess,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(18.dp)

            ) {

                Text("Register")

            }

            Spacer(modifier = Modifier.height(14.dp))

            TextButton(

                onClick = onBackClick

            ) {

                Text("Back to Login")

            }

        }

    }

}