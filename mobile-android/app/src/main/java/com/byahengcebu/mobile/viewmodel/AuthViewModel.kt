package com.byahengcebu.mobile.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.model.User
import com.byahengcebu.mobile.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    var loading by mutableStateOf(false)
        private set

    var message by mutableStateOf("")
        private set

    fun login(

        email: String,

        password: String,

        onSuccess: () -> Unit

    ) {

        loading = true

        viewModelScope.launch {

            try {

                val response = repository.login(

                    User(
                        email = email,
                        password = password
                    )

                )

                message = response.body() ?: "Unknown error"

                if (response.isSuccessful &&
                    message == "Login Successful"
                ) {

                    onSuccess()

                }

            } catch (e: Exception) {

                message = e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

    fun register(

        fullname: String,

        email: String,

        password: String,

        onSuccess: () -> Unit

    ) {

        loading = true

        viewModelScope.launch {

            try {

                val response = repository.register(

                    User(

                        fullname = fullname,

                        email = email,

                        password = password,

                        role = "DRIVER"

                    )

                )

                message = response.body() ?: "Unknown error"

                if (response.isSuccessful &&
                    message == "Registration Successful"
                ) {

                    onSuccess()

                }

            } catch (e: Exception) {

                message = e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

}