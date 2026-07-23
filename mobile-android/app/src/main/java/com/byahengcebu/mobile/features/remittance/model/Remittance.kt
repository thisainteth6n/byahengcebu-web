package com.byahengcebu.mobile.features.remittance.model

data class Remittance(

    val id: Long? = null,

    val tripId: Long,

    val driverName: String = "",

    val vehiclePlate: String = "",

    val totalCollection: Double,

    val expenses: Double,

    val netCollection: Double = 0.0,

    val remarks: String = "",

    val status: String = "",

    val submittedDate: String? = null

)