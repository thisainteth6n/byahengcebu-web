package com.byahengcebu.mobile.model

data class Trip(

    val id: Long? = null,

    val driverName: String = "",

    val vehiclePlate: String = "",

    val route: String = "",

    val startOdometer: Int = 0,

    val endOdometer: Int? = null,

    val passengerCount: Int = 0,

    val status: String = "",

    val startTime: String? = null,

    val endTime: String? = null

)