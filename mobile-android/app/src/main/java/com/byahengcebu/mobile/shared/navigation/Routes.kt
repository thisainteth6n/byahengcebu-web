package com.byahengcebu.mobile.navigation

sealed class Routes(val route: String) {

    object Login : Routes("login")

    object Register : Routes("register")

    object Dashboard : Routes("dashboard")

    object VehicleDetails : Routes("vehicle_details")

    object Trip : Routes("trip")

}