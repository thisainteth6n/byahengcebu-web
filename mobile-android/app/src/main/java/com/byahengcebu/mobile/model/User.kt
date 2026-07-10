package com.byahengcebu.mobile.model

data class User(

    val id: Long? = null,

    val fullname: String = "",

    val email: String = "",

    val password: String = "",

    val role: String = "DRIVER"

)