package com.byahengcebu.mobile.features.issue.model

data class Issue(

    val id: Long? = null,

    val driverName: String = "",

    val vehiclePlate: String = "",

    val issueType: String,

    val description: String,

    val status: String = "",

    val reportedDate: String? = null

)