package com.byahengcebu.mobile.features.issue.model

data class Issue(

    val id: Long? = null,

    val driverName: String = "",

    val vehiclePlate: String = "",

    val title: String,

    val description: String,

    val severity: String,

    val status: String = "",

    val reportedDate: String? = null

)