package com.byahengcebu.mobile.features.auth.model

data class AuthResponse(

    val success: Boolean,

    val message: String,

    val fullname: String? = null,

    val email: String? = null,

    val role: String? = null

)