package com.byahengcebu.mobile.api

import com.byahengcebu.mobile.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body user: User
    ): Response<String>

    @POST("auth/register")
    suspend fun register(
        @Body user: User
    ): Response<String>

}