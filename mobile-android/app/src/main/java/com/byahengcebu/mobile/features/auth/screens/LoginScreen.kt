package com.byahengcebu.mobile.features.auth.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.features.auth.viewmodel.AuthViewModel

@Composable
fun LoginScreen(

    onLoginClick: () -> Unit,

    onRegisterClick: () -> Unit

) {

    val viewModel: AuthViewModel = viewModel()

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

                "🚍 ByahengCebu",

                fontSize = 32.sp,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Fleet Management System")

            Spacer(modifier = Modifier.height(36.dp))

            OutlinedTextField(

                value = email,

                onValueChange = {

                    email = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Email")

                },

                leadingIcon = {

                    Icon(Icons.Default.Email, null)

                },

                shape = RoundedCornerShape(18.dp)

            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(

                value = password,

                onValueChange = {

                    password = it

                },

                modifier = Modifier.fillMaxWidth(),

                label = {

                    Text("Password")

                },

                leadingIcon = {

                    Icon(Icons.Default.Lock, null)

                },

                visualTransformation = PasswordVisualTransformation(),

                shape = RoundedCornerShape(18.dp)

            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(

                onClick = {

                    viewModel.login(

                        email,

                        password

                    ) {

                        onLoginClick()

                    }

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(18.dp),

                enabled = !viewModel.loading

            ) {

                Text(

                    if (viewModel.loading)
                        "Signing In..."
                    else
                        "Login"

                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = viewModel.message.toString(),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(

                onClick = onRegisterClick

            ) {

                Text("Create Account")

            }

        }

    }

}