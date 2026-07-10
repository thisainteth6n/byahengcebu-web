package com.byahengcebu.mobile.repository

import com.byahengcebu.mobile.api.ApiClient
import com.byahengcebu.mobile.model.User

class AuthRepository {

    suspend fun login(user: User) =
        ApiClient.api.login(user)

    suspend fun register(user: User) =
        ApiClient.api.register(user)

}