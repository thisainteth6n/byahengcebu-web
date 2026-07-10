package com.byahengcebu.mobile.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.byahengcebu.mobile.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(

    onRegisterSuccess: () -> Unit,

    onBackClick: () -> Unit

) {

    val viewModel: AuthViewModel = viewModel()

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                text = "🚍 ByahengCebu",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Fleet Management System")

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Create Account",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(28.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Default.Person, null) },
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, null) },
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (viewModel.message.isNotBlank()) {

                Text(
                    text = viewModel.message,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

            }

            Button(

                onClick = {

                    when {

                        fullName.isBlank() -> {

                            viewModel.updateMessage("Please enter your full name.")
                            return@Button

                        }

                        email.isBlank() -> {

                            viewModel.updateMessage("Please enter your email.")
                            return@Button

                        }

                        password.isBlank() -> {

                            viewModel.updateMessage("Please enter your password.")
                            return@Button

                        }

                        confirmPassword.isBlank() -> {

                            viewModel.updateMessage("Please confirm your password.")
                            return@Button

                        }

                        password != confirmPassword -> {

                            viewModel.updateMessage("Passwords do not match.")
                            return@Button

                        }

                    }

                    viewModel.register(

                        fullname = fullName,

                        email = email,

                        password = password

                    ) {

                        onRegisterSuccess()

                    }

                },

                enabled = !viewModel.loading,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(18.dp)

            ) {

                Text(

                    if (viewModel.loading)
                        "Creating Account..."
                    else
                        "Register"

                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = onBackClick
            ) {
                Text("Already have an account? Login")
            }

        }

    }

}