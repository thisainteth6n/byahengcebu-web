package com.byahengcebu.mobile.features.auth.repository

import com.byahengcebu.mobile.features.auth.model.AuthResponse
import com.byahengcebu.mobile.features.auth.model.User
import com.byahengcebu.mobile.shared.api.ApiClient
import retrofit2.Response

class AuthRepository {

    suspend fun login(user: User): Response<AuthResponse> {
        return ApiClient.api.login(user)
    }

    suspend fun register(user: User): Response<AuthResponse> {
        return ApiClient.api.register(user)
    }

}