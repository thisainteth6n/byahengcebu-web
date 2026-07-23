package com.byahengcebu.mobile.shared.navigation

sealed class Routes(val route: String) {

    object Login : Routes("login")

    object Register : Routes("register")

    object Dashboard : Routes("dashboard")

    object Trip : Routes("trip")

    object Remittance : Routes("remittance")

    object ReportIssue : Routes("report_issue")

    object Profile : Routes("profile")

}