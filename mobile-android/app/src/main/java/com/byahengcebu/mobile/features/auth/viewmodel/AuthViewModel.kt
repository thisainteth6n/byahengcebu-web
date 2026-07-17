package com.byahengcebu.mobile.features.auth.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.byahengcebu.mobile.features.auth.model.User
import com.byahengcebu.mobile.features.auth.repository.AuthRepository
import com.byahengcebu.mobile.shared.session.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository()

    private val session =
        SessionManager(application.applicationContext)

    var loading by mutableStateOf(false)
        private set

    var message by mutableStateOf("")
        private set

    fun updateMessage(text: String) {
        message = text
    }

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

                if (response.isSuccessful) {

                    val body = response.body()

                    if (body != null) {

                        message = body.message

                        if (body.success) {

                            session.saveUser(

                                fullname = body.fullname ?: "",

                                email = body.email ?: "",

                                role = body.role ?: ""

                            )

                            delay(1200)

                            onSuccess()

                        }

                    } else {

                        message = "Unknown Server Response"

                    }

                } else {

                    message = "Login Failed"

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

                if (response.isSuccessful) {

                    val body = response.body()

                    if (body != null) {

                        message = body.message

                        if (body.success) {

                            delay(1200)

                            onSuccess()

                        }

                    } else {

                        message = "Unknown Server Response"

                    }

                } else {

                    message = "Registration Failed"

                }

            } catch (e: Exception) {

                message = e.localizedMessage ?: "Connection Error"

            }

            loading = false

        }

    }

}